package com.example.lllov.projectkjh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*==================================================================================================
 * retrofit client
 * base url : 웹서버 url
 * getClient를 통해 Retrofit 인스턴스를 리턴함.
 * gson을 통해 간단하게 json 변환
 *=================================================================================================*/
public class ApiClient {
    public static final String BASE_URL = "http://49.168.191.197:8080/";
    private static Retrofit retrofit = null;



    public static Retrofit getClient() {
        if (retrofit==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
