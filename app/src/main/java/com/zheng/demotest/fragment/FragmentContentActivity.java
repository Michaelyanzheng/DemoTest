package com.zheng.demotest.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/8/29.
 */
public class FragmentContentActivity extends SingleFragmentActivity {

    @Override
    public Fragment newFragment() {
        Intent intent = getIntent();
        String argument = intent.getStringExtra(TitleContentFragment.ARGUMENT);
        return TitleContentFragment.newInstance(argument);
    }
}
