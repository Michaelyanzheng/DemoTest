package com.zheng.demotest.serviceactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zheng.demotest.R;

/**
 * Created by Administrator on 2016/9/6.
 */
public class ServiceChangeActivityActivity extends Activity {

    private TextView mTvChange;
    private Button mBtnChange;

    private MyService.BinderHolder mBinder;

    private MyServiceConnection mMyServiceConnection;

    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Toast.makeText(ServiceChangeActivityActivity.this,"fuck",Toast.LENGTH_SHORT).show();

            mBinder = (MyService.BinderHolder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.service_change_activity_activity);

        mTvChange = (TextView) findViewById(R.id.tv_change);
        mBtnChange = (Button) findViewById(R.id.btn_change);

        mMyServiceConnection = new MyServiceConnection();

        Intent intent = new Intent(this,MyService.class);
        bindService(intent,mMyServiceConnection, Context.BIND_AUTO_CREATE);

        mBtnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ServiceChangeActivityActivity.this,"onClick",Toast.LENGTH_SHORT).show();

                mBinder.changeTextView(mTvChange);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbindService(mMyServiceConnection);
    }
}
