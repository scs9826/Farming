package com.example.farming.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farming.R;
import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.HarvestManage;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RealHarvestAdapter extends RecyclerView.Adapter<RealHarvestAdapter.MyViewHolder> {

    private List<HarvestManage> entityList;//获取需要显示的全部数据
    private int identity;
    private Context context;

    public RealHarvestAdapter(List<HarvestManage> landInfoList, int identity) {
        entityList = landInfoList;
        this.identity = identity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.harvest_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        if (identity != Constants.MARKET) {
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = holder.getAdapterPosition();
                    final HarvestManage landInfo = entityList.get(pos);
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
        final HarvestManage landInfo = entityList.get(i);
        myViewHolder.date.setText(landInfo.getDate());
        myViewHolder.landId.setText(landInfo.getHarvestNum().toString());
        myViewHolder.fee.setText(landInfo.getSeedName());
        myViewHolder.farmwork.setText(landInfo.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView landId;
        TextView fee;
        TextView farmwork;
        TextView delete;

        private void findViews() {
            date = itemView.findViewById( R.id.date );
            landId = itemView.findViewById( R.id.land_id );
            fee = itemView.findViewById( R.id.fee );
            farmwork = itemView.findViewById( R.id.farmwork );
            delete = itemView.findViewById( R.id.delete );
        }


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById( R.id.date );
            landId = itemView.findViewById( R.id.land_id );
            fee = itemView.findViewById( R.id.fee );
            farmwork = itemView.findViewById( R.id.farmwork );
            delete = itemView.findViewById( R.id.delete );
        }

    }
}