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

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.http.POST;

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
        holder.dataVia.setText(mDataList.get(position).who);
        holder.dateIv.setText(DateUtils.date2String(mDataList.get(position).createdAt.getTime(), AppConstant.DAILY_DATE_FORMAT));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent(v, position);
            }
        });

    }

    private void clickEvent(View v, int position) {
        if (mDataList.get(position).type.equals(AppConstant.DATA_TYPE_WELFARE)) {
            PictureActivity.startActivity(mContext, mDataList.get(position).url, mDataList.get(position).desc);


        } else if (mDataList.get(position).type.equals(AppConstant.DATA_TYPE_ANDROID)) {
            WebContentActivity.jumpToWebView(mContext, v, mDataList.get(position).desc,
                    mDataList.get(position).url);

        } else if (mDataList.get(position).type.equals(AppConstant.DATA_TYPE_IOS)) {
            WebContentActivity.jumpToWebView(mContext, v, mDataList.get(position).desc,
                    mDataList.get(position).url);

        } else if (mDataList.get(position).type.equals(AppConstant.DATA_TYPE_JS)) {
            WebContentActivity.jumpToWebView(mContext, v, mDataList.get(position).desc,
                    mDataList.get(position).url);

        } else if (mDataList.get(position).type.equals(AppConstant.DATA_TYPE_REST_VIDEO)) {
            WebContentActivity.jumpToWebView(mContext, v, mDataList.get(position).desc,
                    mDataList.get(position).url);

        } else if (mDataList.get(position).type.equals(AppConstant.DATA_TYPE_APP)) {
            WebContentActivity.jumpToWebView(mContext, v, mDataList.get(position).desc,
                    mDataList.get(position).url);

        } else if (mDataList.get(position).type.equals(AppConstant.DATA_TYPE_EXTEND_RESOURCES)) {
            WebContentActivity.jumpToWebView(mContext, v, mDataList.get(position).desc,
                    mDataList.get(position).url);

        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setDataList(List<BaseGankData> data) {
        if (data != null) {
            mDataList.addAll(data);
        }
        notifyDataSetChanged();
    }


    class GankViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.data_title_tv)
        TextView dataTitle;
        @Bind(R.id.data_tag_tv)
        TextView dataTag;
        @Bind(R.id.data_via_tv)
        TextView dataVia;
        @Bind(R.id.data_date_tv)
        TextView dateIv;

        public GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
