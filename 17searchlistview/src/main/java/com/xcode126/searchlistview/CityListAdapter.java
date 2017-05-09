package com.xcode126.searchlistview;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.xcode126.searchlistview.adapter.CommonAdapter;
import com.xcode126.searchlistview.adapter.ViewHolder;
import com.xcode126.searchlistview.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView的Adapter，关键的是SectionIndexer接口中的两个方法
 * Created by sky on 2017/5/8.
 */

public class CityListAdapter extends CommonAdapter<CityBean> implements SectionIndexer {
    private List<CityBean> allData;
    private List<CityBean> queryData;

    public CityListAdapter(Context context, List<CityBean> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        allData = mDatas;
        queryData = new ArrayList<CityBean>();
    }

    public void queryData(String query) {
        queryData.clear();
        for (CityBean bean : allData) {
            String name = bean.getName();
            if (!TextUtils.isEmpty(name) && name.contains(query)) {
                queryData.add(bean);
            }
        }
        mDatas = queryData;
        notifyDataSetChanged();
    }

    public void resetData() {
        mDatas = allData;
        notifyDataSetChanged();
    }

    @Override
    public void convert(ViewHolder holder, CityBean item, int position) {
        TextView nameTv = holder.getView(R.id.list_item_name);
        TextView headTv = holder.getView(R.id.list_item_head);
        //根据position == getPositionForSection(getSectionForPostion(position))判断某个字母是不是首次出现
        if (position == 0 && getPositionForSection(getSectionForPosition(position)) == -1) {
            headTv.setVisibility(View.VISIBLE);
            headTv.setText("#");
        } else if (position == getPositionForSection(getSectionForPosition(position))) {
            headTv.setVisibility(View.VISIBLE);
            //把section index转化为大写字母
            char c = (char) (getSectionForPosition(position) + 65);
            if (c >= 'A' && c <= 'Z') {
                headTv.setText(String.valueOf(c));
            }
        } else {
            headTv.setVisibility(View.GONE);
        }
        nameTv.setText(item.getName());
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        //根据参数arg0，加上65后得到对应的大写字母
        char c = (char) (sectionIndex + 65);
        //循环遍历ListView中的数据，遇到第一个首字母为上面的就是要找的位置
        for (int i = 0; i < getCount(); i++) {
            if (mDatas.get(i).getFirstLetter() == c) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        //获取该位置的城市名首字母
        char c = (char) (mDatas.get(position).getFirstLetter());
        //如果该字母在A和Z之间，则返回A到Z的索引，从0到25
        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        }
        //如果首字母不是A到Z的字母，则返回26，该类型将会被分类到#下面
        return 26;
    }
}
