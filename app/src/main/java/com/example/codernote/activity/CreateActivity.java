package com.example.codernote.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codernote.R;
import com.example.codernote.bean.Note;
import com.example.codernote.bean.ResponseCode;
import com.example.codernote.bean.UpdateOperationResponse;
import com.example.codernote.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreateActivity extends AppCompatActivity {

    private Button noteSuccess;
    private EditText noteTitle;
    private TextView noteTime;
    private EditText noteContent;
    final Context context = CreateActivity.this;
    private Note seeNote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);  initActionBar();
        init();
        Intent intent = new Intent();
        Gson gson = new Gson();
        seeNote = gson.fromJson(intent.getStringExtra("note"),Note.class);
        if (seeNote != null){
            setInit();
        }
    }

    private void setInit() {
        noteTitle.setText(seeNote.getTitle());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        noteTime.setText(df.format(seeNote.getGmtCreate()));
        noteContent.setText(seeNote.getContent());
    }

    private void init() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        final Date date = new Date();
        noteSuccess = (Button)findViewById(R.id.note_success);
        noteTitle = (EditText)findViewById(R.id.note_title);
        noteTime = (TextView)findViewById(R.id.note_time);
        noteTime.setText(df.format(date));
        noteContent = (EditText)findViewById(R.id.note_content);



        noteSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if ("".equals(noteContent.getText())){
                   Toast.makeText(CreateActivity.this,"内容不能为空！",Toast.LENGTH_LONG).show();
               }else {
                   Note note = new Note();
                   note.setTitle(noteTitle.getText().toString());
                   note.setGmtCreate(date);
                   note.setContent(noteContent.getText().toString());

                   HttpUtil.login("http://192.168.191.5:9999/note/new", note, new Callback() {
                       @Override
                       public void onFailure(Call call, IOException e) {

                       }

                       @Override
                       public void onResponse(Call call, Response response) throws IOException {
                           Gson gson = new Gson();
                           String str = response.body().string().toString();
                           final UpdateOperationResponse update = gson.fromJson(str,UpdateOperationResponse.class);
                           if (ResponseCode.SUCCESS_SUBMIT_NOTE.getCode() == update.getCode()){
                               final Handler handler = new Handler(context.getMainLooper());
                               handler.postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(CreateActivity.this, update.getMessage(), Toast.LENGTH_LONG).show();
                                   }
                               }, 1);
                               Note dataNote = new Note();

                               Intent intent = new Intent(CreateActivity.this,MainActivity.class);
                               intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                               startActivity(intent);
                               finish();
                           }else if (update.getCode() == ResponseCode.ERROR_SUBMIT_NOTE.getCode()){
                               final Handler handler = new Handler(context.getMainLooper());
                               handler.postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       Toast.makeText(CreateActivity.this, update.getMessage(), Toast.LENGTH_LONG).show();
                                   }
                               }, 1);
                           }
                       }
                   });
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
        tvTitle.setText("新建/编辑 笔记");
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
                Intent intent = new Intent(CreateActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
