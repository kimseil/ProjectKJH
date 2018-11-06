package com.example.lllov.projectkjh;

import com.example.lllov.projectkjh.DTO.LocationGuideInfoVO;
import com.example.lllov.projectkjh.DTO.LocationGuideVO;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.example.lllov.projectkjh.DTO.LocationGroupVO;
import com.example.lllov.projectkjh.DTO.LocationInfoVO;
import com.example.lllov.projectkjh.DTO.PlaceVO;

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

    //지역 그룹명
    @GET("getLocationGroupList")
    Call<ArrayList<LocationGroupVO>> getLocationGroupList();

    //지역명, 사진
    @GET("getLocationList")
    Call<ArrayList<LocationVO>> getLocationList();

    //지역 간략한 정보
    @GET("getLocationInfoList")
    Call<ArrayList<LocationInfoVO>> getLocationInfoList(@Query("locationId") int locationId);

    @GET("getLocationGuideList")
    Call<ArrayList<LocationGuideVO>> getLocationGuideList(@Query("locationId") int locationId);

    @GET("getLocationGuideInfoList")
    Call<ArrayList<LocationGuideInfoVO>> getLocationGuideInfoList(@Query("guideId") int guideId);

    @GET("getPlaceList")
    Call<ArrayList<PlaceVO>> getPlaceList(@Query("locationId") int locationId, @Query("type") int type);
}