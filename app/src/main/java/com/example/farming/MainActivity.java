/*史长顺*/
package com.example.farming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView textView;
    private Button button;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-07 17:22:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        button2 = (Button)findViewById( R.id.button2 );
        button3 = (Button)findViewById( R.id.button3 );
        button4 = (Button)findViewById( R.id.button4 );
        textView = (TextView)findViewById( R.id.textView );
        button = (Button)findViewById( R.id.button );

        button2.setOnClickListener( this );
        button3.setOnClickListener( this );
        button4.setOnClickListener( this );
        button.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-07 17:22:43 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        int identity = 0;
        if ( v == button2 ) {
            // Handle clicks for 市场人员
            identity = 3;
        } else if ( v == button3 ) {
            // Handle clicks for 技术员
            identity = 2;
        } else if ( v == button4 ) {
            // Handle clicks for 游客
            identity = 4;
        } else if ( v == button ) {
            // Handle clicks for admin
            identity = 1;
        }
        if (identity == 4) {
            Toast.makeText(this, "游客不用注册，请直接使用账号明guest登陆", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            intent.putExtra("identity", identity);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

}
