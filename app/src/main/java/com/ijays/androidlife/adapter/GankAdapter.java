package com.ijays.androidlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ijays.androidlife.R;
import com.ijays.androidlife.model.BaseGankData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ijaysdev on 16/7/25.
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankViewHolder> {
    private Context mContext;
    private List<BaseGankData> mDataList;
    private LayoutInflater mInflater;

    public GankAdapter(Context context, List<BaseGankData> list) {
        this.mContext = context;
        this.mDataList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_gank_layout, parent, false);
        return new GankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GankViewHolder holder, int position) {
        holder.dataTitle.setText(mDataList.get(position).desc);
        holder.dataTag.setText(mDataList.get(position).type);
        holder.dataVia.setText(mDataList.get(position).who);
        Log.e("SONGJIE", mDataList.get(position).desc);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setDataList(List<BaseGankData> data) {
        this.mDataList = data;
        notifyDataSetChanged();
    }


    class GankViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.data_title_tv)
        TextView dataTitle;
        @Bind(R.id.data_tag_tv)
        TextView dataTag;
        @Bind(R.id.data_via_tv)
        TextView dataVia;

        public GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
