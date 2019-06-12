/*刘云杰*/
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
import com.example.farming.entity.LandInfo;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<LandInfo> entityList;//获取需要显示的全部数据
    private int identity;
    private Context context;

    public MyAdapter(List<LandInfo> landInfoList, int identity) {
        entityList = landInfoList;
        this.identity = identity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        if (identity != Constants.ADMIN) {
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = holder.getAdapterPosition();
                    final LandInfo landInfo = entityList.get(pos);
                    Retrofit retrofit = SingleTopRetrofit.getInstance();
                    AdminService s = retrofit.create(AdminService.class);
                    Call<DataResult<Boolean>> dataResultCall = s.landDel(landInfo.getId());
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
        final LandInfo landInfo = entityList.get(i);
        if (identity == Constants.ADMIN) {

        }
        myViewHolder.block.setText(String.valueOf(landInfo.getBlock()));
        myViewHolder.region.setText(landInfo.getRegion() + " : " + landInfo.getRegionSquare());
        myViewHolder.palce.setText(landInfo.getPlace() + " : " + landInfo.getSquare());
        myViewHolder.tag.setText(landInfo.getTag() + " : " + landInfo.getTagSquare());
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
