/*史长顺*/
package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.HarvestManage;
import com.example.farming.http.MarketService;
import com.example.farming.util.SingleTopRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RealHarvestAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText seedName;
    private EditText date;
    private EditText harvestNum;
    private EditText price;
    private Button landAddComfirm;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-09 19:55:30 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        seedName = (EditText)findViewById( R.id.seed_name );
        date = (EditText)findViewById( R.id.date );
        harvestNum = (EditText)findViewById( R.id.harvest_num );
        price = (EditText)findViewById( R.id.price );
        landAddComfirm = (Button)findViewById( R.id.land_add_comfirm );

        landAddComfirm.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-09 19:55:30 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == landAddComfirm ) {
            // Handle clicks for landAddComfirm
            HarvestManage harvestManage = new HarvestManage();
            harvestManage.setDate(date.getText().toString());
            harvestManage.setSeedName(seedName.getText().toString());
            harvestManage.setHarvestNum(Double.valueOf(harvestNum.getText().toString()));
            harvestManage.setPrice(Double.valueOf(price.getText().toString()));
            Retrofit retrofit = SingleTopRetrofit.getInstance();
            MarketService marketService = retrofit.create(MarketService.class);
            Call<DataResult<Boolean>> call = marketService.addHarvestManage(harvestManage);
            call.enqueue(new Callback<DataResult<Boolean>>() {
                @Override
                public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
                    DataResult<Boolean> dataResultCall = response.body();
                    if (dataResultCall != null && dataResultCall.getData()) {
                        Toast.makeText(RealHarvestAddActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RealHarvestAddActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DataResult<Boolean>> call, Throwable t) {
                    Toast.makeText(RealHarvestAddActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_harvest_add);
        findViews();
    }
}
