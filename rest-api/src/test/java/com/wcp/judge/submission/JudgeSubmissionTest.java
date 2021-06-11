package com.wcp.judge.submission;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.judge.Judge;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class JudgeSubmissionTest {

    Logger logger = LoggerFactory.getLogger(JudgeSubmissionTest.class);
    Gson gson = new GsonBuilder().create();

    private final String judgeUrl = "https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&fields=*";

    private Map<String, String> headerMap = new HashMap<String, String>(){{
        put("x-rapidapi-key","b8648f0040msh885f2f3104a8cd9p17d505jsna839dc3d1d27");
        put("x-rapidapi-host","judge0-ce.p.rapidapi.com");
    }};


    private String json = "{\n" +
            "  \"language_id\": 52,\n" +
            "  \"source_code\": \"I2luY2x1ZGUgPHN0ZGlvLmg+CgppbnQgbWFpbih2b2lkKSB7CiAgY2hhciBuYW1lWzEwXTsKICBzY2FuZigiJXMiLCBuYW1lKTsKICBwcmludGYoImhlbGxvLCAlc1xuIiwgbmFtZSk7CiAgcmV0dXJuIDA7Cn0=\",\n" +
            "  \"stdin\": \"SnVkZ2Uw\"\n" +
            "}";

    @Test
    public void createSubmission() throws IOException, InterruptedException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URI.create("https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&wait=true&fields=*"));
        post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        post.addHeader("x-rapidapi-key", Judge.TOKEN);
        post.addHeader("x-rapidapi-host", Judge.HOST);

        post.setEntity(new StringEntity(json)); //json 메시지 입력

        HttpResponse response = client.execute(post);

        ResponseHandler<String> handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);

        Map<String, Object> configurationMap = gson.fromJson(body, Map.class);
        configurationMap.keySet().forEach(key -> {
                logger.info("Key : [{}], Value : [{}]", key ,configurationMap.get(key));
            });
    }

    @Test
    public void getSubmission() throws  IOException, InterruptedException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(URI.create(Judge.SUBMISSION + "/0a35238b-adc3-4cef-a701-d83286d61cfc?base64_encoded=false&fields=*"));
        get.addHeader("x-rapidapi-key", Judge.TOKEN);
        get.addHeader("x-rapidapi-host", Judge.HOST);
        HttpResponse response = client.execute(get);

        ResponseHandler<String> handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);

        Map<String, Object> configurationMap = gson.fromJson(body, Map.class);
        configurationMap.keySet().forEach(key -> {
            logger.info("Key : [{}], Value : [{}]", key ,configurationMap.get(key));
        });
    }

}
