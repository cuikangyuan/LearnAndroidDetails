package com.cky.learnandroiddetails.TestPair;

import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cky.learnandroiddetails.R;

public class TestPairMainActivity extends AppCompatActivity {

    private static final String TAG = TestPairMainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_pair_main);

        testPair();

    }

    private void testPair() {
        Pair pair1 = new Pair(1, 2);
        Pair pair2 = Pair.create("1", 2);

        Log.d(TAG, "pair1.first.toString()->" + pair1.first.toString());
        Log.d(TAG, "pair1.second.toString()->" + pair1.second.toString());

        Log.d(TAG, "pair2.first.toString()->" + pair2.first.toString());
        Log.d(TAG, "pair2.second.toString()->" + pair2.second.toString());

        Log.d(TAG, "pair1.equals(pair2)" + pair1.equals(pair2));
        Log.d(TAG, "pair1.equals(pair1)" + pair1.equals(pair1));
    }
}
