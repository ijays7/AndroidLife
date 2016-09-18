package com.ijays.androidlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ijays.androidlife.AppConstant;
import com.ijays.androidlife.R;
import com.ijays.androidlife.WebContentActivity;
import com.ijays.androidlife.model.BaseGankData;
import com.ijays.androidlife.mvptest.view.PictureActivity;
import com.ijays.androidlife.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
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
    public void onBindViewHolder(GankViewHolder holder, final int position) {
        holder.dataTitle.setText(mDataList.get(position).desc);
        holder.dataTag.setText(mDataList.get(position).type);
        holder.dataVia.setText(mDataList.get(position).who + " ");
        holder.dateIv.setText(DateUtils.date2String(mDataList.get(position).publishedAt.getTime(), AppConstant.DAILY_DATE_FORMAT));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent(v, position);
            }
        });

    }

    private void clickEvent(View v, int position) {
        switch (mDataList.get(position).type) {
            case AppConstant.DATA_TYPE_WELFARE:
                PictureActivity.startActivity(mContext, mDataList.get(position).url, mDataList.get(position).desc);
                break;
            case AppConstant.DATA_TYPE_ANDROID:
            case AppConstant.DATA_TYPE_IOS:
            case AppConstant.DATA_TYPE_JS:
            case AppConstant.DATA_TYPE_REST_VIDEO:
            case AppConstant.DATA_TYPE_APP:
            case AppConstant.DATA_TYPE_EXTEND_RESOURCES:
            case AppConstant.DATA_TYPE_RECOMMEND:

                WebContentActivity.jumpToWebView(mContext, v, mDataList.get(position).desc,
                        mDataList.get(position).url);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setDataList(List<BaseGankData> data) {
        if (data != null) {
            mDataList.clear();
            mDataList.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addDataList(List<BaseGankData> data) {
        if (data != null) {
            mDataList.addAll(data);
        }
        notifyDataSetChanged();
    }


    class GankViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.data_title_tv)
        TextView dataTitle;
        @BindView(R.id.data_tag_tv)
        TextView dataTag;
        @BindView(R.id.data_via_tv)
        TextView dataVia;
        @BindView(R.id.data_date_tv)
        TextView dateIv;

        public GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
