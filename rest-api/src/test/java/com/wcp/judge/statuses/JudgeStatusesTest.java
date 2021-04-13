//package com.wcp.judge.statuses;
//
//import com.wcp.judge.Judge;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.*;
//import java.net.http.HttpRequest;
//
//public class JudgeStatusesTest {
//
//    @Test
//    public void getStatuses() throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(Judge.STATUSES))
//                .header("x-rapidapi-key", Judge.TOKEN)
//                .header("x-rapidapi-host", Judge.HOST)
//                .GET()
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//    }
//}
