//package com.wcp.judge.subscription;
//
//import com.wcp.judge.Judge;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.*;
//
//public class JudgeSubscriptionDetailTest {
//
//    @Test
//    public void getSubscriptionDetail() throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(Judge.SUBSCRIPTIONDETAIL))
//                .header("x-rapidapi-key", Judge.TOKEN)
//                .header("x-rapidapi-host", Judge.HOST)
//                .GET()
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//    }
//}
