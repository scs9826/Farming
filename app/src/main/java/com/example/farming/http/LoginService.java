package com.example.farming.http;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("account/login")
    Call<DataResult<UserInfo>> login(@Body UserInfo userInfo);


    @POST("account/register")
    Call<DataResult<Boolean>> register(@Body UserInfo userInfo);

    @POST("account/notice")
    Call<DataResult<List<String>>> notice();

}
