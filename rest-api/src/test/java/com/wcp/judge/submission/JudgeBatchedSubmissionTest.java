package com.wcp.judge.submission;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wcp.judge.Judge;
import com.wcp.judge.statuses.JudgeStatusesTest;
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
import java.util.List;
import java.util.Map;

public class JudgeBatchedSubmissionTest {

    Logger logger = LoggerFactory.getLogger(JudgeBatchedSubmissionTest.class);
    Gson gson = new GsonBuilder().create();

    private final String batchJson = "{\n" +
            "  \"submissions\": [\n" +
            "    {\n" +
            "      \"language_id\": 46,\n" +
            "      \"source_code\": \"ZWNobyBoZWxsbyBmcm9tIEJhc2gK\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"language_id\": 71,\n" +
            "      \"source_code\": \"cHJpbnQoImhlbGxvIGZyb20gUHl0aG9uIikK\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"language_id\": 72,\n" +
            "      \"source_code\": \"cHV0cygiaGVsbG8gZnJvbSBSdWJ5IikK\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    public void CreateBatchedSubmission() throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URI.create(Judge.BATCHSUBMISSION + "?base64_encoded=true"));
        post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        post.addHeader("x-rapidapi-key", Judge.TOKEN);
        post.addHeader("x-rapidapi-host", Judge.HOST);

        post.setEntity(new StringEntity(batchJson)); //json 메시지 입력

        HttpResponse response = client.execute(post);

        ResponseHandler<String> handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);

        Type mapType = new TypeToken<List<Map<String, String>>>(){}.getType();
        List<Map<String, String>> configurationMap = gson.fromJson(body, mapType);
        configurationMap.forEach(map -> {
            map.keySet().forEach(key -> {
                logger.info("Key : [{}], Value : [{}]", key ,map.get(key));
            });
        });
    }

    @Test
    public void getBatchedSubmission()  throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(URI.create(Judge.BATCHSUBMISSION +  "?tokens=dce7bbc5-a8c9-4159-a28f-ac264e48c371%2C1ed737ca-ee34-454d-a06f-bbc73836473e%2C9670af73-519f-4136-869c-340086d406db&base64_encoded=true&fields=*"));
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
