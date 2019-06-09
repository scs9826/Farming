package com.example.farming;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farming.constants.Constants;
import com.example.farming.entity.DataResult;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.TimeUtils;

import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MarketMainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView marketNoiceText;
    private Button marketRealHarvest;
    private Button marketPredictHarvest;
    private Button marketProfit;
    private Button marketNoticeButton;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-09 19:44:40 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        marketNoiceText = (TextView)findViewById( R.id.market_noice_text );
        marketRealHarvest = (Button)findViewById( R.id.market_real_harvest );
        marketPredictHarvest = (Button)findViewById( R.id.market_predict_harvest );
        marketProfit = (Button)findViewById( R.id.market_profit );
        marketNoticeButton = (Button)findViewById( R.id.market_notice_button );

        marketRealHarvest.setOnClickListener( this );
        marketPredictHarvest.setOnClickListener( this );
        marketProfit.setOnClickListener( this );
        marketNoticeButton.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-09 19:44:40 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == marketRealHarvest ) {
            // Handle clicks for marketRealHarvest
            Intent intent = new Intent(MarketMainActivity.this, RealHarvestActivity.class);
            intent.putExtra("identity", Constants.MARKET);
            startActivity(intent);
        } else if ( v == marketPredictHarvest ) {
            // Handle clicks for marketPredictHarvest
            AlertDialog.Builder builder = new AlertDialog.Builder(MarketMainActivity.this);
            View view = View.inflate(MarketMainActivity.this, R.layout.comment_content, null);
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
                                Toast.makeText(MarketMainActivity.this, "预计采收量是:" + dataResult.getData(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResult<Long>> call, Throwable t) {
                            Toast.makeText(MarketMainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            builder.show();
        } else if ( v == marketProfit ) {
            // Handle clicks for marketProfit
            Intent intent = new Intent(MarketMainActivity.this, FeeActivity.class);
            intent.putExtra("identity", Constants.MARKET);
            startActivity(intent);
        } else if ( v == marketNoticeButton ) {
            // Handle clicks for marketNoticeButton
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_main);
        findViews();
    }
}
