package com.xunix.android_chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.LinkedList;

public class CustomAdapter extends BaseAdapter {

	private LinkedList<Bean> beans = null;
	private LayoutInflater li;
	public final int ITEM_TYPES = 2, MyMessage = 1,  YourMessage=0;

	public CustomAdapter(Context context, LinkedList<Bean> beans) {
		// TODO Auto-generated constructor stub
		this.beans = beans;
		li = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return beans.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return beans.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return ITEM_TYPES;
	}

    @Override
    public int getItemViewType(int position) {
        return beans.get(position).getId();
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
        TextView time;
        TextView message;
        ImageView portrait;
        ImageView image;
        Bean bean = beans.get(position);
        int type = getItemViewType(position);
        View row = convertView;


        switch (type) {
            case MyMessage:
                row = li.inflate(R.layout.listview_item_right_layout, parent, false);
                break;
            case YourMessage:
                row = li.inflate(R.layout.listview_item_left_layout, parent, false);
        }
        time = (TextView) row.findViewById(R.id.Time);
        message = (TextView)row.findViewById(R.id.Msg);
        portrait = (ImageView) row.findViewById(R.id.Img);
        image=(ImageView)row.findViewById(R.id.Image);



        //set the time
        if(position==0) {   //if time is close to last one ,hide it
            time.setText(DateFomats.getStringTime(bean.getTime()));
        }else if(bean.getTime().getTime()-beans.get(position-1).getTime().getTime()>=30000){
            time.setText(DateFomats.getStringTime(bean.getTime()));
        }else{
            time.setTextSize(0);
        }
        //set the avatar
        if(bean.getPortraitURI()==null) {
            portrait.setImageResource(bean.getPortrait());
        }else{
            portrait.setImageURI(bean.getPortraitURI());
        }
        //set the image
        if(bean.getImageUri()!=null){
            image.setImageURI(bean.getImageUri());
        }
        if(bean.gettMessage()!=null){
            message.setText(bean.gettMessage());
        }

        if(bean.getFiles()!=null){
            image.setImageResource(R.drawable.more_file);
            message.setTextColor(0xff888888);
            message.setText(bean.getFiles().getName());
        }

	    return row;

	}

    public void sendMessage(Bean bean){
        beans.add(bean);
        notifyDataSetChanged();
    }



}
