package com.zheng.demotest.circleball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/8/22.
 */
public class CircleView extends View {

    private Paint mPaint;

    private float mMoveX;
    private float mMoveY;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private void initView() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        mMoveX = 250;
        mMoveY = 250;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(mMoveX,mMoveY,50,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mMoveX = event.getX();
        mMoveY = event.getY();

        invalidate();

        return true;
    }
}
