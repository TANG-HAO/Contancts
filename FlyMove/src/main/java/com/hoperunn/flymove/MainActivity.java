package com.hoperunn.flymove;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private PaintFlyView paintFlyView;
    private int speed;//定义飞机的移动速度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除窗口标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示
        //创建PaintFlyView组件
        paintFlyView=new PaintFlyView(this);
        setContentView(paintFlyView);
        //paintFlyView.setBackgroundResource(R.drawable.back);
        //获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        //获取屏幕宽度高度
        display.getMetrics(metrics);
        //设置飞机的初始位置
        paintFlyView.currentx=metrics.widthPixels/2;
        paintFlyView.currenty=metrics.heightPixels-40;
        //为组件添加键盘事件
        paintFlyView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                //获取哪个键触发事件
                switch (keyEvent.getKeyCode()){
                    //控制飞机下移
                    case KeyEvent.KEYCODE_W:
                        paintFlyView.currenty+=speed;
                        break;
                    case KeyEvent.KEYCODE_S:
                        paintFlyView.currenty-=speed;
                        break;
                    case KeyEvent.KEYCODE_A:
                        paintFlyView.currentx-=speed;
                        break;
                    case KeyEvent.KEYCODE_D:
                        paintFlyView.currenty+=speed;
                        break;
                }
                paintFlyView.invalidate();
                return true;
            }
        });

    }
}
