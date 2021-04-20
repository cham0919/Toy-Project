package com.wcp.judge.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wcp.judge.Judge;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Map;

public class JudgeConfigurationTest {

    Logger logger = LoggerFactory.getLogger(JudgeConfigurationTest.class);
    Gson gson = new GsonBuilder().create();

    @Test
    public void getConfiguration() throws IOException{

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(URI.create(Judge.CONFIG));
        get.addHeader("x-rapidapi-key", Judge.TOKEN);
        get.addHeader("x-rapidapi-host", Judge.HOST);
        HttpResponse response = client.execute(get);

        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();

        ResponseHandler<String> handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);

        Map<String, Object> configurationMap = gson.fromJson(body, mapType);
        configurationMap.keySet().forEach(key -> {
            logger.info("Key : [{}], Value : [{}]", key,configurationMap.get(key).toString());
        });
    }
}
