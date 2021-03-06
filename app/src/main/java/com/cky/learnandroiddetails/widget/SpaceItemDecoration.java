package com.cky.learnandroiddetails.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cuikangyuan on 16/9/24.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 复写onDraw方法，从而达到在每隔条目的被绘制之前（或之后），让他先帮我们画上去几根线吧
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //先初始化一个Paint来简单指定一下Canvas的颜色，就黑的吧！
        Paint paint = new Paint();
        paint.setColor(parent.getContext().getResources().getColor(android.R.color.holo_orange_dark));

        //获得RecyclerView中总条目数量
        int childCount = parent.getChildCount();

        //遍历一下
        for (int i = 0; i < childCount; i++) {

            //获得子View，也就是一个条目的View，准备给他画上边框
            View childView = parent.getChildAt(i);

            //先获得子View的长宽，以及在屏幕上的位置，方便我们得到边框的具体坐标
            float x = childView.getX();
            float y = childView.getY();
            int width = childView.getWidth();
            int height = childView.getHeight();

            //根据这些点画条目的四周的线
            //上
            //c.drawLine(x, y, x + width, y, paint);
            //左
            //c.drawLine(x, y, x, y + height, paint);
            //右
            //c.drawLine(x + width, y, x + width, y + height, paint);
            //下
            //c.drawLine(x, y + height, x + width, y + height, paint);

            //画内间距
            childView.setPadding(0, 0, 5, 5);

        }
    }
}
