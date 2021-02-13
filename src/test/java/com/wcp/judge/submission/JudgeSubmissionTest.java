package com.wcp.judge.submission;

import com.wcp.judge.Judge;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.HashMap;
import java.util.Map;


public class JudgeSubmissionTest {

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
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&fields=*"))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("x-rapidapi-key", Judge.TOKEN)
                .header("x-rapidapi-host", Judge.HOST)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

    }

    @Test
    public void getSubmission() throws  IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Judge.SUBMISSION + "/2e979232-92fd-4012-97cf-3e9177257d10?base64_encoded=true&fields=*"))
                .header("x-rapidapi-key", Judge.TOKEN)
                .header("x-rapidapi-host", Judge.HOST)
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

}
