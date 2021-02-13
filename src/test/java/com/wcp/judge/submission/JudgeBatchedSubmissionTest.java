package com.wcp.judge.submission;

import com.wcp.judge.Judge;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class JudgeBatchedSubmissionTest {

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
    public void CreateBatchedSubmission() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Judge.BATCHSUBMISSION + "?base64_encoded=true"))
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .header("x-rapidapi-key", Judge.TOKEN)
                .header("x-rapidapi-host", Judge.HOST)
                .POST(HttpRequest.BodyPublishers.ofString(batchJson))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Test
    public void getBatchedSubmission()  throws IOException, InterruptedException  {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Judge.BATCHSUBMISSION +  "?tokens=dce7bbc5-a8c9-4159-a28f-ac264e48c371%2C1ed737ca-ee34-454d-a06f-bbc73836473e%2C9670af73-519f-4136-869c-340086d406db&base64_encoded=true&fields=*"))
                .header("x-rapidapi-key", Judge.TOKEN)
                .header("x-rapidapi-host", Judge.HOST)
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
