/*刘云杰*/
package com.example.farming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.farming.adapter.MyAdapter;
import com.example.farming.constants.Constants;
import com.example.farming.entity.Entity;
import com.example.farming.entity.LandInfo;

import java.util.ArrayList;
import java.util.List;

public class LandManagerActivity extends AppCompatActivity {

    private RecyclerView regionRecyclerView;
    private List<Entity> regionList;
    private MyAdapter regionAdapter;
    private ImageView landManageAdd;
    private int identity;
    private List<LandInfo> landInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_manager);
        initData();
    }

    protected void initData() {
        regionRecyclerView = findViewById(R.id.region_recyclerview);
        landManageAdd = findViewById(R.id.land_manager_add);
        regionRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Intent getIntent = getIntent();
        identity = getIntent.getByteExtra("identity", (byte) 0);
        landInfoList = getIntent.getParcelableArrayListExtra("landManage");
        regionAdapter = new MyAdapter(landInfoList, identity);
        regionRecyclerView.setAdapter(regionAdapter);
        switch (identity) {
            default:
                landManageAdd.setVisibility(View.GONE);
            case Constants.ADMIN:
                landManageAdd.setVisibility(View.VISIBLE);
                landManageAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LandManagerActivity.this, LandAdd.class);
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
