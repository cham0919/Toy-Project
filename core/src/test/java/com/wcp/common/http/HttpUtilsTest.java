package com.wcp.common.http;


import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;

import java.lang.reflect.Method;


public class HttpUtilsTest {

    private Object httpMethod;

    @Test
    public void getTest() {
        String requestURL = "http://www.naver.com";
        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성
            HttpResponse response = client.execute(getRequest);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    @Test
    public void post(){
        String requestURL = "http://localhost:8888/mainboard/test";

        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpPost postRequest = new HttpPost(requestURL); //POST 메소드 URL 새성
            //postRequest.addHeader("Authorization", token); // token 이용시

            postRequest.setEntity(new StringEntity("{\"body\":\"a\"}")); //json 메시지 입력

            HttpResponse response = client.execute(postRequest);

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e){
            System.err.println(e.toString());
        }

    }


    @Test
    public void test() throws Exception{
        String className = "org.apache.http.client.methods.HttpPost";

        HttpEntity entity = new BasicHttpEntity();

        Class<?> cls = Class.forName(className); // 다음과 같이하면 클래스를 로딩
        Object obj = cls.newInstance(); // 해당 클래스 인스턴스 생성
        Method method = cls.getMethod("httpPost", HttpEntity.class); // 메소드 로딩( 메소드의 인자값이 String 하나가 들어가 있다)
        method.invoke(obj, entity);// 메소드 인자값인 String 하나를 "야호"라고 넣는다.
        HttpRequestBase httpMethod = new HttpPost();
        HttpPost httpPost = new HttpPost();

    }

}
