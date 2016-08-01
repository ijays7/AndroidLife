package com.ijays.androidlife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.ijays.androidlife.R;
import com.ijays.androidlife.mvptest.view.PictureActivity;
import com.ijays.androidlife.utils.GlideUtils;

import java.util.List;

/**
 * Created by ijaysdev on 16/7/19.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.DefineViewHolder> {
    private List<String> list;
    private Context mContext;

    public ListAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onBindViewHolder(DefineViewHolder viewHolder, final int position) {

        GlideUtils.display(viewHolder.imageView, list.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureActivity.startActivity(mContext, list.get(position), "Gank");
            }
        });
    }

    public void setData(List<String> data) {
        this.list = data;
        notifyDataSetChanged();
    }

    @Override
    public DefineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new DefineViewHolder(view);
    }

    static class DefineViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public DefineViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
