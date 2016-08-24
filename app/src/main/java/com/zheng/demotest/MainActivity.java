package com.zheng.demotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zheng.demotest.circleball.CircleActivity;
import com.zheng.demotest.mybuttom.MyButton;
import com.zheng.demotest.myviewpage.MyViewPageActivity;
import com.zheng.demotest.quickquery.QuickQueryActivity;
import com.zheng.demotest.slidelayout.SlideActivity;
import com.zheng.demotest.wave.WaveActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/22.
 */
public class MainActivity extends Activity implements MyButton.onClickBackListener {

    @BindView(R.id.mbtn_circle)
    MyButton mMbtnCircle;
    @BindView(R.id.mbtn_myviewpage)
    MyButton mMbtnMyviewpage;
    @BindView(R.id.mbtn_quickquery)
    MyButton mMbtnQuickquery;
    @BindView(R.id.mbtn_slidelayout)
    MyButton mMbtnSlidelayout;
    @BindView(R.id.mbtn_wave)
    MyButton mMbtnWave;

//    private MyButton mMbtnCricle;
//    private MyButton mMbtnMyViewPage;
//    private MyButton mMbtnQuickQuery;
//    private MyButton mMbtnSlideLayout;
//    private MyButton mMbtnWare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mMbtnCricle = (MyButton) findViewById(R.id.mbtn_circle);
//        mMbtnMyViewPage = (MyButton) findViewById(R.id.mbtn_myviewpage);
//        mMbtnQuickQuery = (MyButton) findViewById(R.id.mbtn_quickquery);
//        mMbtnSlideLayout = (MyButton) findViewById(R.id.mbtn_slidelayout);
//        mMbtnWare = (MyButton) findViewById(R.id.mbtn_wave);
//
        mMbtnCircle.setOnClickBackListener(this);
        mMbtnMyviewpage.setOnClickBackListener(this);
        mMbtnQuickquery.setOnClickBackListener(this);
        mMbtnSlidelayout.setOnClickBackListener(this);
        mMbtnWave.setOnClickBackListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.mbtn_circle:

                Toast.makeText(this, "mbtn_circle", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, CircleActivity.class));

                break;

            case R.id.mbtn_myviewpage:

                Toast.makeText(this, "mbtn_myviewpage", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MyViewPageActivity.class));

                break;

            case R.id.mbtn_quickquery:

                Toast.makeText(this, "mbtn_quickquery", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, QuickQueryActivity.class));

                break;

            case R.id.mbtn_slidelayout:

                Toast.makeText(this, "mbtn_slidelayout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SlideActivity.class));

                break;

            case R.id.mbtn_wave:

                Toast.makeText(this, "mbtn_wave", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, WaveActivity.class));

                break;
        }
    }
}
