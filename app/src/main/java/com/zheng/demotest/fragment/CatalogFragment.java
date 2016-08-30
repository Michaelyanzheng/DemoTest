package com.zheng.demotest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zheng.demotest.R;

import java.util.ArrayList;
import java.util.List;

import static com.zheng.demotest.R.layout.fragment_catalog;

/**
 * Created by Administrator on 2016/8/27.
 */
public class CatalogFragment extends Fragment {

    private List<String> mStringList;

    private String [][] mStringData = {
            {"北京","shagnhai","shenzhen"},
            {"shagnhai","shagnhai","北京"},
            {"北京","北京","北京"}
    };

    private ListView mListView;
    private ArrayAdapter mArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(fragment_catalog,container,false);
        mListView = (ListView) view.findViewById(R.id.lv_fragment_main);

        mStringList = new ArrayList<>();

        for (int i = 0; i < 3; i++){

            mStringList.add("zheng" + i);
        }

        mArrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,mStringList);
        mListView.setAdapter(mArrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (CatalogFragment.this.mOnItemClickListener != null){

                    CatalogFragment.this.mOnItemClickListener.onClick(mStringData[position]);
                }
            }
        });
        
        return view;
    }

    public interface OnItemClickListener{

        void onClick(String[] catalog);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
