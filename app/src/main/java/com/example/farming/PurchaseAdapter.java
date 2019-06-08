package com.example.farming;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.LandInfo;
import com.example.farming.entity.PurchaseRecord;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.TimeUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.MyViewHolder> {

    private List<PurchaseRecord> entityList;//获取需要显示的全部数据
    private int identity;
    private Context context;

    public PurchaseAdapter(List<PurchaseRecord> landInfoList, int identity) {
        entityList = landInfoList;
        this.identity = identity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.purchase_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        if (identity != Constants.ADMIN) {
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = holder.getAdapterPosition();
                    final PurchaseRecord landInfo = entityList.get(pos);
                    Retrofit retrofit = SingleTopRetrofit.getInstance();
                    AdminService s = retrofit.create(AdminService.class);
                    Call<DataResult<Boolean>> dataResultCall = s.addProduct(landInfo.getId());
                    dataResultCall.enqueue(new Callback<DataResult<Boolean>>() {
                        @Override
                        public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
                            DataResult<Boolean> dataResult = response.body();
                            if (dataResult != null && dataResult.getData()) {
                                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                                entityList.remove(landInfo);
                            } else {
                                Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResult<Boolean>> call, Throwable t) {

                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final PurchaseRecord landInfo = entityList.get(i);
        myViewHolder.block.setText(String.valueOf((landInfo.getDate())));
        myViewHolder.region.setText(landInfo.getName());
        myViewHolder.palce.setText(landInfo.getNum().toString());
        myViewHolder.tag.setText(landInfo.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView region;
        TextView block;
        TextView tag;
        TextView palce;
        TextView delete;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            region = itemView.findViewById(R.id.region);
            block = itemView.findViewById(R.id.block);
            tag = itemView.findViewById(R.id.tag);
            palce = itemView.findViewById(R.id.palce);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
