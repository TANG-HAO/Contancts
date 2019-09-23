package com.hoperunn.testlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edt;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button)findViewById(R.id.bn);//获取组件
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 edt=(EditText)findViewById(R.id.edt);
                 edt.setText("单机bt");
            }
        });//匿名内部类为按钮绑定事件监听器
        bt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                edt=(EditText)findViewById(R.id.edt);
                edt.setText("触碰bt");
                return true;
            }
        });

    }
}
