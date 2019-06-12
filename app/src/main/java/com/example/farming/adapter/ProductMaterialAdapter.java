/*饶丝雨*/
package com.example.farming.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farming.R;
import com.example.farming.constants.Constants;

import java.util.List;

public class ProductMaterialAdapter extends RecyclerView.Adapter<ProductMaterialAdapter.MyViewHolder> {

    private List<String> entityList;//获取需要显示的全部数据
    private int identity;
    private Context context;

    public ProductMaterialAdapter(List<String> landInfoList, int identity) {
        entityList = landInfoList;
        this.identity = identity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_material_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        if (identity == Constants.ADMIN) {
            holder.delete.setVisibility(View.GONE);
        } else {
//            holder.delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final int pos = holder.getAdapterPosition();
//                    final String landInfo = entityList.get(pos);
//                    Retrofit retrofit = SingleTopRetrofit.getInstance();
//                    AdminService s = retrofit.create(AdminService.class);
//                    Call<DataResult<Boolean>> dataResultCall = s.addProduct(landInfo.getId());
//                    dataResultCall.enqueue(new Callback<DataResult<Boolean>>() {
//                        @Override
//                        public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
//                            DataResult<Boolean> dataResult = response.body();
//                            if (dataResult != null && dataResult.getData()) {
//                                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
//                                notifyDataSetChanged();
//                                entityList.remove(landInfo);
//                            } else {
//                                Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<DataResult<Boolean>> call, Throwable t) {
//
//                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final String landInfo = entityList.get(i);
        myViewHolder.name.setText(landInfo);
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView delete;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView. findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);
        }

    }
}
