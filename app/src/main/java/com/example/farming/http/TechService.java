package com.example.farming.http;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.FarmworkRecord;
import com.example.farming.entity.FeeInfo;
import com.example.farming.entity.HarvestManage;
import com.example.farming.entity.Ingredient;
import com.example.farming.entity.LandInfo;
import com.example.farming.entity.PlanDel;
import com.example.farming.entity.PlanManage;
import com.example.farming.entity.ProductMaterial;
import com.example.farming.entity.PurchaseRecord;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TechService {

    @POST("tech/find")
    @FormUrlEncoded
    Call<DataResult<List<LandInfo>>> landInfo(@Field(value = "id", encoded = true) long landId);

    @POST("tech/add")
    Call<DataResult<Boolean>> addLand(@Body LandInfo landInfo);

    @FormUrlEncoded
    @POST("tech/delete")
    Call<DataResult<Boolean>> landDel(@Field(value = "landId", encoded = true) long landId);

    @POST("tech/Product")
    Call<DataResult<List<PurchaseRecord>>> materialProduct();

    @POST("tech/addIngredient")
    Call<DataResult<Boolean>> addIngredient(@Body Ingredient ingredient);

    @POST("tech/addPlan")
    Call<DataResult<Boolean>> findPlan(@Body PlanManage planManage);

    @POST("tech/delPlan")
    Call<DataResult<Boolean>> delPlan(@Body PlanDel planDel);

    @POST("tech/farm")
    Call<DataResult<List<FarmworkRecord>>> farmwork();

    @POST("tech/addFarm")
    Call<DataResult<Boolean>> insertFarm(@Body FarmworkRecord farmworkRecord);

    @DELETE("tech/farm")
    @FormUrlEncoded
    Call<DataResult<Boolean>> farmwork(@Field(value = "id", encoded = true) long id);

    @POST("tech/addProduct")
    Call<DataResult<Boolean>> addProduct(@Body PurchaseRecord purchaseRecord);

    @POST("tech/delProduct")
    @FormUrlEncoded
    Call<DataResult<Boolean>> addProduct(@Field(value = "id", encoded = true) long id);

    @POST("tech/findIngredient")
    Call<DataResult<List<Ingredient>>> findIngredient();

    @POST("tech/plan")
    Call<DataResult<List<PlanManage>>> findPlan();

    @GET("tech/material")
    Call<DataResult<ProductMaterial>> findMaterial();

    @GET("tech/harvestManage")
    Call<DataResult<List<HarvestManage>>> forHarvestManage();

    @POST("tech/planManage")
    @FormUrlEncoded
    Call<DataResult<Long>> forPlan(@Field(value = "date", encoded = true) Date date);

    @POST("tech/feeUpDown")
    Call<DataResult<List<FeeInfo>>> forFee();

}
