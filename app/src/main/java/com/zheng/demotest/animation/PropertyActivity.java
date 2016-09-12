package com.zheng.demotest.animation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/9/10.
 */
public class PropertyActivity extends Activity implements View.OnClickListener {

    private Button mBtnPropertyCombine1;
    private Button mBtnPropertyCombine2;

    private ImageView mIvPropertyCombine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);

        mBtnPropertyCombine1 = (Button) findViewById(R.id.btn_property_combine1);
        mBtnPropertyCombine2 = (Button) findViewById(R.id.btn_property_combine2);

        mIvPropertyCombine = (ImageView) findViewById(R.id.iv_property_combine);

        mBtnPropertyCombine1.setOnClickListener(this);
        mBtnPropertyCombine2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_property_combine1:

                PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(
                        "alpha",1.0f,0
                        );

                PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(
                        "scaleX",1.0f,0
                );

                PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(
                        "scaleY",1.0f,0
                );

                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                        mIvPropertyCombine,alpha,scaleX,scaleY
                );

                objectAnimator.setDuration(3000);
                objectAnimator.start();

                break;

            case R.id.btn_property_combine2:

                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(
                        mIvPropertyCombine,"",1.0f,0);

                objectAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {

                        float value = (float) animation.getAnimatedValue();

                        mIvPropertyCombine.setAlpha(value);
                        mIvPropertyCombine.setScaleX(value);
                        mIvPropertyCombine.setScaleY(value);
                    }
                });

                objectAnimator1.setDuration(6000);
                objectAnimator1.start();

                break;
        }
    }
}
