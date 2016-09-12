package com.zheng.demotest.serviceactivity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyService extends Service {

    private BinderHolder mBinderHolder;

    private Handler mHandler;

    private MyThread mMyThread;

    private int mIndex;

    private boolean mStartBoolean = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinderHolder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mBinderHolder = new BinderHolder();

        mMyThread = new MyThread();
        mMyThread.start();

    }

    class MyThread extends Thread{

        @Override
        public void run() {

            while (true){

                if (mStartBoolean){

                    mIndex++;

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message message = mHandler.obtainMessage();
                    message.arg1 = mIndex;

                    mHandler.sendMessage(message);

                }
            }
        }
    }

    class BinderHolder extends Binder {

        public void changeTextView(final TextView textView){

            mStartBoolean = true;

            mHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    textView.setText("index : " + msg.arg1+"");
                }
            };
        }
    }
}
