package com.example.farming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class IngredientListActivity extends AppCompatActivity {
    private ImageView indredientAdd;
    private RecyclerView ingredientRecyclerview;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-08 20:52:49 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        indredientAdd = (ImageView)findViewById( R.id.indredient_add );
        ingredientRecyclerview = (RecyclerView)findViewById( R.id.ingredient_recyclerview );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_list);
    }
}
