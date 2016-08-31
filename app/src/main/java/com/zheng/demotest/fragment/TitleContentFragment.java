package com.zheng.demotest.fragment;

import android.app.Activity;
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
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/29.
 */
public class TitleContentFragment extends Fragment {

    public static final String ARGUMENT = "argument";
    public static final String REQUEST = "request";

    public static final String EVALUATE_DIALOG = "evaluate_dialog";
    public static final int REQUEST_EVALUATE = 0x110;


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

        TextView textView = new TextView(getContext());

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        textView.setLayoutParams(layoutParams);

        textView.setText(mArgument);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EvaluateDialog evaluateDialog = new EvaluateDialog();

                evaluateDialog.setTargetFragment(TitleContentFragment.this,REQUEST_EVALUATE);
                evaluateDialog.show(getFragmentManager(),EVALUATE_DIALOG);
            }
        });

        return textView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EVALUATE){

            if (resultCode == Activity.RESULT_OK){

                String evaluate = data.getStringExtra(EvaluateDialog.RESPONSE_EVALUATE);
                Toast.makeText(getContext(), evaluate, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra(TitleContentFragment.REQUEST,evaluate);
                getActivity().setResult(TitleListFragment.REQUEST_DETAIL,intent);
            }
        }
    }
}
