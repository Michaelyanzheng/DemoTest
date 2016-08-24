package com.zheng.demotest.mybuttom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MyButton extends Button {

    private int mWidth;
    private int mHeight;

    private String mShowStr;

    private GestureDetector mGestureDetector;

    public MyButton(final Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyButton);
        for (int i = 0; i < typedArray.getIndexCount(); i++){

            int index = typedArray.getIndex(i);

            switch (index){
                case R.styleable.MyButton_show:

                    mShowStr = typedArray.getString(index);

                    break;
            }
        }

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        mWidth = windowManager.getDefaultDisplay().getWidth();
        mHeight = windowManager.getDefaultDisplay().getHeight();

        mGestureDetector = new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {

                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                if (mOnClickBackListener != null){

                    mOnClickBackListener.onClick(MyButton.this);
                }
//                Toast.makeText(context, mShowStr, Toast.LENGTH_SHORT).show();

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    private float mStartX;
    private float mStartY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mGestureDetector.onTouchEvent(event);

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                mStartX = event.getRawX();
                mStartY = event.getRawY();

                break;

            case MotionEvent.ACTION_MOVE:

                float moveX = event.getRawX();
                float moveY = event.getRawY();

                float distanceX = moveX - mStartX;
                float distanceY = moveY - mStartY;

                int left = getLeft();
                int top = getTop();
                int right = getRight();
                int bottom = getBottom();

                left += distanceX;
                right += distanceX;
                top += distanceY;
                bottom += distanceY;

                if (left <= 0 || right > mWidth || top < 0 || bottom > mHeight){
                    break;
                }

                mStartX = moveX;
                mStartY = moveY;

                layout(left,top,right,bottom);

                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }

    public interface onClickBackListener{

        void onClick(View view);
    }

    private onClickBackListener mOnClickBackListener;

    public void setOnClickBackListener(onClickBackListener onClickBackListener){
        this.mOnClickBackListener = onClickBackListener;
    }
}
