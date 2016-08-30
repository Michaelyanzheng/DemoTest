package com.zheng.demotest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class TitleListFragment extends ListFragment {

    public static final int REQUEST_DETAIL = 0X110;

    private int mCurrentPos;

    private ArrayAdapter mArrayAdapter;

    private List<String> mTitleList = Arrays.asList("zheng","michael","shou");

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(mArrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,mTitleList));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        mCurrentPos = position;

        Intent intent = new Intent(getActivity(),FragmentContentActivity.class);
        intent.putExtra(TitleContentFragment.ARGUMENT,mTitleList.get(position));
        startActivityForResult(intent,TitleListFragment.REQUEST_DETAIL);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_DETAIL){
            mTitleList.set(mCurrentPos,data.getStringExtra(TitleContentFragment.REQUEST));
            mArrayAdapter.notifyDataSetChanged();
        }

    }
}
