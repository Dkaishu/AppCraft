package com.tincher.appcraft.main.customviewdemo.multi_select;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tincher.appcraft.R;

import java.util.List;


/**
 * Created by dks on 2017/12/4.
 */

public class MultiSelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MultiSelectAdapter";
    private List<MultiBase> list;
//    private List<List<MultiBase>> list;
    private Context mContext;

    public static enum ITEM_TYPE {
        ITEM_TYPE_TYPE,
        ITEM_TYPE_TYPE3
    }
    public MultiSelectAdapter(Context mContext,List<MultiBase> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_multi_select, parent, false);
        return new TypeVH(view, viewType);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (list == null) {
            return;
        }
        MultiBase MultiBase = list.get(position);
        if (MultiBase instanceof MultiListType.Type1){
            for (int i = 0; i < 12; i++) {
                Button button = new Button(mContext);
                button.setText(Math.pow(12, i) + "15615");
                ((TypeVH)holder).selectViewGroup.addView(button);
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TypeVH extends RecyclerView.ViewHolder {
        MultiSelectViewGroup selectViewGroup;
        TextView tvSubtitle;
        public TypeVH(View itemView, int ViewType) {
            super(itemView);
            tvSubtitle=itemView.findViewById(R.id.tv_subtitle);
            selectViewGroup = itemView.findViewById(R.id.vg_container);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public static class Type3VH extends RecyclerView.ViewHolder {
        MultiSelectViewGroup selectViewGroup;
        TextView tvSubtitle;
        public Type3VH(View itemView, int ViewType) {
            super(itemView);
            tvSubtitle=itemView.findViewById(R.id.tv_subtitle);
            selectViewGroup = itemView.findViewById(R.id.vg_container);
        }
    }
}
