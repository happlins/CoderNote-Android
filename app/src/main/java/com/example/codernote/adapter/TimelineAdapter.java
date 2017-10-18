package com.example.codernote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.codernote.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 黄林 on 2017/10/11.
 */

public class TimelineAdapter extends BaseAdapter{

    private Context context;
    private List<Map<String,Object>> list;
    private LayoutInflater inflater;

    public TimelineAdapter(Context context, List<Map<String, Object>> list) {
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
        ViewHoler viewHoler = null;
        if (convertView == null){
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.listview_item,null);
            viewHoler = new ViewHoler();

            viewHoler.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(viewHoler);
        }else {
            viewHoler = (ViewHoler)convertView.getTag();
        }

        String titleStr = list.get(position).get("title").toString();
        viewHoler.title.setText(titleStr);


        return convertView;
    }


    static class ViewHoler{
        public TextView year;
        public TextView month;
        public TextView title;
    }
}
