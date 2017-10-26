package com.example.codernote.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.codernote.R;
import com.example.codernote.adapter.TimelineAdapter;
import com.example.codernote.bean.Note;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private ListView listView;

    private TimelineAdapter timelineAdapter;

    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private DrawerArrowDrawable drawerArrow;

    private FloatingActionButton fab;

    private ImageButton login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initActionBar();
        initDrawerLayout();
    }

    private void initActionBar() {
        //获得导航栏
        actionBar = getSupportActionBar();
        actionBar.show();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(drawerArrow);

        actionBar.setDisplayShowCustomEnabled(true);
        TextView tvTitle = new TextView(this);
        tvTitle.setText("图灵笔记");
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setTextSize(18);
        tvTitle.setGravity(Gravity.CENTER);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tvTitle.setLayoutParams(params);
        actionBar.setCustomView(tvTitle);
    }


    private void init() {
        listView = (ListView) this.findViewById(R.id.listview);
        listView.setDividerHeight(0);
        timelineAdapter = new TimelineAdapter(this, getData());
        listView.setAdapter(timelineAdapter);
        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(listView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateActivity.class);
                startActivity(intent);
            }
        });
        login = (ImageButton)findViewById(R.id.user_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) super.findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                drawerArrow, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(toggle);
    }

    private void toggleLeftSliding() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleLeftSliding();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {

                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    public List<Note> getData() {
        List<Note> list = new ArrayList<>();

        return list;
    }
}
