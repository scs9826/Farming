package com.example.farming;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {

    private ListView recyclerView;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        list = getIntent().getStringArrayListExtra("list");
        recyclerView = findViewById(R.id.region_recyclerview);
        ArrayAdapter arrayAdapter = new NoticeAdapter(this, R.layout.notice_item, list);
        recyclerView.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra("notice", (ArrayList<String>) list);
        setResult(RESULT_OK, intent);
        finish();
    }

    class NoticeAdapter extends ArrayAdapter {

        public NoticeAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            String name = (String) getItem(position);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item, parent, false);
            TextView view1 = view.findViewById(R.id.delete);
            TextView view2 = view.findViewById(R.id.region);
            view2.setText(name);
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = list.get(position);
                    list.set(position, list.get(0));
                    list.set(0, a);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}
