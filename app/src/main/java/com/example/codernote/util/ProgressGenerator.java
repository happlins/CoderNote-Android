package com.example.codernote.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.dd.processbutton.ProcessButton;
import com.dd.processbutton.iml.ActionProcessButton;
import com.example.codernote.bean.LoginResponseModel;
import com.example.codernote.bean.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 黄林 on 2017/10/12.
 */

public class ProgressGenerator {


    public interface OnCompleteListener {
        public void onComplete(String complete);

        public void onError(String error);
    }


    private OnCompleteListener mListener;
    private Context context;


    public ProgressGenerator(OnCompleteListener mListener, Context context) {
        this.mListener = mListener;
        this.context = context;
    }

    public void start(final ProcessButton button, final User user, final EditText username, final EditText password, final TextView register) {
        changeUI(button,10);
        HttpUtil.login("http://192.168.191.5:9999/user/login", user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string().toString();
                Gson gson = new Gson();
                LoginResponseModel responseModel = gson.fromJson(result,LoginResponseModel.class);
                if (responseModel.getCode() == 10002){
                    changeUI(button,100);
                    mListener.onComplete(responseModel.getMessage());
                }else{
                    changUIS(button,-1,username,password,register);
                    mListener.onError(responseModel.getMessage());
                }
            }
        });
    }
    public void changeUI(final ProcessButton button,final int mProgress){
        final Handler handler = new Handler(context.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setProgress(mProgress);
            }
        },1);
    }

    public void changUIS(final ProcessButton button,final int mProgress,final EditText username,final EditText password,final TextView register){
        final Handler handler = new Handler(context.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setProgress(mProgress);
                button.setEnabled(true);
                username.setEnabled(true);
                password.setEnabled(true);
                register.setEnabled(true);
                button.setProgress(0);
            }
        },1);
    }
}
