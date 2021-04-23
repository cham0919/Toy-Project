package com.wcp.common.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpMethod;

import java.net.URI;

public class HttpUtils {



    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private Object httpMethod;


    private HttpUtils() {
       httpClient = HttpClientBuilder.create().build();
    }

    public static HttpUtils of(){
        return new HttpUtils();
    }

    public HttpUtils get(){
        httpGet = new HttpGet();
        return this;
    }

    public HttpUtils post(){
        httpPost = new HttpPost();
        return null;
    }

    public HttpUtils url(URI url){
        httpGet.setURI(url);
        return this;
    }


}
