package com.example.codernote.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.codernote.R;
import com.example.codernote.bean.Note;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;


public class SeeNoteActivity extends AppCompatActivity {

    private TextView seeNoteTitle;
    private TextView seeNoteTime;
    private TextView seeNoteContent;
    private Button seeNoteEdit;
    private Note note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_note);

        Intent intent = new Intent();
        Gson gson = new Gson();
        note =  gson.fromJson(intent.getStringExtra("note"),Note.class);
        initActionBar();
        init();
    }

    private void init() {
        seeNoteTitle = (TextView) findViewById(R.id.see_note_title);
        seeNoteTime = (TextView) findViewById(R.id.see_note_time);
        seeNoteContent = (TextView) findViewById(R.id.see_note_content);
        seeNoteEdit = (Button) findViewById(R.id.see_note_edit);

        seeNoteTitle.setText(note.getTitle());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        seeNoteTime.setText(df.format(note.getGmtCreate()));
        seeNoteContent.setText(note.getContent());

        seeNoteEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeeNoteActivity.this,CreateActivity.class);
                intent.putExtra("note",new Gson().toJson(note));
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
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
        tvTitle.setText("查看笔记");
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
                Intent intent = new Intent(SeeNoteActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
