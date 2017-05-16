package com.xcode126.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sky on 2017/5/15.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> datas;

    public MyAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_text.setText(datas.get(position));
        holder.iv_icon.setBackgroundResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * item点击事件监听接口
     */
    public interface onItemClickListener {
        void onItemClick(View view, int postion);
    }

    public onItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * item长按事件
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int postion);
    }

    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 图片点击事件监听接口
     */
    public interface onImageClickListener {
        void onImageClick(int position);
    }

    public onImageClickListener onImageClickListener;

    public void setOnImageClickListener(MyAdapter.onImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_text;
        public ImageView iv_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.tv_title);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_image);

            itemView.setOnClickListener(new itemViewOnclick());
            iv_icon.setOnClickListener(new ivIconOnclick());
            itemView.setOnLongClickListener(new itemViewOnLongClick());
        }

        /**
         * item点击事件监听
         */
        private class itemViewOnclick implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, getLayoutPosition());
                }
            }
        }

        /**
         * 图片点击事件监听
         */
        private class ivIconOnclick implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                if (onImageClickListener != null) {
                    onImageClickListener.onImageClick(getLayoutPosition());
                }
            }
        }

        /**
         * item长按事件监听
         */
        private class itemViewOnLongClick implements View.OnLongClickListener {

            @Override
            public boolean onLongClick(View v) {

                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(v, getLayoutPosition());
                }
                return true;
            }
        }
    }

    /**
     * 添加数据
     *
     * @param position
     * @param content
     */
    public void addData(int position, String content) {
        datas.add(position, content);
        notifyItemInserted(position);
    }

    /**
     * 移除数据
     *
     * @param position
     */
    public void removeData(int position) {
        datas.remove(position);
//        datas.clear();
        notifyItemRemoved(position);
    }


}




