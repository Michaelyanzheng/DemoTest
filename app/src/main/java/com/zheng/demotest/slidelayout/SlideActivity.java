package com.zheng.demotest.slidelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zheng.demotest.R;

import java.util.ArrayList;
import java.util.List;

public class SlideActivity extends Activity {

    private ListView mListView;

    private List<MyBean> mMyBeanList;
    private MyAdapter mMyAdapter;

    private SlideLayout mSlideLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        mMyBeanList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {

            MyBean myBean = new MyBean("content" + i);
            mMyBeanList.add(myBean);
        }

        mListView = (ListView) findViewById(R.id.lv_main);

        mMyAdapter = new MyAdapter();
        mListView.setAdapter(mMyAdapter);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mMyBeanList.size();
        }

        @Override
        public Object getItem(int i) {
            return mMyBeanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder;

            if (view == null) {
                view = View.inflate(SlideActivity.this, R.layout.item_main, null);

                viewHolder = new ViewHolder();

                viewHolder.mTvContent = (TextView) view.findViewById(R.id.item_content);
                viewHolder.mTvDelete = (TextView) view.findViewById(R.id.item_delete);

                view.setTag(viewHolder);

            } else {

                viewHolder = (ViewHolder) view.getTag();

            }

            final MyBean myBean = mMyBeanList.get(position);

            viewHolder.mTvContent.setText(myBean.getName());
            viewHolder.mTvDelete.setText("Delete");

            viewHolder.mTvContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MyBean myBean1 = mMyBeanList.get(position);
                    Toast.makeText(SlideActivity.this, myBean1.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.mTvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SlideLayout slideLayout = (SlideLayout) view.getParent();
                    slideLayout.closeDelete();

                    mMyBeanList.remove(position);
                    notifyDataSetChanged();

                }
            });

            SlideLayout slideLayout = (SlideLayout) view;
            slideLayout.setStateChangerListener(new MyStateChangerListener());

            return view;
        }

        class ViewHolder {

            private TextView mTvContent;
            private TextView mTvDelete;
        }
    }

    class MyStateChangerListener implements SlideLayout.onStateChangerListener{

        @Override
        public void onDown(SlideLayout slideLayout) {

            if (mSlideLayout != null && mSlideLayout != slideLayout){
                mSlideLayout.closeDelete();
            }
        }

        @Override
        public void onOpen(SlideLayout slideLayout) {

            mSlideLayout = slideLayout;
        }

        @Override
        public void onClose(SlideLayout slideLayout) {

            if (mSlideLayout == slideLayout){
                mSlideLayout = null;
            }
        }
    }
}
