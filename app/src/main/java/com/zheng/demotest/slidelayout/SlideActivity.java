package com.zheng.demotest.slidelayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

//    @BindView(R.id.rv_main)
//    private RecyclerView mRvMain;

    private ListView mListView;

    private MyRecyclerViewAdapter mMyRecyclerViewAdapter;

    private List<MyBean> mMyBeanList;
    private MyAdapter mMyAdapter;

    private SlideLayout mSlideLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
//        ButterKnife.bind(this);

        initView();
        mListView = (ListView) findViewById(R.id.lv_main);

        mMyAdapter = new MyAdapter();
        mListView.setAdapter(mMyAdapter);

//        mRvMain = (RecyclerView) findViewById(R.id.rv_main);
//
//        mMyRecyclerViewAdapter = new MyRecyclerViewAdapter();
//
//
//        mRvMain.setLayoutManager(new LinearLayoutManager(this));
//
//        mRvMain.setAdapter(mMyRecyclerViewAdapter);

    }

    private void initView(){

        mMyBeanList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {

            MyBean myBean = new MyBean("content" + i);
            mMyBeanList.add(myBean);
        }
    }


    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(View.inflate(SlideActivity.this,R.layout.item_main,null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            MyBean myBean = mMyBeanList.get(position);
            holder.mTvContent.setText(myBean.getName());
            holder.mTvDelete.setText("delete");
        }

        @Override
        public int getItemCount() {
            return mMyBeanList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView mTvContent;
            private TextView mTvDelete;

            public MyViewHolder(View itemView) {
                super(itemView);

                mTvContent = (TextView) itemView.findViewById(R.id.item_content);
                mTvDelete = (TextView) itemView.findViewById(R.id.item_delete);
            }
        }
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

    class MyStateChangerListener implements SlideLayout.onStateChangerListener {

        @Override
        public void onDown(SlideLayout slideLayout) {

            if (mSlideLayout != null && mSlideLayout != slideLayout) {
                mSlideLayout.closeDelete();
            }
        }

        @Override
        public void onOpen(SlideLayout slideLayout) {

            mSlideLayout = slideLayout;
        }

        @Override
        public void onClose(SlideLayout slideLayout) {

            if (mSlideLayout == slideLayout) {
                mSlideLayout = null;
            }
        }
    }
}
