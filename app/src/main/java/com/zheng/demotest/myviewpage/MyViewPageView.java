package com.zheng.demotest.myviewpage;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MyViewPageView extends ViewGroup{

    //  手势
    private Scroller mScroller;
    //  手势监听
    private GestureDetector mGestureDetector;

    public MyViewPageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);

        mGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            /**
             * 手指滑动
             * @param motionEvent
             * @param motionEvent1
             * @param v
             * @param v1
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

                scrollBy((int) v,0);

                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
    }

    /**
     * 让子控件排列
     * @param b
     * @param i
     * @param i1
     * @param i2
     * @param i3
     */
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        for (int j = 0; j < getChildCount(); j++){

            View view = getChildAt(j);
            view.layout(j * getWidth(),0,(j + 1) * getWidth(),getHeight());
        }
    }

    private float mDownX;
    private float mDownY;
    private int currentIndex = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        //  手势监听
        mGestureDetector.onTouchEvent(event);

        boolean intercept = false;

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                mDownX = event.getX();
                mDownY = event.getY();

                break;

            case MotionEvent.ACTION_MOVE:

                float moveX = event.getX();
                float moveY = event.getY();

                float distanceX = Math.abs(moveX - mDownX);
                float distanceY = Math.abs(moveY - mDownY);

                if (distanceX > distanceY){
                    intercept = true;
                }

                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return intercept;
    }

    /**
     * 是否需要跳到前个页面 下个页面 当前页面
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //  手势监听
        mGestureDetector.onTouchEvent(event);

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                mDownX = event.getX();

                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:

                Log.e("zheng", "onTouchEvent: " + "ACTION_UP");

                float upX = event.getX();

                int tempIndex = currentIndex;

                if ((upX - mDownX) > getWidth() / 2){

                    tempIndex--;

                }else if ((mDownX - upX) > getWidth() / 2){

                    tempIndex++;
                }

                scrollToPage(tempIndex);

                break;
        }

        return true;
    }

    /**
     * 根据索引来移动页面
     * @param tempIndex
     */
    public void scrollToPage(int tempIndex) {

        if (tempIndex < 0){

            tempIndex = 0;

        }else if (tempIndex > getChildCount() - 1){

            tempIndex = getChildCount() - 1;
        }

        currentIndex = tempIndex;
        Log.e("zheng", "scrollToPage: " + currentIndex);

        /**
         * 监听当前页面的索引
         */
        if (mChangePage != null){
            mChangePage.onChangePage(currentIndex);
        }

        /**
         * 目标位置减去当前位置
         */
        int distance = currentIndex * getWidth() - getScrollX();

        //  scroller.startScroll(当前的滑动X,当前的滑动Y,需要滑动X上的距离,需要滑动Y上的距离,毫秒);
        mScroller.startScroll(getScrollX(),getScrollY(),distance,getScrollY(),Math.abs(distance));

        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()){

            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        for (int i = 0; i < getChildCount(); i++){

            View childView = getChildAt(i);
            childView.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    /**
     * 监听页面变化回传页面索引
     */
    public interface ChangePage{

        void onChangePage(int currentIndex);
    }

    private ChangePage mChangePage;

    public void setChangePage(ChangePage changePage){

        this.mChangePage = changePage;
    }
}



















