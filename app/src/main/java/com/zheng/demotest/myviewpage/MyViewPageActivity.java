package com.zheng.demotest.myviewpage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MyViewPageActivity extends Activity {

    private int [] pictures = {R.mipmap.a1,R.mipmap.a2,R.mipmap.a3,R.mipmap.a4};

    private RadioGroup mRadioGroup;
    private MyViewPageView mMyViewPageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myviewpage);

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);
        mMyViewPageView = (MyViewPageView) findViewById(R.id.mvpv_main);

        initView();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                Log.e("zheng", "onCheckedChanged: " + i);

                mMyViewPageView.scrollToPage(i);
            }
        });

        mMyViewPageView.setChangePage(new MyViewPageView.ChangePage() {
            @Override
            public void onChangePage(int currentIndex) {

                Log.e("zheng", "onChangePage: " + currentIndex);

                mRadioGroup.check(currentIndex);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initView() {

        for (int i = 0; i < pictures.length; i++){

            ImageView imageView = new ImageView(this);

            imageView.setBackgroundResource(pictures[i]);

            mMyViewPageView.addView(imageView);
        }

        View view = View.inflate(this,R.layout.test,null);
        mMyViewPageView.addView(view,2);

        for (int i = 0; i < mMyViewPageView.getChildCount(); i++){

            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);

            if (i == 0){

                radioButton.setChecked(true);
            }

            mRadioGroup.addView(radioButton);
        }
    }
}
