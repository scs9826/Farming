package com.example.farming;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.example.farming.http.LoginService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.TimeUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GuestMainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView gustNoticeText;
    private Button gustNoticeButton;
    private Button gustPlan;
    private Button gustPredictHarvest;
    private List<String> noticeList;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-09 20:17:22 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        gustNoticeText = (TextView)findViewById( R.id.gust_notice_text );
        gustNoticeButton = (Button)findViewById( R.id.gust_notice_button );
        gustPlan = (Button)findViewById( R.id.gust_plan );
        gustPredictHarvest = (Button)findViewById( R.id.gust_predict_harvest );

        gustNoticeButton.setOnClickListener( this );
        gustPlan.setOnClickListener( this );
        gustPredictHarvest.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-09 20:17:22 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == gustNoticeButton ) {
            // Handle clicks for gustNoticeButton
            Intent intent = new Intent(this, NoticeActivity.class);
            intent.putStringArrayListExtra("list", (ArrayList<String>) noticeList);
            startActivity(intent);
        } else if ( v == gustPlan ) {
            // Handle clicks for gustPlan

            Intent intent = new Intent(GuestMainActivity.this, PlanActivity.class);
            intent.putExtra("identity", Constants.GUEST);
            startActivityForResult(intent, 1);
        } else if ( v == gustPredictHarvest ) {
            // Handle clicks for gustPredictHarvest
            AlertDialog.Builder builder = new AlertDialog.Builder(GuestMainActivity.this);
            View view = View.inflate(GuestMainActivity.this, R.layout.comment_content, null);
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
                                Toast.makeText(GuestMainActivity.this, "预计采收量是:" + dataResult.getData(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DataResult<Long>> call, Throwable t) {
                            Toast.makeText(GuestMainActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            builder.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    noticeList = data.getStringArrayListExtra("notice");
                    gustNoticeText.setText(noticeList.get(0) + "就要上市啦!!!");
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_main);
        findViews();
        Retrofit retrofit = SingleTopRetrofit.getInstance();
        LoginService service = retrofit.create(LoginService.class);
        Call<DataResult<List<String>>> call = service.notice();
        call.enqueue(new Callback<DataResult<List<String>>>() {
            @Override
            public void onResponse(Call<DataResult<List<String>>> call, Response<DataResult<List<String>>> response) {
                DataResult<List<String>> dataResult = response.body();
                if (dataResult != null && dataResult.getData() != null && !dataResult.getData().isEmpty()) {
                    gustNoticeText.setText(dataResult.getData().get(0) + "就要上市啦!!!");
                    noticeList = dataResult.getData();
                } else {
                    gustNoticeText.setText("当前无提醒!!!");
                }
            }

            @Override
            public void onFailure(Call<DataResult<List<String>>> call, Throwable t) {
                gustNoticeText.setText("当前无提醒!!!");
            }
        });
    }
}
