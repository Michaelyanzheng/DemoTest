package com.zheng.demotest.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
public class FlowLayoutView extends ViewGroup {

    public FlowLayoutView(Context context) {
        this(context, null);
    }

    public FlowLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //  wrap_content 模式
        int width = 0;
        int height = 0;

        //  每行的宽高
        int lineWidth = 0;
        int lineHeight = 0;

        int lineMaxWidth = sizeWidth - getPaddingLeft() - getPaddingRight();

        //  孩子数目
        int countChild = getChildCount();

        for (int i = 0; i < countChild; i++){

            View child = getChildAt(i);

            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            // 当新加进来的View需要换行
            if (lineWidth + childWidth > lineMaxWidth){

                //  获得最大的宽度
                width = Math.max(width,lineWidth);

                //  重设每行的宽度
                lineWidth = childWidth;

                // 重设总高度 和 重新设置每行的高度
                height += lineHeight;
                lineHeight = childHeight;

            }else{
                //  不换行的时候

                //  增加每行的长度
                lineWidth += childWidth;

                //  获得当前行的最大的高度
                lineHeight = Math.max(lineHeight,childHeight);
            }

            //  最后一个控件
            if (i == countChild -1){

                width = Math.max(lineWidth,width);
                height += lineHeight;
            }
        }

        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );
    }

    //  存储所有的view
    private List<List<View>> mAllViews = new ArrayList<>();

    //  每行的高度
    private List<Integer> mLineHeight = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        // 每次清除所有的view 和 高度
        mAllViews.clear();
        mLineHeight.clear();

        //  每行的View
        List<View> lineViews = new ArrayList<>();

        //  ViewGroup 的宽度
        int width = getWidth();
        
        int lineWidth = 0;
        int lineHeight = 0;

        //  子控件的数目
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++){

            View child = getChildAt(i);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            //  子控件的宽高
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            //  换行
            if (lineWidth + childWidth > width - getPaddingLeft() - getPaddingRight()){

                //  记录每行的高度
                mLineHeight.add(lineHeight);
                //  把每行View 添加到所有View中
                mAllViews.add(lineViews);

                //  重置宽高
                lineWidth = 0;
                lineHeight = childHeight;

                // 重新初始化每行的View
                lineViews = new ArrayList<>();
            }

            lineWidth += childWidth;
            lineHeight = Math.max(lineHeight,childHeight);
            lineViews.add(child);
        }
        //  最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        // 设置子View的位置

        //  开始的左上坐标
        int left = getPaddingLeft();
        int top = getPaddingTop();

        //  行数
        int lineNum = mAllViews.size();

        for (int i = 0; i < lineNum; i++){

            lineHeight = mLineHeight.get(i);
            lineViews = mAllViews.get(i);

            for (int j = 0; j < lineViews.size(); j++){

                View child = lineViews.get(j);

                //  隐藏就跳过
                if (child.getVisibility() == View.GONE){
                    continue;
                }

                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                //  设置子控件的位置
                child.layout(lc,tc,rc,bc);

                //  左上角变化
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            //  换行的时候 重设左上角
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}

























