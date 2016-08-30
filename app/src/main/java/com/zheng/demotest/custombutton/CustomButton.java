package com.zheng.demotest.custombutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/8/26.
 */
public class CustomButton extends View implements View.OnClickListener {

    private Paint mPaint;

    private float mStartX;
    private float mSlideX;

    private float mMaxSlideX;
    private Bitmap mBackGroundBitmap;
    private Bitmap mSlideBitmap;

    private boolean mIsEnableClick = true;
    private boolean mIsOpen = true;

    public CustomButton(Context context) {
        this(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mSlideX = 0;

        mBackGroundBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_background);
        mSlideBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.slide_button);

        mMaxSlideX = mBackGroundBitmap.getWidth() - mSlideBitmap.getWidth();
        mSlideX = mMaxSlideX;

        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBackGroundBitmap, 0, 0, mPaint);
        canvas.drawBitmap(mSlideBitmap, mSlideX, 0, mPaint);
    }

    @Override
    public void onClick(View v) {

        if (mIsEnableClick){

            refreshView();
        }
    }

    private void refreshView() {

        if (mIsOpen){
            mSlideX = 0;
        }else {
            mSlideX = mMaxSlideX;
        }
        mIsOpen = !mIsOpen;
        invalidate();
    }

    private float mDownX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                mDownX = mStartX = event.getX();
                mIsEnableClick = true;

                break;

            case MotionEvent.ACTION_MOVE:

                float moveX = event.getX();

                float distanceX = moveX - mStartX;

                mSlideX += distanceX;

                if (mSlideX < 0){
                    mSlideX = 0;
                }else if (mSlideX > mMaxSlideX){
                    mSlideX = mMaxSlideX;
                }

                invalidate();

                mStartX = moveX;

                if (Math.abs(moveX - mDownX) > 5){
                    mIsEnableClick = false;
                }

                break;

            case MotionEvent.ACTION_UP:

                if (!mIsEnableClick){

                    if (mSlideX < mMaxSlideX / 2){

                        mIsOpen = true;

                    }else{

                        mIsOpen = false;
                    }

                    refreshView();
                }

                break;
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(mBackGroundBitmap.getWidth(),mBackGroundBitmap.getHeight());
    }
}
