/*史长顺*/
package com.example.farming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.farming.adapter.RealHarvestAdapter;
import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.Entity;
import com.example.farming.entity.HarvestManage;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RealHarvestActivity extends AppCompatActivity {

    private RecyclerView regionRecyclerView;
    private List<Entity> regionList;
    private RealHarvestAdapter regionAdapter;
    private ImageView landManageAdd;
    private int identity;
    private List<HarvestManage> landInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_harvest);
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
        Call<DataResult<List<HarvestManage>>> call = adminService.forHarvestManage();
        call.enqueue(new Callback<DataResult<List<HarvestManage>>>() {
            @Override
            public void onResponse(Call<DataResult<List<HarvestManage>>> call, Response<DataResult<List<HarvestManage>>> response) {
                DataResult<List<HarvestManage>> dataResult = response.body();
                if (dataResult != null) {
                    landInfoList = dataResult.getData();
                    regionAdapter = new RealHarvestAdapter(landInfoList, identity);
                    regionRecyclerView.setAdapter(regionAdapter);
                }
            }

            @Override
            public void onFailure(Call<DataResult<List<HarvestManage>>> call, Throwable t) {

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
                landManageAdd.setVisibility(View.VISIBLE);
                landManageAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RealHarvestActivity.this, RealHarvestActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case Constants.TECH:
                landManageAdd.setVisibility(View.GONE);
                break;

        }
    }
}
