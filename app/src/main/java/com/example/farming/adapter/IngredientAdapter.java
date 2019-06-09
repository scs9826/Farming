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
import com.example.farming.entity.Ingredient;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.MyViewHolder> {

    private List<Ingredient> entityList;//获取需要显示的全部数据
    private int identity;
    private Context context;

    public IngredientAdapter(List<Ingredient> landInfoList, int identity) {
        entityList = landInfoList;
        this.identity = identity;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredient_item, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder(view);
        if (identity == Constants.ADMIN) {
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = holder.getAdapterPosition();
                    final Ingredient landInfo = entityList.get(pos);
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
        final Ingredient landInfo = entityList.get(i);
        myViewHolder.name.setText(String.valueOf((landInfo.getName())));
        myViewHolder.date.setText(landInfo.getDate());
        myViewHolder.checken.setText("chicken:" + landInfo.getChicken().toString());
        myViewHolder.horse.setText("horse:" + landInfo.getHorse().toString());
        myViewHolder.ox.setText("ox:" + landInfo.getOx());
        myViewHolder.cake.setText("cake:" + landInfo.getCake());
        myViewHolder.husk.setText("husk:" + landInfo.getHusk());
        myViewHolder.shell.setText("husk:" + landInfo.getShell());
        myViewHolder.srtaw.setText("straw:" + landInfo.getStraw());
        myViewHolder.water.setText("water:" + landInfo.getWater());
        myViewHolder.leaf.setText("leaf:" + landInfo.getLeaf());
        myViewHolder.fee.setText("fee:" + landInfo.getFee());
        myViewHolder.lossRatio.setText("lossRatio:" + landInfo.getLossRatio());
        myViewHolder.sawdust.setText("sawdust:" + landInfo.getSawdust());
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        TextView delete;
        TextView checken;
        TextView horse;
        TextView ox;
        TextView cake;
        TextView husk;
        TextView shell;
        TextView srtaw;
        TextView sawdust;
        TextView water;
        TextView leaf;
        TextView fee;
        TextView lossRatio;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView. findViewById(R.id.name);
            date = itemView. findViewById(R.id.date);
            delete = itemView. findViewById(R.id.delete);
            checken = itemView. findViewById(R.id.checken);
            horse = itemView. findViewById(R.id.horse);
            ox = itemView. findViewById(R.id.ox);
            cake = itemView. findViewById(R.id.cake);
            husk = itemView. findViewById(R.id.husk);
            shell = itemView. findViewById(R.id.shell);
            srtaw = itemView. findViewById(R.id.srtaw);
            sawdust = itemView. findViewById(R.id.sawdust);
            water = itemView. findViewById(R.id.water);
            leaf = itemView. findViewById(R.id.leaf);
            fee = itemView. findViewById(R.id.fee);
            lossRatio = itemView. findViewById(R.id.loss_ratio);
        }

    }
}
