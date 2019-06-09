package com.example.farming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.Ingredient;
import com.example.farming.entity.ProductMaterial;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductMaterialActivity extends AppCompatActivity {

    private RecyclerView regionRecyclerView;
    private List<Entity> regionList;
    private ProductMaterialAdapter regionAdapter;
    private ImageView landManageAdd;
    private int identity;
    private List<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_material);
        initData();
    }

    protected void initData() {
        regionRecyclerView = findViewById(R.id.ingredient_recyclerview_1);
        landManageAdd = findViewById(R.id.indredient_add_1);
        regionRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Intent getIntent = getIntent();
        identity = getIntent.getByteExtra("identity", (byte) 0);
        Retrofit retrofit = SingleTopRetrofit.getInstance();
        AdminService adminService = retrofit.create(AdminService.class);
        Call<DataResult<ProductMaterial>> call = adminService.findMaterial();
        call.enqueue(new Callback<DataResult<ProductMaterial>>() {
            @Override
            public void onResponse(Call<DataResult<ProductMaterial>> call, Response<DataResult<ProductMaterial>> response) {
                DataResult<ProductMaterial> dataResult = response.body();
                if (dataResult != null) {
                    ProductMaterial productMaterial = dataResult.getData();
                    Map<String, Integer> seedMap = productMaterial.getSeed();
                    Map<String, Integer> ingredientMap = productMaterial.getIngredient();
                    for (Map.Entry<String, Integer> entry : seedMap.entrySet()) {
                        if (!StringUtil.isEmpty(entry.getKey())) {
                            strings.add(entry.getKey() + " : " + entry.getValue());
                        }
                    }
                    for (Map.Entry<String, Integer> entry : ingredientMap.entrySet()) {
                        if (!StringUtil.isEmpty(entry.getKey())) {
                            strings.add(entry.getKey() + " : " + entry.getValue());
                        }
                    }
                    regionAdapter = new ProductMaterialAdapter(strings, identity);
                    regionRecyclerView.setAdapter(regionAdapter);
                }
            }

            @Override
            public void onFailure(Call<DataResult<ProductMaterial>> call, Throwable t) {

            }
        });
        switch (identity) {
            default:
                landManageAdd.setVisibility(View.GONE);
            case Constants.ADMIN:
                landManageAdd.setVisibility(View.GONE);
                break;
            case Constants.GUEST:
                break;
            case Constants.MARKET:
                break;
            case Constants.TECH:
                landManageAdd.setVisibility(View.VISIBLE);
                landManageAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ProductMaterialActivity.this, PurchaseActivity.class);
                        startActivity(intent);
                    }
                });
                break;

        }
    }
}
