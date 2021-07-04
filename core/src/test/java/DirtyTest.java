import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.wcp.coding.test.CodingTest;
import com.wcp.coding.test.CodingTestDto;
import com.wcp.common.AESUtils;
import com.wcp.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class DirtyTest {
    Gson gson = new GsonBuilder().create();

    private String json = "{\n" +
            "  \"language_id\": 52,\n" +
            "  \"source_code\": \"I2luY2x1ZGUgPHN0ZGlvLmg+CgppbnQgbWFpbih2b2lkKSB7CiAgY2hhciBuYW1lWzEwXTsKICBzY2FuZigiJXMiLCBuYW1lKTsKICBwcmludGYoImhlbGxvLCAlc1xuIiwgbmFtZSk7CiAgcmV0dXJuIDA7Cn0=\",\n" +
            "  \"stdin\": \"SnVkZ2Uw\"\n" +
            "}";

    @Test
    public void ceilTest() throws Throwable {
        //58-A0-23-AC-65-93
        String ip = "localhost";
//        AESUtils a256 = AESUtils.getInstance();

        String enId = AESUtils.encrypt(ip);
        System.out.println(enId);
        System.out.println(AESUtils.decrypt(enId));
    }




    @Test
    public void nameTest() throws IOException {
        File file = new File("C:\\git\\file\\2021\\05\\31\\4b648c0b-6683-466c-991e-4776eb18a976\\4b648c0b-6683-466c-991e-4776eb18a976.zip");
        Tika tika = new Tika();
        String type = tika.detect(file);
        System.out.println(type);

    }


    @Test
    public void codingTest() throws Throwable {
        Class user = User.class;
        User u = (User)user.getConstructor().newInstance();

    }


    @Test
    public void testFileEquals() throws IOException {
        String[][] str = {{"yellowhat", "headgear"}, {"bluesunglasses", "headgear"}, {"green_turban", "headgear"}};
        int result = 1;
        Map<String, Integer> maps = new HashMap<>();

        for(String[] strs :str){
            maps.put(strs[1], maps.getOrDefault(strs[1], 0) + 1);
        }

        for (int v  : maps.values()){
            result *= v+1;
        }

        System.out.println(result-1);
    }


    @Test
    public void testStream() throws IOException, URISyntaxException {
        String resource = "/config/authenticationProperties.json";
//        InputStream input = getClass().getResourceAsStream(resource);
//        BufferedReader rd = new BufferedReader(new InputStreamReader(input));
//        String line;
//        StringBuffer response = new StringBuffer();
//        while((line = rd.readLine()) != null) {
//            response.append(line);
//            response.append('\r');
//        }
//        rd.close();
//
//        System.out.println(resource.toString());

        URI uri = getClass().getResource(resource).toURI();
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

        System.out.println(authProperties.toString());
    }
}



