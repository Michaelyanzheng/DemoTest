package com.zheng.demotest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/8/29.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    public abstract Fragment newFragment();

    private Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        mFragment = fragmentManager.findFragmentById(R.id.fg_main);

        if (mFragment == null){

            mFragment = newFragment();
            fragmentManager.beginTransaction().add(R.id.fg_main,mFragment).commit();
        }
    }
}
