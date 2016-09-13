package com.example.y.mvp.network;


import com.example.y.mvp.mvp.Bean.QiubaiVideo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by simaben on 24/3/16.
 */
public interface RouteApi {
    @GET("255-1")
    Observable<QiubaiVideo> video(@Query("page") String page, @Query("showapi_appid") String showapi_appid,
                                  @Query("showapi_timestamp") String showapi_timestamp,
                                  @Query("title") String title, @Query("type") String type,
                                  @Query("showapi_sign") String showapi_sign);
}
