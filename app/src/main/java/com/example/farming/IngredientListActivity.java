/*刘云杰*/
package com.example.farming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.farming.adapter.IngredientAdapter;
import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.Entity;
import com.example.farming.entity.Ingredient;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IngredientListActivity extends AppCompatActivity {
    private RecyclerView regionRecyclerView;
    private List<Entity> regionList;
    private IngredientAdapter regionAdapter;
    private ImageView landManageAdd;
    private int identity;
    private List<Ingredient> landInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);
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
        Call<DataResult<List<Ingredient>>> call = adminService.findIngredient();
        call.enqueue(new Callback<DataResult<List<Ingredient>>>() {
            @Override
            public void onResponse(Call<DataResult<List<Ingredient>>> call, Response<DataResult<List<Ingredient>>> response) {
                DataResult<List<Ingredient>> dataResult = response.body();
                if (dataResult != null) {
                    landInfoList = dataResult.getData();
                    regionAdapter = new IngredientAdapter(landInfoList, identity);
                    regionRecyclerView.setAdapter(regionAdapter);
                }
            }

            @Override
            public void onFailure(Call<DataResult<List<Ingredient>>> call, Throwable t) {

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
                        Intent intent = new Intent(IngredientListActivity.this, IngredientAddActivity.class);
                        startActivity(intent);
                    }
                });
                break;

        }
    }

}
