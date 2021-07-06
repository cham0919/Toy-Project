# 기능 소개

## [목차]


1. [모듈 괸리](#모듈-관리)
2. [Config 관리](#Config-관리)
3. [Authentication 관리](#Authentication-관리)
4. [코딩 테스트 게시판 관리](#코딩-테스트-게시판-관리)
5. [코딩 테스트 채점 관리](#코딩-테스트-채점-관리)



<br/><br/><br/>

## 모듈 관리



 - Gradle 6.8.2 
 
 - core와 rest-api로 분할 모듈 관리
    - core : Business Layer, 핵심 로직 관리
    - rest-api : View Layer, Config 설정 관리
    
<br/>
  
   
#### 이점
  
 - 모듈간 의존성 최소화
 - 명확한 역할 분담
 - 빌드 소요 시간 단축 및 관리성 증대

  
<br/>
  
  **build.gradle**  
    
```java
def javaProjects = [project(':core'), project(':rest-api')]
def javaVersion = 1.8

allprojects { // 프로젝트 정보 명시
    group 'com.wcp'
    version '0.0.1-SNAPSHOT' 
}

buildscript {
    ext {
        springBootVersion = '2.4.2' 
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}


configure(javaProjects) { // 프로젝트 설정
    project.buildDir = '/target'

    repositories {
        mavenCentral()
    }

    // 플러그인 선언
    apply plugin: 'java'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'
    
    version = '1.0.0.SNAPSHOT'

    targetCompatibility = javaVersion
    sourceCompatibility = javaVersion

    dependencyManagement {
        imports {
            mavenBom org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
        }
        dependencies {  // 모 의존성 버전 관리
            dependency  'com.google.code.gson:gson:2.8.5'
            ...
            dependency 'org.apache.tika:tika-core:1.26'
        }
    }

    dependencies {  // 필요한 라이브러리 가져오기
        ...
        implementation 'org.apache.tika:tika-core' 

    }

    println('java projects tasks')
}

```

<br/>

**Project Tree**

```java
├─core
│  └─src
│      ├─main
│      │  ├─generated
│      │  ├─java
│      │  │  └─com
│      │  │      └─wcp
               …
│      │  │          ├─security
│      │  │          ├─token
│      │  │          └─user
│      │  └─resources
│      └─test
└─rest-api
    └─src
        ├─main
        │  ├─generated
        │  ├─java
        │  │  └─com
        │  │      └─wcp
        │  │          ├─auth
               …
        │  │          └─user
        │  └─resources
        │      ├─config
        │      ├─META-INF
        │      ├─static
        │      ├─templates
        │      └─webapp
        └─test

```
   

 
 <br/><br/>

## Config 관리



Custom한 Config 통해 특정 값들 Properties 파일로 관리 가능하게 구현


 </br>

#### 이점
  
 - 내부 설정 관리 유용
 - 변경사항 발생 시, 재빌드없이 Properties 수정 및 서버 재기동만으로 설정 반영 가능 

 <br/>
 
 **PropertiesEnvironment.class**
 
 ```java
public class PropertiesEnvironment implements Environment{

    private final Logger log = LoggerFactory.getLogger(PropertiesEnvironment.class);
    private Properties props = new Properties();

    ...

    // 생성시 초기화
    private PropertiesEnvironment() {
        init();
    }

    // 초기화시, properties 파일을 읽어 props에 추가 
    private void init() {
        File[] propFiles = fetchPropertiesFiles();
        Arrays.stream(propFiles)
                .forEach(this::loadProp);
    }

    
    private void loadProp(File propFile){
        try (
            FileInputStream is = FileUtils.openInputStream(propFile);
        ){
            props.load(is);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    // 하위 properties 파일만 읽어들이기
    private File[] fetchPropertiesFiles() {
        File propDir = new File(getClass().getResource(config).getFile());
        return propDir.listFiles(FileUtils::isPropertiesFile);
    }

    // Properties 내 설정된 key, value add
    public void addProperties(Properties properties) {
        if (properties == null || properties.isEmpty()) { return; }
        log.trace("Adding Properties: {}", properties);
        props.putAll(properties);
    }

  ...

}
```

<br/>

**Config.class**

```java
public class Config {

    public static Properties getAllProperties() {
        return getEnvironment().getAllProperties();
    }

    public static Environment getEnvironment() {
        return PropertiesEnvironment.getInstance();
    }

    // 전역적으로 Properties 사용 가능
    public static String getProperty(String key) {
        return getAllProperties().getProperty(key);
    }

   public static String getProperty(String key, String defaultValue) {
        return getAllProperties().getProperty(key, defaultValue);
    }

  ...

```

 <br/>
 
 ```Config.getProperties({Key});``` 같이 사용 가능
 

 <br/><br/>

 ### Authentication 관리


- Spring Security, JWT 사용
- IUWT 기반 토큰 인증 기술 반영

<br/>
 
 #### 프로젝트 JWT에 대한 블로그 정리 글 참조
 
 [![Velog's GitHub stats](https://velog-readme-stats.vercel.app/api?name=cham&color=dark&slug=JWT-JWT-보안에-대한-고찰)](https://velog.io/@cham/JWT-JWT-%EB%B3%B4%EC%95%88%EC%97%90-%EB%8C%80%ED%95%9C-%EA%B3%A0%EC%B0%B0)
 
 <br/>
 
 [![Velog's GitHub stats](https://velog-readme-stats.vercel.app/api?name=cham&color=dark&slug=JWT-JWT의-강화-만료시간과-Refresh-token)](https://velog.io/@cham/JWT-JWT%EC%9D%98-%EA%B0%95%ED%99%94-%EB%A7%8C%EB%A3%8C%EC%8B%9C%EA%B0%84%EA%B3%BC-Refresh-token)

 <br/>

 #### 프로세스
 
 ![image](https://user-images.githubusercontent.com/61372486/124616731-4ac60080-deb1-11eb-9710-7238da749f88.png)

  
 </br>

 #### 이점
  
 - 토큰 탈취 시, 즉시 무효화 가능
 - DB 접근 없이 사용자 인증 가능
 - Refresh Token 없어짐으로 관리 단순화
 
 <br/>
 
 **JwtAuthenticationFilter.class**
 
 ```java
    ...

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Cookie accessTokenCookie = WebUtils.getCookie(request, Token.ACCESSTOKEN.getToken());
        Cookie validateTokenCookie = WebUtils.getCookie(request, Token.VALIDATETOKEN.getToken());

        if (!hasCookie(validateTokenCookie)) { // 최초 요청 판별 여부
            applyValidateTokenCookie(response); // ValidateToken 발급 
        } else if (hasCookie(accessTokenCookie)) { // 로그인 유저 판별 여부
            TokenDto dto = createTokenDto(accessTokenCookie, validateTokenCookie);
            setAuthentication(dto);  // Authentication 삽입
        } else {
            setAnonymousAuthentication(); // 익명 권한 부여
        }
        chain.doFilter(req, res);
    }
}

...
```
 
 - 최초 요청 시 Validate Token 발급
 - AccessToken 보유 시, 검증 후 권한 부여
 - 나머지 익명 권한 부여
 - 검증 후, SecurityContext에 권한 부여로 인해 Security에서 제공하는 기능 모두 사용 가능
 
 <br/>
 
 **JwtAuthentication.class**
 
 ```java
public class JwtAuthentication implements Authentication {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthentication.class);

	private final String accessToken;
	private final String key;
	private final String role;
	private boolean isAuthenticated;

	public JwtAuthentication(TokenDto dto) {
		this.accessToken = dto.getAccessToken();
		this.key = dto.getKey();
		this.role = dto.getRole();
		this.isAuthenticated = true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		logger.debug("Getting authorities");
		if (isAuthenticated) {
			return Collections.singletonList(new SimpleGrantedAuthority(role));
		} else {
			return Collections.singletonList(
					new SimpleGrantedAuthority(Role.ANONYMOUS.getValue())
			);
		}
	}
...
```

 <br/>
 
 **AnonymousAuthentication.class**
 
 ```java
@Component
public class AnonymousAuthentication implements Authentication {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(Role.ANONYMOUS.getValue()));
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

	}

	@Override
	public String getName() {
		return null;
	}
}
```

 - UsernamePasswordAuthenticationToken이 아닌 JWT 기반 Custom한 Authentication 사용
 - UsernamePasswordAuthenticationToken 사용하지 않기 때문에 loadUserByUsername() 및 UserDetails 구현하지 않아도 됨
 - DB 접근없이 권한 부여 가능

 <br/>
 
 **SecurityConfig.class**
 
 ```java
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    ...
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        applyAuthenticationConfig(http); // authenticationProperties.json 읽어 접근 제한 설정

        http.authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEndPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

    }

    private void applyAuthenticationConfig(HttpSecurity http) throws Exception {
        InputStream input = getClass().getResourceAsStream(authenticationPropertiesPath);
        JsonObject authProperties = toJsonObject(input);

        for (Map.Entry<String, JsonElement> entry : authProperties.entrySet()) {
            applyAccessAuths(http, entry);
        }
    }

    private void applyAccessAuths(HttpSecurity http, Map.Entry<String, JsonElement> entry) throws Exception {
        String url = entry.getKey();
        if (entry.getValue().isJsonArray()) {
            applyUrlAccessAuths(entry, http.authorizeRequests().antMatchers(url));
        } else if (entry.getValue().isJsonObject()) {
            applyUrlAndMethodAccessAuths(http, entry, url);
        } else {
            throw new RuntimeException("Invalid format! Only JsonArray and JsonObject will be accepted");
        }
    }

    private void applyUrlAndMethodAccessAuths(HttpSecurity http, Map.Entry<String, JsonElement> entry, String url) throws Exception {
        JsonObject roleMap = entry.getValue().getAsJsonObject();
        for (Map.Entry<String, JsonElement> roleEntry : roleMap.entrySet()) {
            applyUrlAndMethodAccessAuth(http, url, roleEntry);
        }
    }

    private void applyUrlAndMethodAccessAuth(HttpSecurity http, String url, Map.Entry<String, JsonElement> roleEntry) throws Exception {
        HttpMethod httpMethod = HttpMethod.valueOf(roleEntry.getKey().toUpperCase());
        applyUrlAccessAuths(roleEntry, http.authorizeRequests().antMatchers(httpMethod, url));
    }


    private void applyUrlAccessAuths(Map.Entry<String, JsonElement> entry, ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl) {
        List<String> roleList = new ArrayList<>();
        entry.getValue().getAsJsonArray()
                .forEach(role -> roleList.add(role.getAsString()));

        authorizedUrl.hasAnyAuthority(roleList.toArray(new String[0]));
    }

    private JsonObject toJsonObject(InputStream input) {
        return new Gson().fromJson(new InputStreamReader(input), JsonObject.class);
    }


}

```
 
  - ```applyAuthenticationConfig(http)``` : ```authenticationProperties.json``` 파일 읽어와 Authority 적용

<br/>

**authenticationProperties.json**

```java
{
  "/wcp/signUp": [
    "ROLE_ANONYMOUS",
    "ROLE_MEMBER",
    "ROLE_ADMIN"
  ],
  "/wcp/coding/room/page/**": [
    "ROLE_ANONYMOUS",
    "ROLE_MEMBER",
    "ROLE_ADMIN"
  ],
  "/wcp/**": [
    "ROLE_MEMBER",
    "ROLE_ADMIN"
  ]
}

```
  
 <br/><br/>
  
 ## 코딩 테스트 게시판 관리
  
 <br/><br/>
  
 ## 코딩 테스트 채점 관리
 
 

