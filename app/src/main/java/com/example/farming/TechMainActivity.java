/*史长顺*/
package com.example.farming;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
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
import com.example.farming.entity.FarmworkRecord;
import com.example.farming.entity.LandInfo;
import com.example.farming.http.AdminService;
import com.example.farming.http.LoginService;
import com.example.farming.http.TechService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.TimeUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TechMainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView techNoticeText;
    private Button techLand;
    private Button techPurchase;
    private Button techIngredient;
    private Button techPlan;
    private Button techFarmwork;
    private Button techRealHarvest;
    private Button techNoticeButton;
    private Button techPredictHarvest;
    private Button techProfit;
    private List<String> noticeList;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-09 16:54:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        techNoticeText = (TextView)findViewById( R.id.tech_notice_text );
        techLand = (Button)findViewById( R.id.tech_land );
        techPurchase = (Button)findViewById( R.id.tech_purchase );
        techIngredient = (Button)findViewById( R.id.tech_ingredient );
        techPlan = (Button)findViewById( R.id.tech_plan );
        techFarmwork = (Button)findViewById( R.id.tech_farmwork );
        techRealHarvest = (Button)findViewById( R.id.tech_real_harvest );
        techNoticeButton = (Button)findViewById( R.id.tech_notice_button );
        techPredictHarvest = (Button)findViewById( R.id.tech_predict_harvest );
        techProfit = (Button)findViewById( R.id.tech_profit );

        techLand.setOnClickListener( this );
        techPurchase.setOnClickListener( this );
        techIngredient.setOnClickListener( this );
        techPlan.setOnClickListener( this );
        techFarmwork.setOnClickListener( this );
        techRealHarvest.setOnClickListener( this );
        techNoticeButton.setOnClickListener( this );
        techPredictHarvest.setOnClickListener( this );
        techProfit.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-09 16:54:26 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == techLand ) {
            // Handle clicks for techLand
            // Handle clicks for adminLand
            Retrofit retrofit = SingleTopRetrofit.getInstance();
            TechService adminService = retrofit.create(TechService.class);
            Call<DataResult<List<LandInfo>>> call = adminService.landInfo(CacheUtils.getUserInfo(TechMainActivity.this).getId());
            call.enqueue(new Callback<DataResult<List<LandInfo>>>() {
                @Override
                public void onResponse(Call<DataResult<List<LandInfo>>> call, Response<DataResult<List<LandInfo>>> response) {
                    DataResult<List<LandInfo>> dataResult = response.body();
                    if (dataResult != null) {
                        Intent intent = new Intent(TechMainActivity.this, LandManagerActivity.class);
                        intent.putParcelableArrayListExtra("landManage", (ArrayList<? extends Parcelable>) dataResult.getData());
                        intent.putExtra("identity", Constants.TECH);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<DataResult<List<LandInfo>>> call, Throwable t) {
                    Toast.makeText(TechMainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else if ( v == techPurchase ) {
            // Handle clicks for techPurchase
            Intent intent = new Intent(TechMainActivity.this, RecordActivity.class);
            intent.putExtra("identity", Constants.TECH);
            startActivity(intent);
        } else if ( v == techIngredient ) {
            // Handle clicks for techIngredient
            Intent intent = new Intent(TechMainActivity.this, IngredientListActivity.class);
            intent.putExtra("identity", Constants.TECH);
            startActivity(intent);
        } else if ( v == techPlan ) {
            // Handle clicks for techPlan
            Intent intent = new Intent(TechMainActivity.this, PlanActivity.class);
            intent.putExtra("identity", Constants.TECH);
            startActivity(intent);
        } else if ( v == techFarmwork ) {
            // Handle clicks for techFarmwork
            Intent intent = new Intent(TechMainActivity.this, FarmworkRecordActivity.class);
            intent.putExtra("identity", Constants.TECH);
            startActivity(intent);
        } else if ( v == techRealHarvest ) {
            // Handle clicks for techRealHarvest
            Intent intent = new Intent(TechMainActivity.this, RealHarvestActivity.class);
            intent.putExtra("identity", Constants.TECH);
            startActivity(intent);
        } else if ( v == techNoticeButton ) {
            // Handle clicks for techNoticeButton
            Intent intent = new Intent(this, NoticeActivity.class);
            intent.putStringArrayListExtra("list", (ArrayList<String>) noticeList);
            startActivityForResult(intent, 1);
        } else if ( v == techPredictHarvest ) {
            // Handle clicks for techPredictHarvest
            AlertDialog.Builder builder = new AlertDialog.Builder(TechMainActivity.this);
            View view = View.inflate(TechMainActivity.this, R.layout.comment_content, null);
            builder.setView(view);
            final EditText editText = view.findViewById(R.id.editText);
            builder.setTitle("预测");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Retrofit retrofit = SingleTopRetrofit.getInstance();
                    AdminService adminService = retrofit.create(AdminService.class);
                    Call<DataResult<Long>> call = null;
                    call = adminService.forPlan(editText.getText().toString());
                    call.enqueue(new Callback<DataResult<Long>>() {
                        @Override
                        public void onResponse(Call<DataResult<Long>> call, Response<DataResult<Long>> response) {
                            DataResult<Long> dataResult = response.body();
                            if (dataResult != null && dataResult.getData() != null) {
                                Toast.makeText(TechMainActivity.this, "预计采收量是:" + dataResult.getData(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResult<Long>> call, Throwable t) {
                            Toast.makeText(TechMainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            builder.show();
        } else if ( v == techProfit ) {
            // Handle clicks for techProfit
            Intent intent = new Intent(TechMainActivity.this, FeeActivity.class);
            intent.putExtra("identity", Constants.TECH);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_main);
        findViews();
        Retrofit retrofit = SingleTopRetrofit.getInstance();
        LoginService service = retrofit.create(LoginService.class);
        Call<DataResult<List<String>>> call = service.notice();
        call.enqueue(new Callback<DataResult<List<String>>>() {
            @Override
            public void onResponse(Call<DataResult<List<String>>> call, Response<DataResult<List<String>>> response) {
                DataResult<List<String>> dataResult = response.body();
                if (dataResult != null && dataResult.getData() != null && !dataResult.getData().isEmpty()) {
                    techNoticeText.setText(dataResult.getData().get(0) + "就要上市啦!!!");
                    noticeList = dataResult.getData();
                } else {
                    techNoticeText.setText("当前无提醒!!!");
                }
            }

            @Override
            public void onFailure(Call<DataResult<List<String>>> call, Throwable t) {
                techNoticeText.setText("当前无提醒!!!");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    noticeList = data.getStringArrayListExtra("notice");
                    techNoticeText.setText(noticeList.get(0) + "就要上市啦!!!");
                }
                break;
        }
    }
}
