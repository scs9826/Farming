/*史长顺*/
package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.FarmworkRecord;
import com.example.farming.entity.PlanManage;
import com.example.farming.http.TechService;
import com.example.farming.util.SingleTopRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FarmworkRecordAddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText landId;
    private EditText date;
    private EditText fee;
    private EditText farmWork;
    private EditText ingredientName;
    private EditText ingredientNum;
    private EditText seedName;
    private EditText seedNum;
    private Button landAddComfirm;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-09 19:25:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        landId = (EditText)findViewById( R.id.land_id );
        date = (EditText)findViewById( R.id.date );
        fee = (EditText)findViewById( R.id.fee );
        farmWork = (EditText)findViewById( R.id.farm_work );
        ingredientName = (EditText)findViewById( R.id.ingredient_name );
        ingredientNum = (EditText)findViewById( R.id.ingredient_num );
        seedName = (EditText)findViewById( R.id.seed_name );
        seedNum = (EditText)findViewById( R.id.seed_num );
        landAddComfirm = (Button)findViewById( R.id.land_add_comfirm );

        landAddComfirm.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-09 19:25:31 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == landAddComfirm ) {
            // Handle clicks for landAddComfirm
            FarmworkRecord farmworkRecord = new FarmworkRecord();
            farmworkRecord.setLandId(Long.valueOf(landId.getText().toString()));
            farmworkRecord.setDate(date.getText().toString());
            farmworkRecord.setFee(Integer.valueOf(fee.getText().toString()));
            farmworkRecord.setFarmwork(farmWork.getText().toString());
            farmworkRecord.setIngredientName(ingredientName.getText().toString());
            farmworkRecord.setIngredientNum(Double.valueOf(ingredientNum.getText().toString()));
            farmworkRecord.setSeedName(seedName.getText().toString());
            farmworkRecord.setSeedNum(Double.valueOf(seedNum.getText().toString()));
            Retrofit retrofit = SingleTopRetrofit.getInstance();
            TechService techService = retrofit.create(TechService.class);
            Call<DataResult<Boolean>> dataResultCall = techService.insertFarm(farmworkRecord);
            dataResultCall.enqueue(new Callback<DataResult<Boolean>>() {
                @Override
                public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
                    DataResult<Boolean> dataResult = response.body();
                    if (dataResult != null && dataResult.getData()) {
                        Toast.makeText(FarmworkRecordAddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_farmwork_record_add);
        findViews();
    }
}
