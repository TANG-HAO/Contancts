package com.example.contactlisttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SideBar extends View {
    //触摸事件监听器
    private OnTouchingLetterChangedListener listener;
    //定义二十六个字母数组
    private final static String[] TWNTY_SIX={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };
    //声明Pianter类
    private Paint paint=new Paint();
    //点击字母后出现的dialog   //todo???
    private TextView textViewDialog;
    //选中的状态
    private int choose=-1;

    public void setTextViewDialog(TextView textViewDialog){
        this.textViewDialog=textViewDialog;
    }

    public SideBar(Context context) {
        super(context);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //重写onDraw方法绘制字母

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取组件的高度进行绘制
        int height=getHeight();//获取组件的高度
        int width=getWidth();//获取组件的宽度
        int singleHeight=height/TWNTY_SIX.length;//单个字母的高度

        //遍历数组，设置绘图类的颜色，字体，开启抗锯齿
        for (int i = 0; i < TWNTY_SIX.length; i++) {
            paint.setColor(Color.rgb(33,65,98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextSize(20);
            paint.setAntiAlias(true);

            //todo 选中状态字母颜色改变
            if(i==choose){
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            //开始绘制，得到每个字母绘制的初始位置
            float xPos=width/2-paint.measureText(TWNTY_SIX[i]);
            float yPos=singleHeight*i+singleHeight;
            canvas.drawText(TWNTY_SIX[i],xPos,yPos,paint);
            paint.reset();//每次画完都，重置画笔
        }
    }

    //重写触摸屏幕事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action=event.getAction();//获取当前事件的内容
        final float y=event.getY();//获得点击的y坐标
        final int oldChoose=choose;
        final OnTouchingLetterChangedListener listener=this.listener;
        final int c=(int) (y/getHeight()*TWNTY_SIX.length);

        switch (action){
            case MotionEvent.ACTION_UP:  //监听触摸拿起事件
                //setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose=-1;// 离开触摸屏之后，则为无选中状态 否则字母的背景颜色回到原来的状态
                invalidate();//view刷新
                if(textViewDialog !=null){
                    textViewDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                //设置右侧字母列表的背景颜色
               //setBackgroundResource(R.drawable.sorlistview_show_head_toast_bg);//todo 只有action不为空时，才会有背景色？？？？
                //System.out.println(oldChoose);
                if(oldChoose!=c){
                    if(c>=0&&c<TWNTY_SIX.length){
                        if(listener!=null){
                            listener.onTouchingLetterChanged(TWNTY_SIX[c]);
                        }
                        if(textViewDialog!=null){
                            System.out.println(TWNTY_SIX[c]);
                            textViewDialog.setText(TWNTY_SIX[c]);
                            textViewDialog.setVisibility(View.VISIBLE);//可视
                        }
                        choose=c;
                        invalidate();//当view组件布局变化时进行刷新
                    }
                }
                break;
        }
        return true;
    }

    //向外公开的绑定监听方法
    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener){
        this.listener=onTouchingLetterChangedListener;
    }

    //内部接口，监听出力方法
    public interface OnTouchingLetterChangedListener{
        public void onTouchingLetterChanged(String s);
    }

}
