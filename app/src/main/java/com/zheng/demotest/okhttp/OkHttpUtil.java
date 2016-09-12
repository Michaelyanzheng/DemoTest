package com.zheng.demotest.okhttp;

import android.app.Activity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/4.
 */
public class OkHttpUtil {

    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    public static void getRequest(final Activity activity, String url, final GetResponse getResponse){

        Request requestGet = new Request.Builder()
                .url(url)
                .build();

        Call callGet = mOkHttpClient.newCall(requestGet);

        callGet.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                if (getResponse != null){

                    final String err = e.toString();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getResponse.error(err);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (getResponse != null) {

                    final String result = response.body().string();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getResponse.response(result);
                        }
                    });
                }
            }
        });
    }

    public interface GetResponse{

        void error(String error);

        void response(String response);
    }
}
