package com.example.codernote.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codernote.R;
import com.example.codernote.bean.RegisterResponseModel;
import com.example.codernote.bean.RegisterUser;
import com.example.codernote.bean.ResponseCode;
import com.example.codernote.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ResponseCache;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassWord;
    private EditText editCPassWord;
    private EditText editEmail;
    private Button registerBtn;
    final Context context = RegisterActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initActionBar();
        init();
    }

    private void init() {
        editUserName = (EditText) findViewById(R.id.register_username);
        editPassWord = (EditText) findViewById(R.id.register_password);
        editCPassWord = (EditText) findViewById(R.id.register_c_password);
        editEmail = (EditText) findViewById(R.id.register_email);
        registerBtn = (Button) findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editUserName.getText().toString();
                String password = editPassWord.getText().toString();
                String cpassword = editCPassWord.getText().toString();
                String email = editEmail.getText().toString();

                if (password.equals(cpassword)) {
                    RegisterUser registerUser = new RegisterUser();
                    registerUser.setUserName(username);
                    registerUser.setPassWord(password);


                    HttpUtil.login("http://192.168.191.5:9999/user/register", registerUser, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("失败", "失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Gson gson = new Gson();
                            final RegisterResponseModel registerResponseModel = gson.fromJson(response.body().string().toString(), RegisterResponseModel.class);
                            if (registerResponseModel.getCode() == ResponseCode.SUCCESS_REGISTER.getCode()) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                final Handler handler = new Handler(context.getMainLooper());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, registerResponseModel.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }, 1);
                                startActivity(intent);
                                finish();
                            } else if (registerResponseModel.getCode() == ResponseCode.ERROR_USERNAME_EXIST.getCode()) {
                                final Handler handler = new Handler(context.getMainLooper());
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                       Toast.makeText(RegisterActivity.this, registerResponseModel.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }, 1);
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码不相同，请检查密码", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initActionBar() {
        //获得导航栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        actionBar.setDisplayShowCustomEnabled(true);
        TextView tvTitle = new TextView(this);
        tvTitle.setText("注册用户");
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(18);
        tvTitle.setGravity(Gravity.CENTER);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tvTitle.setLayoutParams(params);
        actionBar.setCustomView(tvTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
