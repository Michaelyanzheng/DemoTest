package com.zheng.demotest.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/8/29.
 */
public class FragmentListActivity extends SingleFragmentActivity {

    @Override
    public Fragment newFragment() {
        return new TitleListFragment();
    }

    //    private TitleListFragment mTitleListFragment;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_main);
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        mTitleListFragment = (TitleListFragment) fragmentManager.findFragmentById(R.id.fg_main);
//
//        if (mTitleListFragment != null){
//
//            mTitleListFragment = new TitleListFragment();
//            fragmentManager.beginTransaction().add(R.id.fg_main,mTitleListFragment).commit();
//        }
//    }
}
