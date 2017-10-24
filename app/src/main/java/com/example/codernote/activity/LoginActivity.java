package com.example.codernote.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.codernote.R;
import com.example.codernote.bean.User;
import com.example.codernote.util.HttpUtil;
import com.example.codernote.util.ProgressGenerator;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements ProgressGenerator.OnCompleteListener{

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";


    private EditText editName;
    private EditText editPassword;
    private ActionProcessButton btnSigIn;
    private ProgressGenerator progressGenerator;
    final User user = new User();

    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initActionBar();
        init();
    }

    private void init() {
        editName = (EditText)findViewById(R.id.editName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnSigIn = (ActionProcessButton)findViewById(R.id.btnSignIn);
        progressGenerator = new ProgressGenerator(this,LoginActivity.this);


        btnSigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editName.getText().toString();
                String password = editPassword.getText().toString();
                user.setUserName(username);
                user.setPassWord(password);

                progressGenerator.start(btnSigIn,user,editName,editPassword);
                btnSigIn.setEnabled(false);
                editName.setEnabled(false);
                editPassword.setEnabled(false);
            }
        });

        register = (TextView) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
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
        tvTitle.setText("用户登陆");
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(18);
        tvTitle.setGravity(Gravity.CENTER);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tvTitle.setLayoutParams(params);
        actionBar.setCustomView(tvTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComplete(String complete) {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String error) {
        Log.d(error,"登陆失败");
    }
}
