package com.example.farming.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farming.AdminMainActivity;
import com.example.farming.R;
import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.PlanDel;
import com.example.farming.entity.PlanManage;
import com.example.farming.http.AdminService;
import com.example.farming.http.TechService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.TimeUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {

    private List<PlanManage> entityList;//获取需要显示的全部数据
    private int identity;
    private Context context;

    public PlanAdapter(List<PlanManage> landInfoList, int identity) {
        entityList = landInfoList;
        this.identity = identity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plan_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        if (identity == Constants.ADMIN || identity == Constants.GUEST) {
            holder.delete.setVisibility(View.GONE);
        } else if (identity == Constants.TECH) {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    View view = View.inflate(context, R.layout.del_reason, null);
                    builder.setView(view);
                    final EditText editText = view.findViewById(R.id.editText);
                    builder.setTitle("删除原因");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final int pos = holder.getAdapterPosition();
                            final PlanManage landInfo = entityList.get(pos);
                            PlanDel planDel = new PlanDel();
                            planDel.setPlanId(landInfo.getId());
                            planDel.setReason(editText.getText().toString());
                            planDel.setTemDate(TimeUtils.formatDate(new Date()));
                            Retrofit retrofit = SingleTopRetrofit.getInstance();
                            TechService s = retrofit.create(TechService.class);
                            Call<DataResult<Boolean>> dataResultCall = s.delPlan(planDel);
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
                    builder.show();

//

                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final PlanManage landInfo = entityList.get(i);
        myViewHolder.date.setText(String.valueOf((landInfo.getLandId())));
        myViewHolder.landId.setText(landInfo.getName());
        myViewHolder.fee.setText(landInfo.getPlanDate());
        myViewHolder.farmwork.setText(landInfo.getUpMarketDate() + ":" + landInfo.getUpHarvest() + "元");
        myViewHolder.ingredient.setText(landInfo.getPeakDate() + ":" + landInfo.getPeakHarvest() + "元");
        myViewHolder.seed.setText(landInfo.getDownMarketDate());
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
        TextView ingredient;
        TextView seed;
        TextView delete;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            landId = itemView.findViewById(R.id.land_id);
            fee = itemView.findViewById(R.id.fee);
            farmwork = itemView.findViewById(R.id.farmwork);
            ingredient = itemView.findViewById(R.id.ingredient);
            seed = itemView.findViewById(R.id.seed);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
