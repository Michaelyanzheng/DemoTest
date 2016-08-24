package com.zheng.demotest.slidelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SlideLayout extends FrameLayout{

    //  Content Delete View
    private View mContentView;
    private View mDeleteView;

    //  Content delete长度 他们的高度
    private int mContentWidth;
    private int mDeleteWidth;
    private int mHeight;

    //  滑动
    private Scroller mScroller;

    //
    private double mStartX;

    private double mDownX;
    private double mDownY;

    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context);
    }

    /**
     * 测量内容的长，高，delete的长
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mContentView = getChildAt(0);
        mDeleteView = getChildAt(1);

        mContentWidth = mContentView.getMeasuredWidth();
        mHeight = mContentView.getMeasuredHeight();

        mDeleteWidth = mDeleteView.getMeasuredWidth();
    }

    /**
     * 绘制delete的位置
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mDeleteView.layout(mContentWidth, 0, mContentWidth + mDeleteWidth, mHeight);
    }

    /**
     * 如果不在move事件中做拦截处理，就会事件传到子控件去
     * SlideLayout的onTouchEvent就不会处理，SlideLayout就不会发生滑动
     * @param event
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        boolean intercept = false;

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                mDownX = event.getX();

                if (mOnStateChangerListener != null){
                    mOnStateChangerListener.onDown(this);
                }

                break;

            case MotionEvent.ACTION_MOVE:

                double moveX = event.getX();

                double distanceX = moveX - mDownX;

                if (Math.abs(distanceX) > 2){

                    intercept = true;
                }

                break;
        }

        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                Log.e("zheng", "onTouchEvent: " + "ACTION_DOWN");

                mStartX = mDownX = event.getX();
                mDownY = event.getY();

                break;

            case MotionEvent.ACTION_MOVE:

                Log.e("zheng", "onTouchEvent: " + "ACTION_MOVE");

                double mMoveX = event.getX();
                double mMoveY = event.getY();


                /**
                 * 滑动处理
                 */
                double distanceX = mMoveX - mStartX;

                int toScrollX = (int) (getScrollX() - distanceX);

//                Log.e("zheng", "distanceX " + distanceX + " toScrollX " + toScrollX);

                if (toScrollX < 0){
                    toScrollX = 0;
                }else if (toScrollX > mDeleteWidth){
                    toScrollX = mDeleteWidth;
                }

                scrollTo(toScrollX,getScrollY());

                mStartX = mMoveX;


                /**
                 * 如果上下左右滑动的时候，
                 * 当左右滑动的距离大于上下滑动的距离，
                 * 不要让父控件来消费事件，反拦截父控件来消费事件，让事件传到本控件
                 */

                //  X Y 滑动的距离
                double DX = Math.abs(mMoveX - mDownX);
                double DY = Math.abs(mMoveY - mDownY);

                //  水平方向滑动 反拦截 事件给SlideLayout
                if (DX > DY && DX > 2){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;

            case MotionEvent.ACTION_UP:

                Log.e("zheng", "onTouchEvent: " + "ACTION_UP");

                double scrollX = getScrollX();

                if (scrollX > mDeleteWidth / 2){
                    //  打开删除
                    openDelete();
                }else {
                    //  关闭删除
                    closeDelete();
                }

                break;
        }

        return true;
    }

    private void openDelete() {

        int scrollerX = mDeleteWidth - getScrollX();

//        Log.e("zheng", "openDelete: " + scrollerX);

        mScroller.startScroll(getScrollX(), getScrollY(), scrollerX, getScrollY());

        invalidate();

        if (mOnStateChangerListener != null){
            mOnStateChangerListener.onOpen(this);
        }
    }

    public void closeDelete() {

        int scrollerX = 0 - getScrollX();

//        Log.e("zheng", "closeDelete: " + scrollerX);

        mScroller.startScroll(getScrollX(), getScrollY(), scrollerX, getScrollY());

        invalidate();

        if (mOnStateChangerListener != null){
            mOnStateChangerListener.onClose(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
        }
    }

    /**
     * 状态监听
     */
    public interface onStateChangerListener{

        void onDown(SlideLayout slideLayout);
        void onOpen(SlideLayout slideLayout);
        void onClose(SlideLayout slideLayout);
    }

    public void setStateChangerListener(onStateChangerListener onStateChangerListener){

        this.mOnStateChangerListener = onStateChangerListener;
    }

    private onStateChangerListener mOnStateChangerListener;
}
