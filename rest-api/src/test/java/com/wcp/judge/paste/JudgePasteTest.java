package com.wcp.judge.paste;

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
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Map;

public class JudgePasteTest {


    Logger logger = LoggerFactory.getLogger(JudgePasteTest.class);
    Gson gson = new GsonBuilder().create();

    @Test
    public void createPaste() throws IOException{

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URI.create(Judge.PASTE));
        post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        post.addHeader(HttpHeaders.ACCEPT, "application/json");
        post.addHeader("x-rapidapi-key", Judge.TOKEN);
        post.addHeader("x-rapidapi-host", Judge.HOST);

        post.setEntity(new StringEntity("{\"content\": \"Hi Judge0!\"}")); //json 메시지 입력

        HttpResponse response = client.execute(post);

        ResponseHandler<String> handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);
        Map<String, Object> configurationMap = gson.fromJson(body, (Type) Map.class);
        configurationMap.keySet().forEach(key -> {
                logger.info("Key : [{}], Value : [{}]", key ,configurationMap.get(key));
            });
    }

    @Test
    public void getPaste() throws IOException{

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(URI.create(Judge.PASTE + "/61Xm"));
        get.addHeader("x-rapidapi-key", Judge.TOKEN);
        get.addHeader("x-rapidapi-host", Judge.HOST);
        HttpResponse response = client.execute(get);


        ResponseHandler<String> handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);
        logger.info(body);
    }

}
