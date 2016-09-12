package com.zheng.demotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zheng.demotest.animation.AnimationActivity;
import com.zheng.demotest.circleball.CircleActivity;
import com.zheng.demotest.custombutton.CustomButtonActivity;
import com.zheng.demotest.db.DbActivity;
import com.zheng.demotest.flowlayout.FlowLayoutActivity;
import com.zheng.demotest.fragment.FragmentListActivity;
import com.zheng.demotest.handler.HandlerActivity;
import com.zheng.demotest.mybuttom.MyButton;
import com.zheng.demotest.myviewpage.MyViewPageActivity;
import com.zheng.demotest.okhttp.OkhttpActivity;
import com.zheng.demotest.quickquery.QuickQueryActivity;
import com.zheng.demotest.serviceactivity.ServiceChangeActivityActivity;
import com.zheng.demotest.slidelayout.SlideActivity;
import com.zheng.demotest.wave.WaveActivity;

/**
 * Created by Administrator on 2016/8/22.
 */
public class MainActivity extends Activity implements MyButton.onClickBackListener {

//    @BindView(R.id.mbtn_circle)
//    MyButton mMbtnCircle;
//    @BindView(R.id.mbtn_myviewpage)
//    MyButton mMbtnMyviewpage;
//    @BindView(R.id.mbtn_quickquery)
//    MyButton mMbtnQuickquery;
//    @BindView(R.id.mbtn_slidelayout)
//    MyButton mMbtnSlidelayout;
//    @BindView(R.id.mbtn_wave)
//    MyButton mMbtnWave;
//    @BindView(R.id.mbtn_flowlayout)
//    MyButton mMbtnFlowlayout;

    private MyButton mMbtnCircle;
    private MyButton mMbtnMyviewpage;
    private MyButton mMbtnQuickquery;
    private MyButton mMbtnSlidelayout;
    private MyButton mMbtnWave;
    private MyButton mMbtnFlowlayout;
    private MyButton mMbtnCustomButton;
    private MyButton mMbtnHandler;
    private MyButton mMbtnFragmentTest;
    private MyButton mMbtnDBActivity;
    private MyButton mMbtnOkhttp;
    private MyButton mMbtnServiceChangeActivity;
    private MyButton mMbtnAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
        mMbtnCircle = (MyButton) findViewById(R.id.mbtn_circle);
        mMbtnMyviewpage = (MyButton) findViewById(R.id.mbtn_myviewpage);
        mMbtnQuickquery = (MyButton) findViewById(R.id.mbtn_quickquery);
        mMbtnSlidelayout = (MyButton) findViewById(R.id.mbtn_slidelayout);
        mMbtnWave = (MyButton) findViewById(R.id.mbtn_wave);
        mMbtnFlowlayout = (MyButton) findViewById(R.id.mbtn_flowlayout);
        mMbtnCustomButton = (MyButton) findViewById(R.id.mbtn_custombtn);
        mMbtnHandler = (MyButton) findViewById(R.id.mbtn_handler);
        mMbtnFragmentTest = (MyButton) findViewById(R.id.mbtn_fragmenttestactivity);
        mMbtnDBActivity = (MyButton) findViewById(R.id.mbtn_dbactivity);
        mMbtnOkhttp = (MyButton) findViewById(R.id.mbtn_okhttpactivity);
        mMbtnServiceChangeActivity = (MyButton) findViewById(R.id.mbtn_service_change_activity);
        mMbtnAnimation = (MyButton) findViewById(R.id.mbtn_animation);
//
        mMbtnCircle.setOnClickBackListener(this);
        mMbtnMyviewpage.setOnClickBackListener(this);
        mMbtnQuickquery.setOnClickBackListener(this);
        mMbtnSlidelayout.setOnClickBackListener(this);
        mMbtnWave.setOnClickBackListener(this);
        mMbtnFlowlayout.setOnClickBackListener(this);
        mMbtnCustomButton.setOnClickBackListener(this);
        mMbtnHandler.setOnClickBackListener(this);
        mMbtnFragmentTest.setOnClickBackListener(this);
        mMbtnDBActivity.setOnClickBackListener(this);
        mMbtnOkhttp.setOnClickBackListener(this);
        mMbtnServiceChangeActivity.setOnClickBackListener(this);
        mMbtnAnimation.setOnClickBackListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

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

            case R.id.mbtn_flowlayout:

                Toast.makeText(this, "mbtn_flowlayout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, FlowLayoutActivity.class));

                break;

            case R.id.mbtn_custombtn:

                Toast.makeText(this, "mbtn_custombtn", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, CustomButtonActivity.class));

                break;

            case R.id.mbtn_handler:

                Toast.makeText(this, "mbtn_handler", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, HandlerActivity.class));

                break;

            case R.id.mbtn_fragmenttestactivity:

                Toast.makeText(this, "mbtn_fragmenttestactivity", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, FragmentTestActivity.class));
                startActivity(new Intent(this, FragmentListActivity.class));

                break;

            case R.id.mbtn_dbactivity:

                Toast.makeText(this, "mbtn_dbactivity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, DbActivity.class));

                break;

            case R.id.mbtn_okhttpactivity:

                Toast.makeText(this, "mbtn_dbactivity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, OkhttpActivity.class));

                break;

            case R.id.mbtn_service_change_activity:

//                Toast.makeText(this, "mbtn_service_change_activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, ServiceChangeActivityActivity.class));

                break;

            case R.id.mbtn_animation:

//                Toast.makeText(this, "mbtn_service_change_activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, AnimationActivity.class));

                break;
        }
    }
}
