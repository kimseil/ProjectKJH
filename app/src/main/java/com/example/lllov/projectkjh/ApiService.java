package com.example.lllov.projectkjh;

import com.example.lllov.projectkjh.DTO.FavoritePlaceVO;
import com.example.lllov.projectkjh.DTO.LocationGroupVO;
import com.example.lllov.projectkjh.DTO.LocationGuideInfoVO;
import com.example.lllov.projectkjh.DTO.LocationGuideVO;
import com.example.lllov.projectkjh.DTO.LocationInfoVO;
import com.example.lllov.projectkjh.DTO.LocationVO;
import com.example.lllov.projectkjh.DTO.PlaceInfoVO;
import com.example.lllov.projectkjh.DTO.PlaceVO;
import com.example.lllov.projectkjh.DTO.ResponseScheduleInfoVO;
import com.example.lllov.projectkjh.DTO.ResponseScheduleVO;
import com.example.lllov.projectkjh.DTO.ScheduleVO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*==================================================================================================
 * Retrofit 인터페이스
 * 설명 더 필요
 *=================================================================================================*/
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

    //지역 그룹 리스트를 가져옴(국가)
    @GET("getLocationGroupList")
    Call<ArrayList<LocationGroupVO>> getLocationGroupList();

    //지역 리스트를 가져옴
    @GET("getLocationList")
    Call<ArrayList<LocationVO>> getLocationList();

    //선택한 지역의 정보 리스트를 가져옴
    @GET("getLocationInfoList")
    Call<ArrayList<LocationInfoVO>> getLocationInfoList(@Query("locationId") int locationId);

    //선택한 지역의 가이드 리스트와 찜 여부를 가져옴
    @GET("getLocationGuideList")
    Call<ArrayList<LocationGuideVO>> getLocationGuideList(@Query("locationId") int locationId, @Query("userId") long userId);

    //선택한 가이드의 정보 리스트를 가져옴
    @GET("getLocationGuideInfoList")
    Call<ArrayList<LocationGuideInfoVO>> getLocationGuideInfoList(@Query("guideId") int guideId);

    //선택한 지역의 장소리스트를 가져옴
    @GET("getPlaceAllList")
    Call<ArrayList<FavoritePlaceVO>> getPlaceAllList(@Query("locationId") int locationId);

    //선택한 지역, 종류의 장소 리스트와 찜 여부를 가져옴
    @GET("getPlaceList")
    Call<ArrayList<PlaceVO>> getPlaceList(@Query("locationId") int locationId, @Query("type") int type, @Query("userId") long userId);

    //선택한 장소의 정보 리스트를 가져옴
    @GET("getPlaceInfoList")
    Call<ArrayList<PlaceInfoVO>> getPlaceInfoList(@Query("placeId") int placeId);

    //선택한 장소의 관련 정보 리스트를 가져옴
    @GET("getPlaceRelevantList")
    Call<ArrayList<PlaceInfoVO>> getPlaceRelevantList(@Query("placeId") int placeId);

    //SNS 로그인 후 해당 아이디가 database에 없다면 회원정보를 등록시켜줌
    @GET("login")
    Call<Integer> login(@Query("id") long id, @Query("nickname") String nickname);

    //현재 사용중인 회원이 찜한 가이드인지 여부
    @GET("setIsFavoriteGuide")
    Call<Boolean> setIsFavoriteGuide(@Query("userId") long userId, @Query("guideId") int guideId);

    //현재 사용중인 회원이 찜한 장소인지 여부
    @GET("setIsFavoritePlace")
    Call<Boolean> setIsFavoritePlace(@Query("userId") long userId, @Query("placeId") int placeId);

    //여행 일정 등록
    @GET("registrationTravel")
    Call<ScheduleVO> registrationTravel(@Query("startDay") long startDay, @Query("endDay") long endDay, @Query("locationId") int locationId, @Query("userId") long userId);

    //일정 리스트 가져옴
    @GET("getScheduleList")
    Call<ArrayList<ResponseScheduleVO>> getScheduleList(@Query("userId") long userId);

    //찜한 장소 리스트 가져옴
    @GET("getFavoritePlaceList")
    Call<ArrayList<FavoritePlaceVO>> getFavoritePlaceList(@Query("userId") long userId);

    //특정 지역의 찜한 장소 리스트 가져옴
    @GET("getFavoriteLocationPlaceList")
    Call<ArrayList<FavoritePlaceVO>> getFavoriteLocationPlaceList(@Query("userId") long userId, @Query("locationId") int locationId);

    //특정 스케쥴, 특정 날짜의 스케쥴 리스트를 가져옴
    @GET("getScheduleInfoList")
    Call<ArrayList<ResponseScheduleInfoVO>> getScheduleInfoList(@Query("scheduleId") int scheduleId, @Query("day") long day);

    @GET("addScheduleInfoPlace")
    Call<Integer> addScheduleInfoPlace(@Query("day") long day, @Query("number") int number, @Query("placeId") int placeId, @Query("scheduleId") int scheduleId);
}