package com.jinribeidou.material_design.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jason on 2019/11/6.
 */
public class DrawerListAdapter extends BaseAdapter {

    private List<String> list;//文字的列表
    private Context mContext;

    public DrawerListAdapter(Context context, List<String> strings) {
        mContext = context;
        list = strings;
    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        private TextView name;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        return null;
    }
}
