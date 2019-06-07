package com.example.farming;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("account/login")
    Call<DataResult<Byte>> login(@Body UserInfo userInfo);


    @POST("account/register")
    Call<DataResult<Boolean>> register(@Body UserInfo userInfo);

}
