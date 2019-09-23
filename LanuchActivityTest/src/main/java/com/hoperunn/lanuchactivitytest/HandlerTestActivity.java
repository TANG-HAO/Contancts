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

import androidx.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

public class HandlerTestActivity extends Activity {
    /**

     *

     * @author Administrator

    1）界面从“死亡”-->“运行"

    创建对象-->onCreate()-->onStart()-->onResume()---可见可操作(运行状态)

    2) 界面从“运行”-->“死亡"

    onPause()-->onStop()-->onDestroy()-->Activity对象成为垃圾对象---不可见也不存在死亡状态)

    3) 界面从“运行”-->“停止"

    onPause()-->onStop()---不可见但存在

    4) 界面从“停止” -->“运行"

    onRestart()-->onStart()-->onResume()

    5) 界面从“运行”-->“暂停"

    onPause()

    6) 界面从“暂停” -->“运行"

    onResume()



    重要的:

    1. onCreate(): 在Activity对象创建后调用, 只执行一次

    2. onDestroy(): 在Activity死亡之前调用, 只执行一次

    3. onResume(): 界面只有经历此方法才能可见可操作

     */





        public HandlerTestActivity() {

            Log.e("TAG", "HandlerTestActivity()");

        }


        //实现自动播放动画
        //图片整型数组
        int[] imageIds=new int[]{
                R.drawable.p,
                R.drawable.z,
                R.drawable.t
        };
        private int currentImageId=0;

        @Override

        protected void onCreate(Bundle savedInstanceState) {

            Log.e("TAG", "onCreate()");

            super.onCreate(savedInstanceState);
            setContentView(R.layout.handler_test);
            final ImageView imageView = (ImageView) findViewById(R.id.images);//获取图片组件 todo为什么一些组件的声明用final修饰
            final Button bt=(Button)findViewById(R.id.bt);
            final Handler handler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    if(msg.what==0x123){
                        imageView.setImageResource(imageIds[currentImageId++%imageIds.length]);//改变图片组件的数据来源
                    }
                }
            };//创建handler匿名内部类类
            //定义一个定时器该定时器周期性的执行某一任务
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0x123);
                }
            },0,1000);
            //为button绑定单击事件
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startSecond(view);
                }
            });

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


