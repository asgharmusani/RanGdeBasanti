package com.iotechnica.rangdebasanti;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class GetRequest {
    private OkHttpClient client = new OkHttpClient();

    // code request code here
    private String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    Boolean initiateRequest(String address, String value)
    {
        Runnable request = () ->{
            try {
                doGetRequest("http://" + address + "/set?" + value);
            }
            catch (Exception e) {

            }
        };
        new Thread(request).start();
        return true;
    }
}
