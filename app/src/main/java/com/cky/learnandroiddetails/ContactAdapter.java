package com.cky.learnandroiddetails;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Contact> mContacts;

    private Context mContext;

    private List<View> headers = new ArrayList<>();

    private List<View> footers = new ArrayList<>();

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_FOOTER = 2;
    private static final int TYPE_GENERIC = 3;

    private static OnItemClickListener onItemClickListener;

    private OnItemLongClickListener onItemLongClickListener;

    private final onStartDragListener onStartDragListener;


    public ContactAdapter(Context context, List<Contact> contacts, onStartDragListener onStartDragListener) {

        this.mContacts = contacts;
        this.mContext = context;
        this.onStartDragListener = onStartDragListener;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View contactView = layoutInflater.inflate(R.layout.item_contact, parent, false);

        return new ViewHolder(contactView);
        */
        if (viewType == TYPE_GENERIC) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_without_btn, parent, false);
            return new ViewHolder(view);
        } else {
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new HeaderFooterViewHolder(frameLayout);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Contact contact = mContacts.get(position);
        //holder.tvContactName.setText(contact.getmName());
        //holder.btnMessage.setText(contact.getmOnline()?"OnLine" : "OffLine");
        if (position < headers.size()) {
            View v = headers.get(position);
            prepareHeaderFooter((HeaderFooterViewHolder)holder, v);
        } else if (position >= headers.size() + mContacts.size()) {
            View v = footers.get(position - headers.size() - mContacts.size());
            prepareHeaderFooter((HeaderFooterViewHolder)holder, v);
        } else {
            Contact contact = mContacts.get(position - headers.size());
            prepareGeneric((ViewHolder)holder, contact);
        }
    }

    public void addHeader(View header) {
        if (!headers.contains(header)) {
            headers.add(header);
            notifyItemInserted(headers.size() - 1);
        }
    }

    public void removeHeader(View header) {
        if (headers.contains(header)) {
            notifyItemRemoved(headers.indexOf(header));
            headers.remove(header);
            if (header.getParent() != null) {
                ((ViewGroup)header.getParent()).removeView(header);
            }

        }

    }

    public void addFooter(View footer) {
        if (!footers.contains(footer)) {
            footers.add(footer);
            notifyItemInserted(headers.size() + mContacts.size() + footers.size() - 1);
        }
    }

    public void removeFooter(View footer) {
        if (headers.contains(footer)) {
            notifyItemRemoved(footers.indexOf(footer));
            headers.remove(footer);
            if (footer.getParent() != null) {
                ((ViewGroup)footer.getParent()).removeView(footer);
            }

        }
    }

    private void prepareHeaderFooter(HeaderFooterViewHolder holder, View view) {
        holder.base.removeAllViews();
        holder.base.addView(view);
    }

    private void prepareGeneric(final ViewHolder holder, Contact contact) {
        holder.tvContactName.setText(contact.getmName());
        //holder.btnMessage.setText(contact.getmOnline()?"OnLine" : "OffLine");
        holder.ivHandle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    if (onStartDragListener != null ) {
                        onStartDragListener.onStartDrag(holder);
                    }
                }
                return false;
            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if (position < headers.size()) {
            return TYPE_HEADER;
        } else if (position >= headers.size() + mContacts.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_GENERIC;
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size() + headers.size() + footers.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mContacts, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mContacts.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvContactName;
        //public Button btnMessage;
        public ImageView ivHandle;

        public ViewHolder(View itemView) {
            super(itemView);

            tvContactName = (TextView)itemView.findViewById(R.id.tv_contact_name);
            //btnMessage = (Button)itemView.findViewById(R.id.btn_message);
            ivHandle = (ImageView) itemView.findViewById(R.id.iv_handle);

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

    public static class HeaderFooterViewHolder extends RecyclerView.ViewHolder {
        FrameLayout base;
        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
            this.base = (FrameLayout)itemView;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
