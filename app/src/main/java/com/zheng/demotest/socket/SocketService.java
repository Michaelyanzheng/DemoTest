package com.zheng.demotest.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/9/13.
 */
public class SocketService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();


        new Thread(new AndroidRunable()).start();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class AndroidRunable implements Runnable {

        @Override
        public void run() {

            while (true){

                ServerSocket mServerSocket = null;

                Socket socket = null;
                try {

                    mServerSocket = new ServerSocket(30000);
                    socket = mServerSocket.accept();

                    // 向android客户端输出hello worild
                    String line = null;
                    InputStream input;
                    OutputStream output;
                    String str = "hello world!";
                    try {
                        //向客户端发送信息
                        output = socket.getOutputStream();
                        input = socket.getInputStream();
                        BufferedReader bff = new BufferedReader(
                                new InputStreamReader(input));
                        output.write(str.getBytes("gbk"));
                        output.flush();
                        //半关闭socket
                        socket.shutdownOutput();
                        //获取客户端的信息


                        //读取发来服务器信息
                        String buffer = "";
                        while ((line = bff.readLine()) != null) {

                            buffer = line + buffer;
                        }
                        Log.e("zheng", "run: " + buffer);
                        //关闭输入输出流
                        output.close();
                        bff.close();
                        input.close();
                        socket.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
