package com.wcp.judge.subscription;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.judge.Judge;
import com.wcp.judge.submission.JudgeSubmissionTest;
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
import java.net.URI;
import java.util.Map;

public class JudgeSubscriptionDetailTest {

    Logger logger = LoggerFactory.getLogger(JudgeSubscriptionDetailTest.class);
    Gson gson = new GsonBuilder().create();

    @Test
    public void getSubscriptionDetail() throws IOException, InterruptedException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(URI.create(Judge.SUBSCRIPTIONDETAIL));
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
