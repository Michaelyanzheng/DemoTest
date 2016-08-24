package com.zheng.demotest.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class WaveView extends View{

    private int [] colors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,0xFF47E02F,0xFF00CCFF};

    private final static int RADIUS = 10;

    private List<CircleHolder> mCircleHolderList;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mCircleHolderList = new ArrayList<>();
    }

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            for (int i = 0; i < mCircleHolderList.size(); i++){

                if (mCircleHolderList.get(i).getPaint().getAlpha() > 0){

                    mCircleHolderList
                            .get(i)
                            .setRadius(mCircleHolderList.get(i).getRadius() + 10);

                    mCircleHolderList
                            .get(i)
                            .getPaint()
                            .setAlpha(mCircleHolderList.get(i).getPaint().getAlpha() - 5);

                }else {

                    mCircleHolderList.remove(i);

                }

                invalidate();
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mCircleHolderList.size(); i++){

            canvas.drawCircle(
                    (int) mCircleHolderList.get(i).getDownX(),
                    (int) mCircleHolderList.get(i).getDownY(),
                    mCircleHolderList.get(i).getRadius(),
                    mCircleHolderList.get(i).getPaint());

            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessageAtTime(0,500);
        }
    }

    private double mPreDownX = 0;
    private double mPreDownY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                Log.e("zheng", "onTouchEvent: " + "ACTION_DOWN");

                double downX = event.getX();
                double downY = event.getY();

                mPreDownX = downX;
                mPreDownY = downY;

                Paint paint = new Paint();
//                paint.setColor(colors[(int) (Math.random()*colors.length)]);
                paint.setColor((int) (Math.random()*Integer.MAX_VALUE));
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(30);

                CircleHolder circleHolder = new CircleHolder(downX,downY,paint,RADIUS);
                mCircleHolderList.add(circleHolder);

                invalidate();

                break;

            case MotionEvent.ACTION_MOVE:


            case MotionEvent.ACTION_UP:

                Log.e("zheng", "onTouchEvent: " + "ACTION_MOVE");

                double moveX = event.getX();
                double moveY = event.getY();

                double distanceX = moveX - mPreDownX;
                double distanceY = moveY - mPreDownY;

                if ((distanceX * distanceX + distanceY * distanceY) > 25){

                    Log.e("zheng", "onTouchEvent: " + "ACTION_MOVE");

                    mPreDownX = moveX;
                    mPreDownY = moveY;

                    Paint paintMove = new Paint();
//                    paintMove.setColor(colors[(int) (Math.random()*colors.length)]);
                    paintMove.setColor((int) (Math.random()*Integer.MAX_VALUE));
                    paintMove.setAntiAlias(true);
                    paintMove.setStyle(Paint.Style.STROKE);
                    paintMove.setStrokeWidth(30);

                    CircleHolder circleHolderMove = new CircleHolder(moveX,moveY,paintMove,RADIUS);
                    mCircleHolderList.add(circleHolderMove);

                    invalidate();
                }

                break;
        }

        return true;
    }
}
