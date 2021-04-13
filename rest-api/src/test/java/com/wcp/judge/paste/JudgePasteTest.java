//package com.wcp.judge.paste;
//
//import com.wcp.judge.Judge;
//import org.apache.http.HttpHeaders;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class JudgePasteTest {
//
//    @Test
//    public void createPaste() throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(Judge.PASTE))
//                .header(HttpHeaders.CONTENT_TYPE, "application/json")
//                .header(HttpHeaders.ACCEPT, "application/json")
//                .header("x-rapidapi-key", Judge.TOKEN)
//                .header("x-rapidapi-host", Judge.HOST)
//                .POST(HttpRequest.BodyPublishers.ofString("{\"content\": \"Hi Judge0!\"}"))
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//
//    }
//
//    @Test
//    public void getPaste() throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(Judge.PASTE + "/61Xm"))
//                .header("x-rapidapi-key", Judge.TOKEN)
//                .header("x-rapidapi-host", Judge.HOST)
//                .GET()
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//    }
//
//}
