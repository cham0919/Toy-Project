package com.wcp.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.wcp.auth.JwtAuthenticationFilter;
import com.wcp.security.LoginFailHandler;
import com.wcp.security.LoginSuccessHandler;
import com.wcp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${authenticationPropertiesPath}")
    private String authenticationPropertiesPath;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginSuccessHandler successHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailHandler failHandler() {
        return new LoginFailHandler();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }


    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다.
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        applyAuthenticationConfig(http);


        http.authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    private void applyAuthenticationConfig(HttpSecurity http) throws Exception {
        URI uri = getClass().getResource(authenticationPropertiesPath).toURI();
        File jsonFile = new File(uri);

        FileReader reader = new FileReader(jsonFile);
        JsonObject authProperties;
        try {
            authProperties = new Gson().fromJson(reader, JsonObject.class);
        } catch (Exception e) {
            throw e;
        } finally {
            reader.close();
        }
        for (Map.Entry<String, JsonElement> entry : authProperties.entrySet()) {
            String url = entry.getKey();
            if (entry.getValue().isJsonArray()) {
                List<String> roleList = new ArrayList<String>();
                for (JsonElement role : entry.getValue().getAsJsonArray()) {
                    roleList.add(role.getAsString());
                }
                http.authorizeRequests().antMatchers(url).hasAnyAuthority(roleList.toArray(new String[0]));
            } else if (entry.getValue().isJsonObject()) {
                JsonObject roleMap = entry.getValue().getAsJsonObject();
                for (Map.Entry<String, JsonElement> roleEntry : roleMap.entrySet()) {
                    HttpMethod httpMethod = HttpMethod.valueOf(roleEntry.getKey().toUpperCase());
                    List<String> roleList = new ArrayList<>();
                    for (JsonElement role : roleEntry.getValue().getAsJsonArray()) {
                        roleList.add(role.getAsString());
                    }
                    http.authorizeRequests().antMatchers(httpMethod, url).hasAnyAuthority(roleList.toArray(new String[0]));
                }
            } else {
                throw new RuntimeException("Invalid format! Only JsonArray and JsonObject will be accepted");
            }
        }
    }



}
