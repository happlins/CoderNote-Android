package com.example.codernote.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.codernote.R;
import com.example.codernote.activity.MainActivity;
import com.example.codernote.activity.SeeNoteActivity;
import com.example.codernote.bean.Note;
import com.google.gson.Gson;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * Created by 黄林 on 2017/10/11.
 */

public class TimelineAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private List<Note> list;
    private LayoutInflater inflater;
    int index = 0;

    public TimelineAdapter(Context context, List<Note> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        index = position;
        ViewHoler viewHoler = null;
        if (convertView == null){
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.listview_item,null);
            viewHoler = new TimelineAdapter.ViewHoler();

            viewHoler.title = (TextView) convertView.findViewById(R.id.title);
            viewHoler.year = (TextView) convertView.findViewById(R.id.show_time);
            viewHoler.hour = (TextView) convertView.findViewById(R.id.show_hour);
            viewHoler.note = (RelativeLayout) convertView.findViewById(R.id.note_relative);
            convertView.setTag(viewHoler);
        }else {
            viewHoler = (ViewHoler)convertView.getTag();
        }

        viewHoler.title.setText(list.get(position).getTitle());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] time = df.format(list.get(position).getGmtCreate()).split(" ");
        viewHoler.year.setText(time[0]);
        viewHoler.hour.setText(time[1]);
        viewHoler.note.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, SeeNoteActivity.class);
        intent.putExtra("note",new Gson().toJson(list.get(index)));
        context.startActivity(intent);
    }


    static class ViewHoler{
        public TextView year;
        public TextView hour;
        public TextView title;
        public RelativeLayout note;
    }
}
