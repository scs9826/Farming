package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.Ingredient;
import com.example.farming.entity.PlanManage;
import com.example.farming.http.TechService;
import com.example.farming.util.SingleTopRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlanAddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText landId;
    private EditText planDate;
    private EditText sowDate;
    private EditText upMarketDate;
    private EditText upHarvest;
    private EditText peakDate;
    private EditText peakHarvest;
    private EditText downMarketDate;
    private Button landAddComfirm;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-09 18:33:33 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        landId = (EditText)findViewById( R.id.land_id );
        planDate = (EditText)findViewById( R.id.plan_date );
        sowDate = (EditText)findViewById( R.id.sow_date );
        upMarketDate = (EditText)findViewById( R.id.up_market_date );
        upHarvest = (EditText)findViewById( R.id.up_harvest );
        peakDate = (EditText)findViewById( R.id.peak_date );
        peakHarvest = (EditText)findViewById( R.id.peak_harvest );
        downMarketDate = (EditText)findViewById( R.id.down_market_date );
        landAddComfirm = (Button)findViewById( R.id.land_add_comfirm );

        landAddComfirm.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-09 18:33:33 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == landAddComfirm ) {
            // Handle clicks for landAddComfirm
            PlanManage planManage = new PlanManage();
            planManage.setLandId(Long.valueOf(landId.getText().toString()));
            planManage.setPlanDate(planDate.getText().toString());
            planManage.setSowDate(planDate.getText().toString());
            planManage.setUpMarketDate(upMarketDate.getText().toString());
            planManage.setUpHarvest(Double.valueOf(upHarvest.getText().toString()));
            planManage.setPeakDate(peakDate.getText().toString());
            planManage.setPeakHarvest(Double.valueOf(peakHarvest.getText().toString()));
            planManage.setDownMarketDate(downMarketDate.getText().toString());
            Retrofit retrofit = SingleTopRetrofit.getInstance();
            TechService techService = retrofit.create(TechService.class);
            Call<DataResult<Boolean>> dataResultCall = techService.findPlan(planManage);
            dataResultCall.enqueue(new Callback<DataResult<Boolean>>() {
                @Override
                public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
                    DataResult<Boolean> dataResult = response.body();
                    if (dataResult != null && dataResult.getData()) {
                        Toast.makeText(PlanAddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DataResult<Boolean>> call, Throwable t) {

                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_add);
        findViews();
    }
}
