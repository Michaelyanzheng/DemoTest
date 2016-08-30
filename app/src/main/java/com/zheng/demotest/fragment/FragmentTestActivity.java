package com.zheng.demotest.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/8/27.
 */
public class FragmentTestActivity extends FragmentActivity {

    private CatalogFragment mCatalogFragment;
    private ContentFragment mContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenttest);

        FragmentManager fragmentManager = getSupportFragmentManager();

        mCatalogFragment = (CatalogFragment) fragmentManager.findFragmentById(R.id.fl_catalog);

        if (mCatalogFragment == null){

            mCatalogFragment = new CatalogFragment();
            fragmentManager.beginTransaction().add(R.id.fl_catalog,mCatalogFragment).commit();
        }

        mContentFragment = (ContentFragment) fragmentManager.findFragmentById(R.id.fl_content);

        if (mContentFragment == null){

            mContentFragment = ContentFragment.newInstance(null);
            fragmentManager.beginTransaction().add(R.id.fl_content,mContentFragment).commit();
        }

        mCatalogFragment.setOnItemClickListener(new CatalogFragment.OnItemClickListener() {
            @Override
            public void onClick(String[] catalog) {

                mContentFragment.updateData(catalog);
            }
        });
    }
}
