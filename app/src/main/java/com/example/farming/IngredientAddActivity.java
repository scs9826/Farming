package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farming.entity.DataResult;
import com.example.farming.entity.Ingredient;
import com.example.farming.http.TechService;
import com.example.farming.util.SingleTopRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IngredientAddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText fee;
    private EditText time;
    private EditText lossRatio;
    private EditText chicken;
    private EditText horse;
    private EditText ox;
    private EditText cake;
    private EditText husk;
    private EditText shell;
    private EditText straw;
    private EditText sawdust;
    private EditText water;
    private EditText leaf;
    private Button landAddComfirm;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-09 17:39:10 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        name = (EditText)findViewById( R.id.name );
        fee = (EditText)findViewById( R.id.fee );
        time = (EditText)findViewById( R.id.time );
        lossRatio = (EditText)findViewById( R.id.loss_ratio );
        chicken = (EditText)findViewById( R.id.chicken );
        horse = (EditText)findViewById( R.id.horse );
        ox = (EditText)findViewById( R.id.ox );
        cake = (EditText)findViewById( R.id.cake );
        husk = (EditText)findViewById( R.id.husk );
        shell = (EditText)findViewById( R.id.shell );
        straw = (EditText)findViewById( R.id.straw );
        sawdust = (EditText)findViewById( R.id.sawdust );
        water = (EditText)findViewById( R.id.water );
        leaf = (EditText)findViewById( R.id.leaf );
        landAddComfirm = (Button)findViewById( R.id.land_add_comfirm );

        landAddComfirm.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-09 17:39:10 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == landAddComfirm ) {
            // Handle clicks for landAddComfirm
            Ingredient ingredient = new Ingredient();
            ingredient.setCake(Double.valueOf(cake.getText().toString()));
            ingredient.setName(name.getText().toString());
            ingredient.setFee(Integer.valueOf(fee.getText().toString()));
            ingredient.setDate(time.getText().toString());
            ingredient.setLossRatio(Double.valueOf(lossRatio.getText().toString()));
            ingredient.setChicken(Double.valueOf(chicken.getText().toString()));
            ingredient.setHorse(Double.valueOf(horse.getText().toString()));
            ingredient.setOx(Double.valueOf(ox.getText().toString()));
            ingredient.setCake(Double.valueOf(cake.getText().toString()));
            ingredient.setHusk(Double.valueOf(husk.getText().toString()));
            ingredient.setShell(Double.valueOf(shell.getText().toString()));
            ingredient.setStraw(Double.valueOf(straw.getText().toString()));
            ingredient.setSawdust(Double.valueOf(sawdust.getText().toString()));
            ingredient.setWater(Double.valueOf(water.getText().toString()));
            ingredient.setLeaf(Double.valueOf(leaf.getText().toString()));
            Retrofit retrofit = SingleTopRetrofit.getInstance();
            TechService techService = retrofit.create(TechService.class);
            Call<DataResult<Boolean>> dataResultCall = techService.addIngredient(ingredient);
            dataResultCall.enqueue(new Callback<DataResult<Boolean>>() {
                @Override
                public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
                    DataResult<Boolean> dataResult = response.body();
                    if (dataResult != null && dataResult.getData()) {
                        Toast.makeText(IngredientAddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_ingredient_add);
        findViews();
    }
}