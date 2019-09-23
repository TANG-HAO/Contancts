package com.hoperunn.flymove;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class PaintFlyView extends View {
    public float currentx;
    public float currenty;
    Bitmap paintFly;
    public PaintFlyView(Context context){
        super(context);
        //从资源文件中生成绘图
        paintFly= BitmapFactory.decodeResource(context.getResources(),R.mipmap.fly);
        setFocusable(true);  //使得控件具有获得焦点的能力
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint paint = new Paint();
        //绘制飞机
        canvas.drawBitmap(paintFly,currentx,currenty,paint);
    }
}
