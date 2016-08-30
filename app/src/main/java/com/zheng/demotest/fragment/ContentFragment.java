package com.zheng.demotest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zheng.demotest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/27.
 */
public class ContentFragment extends Fragment {

    private List<String> mStringList;
    private ArrayAdapter mArrayAdapter;
    private ListView mListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStringList = new ArrayList<>();

        Bundle argument = getArguments();
        if (argument != null){

            String[] data = argument.getStringArray("data");

            for (String string : data){

                mStringList.add(string);
            }
        }
    }

    public static ContentFragment newInstance(Bundle bundle){

        ContentFragment contentFragment = new ContentFragment();

        contentFragment.setArguments(bundle);

        return contentFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_content,container,false);


        mArrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,mStringList);
        mListView = (ListView) view.findViewById(R.id.lv_fg_content);
        mListView.setAdapter(mArrayAdapter);

        return view;
    }

    public void updateData(String [] data){

        if (data == null && data.length == 0){
            return;
        }

        mStringList.clear();

        for (String str : data){

            mStringList.add(str);
        }

        mArrayAdapter.notifyDataSetChanged();

    }
}
