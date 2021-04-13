//package com.wcp.judge.language;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//import com.wcp.judge.Judge;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//import java.net.URI;
//import java.net.http.*;
//import java.util.List;
//import java.util.Map;
//
//
//public class JudgeLanguageTest {
//
//    Logger logger = LoggerFactory.getLogger(JudgeLanguageTest.class);
//    Gson gson = new GsonBuilder().create();
//
//    @Test
//    public void getLanguages() throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(Judge.LANGUAGES))
//                .header("x-rapidapi-key", Judge.TOKEN)
//                .header("x-rapidapi-host", Judge.HOST)
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//        Type mapType = new TypeToken<List<Map<String, String>>>(){}.getType();
//        List<Map<String, String>> configurationMap = gson.fromJson(response.body(), mapType);
//        configurationMap.forEach(map -> {
//            map.keySet().forEach(key -> {
//                logger.info("Key : [{}], Value : [{}]", key ,map.get(key));
//            });
//        });
//    }
//
//    @Test
//    public void getLanguage()  throws IOException, InterruptedException{
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(Judge.LANGUAGES+"/52"))
//                .header("x-rapidapi-key", Judge.TOKEN)
//                .header("x-rapidapi-host", Judge.HOST)
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//
//        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
//        Map<String, Object> configurationMap = gson.fromJson(response.body(), mapType);
//        configurationMap.keySet().forEach(key -> {
//            logger.info("Key : [{}], Value : [{}]", key,configurationMap.get(key).toString());
//        });
//    }
//
//
//}
