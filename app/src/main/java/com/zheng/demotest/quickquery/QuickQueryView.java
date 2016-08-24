package com.zheng.demotest.quickquery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/8/23.
 */
public class QuickQueryView extends View {

    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private Paint mPaint;

    private int itemWidth;
    private int itemHeight;

    private int index;



    public QuickQueryView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private void initView() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextSize(22);

        index = -1;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        itemWidth = getMeasuredWidth();
        itemHeight = getMeasuredHeight() / words.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < words.length; i++){

            if (index == i){

                mPaint.setColor(Color.GRAY);

            }else {

                mPaint.setColor(Color.WHITE);

            }

            String word = words[i];

            Rect rect = new Rect();
            mPaint.getTextBounds(word,0,1,rect);

            int wordX = itemWidth / 2 - rect.width() / 2;
            int wordY = itemHeight / 2 - rect.height() / 2 + i * itemHeight;

            canvas.drawText(words[i],wordX,wordY,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float moveY;

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:

                moveY = event.getY();

                index = (int) (moveY / itemHeight);

                if (mChangeWord != null){
                    mChangeWord.onChangeWord(words[index]);
                }

                invalidate();

                break;

            case MotionEvent.ACTION_UP:

                index = -1;
                invalidate();

                break;
        }

        return true;
    }

    public interface ChangeWord{

        void onChangeWord(String word);
    }

    private ChangeWord mChangeWord;

    public void setChangeWord(ChangeWord changeWord){

        this.mChangeWord = changeWord;
    }
}








