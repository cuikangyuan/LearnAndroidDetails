package com.cky.learnandroiddetails.Matrix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cky.learnandroiddetails.ManifestPlaceHolder.Helper;
import com.cky.learnandroiddetails.R;

public class MatrixTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_test);


        Log.d("buildConfig", Helper.getManifestPlaceHolder(Helper.A_HOST));
    }
}
