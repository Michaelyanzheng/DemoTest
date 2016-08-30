package com.zheng.demotest.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Administrator on 2016/8/29.
 */
public class TitleContentFragment extends Fragment {

    public static final String ARGUMENT = "argument";
    public static final String REQUEST = "request";

    private String mArgument;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null){

            mArgument = bundle.getString(ARGUMENT);

            Intent intent = new Intent();
            intent.putExtra(REQUEST,"good");
            getActivity().setResult(TitleListFragment.REQUEST_DETAIL,intent);
        }
    }

    public static TitleContentFragment newInstance(String argument){

        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT,argument);

        TitleContentFragment titleContentFragment = new TitleContentFragment();
        titleContentFragment.setArguments(bundle);

        return titleContentFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Random random = new Random();

        TextView textView = new TextView(getContext());

        textView.setText(mArgument);
        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.argb(random.nextInt(100),random.nextInt(256),random.nextInt(256),random.nextInt(256)));
        textView.setTextColor(Color.RED);

        return textView;
    }
}
