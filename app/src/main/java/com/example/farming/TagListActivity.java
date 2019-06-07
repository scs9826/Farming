package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class TagListActivity extends AppCompatActivity {

    private RecyclerView tagRecyclerView;
    private List<Entity> tagList;
    private MyAdapter tagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_list);

        initData();
        tagRecyclerView = findViewById(R.id.tag_recyclerview);
        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        tagAdapter = new MyAdapter(tagList);
        tagRecyclerView.setAdapter(tagAdapter);
    }

    protected void initData(){
        //todo
    }
}
