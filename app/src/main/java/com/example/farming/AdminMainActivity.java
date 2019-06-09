package com.example.farming;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farming.constants.CacheUtils;
import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.LandInfo;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.TimeUtils;

import org.w3c.dom.Comment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminMainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView adminNoticeText;
    private Button adminLand;
    private Button adminPurchase;
    private Button adminIngredient;
    private Button adminPlan;
    private Button adminFarmwork;
    private Button adminRealHarvest;
    private Button adminNoticeButton;
    private Button adminPredictHarvest;
    private Button adminProfit;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-08 14:12:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        adminNoticeText = (TextView)findViewById( R.id.admin_notice_text );
        adminLand = (Button)findViewById( R.id.admin_land );
        adminPurchase = (Button)findViewById( R.id.admin_purchase );
        adminIngredient = (Button)findViewById( R.id.admin_ingredient );
        adminPlan = (Button)findViewById( R.id.admin_plan );
        adminFarmwork = (Button)findViewById( R.id.admin_farmwork );
        adminRealHarvest = (Button)findViewById( R.id.admin_real_harvest );
        adminNoticeButton = (Button)findViewById( R.id.admin_notice_button );
        adminPredictHarvest = (Button)findViewById( R.id.admin_predict_harvest );
        adminProfit = (Button)findViewById( R.id.admin_profit );

        adminLand.setOnClickListener( this );
        adminPurchase.setOnClickListener( this );
        adminIngredient.setOnClickListener( this );
        adminPlan.setOnClickListener( this );
        adminFarmwork.setOnClickListener( this );
        adminRealHarvest.setOnClickListener( this );
        adminNoticeButton.setOnClickListener( this );
        adminPredictHarvest.setOnClickListener( this );
        adminProfit.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-08 14:12:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == adminLand ) {
            // Handle clicks for adminLand
            Retrofit retrofit = SingleTopRetrofit.getInstance();
            AdminService adminService = retrofit.create(AdminService.class);
            Call<DataResult<List<LandInfo>>> call = adminService.landInfo();
            call.enqueue(new Callback<DataResult<List<LandInfo>>>() {
                @Override
                public void onResponse(Call<DataResult<List<LandInfo>>> call, Response<DataResult<List<LandInfo>>> response) {
                    DataResult<List<LandInfo>> dataResult = response.body();
                    if (dataResult != null) {
                        Intent intent = new Intent(AdminMainActivity.this, LandManagerActivity.class);
                        intent.putParcelableArrayListExtra("landManage", (ArrayList<? extends Parcelable>) dataResult.getData());
                        intent.putExtra("identity", Constants.ADMIN);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<DataResult<List<LandInfo>>> call, Throwable t) {
                    Toast.makeText(AdminMainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else if ( v == adminPurchase ) {
            // Handle clicks for adminPurchase
            Intent intent = new Intent(AdminMainActivity.this, RecordActivity.class);
            intent.putExtra("identity", Constants.ADMIN);
            startActivity(intent);
        } else if ( v == adminIngredient ) {
            // Handle clicks for adminIngredient
            Intent intent = new Intent(AdminMainActivity.this, IngredientListActivity.class);
            intent.putExtra("identity", Constants.ADMIN);
            startActivity(intent);
        } else if ( v == adminPlan ) {
            // Handle clicks for adminPlan
            Intent intent = new Intent(AdminMainActivity.this, PlanActivity.class);
            intent.putExtra("identity", Constants.ADMIN);
            startActivity(intent);
        } else if ( v == adminFarmwork ) {
            // Handle clicks for adminFarmwork
            Intent intent = new Intent(AdminMainActivity.this, ProductMaterialActivity.class);
            intent.putExtra("identity", Constants.ADMIN);
            startActivity(intent);
        } else if ( v == adminRealHarvest ) {
            // Handle clicks for adminRealHarvest
            Intent intent = new Intent(AdminMainActivity.this, RealHarvestActivity.class);
            intent.putExtra("identity", Constants.ADMIN);
            startActivity(intent);
        } else if ( v == adminNoticeButton ) {
            // Handle clicks for adminNoticeButton

        } else if ( v == adminPredictHarvest ) {
            // Handle clicks for adminPredictHarvest
            AlertDialog.Builder builder = new AlertDialog.Builder(AdminMainActivity.this);
            View view = View.inflate(AdminMainActivity.this, R.layout.comment_content, null);
            builder.setView(view);
            final EditText editText = view.findViewById(R.id.editText);
            builder.setTitle("预测");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Retrofit retrofit = SingleTopRetrofit.getInstance();
                    AdminService adminService = retrofit.create(AdminService.class);
                    Call<DataResult<Long>> call = null;
                    try {
                        call = adminService.forPlan(TimeUtils.formatString(editText.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    call.enqueue(new Callback<DataResult<Long>>() {
                        @Override
                        public void onResponse(Call<DataResult<Long>> call, Response<DataResult<Long>> response) {
                            DataResult<Long> dataResult = response.body();
                            if (dataResult != null && dataResult.getData() != null) {
                                Toast.makeText(AdminMainActivity.this, "预计采收量是:" + dataResult.getData(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResult<Long>> call, Throwable t) {
                            Toast.makeText(AdminMainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            builder.show();
        } else if ( v == adminProfit ) {
            // Handle clicks for adminProfit
            Intent intent = new Intent(AdminMainActivity.this, FeeActivity.class);
            intent.putExtra("identity", Constants.ADMIN);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        findViews();
    }
}
