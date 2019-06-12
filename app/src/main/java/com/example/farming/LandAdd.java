/*刘云杰*/
package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farming.constants.CacheUtils;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.LandInfo;
import com.example.farming.http.AdminService;
import com.example.farming.util.SingleTopRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LandAdd extends AppCompatActivity implements View.OnClickListener {

    private EditText landRegion;
    private EditText landRegionSquare;
    private EditText landTag;
    private EditText landTagSquare;
    private EditText landBlock;
    private EditText landPlace;
    private EditText landPlaceSquare;
    private EditText landAddId;
    private Button landAddComfirm;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-08 18:58:13 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        landRegion = (EditText)findViewById( R.id.land_region );
        landRegionSquare = (EditText)findViewById( R.id.land_region_square );
        landTag = (EditText)findViewById( R.id.land_tag );
        landTagSquare = (EditText)findViewById( R.id.land_tag_square );
        landBlock = (EditText)findViewById( R.id.land_block );
        landPlace = (EditText)findViewById( R.id.land_place );
        landPlaceSquare = (EditText)findViewById( R.id.land_place_square );
        landAddComfirm = (Button)findViewById( R.id.land_add_comfirm );

        landAddComfirm.setOnClickListener( this );
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
            LandInfo landInfo = new LandInfo();
            try {
                landInfo.setPlace(landPlace.getText().toString());
                landInfo.setSquare(Double.valueOf(landPlaceSquare.getText().toString()));
                landInfo.setRegion(landRegion.getText().toString());
                landInfo.setUid(2L);
                landInfo.setRegionSquare(Double.valueOf(landRegionSquare.getText().toString()));
                landInfo.setBlock(Integer.valueOf(landBlock.getText().toString()));
                landInfo.setTag(landTag.getText().toString());
                landInfo.setTagSquare(Double.valueOf(landTagSquare.getText().toString()));
                Retrofit retrofit = SingleTopRetrofit.getInstance();
                AdminService adminService = retrofit.create(AdminService.class);
                Call<DataResult<Boolean>> call = adminService.addLand(landInfo);
                call.enqueue(new Callback<DataResult<Boolean>>() {
                    @Override
                    public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
                        DataResult<Boolean> dataResult = response.body();
                        if (dataResult != null && dataResult.getData()) {
                            Toast.makeText(LandAdd.this, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataResult<Boolean>> call, Throwable t) {

                    }
                });
            }catch (Exception e) {
                Toast.makeText(this, landInfo.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land_add);
        findViews();
    }
}
