package com.example.farming;

import android.content.Intent;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farming.constants.Constants;
import com.example.farming.constants.HttpStatus;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.UserInfo;
import com.example.farming.util.SingleTopRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText2;
    private EditText editText;
    private Button logIn;
    private int identity;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-07 17:13:07 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        editText2 = (EditText) findViewById(R.id.editText2);
        editText = (EditText) findViewById(R.id.editText);
        logIn = (Button) findViewById(R.id.log_in_);

        logIn.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-07 17:13:07 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == logIn) {
            // Handle clicks for logIn
            final String name = editText.getText().toString();
            final String password = editText2.getText().toString();
            UserInfo userInfo = new UserInfo();
            userInfo.setIdentity((byte) identity);
            userInfo.setuName(name);
            userInfo.setuPassword(password);
            Retrofit retrofit = SingleTopRetrofit.getInstance();
            LoginService loginService = retrofit.create(LoginService.class);
            Call<DataResult<Boolean>> call = loginService.register(userInfo);
            call.enqueue(new Callback<DataResult<Boolean>>() {
                @Override
                public void onResponse(Call<DataResult<Boolean>> call, Response<DataResult<Boolean>> response) {
                    DataResult<Boolean> result = response.body();
                    if (result == null) {
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (result.getStatus() == HttpStatus.HTTP_STATUS_AUTHORITY) {
                        Toast.makeText(RegisterActivity.this, "权限不够", Toast.LENGTH_SHORT).show();
                    } else {
                        if (result.getStatus() == HttpStatus.HTTP_STATUS_OK && result.getData()) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("userName", name);
                            intent.putExtra("pwd", password);
                            startActivity(intent);
                        }
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
        setContentView(R.layout.activity_register);
        identity = getIntent().getIntExtra("identity", 0);
        findViews();
    }

}
