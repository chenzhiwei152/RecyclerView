package com.example.chenzhiwei.recycleview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by chen.zhiwei on 2017-5-10.
 */

public class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> mContentList;
    Random mRandom = new Random();
    private static final int NORMAL_TYPE = 0;
    private static final int CHECK_TYPE = 1;
    private int[] iv_collec = {R.mipmap.e_bw, R.mipmap.e_re,R.mipmap.iv_1,R.mipmap.iv_2};

    public RVAdapter(ArrayList<String> mContentList) {
        this.mContentList = mContentList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new NormalViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_iv, parent, false);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).cardView.setCardBackgroundColor(getRandomColor());
            ((MyViewHolder) holder).iv.setImageResource(getRandomImage());
            holder.itemView.getLayoutParams().height = getRandomHeight(300, 400);//产生随机高度，看上去像瀑布
        } else if (holder instanceof NormalViewHolder) {
            ((NormalViewHolder) holder).cardView.setCardBackgroundColor(getRandomColor());
            ((NormalViewHolder) holder).textView.setText(mContentList.get(position));
            holder.itemView.getLayoutParams().height = getRandomHeight(200, 300);//产生随机高度，看上去像瀑布
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 4 == 0) {
            return CHECK_TYPE;
        }
        return NORMAL_TYPE;
    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }

    //ViewHolder继承自RecyclerView.ViewHolder  子View拿到方便后面访问
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_name);
            cardView = (CardView) itemView.findViewById(R.id.cv_bg);
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_name);
            cardView = (CardView) itemView.findViewById(R.id.cv_bg);
        }
    }

    //产生随机颜色
    private int getRandomColor() {
        return (0xff000000 | mRandom.nextInt(0x00ffffff));
    }

    //产生随机高度
    private int getRandomHeight(int min, int max) {
        return (mRandom.nextInt(max - min) + min);
    }

    //添加一个子项
    public void addData(int position) {
        mContentList.add(position, "Insert One");
        notifyItemInserted(position);//不调用这个没有动画效果
    }

    //删除一个子项
    public void removeData(int position) {
        mContentList.remove(position);
        notifyItemRemoved(position);//不调用这个没有动画效果
    }

    //获取随机图片
    public int getRandomImage() {
        return iv_collec[mRandom.nextInt(iv_collec.length)];
    }
}
