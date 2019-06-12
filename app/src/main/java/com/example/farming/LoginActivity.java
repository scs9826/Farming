/*史长顺*/
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

import com.example.farming.constants.CacheUtils;
import com.example.farming.constants.Constants;
import com.example.farming.constants.HttpStatus;
import com.example.farming.entity.DataResult;
import com.example.farming.entity.UserInfo;
import com.example.farming.http.LoginService;
import com.example.farming.util.SingleTopRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        logIn = (Button) findViewById(R.id.log_in);
        register = (Button) findViewById(R.id.register);
        guideline = (Guideline) findViewById(R.id.guideline);

        logIn.setOnClickListener(this);
        register.setOnClickListener(this);
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
            String name = editText.getText().toString();
            String password = editText2.getText().toString();
            Retrofit retrofit = SingleTopRetrofit.getInstance();
            final LoginService service = retrofit.create(LoginService.class);
            final UserInfo userInfo = new UserInfo();
            userInfo.setuName(name);
            userInfo.setuPassword(password);
            Call<DataResult<UserInfo>> call = service.login(userInfo);
            call.enqueue(new Callback<DataResult<UserInfo>>() {
                @Override
                public void onResponse(Call<DataResult<UserInfo>> call, Response<DataResult<UserInfo>> response) {
                    DataResult<UserInfo> dataResult = response.body();
                    if (dataResult == null || dataResult.getStatus() == HttpStatus.HTTP_STATUS_AUTHORITY) {
                        Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    byte byt = dataResult.getData().getIdentity();
                    UserInfo i = dataResult.getData();
                    switch (byt) {
                        default:
                            Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                            break;
                        case Constants.ADMIN:
                            Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                            startActivity(intent);
                            CacheUtils.putUserInfo(i, LoginActivity.this);
                            finish();
                            break;
                        case Constants.TECH:
                            Intent intent1 = new Intent(LoginActivity.this, TechMainActivity.class);
                            startActivity(intent1);
                            CacheUtils.putUserInfo(i, LoginActivity.this);
                            finish();
                            break;
                        case Constants.MARKET:
                            Intent intent2 = new Intent(LoginActivity.this, MarketMainActivity.class);
                            startActivity(intent2);
                            CacheUtils.putUserInfo(i, LoginActivity.this);
                            finish();
                            break;
                        case Constants.GUEST:
                            Intent intent3 = new Intent(LoginActivity.this, GuestMainActivity.class);
                            startActivity(intent3);
                            CacheUtils.putUserInfo(i, LoginActivity.this);
                            finish();
                            break;

                    }
                }

                @Override
                public void onFailure(Call<DataResult<UserInfo>> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (v == register) {
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
        initView();
    }

    private void initView() {
        editText.setText(getIntent().getStringExtra("userName") == null ? "" : getIntent().getStringExtra("userName"));
        editText2.setText(getIntent().getStringExtra("pwd") == null ? "" : getIntent().getStringExtra("pwd"));
//        editText.setText("guest");
//        editText2.setText("");
    }

}
