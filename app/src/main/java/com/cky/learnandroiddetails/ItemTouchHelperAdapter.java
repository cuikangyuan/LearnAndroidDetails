package com.cky.learnandroiddetails;

/**
 * Created by Administrator on 2016/8/30.
 */

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
