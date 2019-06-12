/*史长顺*/
package com.example.farming.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.farming.R;
import com.example.farming.entity.FeeInfo;

import java.util.List;

public class FeeAdapter extends RecyclerView.Adapter<FeeAdapter.MyViewHolder> {

    private List<FeeInfo> entityList;//获取需要显示的全部数据
    private int identity;
    private Context context;

    public FeeAdapter(List<FeeInfo> landInfoList, int identity) {
        entityList = landInfoList;
        this.identity = identity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fee_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
//        if (identity == Constants.ADMIN) {
//            holder.delete.setVisibility(View.GONE);
//        } else {
//            holder.delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final int pos = holder.getAdapterPosition();
//                    final FeeInfo landInfo = entityList.get(pos);
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
//    }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final FeeInfo landInfo = entityList.get(i);
        myViewHolder.date.setText("预计可采收量:" + landInfo.getHarvestNum());
        myViewHolder.landId.setText("利润:" + landInfo.getProfits());
        myViewHolder.fee.setText("花费:" + landInfo.getCost());
        myViewHolder.farmwork.setText("预计采收量:" + landInfo.getNum());
        myViewHolder.delete.setText("农做记录：" + landInfo.getId());
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
            date = itemView.findViewById(R.id.date);
            landId = itemView.findViewById(R.id.land_id);
            fee = itemView.findViewById(R.id.fee);
            farmwork = itemView.findViewById(R.id.farmwork);
            delete = itemView.findViewById(R.id.delete);
        }


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            landId = itemView.findViewById(R.id.land_id);
            fee = itemView.findViewById(R.id.fee);
            farmwork = itemView.findViewById(R.id.farmwork);
            delete = itemView.findViewById(R.id.delete);
        }

    }
}
