package com.xcode126.sidelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sky on 2017/5/4.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<TownBean> list;

    public ExpandableListAdapter(Context context, List<TownBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvCity = (TextView) convertView.findViewById(R.id.tvCity);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tvCity.setText(list.get(groupPosition).getTownName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_source, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvDataSourName = (TextView) convertView.findViewById(R.id.tvDataSourName);
            childViewHolder.tvSubCateGoryName = (TextView) convertView.findViewById(R.id.tvSubCateGoryName);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvDataSourName.setText(list.get(groupPosition).getList().get(childPosition).getFactoryName());
        childViewHolder.tvSubCateGoryName.setText(list.get(groupPosition).getList().get(childPosition).getType());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class GroupViewHolder {
        TextView tvCity;
    }

    private class ChildViewHolder {
        TextView tvDataSourName;
        TextView tvSubCateGoryName;
    }
}
