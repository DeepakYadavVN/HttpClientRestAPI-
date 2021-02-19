package com.qa.Client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    CloseableHttpClient httpClient;

    // GET Method without header.
    public CloseableHttpResponse get(String URL) throws IOException {
        httpClient = HttpClients.createDefault();// Create client connection and return closeable http client class object.
        HttpGet httpget = new HttpGet(URL); // Create get connection with this URL
        CloseableHttpResponse httpResponse = httpClient.execute(httpget); // Hit the GET URL
        return httpResponse;
    }

    // GET Method with header.
    public CloseableHttpResponse get(String URL, HashMap<String, String> headerMap) throws IOException {
        httpClient = HttpClients.createDefault();// create client connection and return Closable http client class object.
        HttpGet httpget = new HttpGet(URL); // Create get connection with this URL
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpget.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse httpResponse = httpClient.execute(httpget); // Hit the GET URL
        return httpResponse;
    }

    // POST Method>> we pass the PayLoad
    public CloseableHttpResponse post(String URL, String entityString, HashMap<String, String> headerMap) throws IOException {
        httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(URL);// Create POST Request
        httpPost.setEntity(new StringEntity(entityString));// SetEntity method is used to define the PayLoad.

        //For headers.
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        return httpResponse;
    }

    // POST Method>> we pass the PayLoad
    public CloseableHttpResponse put(String URL, String entityString, HashMap<String, String> headerMap) throws IOException {
        httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(URL);// Create PUT Request
        httpPut.setEntity(new StringEntity(entityString));// SetEntity method is used to define the PayLoad.

        //For headers.
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpPut.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
        return httpResponse;
    }

}
