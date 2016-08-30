package com.zheng.demotest.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/8/27.
 */
public class HandlerActivity extends Activity {

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Toast.makeText(HandlerActivity.this, "handleMessage", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//
//                Message message = new Message();
//
//                mHandler.sendMessage(message);
//
//            }
//        }).start();


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Message message = Message.obtain();
//                mHandler.sendMessage(message);

//                Toast.makeText(HandlerActivity.this, "handleMessage", Toast.LENGTH_SHORT).show();
            }
        },8000);


    }
}
