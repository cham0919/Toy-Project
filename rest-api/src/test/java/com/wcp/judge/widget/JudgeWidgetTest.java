package com.wcp.judge.widget;

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
import java.util.Map;

public class JudgeWidgetTest {

    Logger logger = LoggerFactory.getLogger(JudgeWidgetTest.class);
    Gson gson = new GsonBuilder().create();

    private final String json = "{\n" +
            "  \"content\": \"eyJzb3VyY2VfY29kZSI6IiNpbmNsdWRlIDxpb3N0cmVhbT5cblxuaW50IG1haW4oKVxue1xuICAgIHN0ZDo6c3RyaW5nIG5hbWU7XG4gICAgc3RkOjpjaW4gPj4gbmFtZTtcbiAgICBzdGQ6OmNvdXQgPDwgXCJIaSBcIiA8PCBuYW1lIDw8IFwiIVxcblwiO1xuICAgIHJldHVybiAwO1xufSIsImxhbmd1YWdlX2lkIjoiNTQiLCJzdGRpbiI6IkpvaG4iLCJmbGF2b3IiOiJjZSIsImFwaV9rZXkiOiIyMDk2YTU5MTU2bXNoNTJjNzhlZDY0NDJhNzVkcDE2NDA3YWpzbjRlZjcwMzRhNTBkMCIsImxheW91dCI6IntcInNldHRpbmdzXCI6e1wiaGFzSGVhZGVyc1wiOnRydWUsXCJjb25zdHJhaW5EcmFnVG9Db250YWluZXJcIjp0cnVlLFwicmVvcmRlckVuYWJsZWRcIjp0cnVlLFwic2VsZWN0aW9uRW5hYmxlZFwiOmZhbHNlLFwicG9wb3V0V2hvbGVTdGFja1wiOmZhbHNlLFwiYmxvY2tlZFBvcG91dHNUaHJvd0Vycm9yXCI6dHJ1ZSxcImNsb3NlUG9wb3V0c09uVW5sb2FkXCI6dHJ1ZSxcInNob3dQb3BvdXRJY29uXCI6ZmFsc2UsXCJzaG93TWF4aW1pc2VJY29uXCI6dHJ1ZSxcInNob3dDbG9zZUljb25cIjp0cnVlLFwicmVzcG9uc2l2ZU1vZGVcIjpcIm9ubG9hZFwiLFwidGFiT3ZlcmxhcEFsbG93YW5jZVwiOjAsXCJyZW9yZGVyT25UYWJNZW51Q2xpY2tcIjp0cnVlLFwidGFiQ29udHJvbE9mZnNldFwiOjEwfSxcImRpbWVuc2lvbnNcIjp7XCJib3JkZXJXaWR0aFwiOjUsXCJib3JkZXJHcmFiV2lkdGhcIjoxNSxcIm1pbkl0ZW1IZWlnaHRcIjoxMCxcIm1pbkl0ZW1XaWR0aFwiOjEwLFwiaGVhZGVySGVpZ2h0XCI6MjAsXCJkcmFnUHJveHlXaWR0aFwiOjMwMCxcImRyYWdQcm94eUhlaWdodFwiOjIwMH0sXCJsYWJlbHNcIjp7XCJjbG9zZVwiOlwiY2xvc2VcIixcIm1heGltaXNlXCI6XCJtYXhpbWlzZVwiLFwibWluaW1pc2VcIjpcIm1pbmltaXNlXCIsXCJwb3BvdXRcIjpcIm9wZW4gaW4gbmV3IHdpbmRvd1wiLFwicG9waW5cIjpcInBvcCBpblwiLFwidGFiRHJvcGRvd25cIjpcImFkZGl0aW9uYWwgdGFic1wifSxcImNvbnRlbnRcIjpbe1widHlwZVwiOlwiY29sdW1uXCIsXCJpc0Nsb3NhYmxlXCI6dHJ1ZSxcInJlb3JkZXJFbmFibGVkXCI6dHJ1ZSxcInRpdGxlXCI6XCJcIixcImNvbnRlbnRcIjpbe1widHlwZVwiOlwic3RhY2tcIixcImhlaWdodFwiOjY2LjIwNzk1MTA3MDMzNjM5LFwiaXNDbG9zYWJsZVwiOnRydWUsXCJyZW9yZGVyRW5hYmxlZFwiOnRydWUsXCJ0aXRsZVwiOlwiXCIsXCJhY3RpdmVJdGVtSW5kZXhcIjowLFwiY29udGVudFwiOlt7XCJ0eXBlXCI6XCJjb21wb25lbnRcIixcImNvbXBvbmVudE5hbWVcIjpcInNvdXJjZVwiLFwiaWRcIjpcInNvdXJjZVwiLFwidGl0bGVcIjpcIjxzcGFuIGlkPVxcXCJqdWRnZTAtdGFiLXRpdGxlLXNvdXJjZVxcXCIgY2xhc3M9XFxcImp1ZGdlMC10YWItdGl0bGVcXFwiIHRpdGxlPVxcXCJTb3VyY2VcXFwiPjwvc3Bhbj5cIixcImlzQ2xvc2FibGVcIjpmYWxzZSxcImhlaWdodFwiOjcwLFwicmVvcmRlckVuYWJsZWRcIjp0cnVlfV19LHtcInR5cGVcIjpcInJvd1wiLFwiaXNDbG9zYWJsZVwiOnRydWUsXCJyZW9yZGVyRW5hYmxlZFwiOnRydWUsXCJ0aXRsZVwiOlwiXCIsXCJoZWlnaHRcIjozMy43OTIwNDg5Mjk2NjM2MSxcImNvbnRlbnRcIjpbe1widHlwZVwiOlwic3RhY2tcIixcIndpZHRoXCI6MzUsXCJpc0Nsb3NhYmxlXCI6dHJ1ZSxcInJlb3JkZXJFbmFibGVkXCI6dHJ1ZSxcInRpdGxlXCI6XCJcIixcImFjdGl2ZUl0ZW1JbmRleFwiOjAsXCJjb250ZW50XCI6W3tcInR5cGVcIjpcImNvbXBvbmVudFwiLFwiY29tcG9uZW50TmFtZVwiOlwiaW5wdXRcIixcImlkXCI6XCJpbnB1dFwiLFwidGl0bGVcIjpcIjxzcGFuIGlkPVxcXCJqdWRnZTAtdGFiLXRpdGxlLWlucHV0XFxcIiBjbGFzcz1cXFwianVkZ2UwLXRhYi10aXRsZVxcXCIgdGl0bGU9XFxcIklucHV0XFxcIj48L3NwYW4+XCIsXCJpc0Nsb3NhYmxlXCI6ZmFsc2UsXCJyZW9yZGVyRW5hYmxlZFwiOnRydWV9XX0se1widHlwZVwiOlwic3RhY2tcIixcIndpZHRoXCI6NjUsXCJpc0Nsb3NhYmxlXCI6dHJ1ZSxcInJlb3JkZXJFbmFibGVkXCI6dHJ1ZSxcInRpdGxlXCI6XCJcIixcImFjdGl2ZUl0ZW1JbmRleFwiOjAsXCJjb250ZW50XCI6W3tcInR5cGVcIjpcImNvbXBvbmVudFwiLFwiY29tcG9uZW50TmFtZVwiOlwib3V0cHV0XCIsXCJpZFwiOlwib3V0cHV0XCIsXCJ0aXRsZVwiOlwiPHNwYW4gaWQ9XFxcImp1ZGdlMC10YWItdGl0bGUtb3V0cHV0XFxcIiBjbGFzcz1cXFwianVkZ2UwLXRhYi10aXRsZVxcXCIgdGl0bGU9XFxcIk91dHB1dFxcXCI+PC9zcGFuPlwiLFwiaXNDbG9zYWJsZVwiOmZhbHNlLFwid2lkdGhcIjo2NSxcInJlb3JkZXJFbmFibGVkXCI6dHJ1ZX1dfV19XX1dLFwiaXNDbG9zYWJsZVwiOnRydWUsXCJyZW9yZGVyRW5hYmxlZFwiOnRydWUsXCJ0aXRsZVwiOlwiXCIsXCJvcGVuUG9wb3V0c1wiOltdLFwibWF4aW1pc2VkSXRlbUlkXCI6bnVsbH0iLCJzZXR0aW5ncyI6eyJjc3MiOiIiLCJleHBlY3RlZF9vdXRwdXQiOiIifX0=\"\n" +
            "}";

    @Test
    public void createWidget() throws IOException, InterruptedException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(URI.create(Judge.WIDGET));
        post.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        post.addHeader(HttpHeaders.ACCEPT, "application/json");
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
    public void getWidget() throws IOException, InterruptedException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(URI.create(Judge.WIDGET + "/lXwn"));
        get.addHeader("x-rapidapi-key", Judge.TOKEN);
        get.addHeader("x-rapidapi-host", Judge.HOST);
        HttpResponse response = client.execute(get);

        ResponseHandler<String> handler = new BasicResponseHandler();
        String body = handler.handleResponse(response);

        logger.info(body);
    }

}
