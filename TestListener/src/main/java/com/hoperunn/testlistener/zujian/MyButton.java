package com.hoperunn.testlistener.zujian;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;


import com.hoperunn.testlistener.R;

public class MyButton extends Button {
    private EditText edt;
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //事件源与事件监听相统一
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        Log.v("-MyButton-","the onkeyDown in MyButton");
        edt=(EditText)findViewById(R.id.edt);
        edt.setText("回调单击");
        return true;  //表明事件不会向外扩散
    }
}
