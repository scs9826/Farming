package com.example.farming;

import android.content.Intent;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView3;
    private TextView textView4;
    private EditText editText2;
    private TextView textView;
    private EditText editText;
    private Button logIn;
    private Button register;
    private Guideline guideline;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-07 17:13:07 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        textView3 = (TextView)findViewById( R.id.textView3 );
        textView4 = (TextView)findViewById( R.id.textView4 );
        editText2 = (EditText)findViewById( R.id.editText2 );
        textView = (TextView)findViewById( R.id.textView );
        editText = (EditText)findViewById( R.id.editText );
        logIn = (Button)findViewById( R.id.log_in );
        register = (Button)findViewById( R.id.register );
        guideline = (Guideline)findViewById( R.id.guideline );

        logIn.setOnClickListener( this );
        register.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-07 17:13:07 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == logIn ) {
            // Handle clicks for logIn
            String name = editText.getText().toString();
            String password = editText2.getText().toString();
        } else if ( v == register ) {
            // Handle clicks for register
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

}
