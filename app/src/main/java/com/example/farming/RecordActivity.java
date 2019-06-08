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
import com.example.farming.entity.LandInfo;
import com.example.farming.entity.PurchaseRecord;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecordActivity extends AppCompatActivity {
    private RecyclerView regionRecyclerView;
    private PurchaseAdapter regionAdapter;
    private ImageView landManageAdd;
    private int identity;
    private List<PurchaseRecord> landInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initData();
    }

    protected void initData() {
        regionRecyclerView = findViewById(R.id.purchase_recyclerview);
        landManageAdd = findViewById(R.id.purchase_add);
        regionRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Intent getIntent = getIntent();
        identity = getIntent.getByteExtra("identity", (byte) 0);
        Retrofit retrofit = SingleTopRetrofit.getInstance();
        AdminService adminService = retrofit.create(AdminService.class);
        Call<DataResult<List<PurchaseRecord>>> call = adminService.materialProduct();
        call.enqueue(new Callback<DataResult<List<PurchaseRecord>>>() {
            @Override
            public void onResponse(Call<DataResult<List<PurchaseRecord>>> call, Response<DataResult<List<PurchaseRecord>>> response) {
                DataResult<List<PurchaseRecord>> dataResult = response.body();
                if (dataResult != null) {
                    landInfoList = dataResult.getData();
                    regionAdapter = new PurchaseAdapter(landInfoList, identity);
                    regionRecyclerView.setAdapter(regionAdapter);
                }
            }

            @Override
            public void onFailure(Call<DataResult<List<PurchaseRecord>>> call, Throwable t) {

            }
        });
        switch (identity) {
            default:
                landManageAdd.setVisibility(View.GONE);
            case Constants.ADMIN:
                landManageAdd.setVisibility(View.VISIBLE);
                landManageAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RecordActivity.this, PurchaseActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case Constants.GUEST:
                break;
            case Constants.MARKET:
                break;
            case Constants.TECH:
                landManageAdd.setVisibility(View.GONE);
                break;

        }
    }
}
