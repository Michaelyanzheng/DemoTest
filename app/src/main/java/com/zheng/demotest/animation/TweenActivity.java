package com.zheng.demotest.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/9/8.
 */
public class TweenActivity extends Activity {

    private ImageView mIvTranslation;
    private ImageView mIvAlpha;
    private ImageView mIvScale;
    private ImageView mIvRotate;

    private ImageView mIvSetTween;
    private ImageView mIvRotate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);

        mIvTranslation = (ImageView) findViewById(R.id.iv_translation);
        mIvAlpha = (ImageView) findViewById(R.id.iv_alpha);
        mIvScale = (ImageView) findViewById(R.id.iv_scale);
        mIvRotate = (ImageView) findViewById(R.id.iv_rotate);

        mIvSetTween = (ImageView) findViewById(R.id.iv_set_tween);
        mIvRotate1 = (ImageView) findViewById(R.id.iv_rotate1);

        translation();
        alpha();

        scale();
        rotate();

        rotate1();


    }

    private void rotate1() {

//        RotateAnimation rotateAnimation = new RotateAnimation(
//                0,360,
//                Animation.RELATIVE_TO_SELF,0.5f,
//                Animation.RELATIVE_TO_SELF,0.5f
//        );
//
//        rotateAnimation.setDuration(5000);
//        rotateAnimation.setRepeatCount(1);
//
//        mIvRotate1.setAnimation(rotateAnimation);
//
//        rotateAnimation.start();

        RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotate);

        mIvRotate1.setAnimation(rotateAnimation);

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

//                Toast.makeText(TweenActivity.this, "onAnimationStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

//                Toast.makeText(TweenActivity.this, "onAnimationEnd", Toast.LENGTH_SHORT).show();

                setTween();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

//                Toast.makeText(TweenActivity.this, "onAnimationRepeat", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setTween() {

        AnimationSet animationSet = new AnimationSet(false);

        //  透明度
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);

        alphaAnimation.setDuration(3000);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.RESTART);

        animationSet.addAnimation(alphaAnimation);

        //  缩放
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1,0,1,0,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
        );

        scaleAnimation.setDuration(3000);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.RESTART);

        animationSet.addAnimation(scaleAnimation);

        //  旋转
        RotateAnimation rotateAnimation = new RotateAnimation(
                0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f
        );

        rotateAnimation.setDuration(3000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.RESTART);

        animationSet.addAnimation(rotateAnimation);

        //  移动
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,0.5f,
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_PARENT,-0.5f
        );

        translateAnimation.setDuration(3000);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.RESTART);

        animationSet.addAnimation(translateAnimation);

        mIvSetTween.setAnimation(animationSet);

        animationSet.start();

    }

    private void rotate() {

//        RotateAnimation rotateAnimation = new RotateAnimation(
//                0,360,
//                Animation.RELATIVE_TO_SELF,0.5f,
//                Animation.RELATIVE_TO_SELF,0.5f
//        );
//
//        rotateAnimation.setDuration(3000);
//        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        rotateAnimation.setRepeatMode(Animation.REVERSE);
//
//        mIvRotate.setAnimation(rotateAnimation);
//
//        rotateAnimation.start();

        RotateAnimation rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotate);
        mIvRotate.setAnimation(rotateAnimation);
        rotateAnimation.start();

        Toast.makeText(this,"rotateAnimation",Toast.LENGTH_SHORT).show();
    }

    private void scale() {

//        ScaleAnimation scaleAnimation = new ScaleAnimation(
//                1,3,1,3,
//                Animation.RELATIVE_TO_SELF,0.5f,
//                Animation.RELATIVE_TO_SELF,0.5f
//        );
//
//        scaleAnimation.setDuration(3000);
//
//        scaleAnimation.setRepeatCount(Animation.INFINITE);
//        scaleAnimation.setRepeatMode(Animation.REVERSE);
//        mIvScale.setAnimation(scaleAnimation);
//
//        scaleAnimation.start();

        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this,R.anim.scale);

        mIvScale.setAnimation(scaleAnimation);

        scaleAnimation.start();


    }

    private void alpha() {

//        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0);
//
//        alphaAnimation.setDuration(3000);
//        alphaAnimation.setRepeatMode(Animation.REVERSE);
//        alphaAnimation.setRepeatCount(Animation.INFINITE);
//
//        mIvAlpha.setAnimation(alphaAnimation);
//
//        alphaAnimation.start();

        AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(this,R.anim.alpha);

        mIvAlpha.setAnimation(alphaAnimation);

        alphaAnimation.start();


    }

    private void translation() {

//        TranslateAnimation translateAnimation = new TranslateAnimation(
//                Animation.RELATIVE_TO_PARENT,0,
//                Animation.RELATIVE_TO_PARENT,1.0f,
//                Animation.RELATIVE_TO_PARENT,0,
//                Animation.RELATIVE_TO_PARENT,1.0f
//        );
//
//        translateAnimation.setDuration(3000);
////        translateAnimation.setFillAfter(false);
//        translateAnimation.setRepeatMode(Animation.REVERSE);
//        translateAnimation.setRepeatCount(Animation.INFINITE);
//
//
//        mIvTranslation.setAnimation(translateAnimation);
//
//        translateAnimation.start();

        TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(this,R.anim.translation);
        mIvTranslation.setAnimation(translateAnimation);

        translateAnimation.setInterpolator(new BounceInterpolator());

        translateAnimation.start();

//        Toast.makeText(this,"----------",Toast.LENGTH_SHORT).show();

    }
}
