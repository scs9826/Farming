package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class LandManagerActivity extends AppCompatActivity {

    private RecyclerView regionRecyclerView;
    private List<Entity> regionList;
    private MyAdapter regionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_manager);

        initData();
        regionRecyclerView = findViewById(R.id.region_recyclerview);
        regionRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        regionAdapter = new MyAdapter(regionList);
        regionRecyclerView.setAdapter(regionAdapter);
    }

    protected void initData(){
        //todo
    }
}
