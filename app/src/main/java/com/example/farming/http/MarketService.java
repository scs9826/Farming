/*史长顺*/
package com.example.farming.http;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.HarvestManage;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MarketService {

    @POST("market/addHarvestManage")
    Call<DataResult<Boolean>> addHarvestManage(@Body HarvestManage harvestManage) ;

    @POST("market/delHarvestManage")
    @FormUrlEncoded
    Call<DataResult<Boolean>> addHarvestManage(@Field(value = "id", encoded = true)long id) ;

}
