package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class BlockListActivity extends AppCompatActivity {

    private RecyclerView blockRecyclerView;
    private List<Entity> blockList;
    private MyAdapter blockAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_list);

        initData();
        blockRecyclerView = findViewById(R.id.block_recyclerview);
        blockRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        blockAdapter = new MyAdapter(blockList);
        blockRecyclerView.setAdapter(blockAdapter);
    }

    protected void initData(){
        //todo
    }
}
