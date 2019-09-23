package com.example.contactlisttest;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ContactingActivity extends AppCompatActivity {
    private LinearLayout keybroad;
    private Button callbroad;
    //获得号码
    private TextView tv_text2;
    private Button bt_delete;
    //拨号盘
    private Button bt_zero;
    private Button bt_one;
    private Button bt_two;
    private Button bt_three;
    private Button bt_four;
    private Button bt_five;
    private Button bt_six;
    private Button bt_seven;
    private Button bt_eight;
    private Button bt_nine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        keybroad=(LinearLayout)findViewById(R.id.keyboard);
        tv_text2 = (TextView) findViewById(R.id.tv_text2);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_zero = (Button) findViewById(R.id.bt_zero);
        bt_one = (Button) findViewById(R.id.bt_one);
        bt_two = (Button) findViewById(R.id.bt_two);
        bt_three = (Button) findViewById(R.id.bt_three);
        bt_four = (Button) findViewById(R.id.bt_four);
        bt_five = (Button) findViewById(R.id.bt_five);
        bt_six = (Button) findViewById(R.id.bt_six);
        bt_seven = (Button) findViewById(R.id.bt_seven);
        bt_eight = (Button) findViewById(R.id.bt_eight);
        bt_nine = (Button) findViewById(R.id.bt_nine);

        final FloatingActionButton fab = findViewById(R.id.fab_history);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(keybroad.getVisibility()==View.INVISIBLE){
                    keybroad.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.INVISIBLE);
                }else {
                    keybroad.setVisibility(View.INVISIBLE);
                }
            }
        });

        callbroad=(Button)findViewById(R.id.bt_callbroad);
        callbroad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setVisibility(View.VISIBLE);
                keybroad.setVisibility(View.INVISIBLE);
            }
        });

        bt_one.setOnClickListener(new OnKeyClickListener());

    }
    class OnKeyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View currentView) {
            String all=tv_text2.getText().toString()+((Button)currentView).getText().toString();
            tv_text2.setText(all);
            Toast.makeText(ContactingActivity.this,tv_text2.getText().toString(),Toast.LENGTH_SHORT).show();
            System.out.println(tv_text2.getText().toString());

        }
    }
}