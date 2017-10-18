package com.example.codernote.util;

import android.os.Handler;

import com.dd.processbutton.ProcessButton;
import com.dd.processbutton.iml.ActionProcessButton;
import com.example.codernote.bean.User;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 黄林 on 2017/10/12.
 */

public class ProgressGenerator {



    public interface OnCompleteListener{
        public void onComplete(String complete);
        public void onError(String error);
    }


    private OnCompleteListener mListener;
    private int mProgress;

    public ProgressGenerator(OnCompleteListener mListener) {
        this.mListener = mListener;
    }

    public void start(final ProcessButton button,final User user){
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setProgress(mProgress);
                handler.postDelayed(this,generateDelay());
                HttpUtil.login("http://192.168.137.138:9999/user/login", user, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mListener.onError(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                       String result =  response.body().toString();
                        mListener.onComplete(result);
                    }
                });
            }
        },generateDelay());
    }

    private Random random = new Random();

    private int generateDelay(){
        return random.nextInt(1000);
    }
}
