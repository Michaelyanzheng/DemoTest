package com.zheng.demotest.wave;

import android.graphics.Paint;

/**
 * Created by Administrator on 2016/8/22.
 */
public class CircleHolder {

    private double mDownX;
    private double mDownY;

    private Paint mPaint;
    private int mRadius;

    public CircleHolder(double downX, double downY, Paint paint, int radius) {
        mDownX = downX;
        mDownY = downY;
        mPaint = paint;
        mRadius = radius;
    }

    public double getDownX() {
        return mDownX;
    }

    public void setDownX(double downX) {
        mDownX = downX;
    }

    public double getDownY() {
        return mDownY;
    }

    public void setDownY(double downY) {
        mDownY = downY;
    }

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }
}
