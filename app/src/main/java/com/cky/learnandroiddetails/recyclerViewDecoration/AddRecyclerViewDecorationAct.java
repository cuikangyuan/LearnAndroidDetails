package com.cky.learnandroiddetails.recyclerViewDecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cky.learnandroiddetails.R;
import com.cky.learnandroiddetails.RecyclerViewPlus;

public class AddRecyclerViewDecorationAct extends AppCompatActivity {

    private RecyclerViewPlus mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recycler_view_decoration);

        mRecyclerView = (RecyclerViewPlus) findViewById(R.id.recycler_view);

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(AddRecyclerViewDecorationAct.this));
        mRecyclerView.setLayoutManager(new GridLayoutManager(AddRecyclerViewDecorationAct.this, 3));
        mRecyclerView.setAdapter(new RecyclerViewAdapter());
        mRecyclerView.addItemDecoration(new ItemDecorationDivider(AddRecyclerViewDecorationAct.this));
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(AddRecyclerViewDecorationAct.this).inflate(R.layout.recycler_view_item, parent, false);

            return new RecyclerViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class RecyclerViewHolder extends RecyclerView.ViewHolder {

            public RecyclerViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    public static class ItemDecorationDivider extends RecyclerView.ItemDecoration {

        private static final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };
        private Drawable mDrawable;

        public ItemDecorationDivider(Context context) {
            final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
            mDrawable = typedArray.getDrawable(0);
            typedArray.recycle();
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

            drawHorizontal(c, parent);
            drawVertical(c, parent);
        }


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            int spanCount = getSpanCount(parent);
            int childCount = parent.getAdapter().getItemCount();

            if (isLastRow(parent, position, spanCount, childCount)) {
                outRect.set(0, 0, mDrawable.getIntrinsicWidth(), 0);
            } else if (isLastColumn(parent, position, spanCount, childCount)) {
                outRect.set(0, 0, 0, mDrawable.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
            }
        }

        private boolean isLastRow(RecyclerView parent, int pos, int spanCount, int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

            if (layoutManager instanceof GridLayoutManager) {
                //算出最后一行开始的index
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount) {
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    //算出最后一行开始的index
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount) {
                        return true;
                    }
                } else {
                    // 横向滚动
                    // 如果是最后一行，则不需要绘制底部
                    if ((pos + 1) % spanCount == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();

                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    if ((pos + 1) % spanCount == 0) {
                        return true;
                    }
                } else {
                    childCount = childCount - childCount % spanCount;
                    if (pos >= childCount) {
                        return true;
                    }
                }
            }
            return false;
        }

        private void drawHorizontal(Canvas c, RecyclerView parent) {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getLeft() - params.leftMargin;
                final int right = child.getRight() + params.rightMargin
                        + mDrawable.getIntrinsicWidth();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + mDrawable.getIntrinsicHeight();
                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }

        private void drawVertical(Canvas c, RecyclerView parent) {
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++)
            {
                final View child = parent.getChildAt(i);

                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getTop() - params.topMargin;
                final int bottom = child.getBottom() + params.bottomMargin;
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDrawable.getIntrinsicWidth();

                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }

        private int getSpanCount(RecyclerView parent) {
            //列数
            int spanCount = 1;
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

            if (layoutManager instanceof GridLayoutManager) {
                spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            }

            return spanCount;
        }
    }
}
