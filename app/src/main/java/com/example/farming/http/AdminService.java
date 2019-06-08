package com.example.farming.http;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.Ingredient;
import com.example.farming.entity.LandInfo;
import com.example.farming.entity.PurchaseRecord;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AdminService {

    @GET("admin/find")
    Call<DataResult<List<LandInfo>>> landInfo();

    @POST("admin/add")
    Call<DataResult<Boolean>> addLand(@Body LandInfo landInfo);

    @FormUrlEncoded
    @POST("admin/delete")
    Call<DataResult<Boolean>> landDel(@Field(value = "landId", encoded = true) long landId);

    @POST("admin/Product")
    Call<DataResult<List<PurchaseRecord>>> materialProduct();

    @POST("admin/addProduct")
    Call<DataResult<Boolean>> addProduct(@Body PurchaseRecord purchaseRecord);

    @POST("admin/delProduct")
    @FormUrlEncoded
    Call<DataResult<Boolean>> addProduct(@Field(value = "id", encoded = true) long id);

    @POST("admin/findIngredient")
    Call<DataResult<List<Ingredient>>> findIngredient();

}
