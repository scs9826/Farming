package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.LandInfo;
import com.example.farming.entity.PurchaseRecord;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;
import com.example.farming.util.TimeUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PurchaseActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText landRegion;
    private EditText landTag;
    private EditText landBlock;
    private EditText landPlace;
    private Button landAddComfirm;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-08 20:17:30 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        landRegion = (EditText)findViewById( R.id.land_region );
        landTag = (EditText)findViewById( R.id.land_tag );
        landBlock = (EditText)findViewById( R.id.land_block );
        landPlace = (EditText)findViewById( R.id.land_place );
        landAddComfirm = (Button)findViewById( R.id.land_add_comfirm );

        landAddComfirm.setOnClickListener( this );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        findViews();
    }


    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-08 18:58:13 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == landAddComfirm ) {
            // Handle clicks for landAddComfirm
            PurchaseRecord landInfo = new PurchaseRecord();
            try {
                landInfo.setName(landRegion.getText().toString());
                landInfo.setNum(Integer.valueOf(landTag.getText().toString()));
                landInfo.setDate(landBlock.getText().toString());
                landInfo.setPrice(Double.valueOf(landPlace.getText().toString()));
                Retrofit retrofit = SingleTopRetrofit.getInstance();
                AdminService adminService = retrofit.create(AdminService.class);
                Call<DataResult<Boolean>> call = adminService.addProduct(landInfo);
                call.enqueue(new Callback<DataResult<Boolean>>() {
                    @Override
                    public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
                        DataResult<Boolean> dataResult = response.body();
                        if (dataResult != null && dataResult.getData()) {
                            Toast.makeText(PurchaseActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataResult<Boolean>> call, Throwable t) {
                        Toast.makeText(PurchaseActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }catch (Exception e) {
                Toast.makeText(this, landInfo.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

