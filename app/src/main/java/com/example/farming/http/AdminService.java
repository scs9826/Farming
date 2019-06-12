/*史长顺*/
package com.example.farming.http;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.FeeInfo;
import com.example.farming.entity.HarvestManage;
import com.example.farming.entity.Ingredient;
import com.example.farming.entity.LandInfo;
import com.example.farming.entity.PlanManage;
import com.example.farming.entity.ProductMaterial;
import com.example.farming.entity.PurchaseRecord;

import java.util.Date;
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

    @POST("admin/plan")
    Call<DataResult<List<PlanManage>>> findPlan();

    @GET("admin/material")
    Call<DataResult<ProductMaterial>> findMaterial();

    @GET("admin/harvestManage")
    Call<DataResult<List<HarvestManage>>> forHarvestManage();

    @POST("admin/planManage")
    @FormUrlEncoded
    Call<DataResult<Long>> forPlan(@Field(value = "date", encoded = true) String date);

    @POST("admin/feeUpDown")
    Call<DataResult<List<FeeInfo>>> forFee();

}
