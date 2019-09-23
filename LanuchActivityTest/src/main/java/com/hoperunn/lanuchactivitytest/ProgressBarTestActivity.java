package com.hoperunn.lanuchactivitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressBarTestActivity extends Activity {
    public ProgressBarTestActivity() {

        Log.e("TAG", "ProgressBarTestActivity()");

    }


   //使程序模拟填充程度为100的数组
    private int[] data=new int[100];
    int hasData=0;
    //记录ProgressBar的进度
    int status=0;
    ProgressBar bar,bar2;
    //创建一个负责更新的进度的Handler
    final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==0x11){
                bar.setProgress(status);
                bar2.setProgress(status);
            }
        }
    };

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        Log.e("TAG", "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.progessbar_test);
        bar=(ProgressBar)findViewById(R.id.bar);
        bar2=(ProgressBar)findViewById(R.id.bar2);
        //启动线程执行任务
        new Thread(){
            @Override
            public void run() {
                while (status<100){
                    Message msg=new Message();
                    msg.what=0x11;
                    //获取耗时操作的完成百分比
                    status=dowork();
                    //发送消息
                    handler.sendMessageDelayed(msg,10000);  //周期性发送指定任务
                }
            }
        }.start();
    }

    private int dowork() {
        //为数组元素赋值
        data[hasData++]=(int)(Math.random()*100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }


    @Override

    protected void onStart() {

        Log.e("TAG", "onStart()");

        super.onStart();

    }



    @Override

    protected void onResume() {

        Log.e("TAG", "onResume()");

        super.onResume();

    }



    @Override

    protected void onPause() {

        Log.e("TAG", "onPause()");

        super.onPause();

    }



    @Override

    protected void onStop() {

        Log.e("TAG", "onStop()");

        super.onStop();

    }



    @Override

    protected void onRestart() {

        Log.e("TAG", "onRestart()");

        super.onRestart();

    }



    @Override

    protected void onDestroy() {

        Log.e("TAG", "onDestroy()");

        super.onDestroy();

    }



    public void startSecond(View v) {

        startActivity(new Intent(this, ExpandableListActivityTest.class));

    }

}
