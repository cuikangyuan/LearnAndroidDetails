package com.cky.learnandroiddetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cky.learnandroiddetails.widget.SpaceItemDecoration;

/*
* http://zijianwang90.github.io/blog/Android-dev-recyclerview-replace-gridview/
* */
public class TestGridAct extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private TestGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_grid);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TestGridAdapter(TestGridAct.this);

        mRecyclerView.addItemDecoration(new SpaceItemDecoration());
        mRecyclerView.setLayoutManager(new GridLayoutManager(TestGridAct.this, 3));
        mRecyclerView.setAdapter(mAdapter);
    }


    class TestGridAdapter extends RecyclerView.Adapter<TestGridAdapter.TestGridViewHolder> {

        private Context mContext;

        private int[] mData = {
                R.mipmap.test1,
                R.mipmap.test2,
                R.mipmap.test3,
                R.mipmap.test4,
                R.mipmap.test55,
                R.mipmap.test6,
                R.mipmap.test7,
                R.mipmap.test8,
                R.mipmap.test9};


        public TestGridAdapter(Context context) {

            this.mContext = context;
        }

        @Override
        public TestGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.test_grid_item, parent, false);

            return new TestGridViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TestGridViewHolder holder, int position) {

            int resId = mData[position];
            //holder.imageView.setBackgroundResource(resId);
            holder.textView.setText(position + 1 + "");
        }

        @Override
        public int getItemCount() {
            return mData.length;
        }

        class TestGridViewHolder extends RecyclerView.ViewHolder {

            //ImageView imageView;
            TextView textView;

            public TestGridViewHolder(View itemView) {
                super(itemView);
                //imageView = (ImageView) itemView.findViewById(R.id.image);
                textView = (TextView) itemView.findViewById(R.id.text);

            }
        }
    }
}
