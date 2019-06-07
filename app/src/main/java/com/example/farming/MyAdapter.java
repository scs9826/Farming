package com.example.farming;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public MyAdapter(List<Entity> entityList) {
        this.entityList = entityList;
    }

    private List<Entity> entityList;//获取需要显示的全部数据

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.item.setText(entityList.get(i).getItem());
        myViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo  
                // 删除本条信息
            }
        });
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }


    //myveiwholder类里包括了xml中的组件
    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView item;
        private TextView delete;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
