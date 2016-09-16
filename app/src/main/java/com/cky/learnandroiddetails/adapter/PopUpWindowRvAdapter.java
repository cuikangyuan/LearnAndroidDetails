package com.cky.learnandroiddetails.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.model.PopUpWindowItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuikangyuan on 16/9/14.
 */
public class PopUpWindowRvAdapter extends RecyclerView.Adapter<PopUpWindowRvAdapter.PopUpWindowRvViewHolder> {


    private Context mContext;

    private List<PopUpWindowItem> data = new ArrayList<>();

    private boolean[] checkStates;

    private static OnItemClickListener onItemClickListener;

    public PopUpWindowRvAdapter(Context mContext, List<PopUpWindowItem> data) {
        this.mContext = mContext;
        this.data = data;
        checkStates = new boolean[data.size()];
    }

    @Override
    public PopUpWindowRvAdapter.PopUpWindowRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pop_window_item, parent, false);

        return new PopUpWindowRvViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PopUpWindowRvAdapter.PopUpWindowRvViewHolder holder, final int position) {

        PopUpWindowItem item = data.get(position);

        holder.mNameTv.setText(item.getName());
        /*
        if (checkStates == null) {
            checkStates = new Boolean[data.size()];
        } else {
            holder.mNameCb.setChecked(checkStates[position]);

            if (holder.mNameCb.isChecked()) {
                holder.mNameEt.setVisibility(View.VISIBLE);
            } else {
                holder.mNameEt.setVisibility(View.GONE);
            }
        }
        */
        if (checkStates == null) {
            checkStates = new boolean[data.size()];
        } else {

        }

        holder.mNameCb.setChecked(checkStates[position]);

        if (holder.mNameCb.isChecked()) {
            holder.mNameEt.setVisibility(View.VISIBLE);
        } else {
            holder.mNameEt.setVisibility(View.GONE);
        }

        holder.mNameCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.mNameEt.setVisibility(View.VISIBLE);
                    checkStates[position] = true;
                } else {
                    holder.mNameEt.setVisibility(View.GONE);
                    checkStates[position] = false;
                }
            }
        });
    }

    public void setData(List<PopUpWindowItem> data) {

        this.data = data;
    }

    public List<PopUpWindowItem> getData() {

        return this.data;
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        } else {
            return data.size();
        }
    }

    public static class PopUpWindowRvViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTv;
        CheckBox mNameCb;
        EditText mNameEt;

        public PopUpWindowRvViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.item_name_tv);
            mNameCb = (CheckBox) itemView.findViewById(R.id.item_name_cb);
            mNameEt = (EditText) itemView.findViewById(R.id.item_name_et);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
