package com.zheng.demotest.flowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.zheng.demotest.R;

public class FlowLayoutActivity extends AppCompatActivity {

//    @BindView(R.id.flv_main)
//    FlowLayoutView mFlvMain;

    private FlowLayoutView mFlvMain;

//    private String[] msgs = new String[]
//            { "CircleActivity", "MyViewPageActivity", "QuickQueryActivity",
//                    "SlideActivity", "WaveActivity", "FlowLayoutActivity",
//                    "Android", "TextView", "michael"};

    private String[] msgs = new String[]
            { "MyViewPageActivity", "QuickQueryActivity",
                    "SlideActivity", "WaveActivity", "FlowLayoutActivity",
                    "Android", "TextView", "中国"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
//        ButterKnife.bind(this);

        mFlvMain = (FlowLayoutView) findViewById(R.id.flv_main);

        LayoutInflater layoutInflater = LayoutInflater.from(this);

        for (int i = 0; i < msgs.length; i++){

            TextView textView = (TextView) layoutInflater.inflate(R.layout.tv_flowlayout,mFlvMain,false);
            textView.setText(msgs[i]);

            mFlvMain.addView(textView);
        }
    }
}
