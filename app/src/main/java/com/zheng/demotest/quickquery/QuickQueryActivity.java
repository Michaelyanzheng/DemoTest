package com.zheng.demotest.quickquery;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zheng.demotest.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class QuickQueryActivity extends Activity {

    private TextView mTextView;
    private QuickQueryView mQuickQueryView;
    private ListView mListView;

    private GestureDetector mGestureDetector;

    private List<Person> mPersonList;
    private MyAdapter mMyAdapter;
    private MyOnItemClickListener mMyOnItemClickListener;
    private MyOnItemLongClickListener mMyOnItemLongClickListener;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickquery);

        mTextView = (TextView) findViewById(R.id.tv_show);
        mQuickQueryView = (QuickQueryView) findViewById(R.id.qqv_main);
        mListView = (ListView) findViewById(R.id.lv_main);

        //  快速查询View 触摸监听
        mQuickQueryView.setChangeWord(new MyChangeWord());

        //  初始化联系人数据
        initData();

        //  设置 ListView Adapter 和 点击事件监听 长按事件
        mMyAdapter = new MyAdapter();
        mMyOnItemClickListener = new MyOnItemClickListener();
        mMyOnItemLongClickListener = new MyOnItemLongClickListener();
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemClickListener(mMyOnItemClickListener);
        mListView.setOnItemLongClickListener(mMyOnItemLongClickListener);

        mGestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
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

        mListView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int first = mListView.getFirstVisiblePosition();
                int last = mListView.getLastVisiblePosition();

                int middle = first + (last - first) / 2;

                String firstName = mPersonList.get(middle).getName().substring(0,1);

                changeText(firstName);


                return false;
            }
        });
    }

    /**
     * ListView 点击事件
     */
    class MyOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Toast.makeText(QuickQueryActivity.this, mPersonList.get(i).getName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+ mPersonList.get(i).getTelephone()));//mobile为你要拨打的电话号码，模拟器中为模拟器编号也可
            startActivity(intent);
        }
    }

    class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener{

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            Toast.makeText(QuickQueryActivity.this, "电话号码已经复制到粘贴板", Toast.LENGTH_SHORT).show();

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

            clipboardManager.setText(mPersonList.get(i).getTelephone());

            return true;
        }
    }


    /**
     * ListView Adapter
     */
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mPersonList.size();
        }

        @Override
        public Object getItem(int i) {
            return mPersonList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder = null;

            if (view == null){

                view = View.inflate(QuickQueryActivity.this,R.layout.item_quickquery_main,null);

                viewHolder = new ViewHolder();

                viewHolder.mTvWord = (TextView) view.findViewById(R.id.tv_word);
                viewHolder.mTvName = (TextView) view.findViewById(R.id.tv_name);
                viewHolder.mTvTelephone = (TextView) view.findViewById(R.id.tv_telephone);

                view.setTag(viewHolder);

            }else {

                viewHolder = (ViewHolder) view.getTag();

            }

            Person person = mPersonList.get(position);

            //  除了第一个字母都显示，其他要是当前字母和上一个相同就隐藏
            if(position == 0){

                viewHolder.mTvWord.setVisibility(View.VISIBLE);

            }else{

                String preWord = mPersonList.get(position - 1).getPinyin().substring(0,1);
                String curWord = person.getPinyin().substring(0,1);

                if (preWord.equals(curWord)){

                    viewHolder.mTvWord.setVisibility(View.GONE);

                }else{

                    viewHolder.mTvWord.setVisibility(View.VISIBLE);
                    viewHolder.mTvWord.setText(curWord);
                }
            }

            viewHolder.mTvName.setText(person.getName());
            viewHolder.mTvTelephone.setText(person.getTelephone());

            return view;
        }

        class ViewHolder{

            TextView mTvWord;
            TextView mTvName;
            TextView mTvTelephone;
        }
    }

    /**
     * 快速查询的触摸的回传
     */
    class MyChangeWord implements QuickQueryView.ChangeWord {

        @Override
        public void onChangeWord(String word) {
            changeText(word);
            changeListView(word);
        }
    }

    /**
     * 改变ListView 位置
     * @param word
     */
    private void changeListView(String word) {

        for (int i = 0; i < mPersonList.size(); i++){

            if (mPersonList.get(i).getPinyin().substring(0,1).equals(word)){

                mListView.setSelection(i);
                break;
            }
        }
    }

    /**
     * 改变文字
     * @param word
     */
    private void changeText(String word){

        mTextView.setText(word);
        mTextView.setVisibility(View.VISIBLE);

        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                mTextView.setVisibility(View.GONE);
            }
        },3000);
    }

    /**
     * 初始化数据
     */
    private void initData() {

        mPersonList = new ArrayList<>();

        Cursor cursor = null;

        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);

        while (cursor.moveToNext()){

            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String telephone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            mPersonList.add(new Person(name,telephone));
        }

        if (cursor != null){

            cursor.close();
        }

        //排序
        Collections.sort(mPersonList, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
    }
}
