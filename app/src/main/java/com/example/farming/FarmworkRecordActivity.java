package com.example.farming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.farming.adapter.ProductMaterialAdapter;
import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.Entity;
import com.example.farming.entity.FarmworkRecord;
import com.example.farming.entity.ProductMaterial;
import com.example.farming.http.AdminService;
import com.example.farming.http.TechService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FarmworkRecordActivity extends AppCompatActivity {

    private RecyclerView regionRecyclerView;
    private List<Entity> regionList;
    private FarmRecordAdapter regionAdapter;
    private ImageView landManageAdd;
    private int identity;
    private List<FarmworkRecord> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmwork_record);
        initData();
    }

    protected void initData() {
        regionRecyclerView = findViewById(R.id.ingredient_recyclerview);
        landManageAdd = findViewById(R.id.indredient_add);
        regionRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Intent getIntent = getIntent();
        identity = getIntent.getByteExtra("identity", (byte) 0);
        Retrofit retrofit = SingleTopRetrofit.getInstance();
        TechService adminService = retrofit.create(TechService.class);
        Call<DataResult<List<FarmworkRecord>>> call = adminService.farmwork();
        call.enqueue(new Callback<DataResult<List<FarmworkRecord>>>() {
            @Override
            public void onResponse(Call<DataResult<List<FarmworkRecord>>> call, Response<DataResult<List<FarmworkRecord>>> response) {
                DataResult<List<FarmworkRecord>> dataResult = response.body();
                if (dataResult != null) {
                    strings = dataResult.getData();
                    regionAdapter = new FarmRecordAdapter(strings, identity);
                    regionRecyclerView.setAdapter(regionAdapter);
                }
            }

            @Override
            public void onFailure(Call<DataResult<List<FarmworkRecord>>> call, Throwable t) {

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
                        Intent intent = new Intent(FarmworkRecordActivity.this, FarmworkRecordAddActivity.class);
                        startActivity(intent);
                    }
                });
                break;

        }
    }
}
