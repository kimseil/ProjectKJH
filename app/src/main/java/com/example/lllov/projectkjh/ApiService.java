package com.example.lllov.projectkjh;

import com.example.lllov.projectkjh.DTO.DTOLocationInfo;
import com.example.lllov.projectkjh.DTO.DTOWhere;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    /*
    @GET("JSONServer")
    Call<ArrayList<Event>> getEventList(@Query("theme") String theme, @Query("address") String address);

    @GET("Location")
    Call<Location> getLocation(@Query("location") String location);

    @Headers("Content-Type: application/json")
    @POST("JSONLogin")
    */
    /*Call<DTOLoginReceive> login(@Field("member_id") String id, @Field("password") String password);*/
    /*
    Call<DTOMember> login(@Body DTOLoginRequest dtoLoginRequest);


    @Headers("Content-Type: application/json")
    @POST("JSONRegister")
    Call<DTORegisterReceive> register(@Body DTORegisterRequest registerRequest);

    @GET("JSONReplyLoad")
    Call<ArrayList<DTOReplyLoadReceive>> loadReply(@Query("shop_id") int id);

    @GET("JSONReplySend")
    Call<DTORegisterReceive> sendReply(@Query("shop_id") int shopId, @Query("member_id") String memberId, @Query("content") String content);
    */

    @GET("getLocationInfo")
    Call<ArrayList<DTOLocationInfo>> getLocationInfo(@Query("name") String name);

    @GET("getLocationGroup")
    Call<ArrayList<String>> getLocationGroup();

    @GET("getLocations")
    Call<ArrayList<DTOWhere>> getLocations(@Query("name") String name);
}