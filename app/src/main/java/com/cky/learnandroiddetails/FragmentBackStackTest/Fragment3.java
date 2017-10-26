package com.cky.learnandroiddetails.FragmentBackStackTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cky.learnandroiddetails.R;

/**
 * Created by cuikangyuan on 2017/10/26.
 */

public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_back_stack_3, container, false);
        return view;
    }
}
