package com.zheng.demotest.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zheng.demotest.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/31.
 */
public class OkhttpActivity extends Activity implements View.OnClickListener {

    public static final MediaType JSON = MediaType.parse("application/mJson,charset=utf-8");
    private String [] mUrlArray = {"https://www.taobao.com","https://www.baidu.com","https://www.youku.com","https://www.tudou.com"};

    private Button mBtnOkhttpGet;
    private Button mBtnOkhttpPost;
    private TextView mTvOkhttpGet;

    private OkHttpClient mOkHttpClient;
    private Request mRequestGet;
    private Call mCallGet;

    private String mStringJson;
    private RequestBody mRequestBodyPost;
    private Request mRequestPost;
    private Call mCallPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        mBtnOkhttpGet = (Button) findViewById(R.id.btn_okhttp_get);
        mBtnOkhttpPost = (Button) findViewById(R.id.btn_okhttp_post);
        mTvOkhttpGet = (TextView) findViewById(R.id.tv_okhttp_get);

        mBtnOkhttpGet.setOnClickListener(this);
        mBtnOkhttpPost.setOnClickListener(this);

        mOkHttpClient = new OkHttpClient();
//        mRequestGet = new Request.Builder().url(url).build();
//        mCallGet = mOkHttpClient.newCall(mRequestGet);


        mStringJson = bowlingJson("zheng","michael");
        mRequestBodyPost = RequestBody.create(JSON,mStringJson);
        mRequestPost = new Request.Builder()
                .url("http://www.roundsapp.com/post")
                .post(mRequestBodyPost)
                .build();

        mCallPost = mOkHttpClient.newCall(mRequestPost);
    }

    private String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

    private int i = 0;

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_okhttp_get:

                if (i >= mUrlArray.length){
                    i = 0;
                }

                OkHttpUtil.getRequest(this, mUrlArray[i++], new OkHttpUtil.GetResponse() {
                    @Override
                    public void error(String error) {
                        mTvOkhttpGet.setText(error);
                    }

                    @Override
                    public void response(String response) {
                        mTvOkhttpGet.setText(response);
                    }
                });


//                mCallGet.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        final String res = e.getMessage();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                mTvOkhttpGet.setText(res);
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//
//                        final String res = response.body().string();
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                mTvOkhttpGet.setText(res);
//                            }
//                        });
//                    }
//                });

                break;

            case R.id.btn_okhttp_post:

                mCallPost.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        final String res = e.getMessage();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                mTvOkhttpGet.setText(res);
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        final String res = response.body().string();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        mTvOkhttpGet.setText(res);
                                    }
                                });
                            }
                        });
                    }
                });

                break;
        }
    }
}
