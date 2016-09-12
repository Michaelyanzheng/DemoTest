package com.zheng.demotest.animation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/9/7.
 */
public class AnimationActivity extends Activity implements View.OnClickListener {

    private AnimationDrawable mAnimationDrawable;

    private Button mBtnTween;
    private Button mBtnProperty;
    private Button mBtnStartAnimation;
    private Button mBtnStopAnimation;

    private ImageView mIvAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mBtnTween = (Button) findViewById(R.id.btn_tween);
        mBtnProperty = (Button) findViewById(R.id.btn_property);
        mBtnStartAnimation = (Button) findViewById(R.id.btn_start_animation);
        mBtnStopAnimation = (Button) findViewById(R.id.btn_stop_animation);
        mIvAnimation = (ImageView) findViewById(R.id.iv_animation);

        mBtnTween.setOnClickListener(this);
        mBtnProperty.setOnClickListener(this);
        mBtnStartAnimation.setOnClickListener(this);
        mBtnStopAnimation.setOnClickListener(this);

        mAnimationDrawable = new AnimationDrawable();

//        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.bird1),100);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.bird2),100);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.bird3),100);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.bird4),100);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.bird5),100);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.bird6),100);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.bird7),100);
//        mAnimationDrawable.addFrame(getResources().getDrawable(R.drawable.bird8),100);

        mAnimationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_bird);

        mIvAnimation.setBackground(mAnimationDrawable);

        mAnimationDrawable.setOneShot(false);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_tween:

                startActivity(new Intent(this,TweenActivity.class));
                overridePendingTransition(R.anim.alpha_enter,R.anim.alpha_leave);

                break;

            case R.id.btn_property:

                startActivity(new Intent(this,PropertyActivity.class));
                overridePendingTransition(R.anim.translate_enter,R.anim.translate_leave);

                break;

            case R.id.btn_start_animation:

                if (!mAnimationDrawable.isRunning()){

                    Toast.makeText(AnimationActivity.this, "btn_start_animation", Toast.LENGTH_SHORT).show();

                    mAnimationDrawable.start();

                }

                break;

            case R.id.btn_stop_animation:

                if (mAnimationDrawable.isRunning()){

                    mAnimationDrawable.stop();
                }

                break;

        }
    }
}
