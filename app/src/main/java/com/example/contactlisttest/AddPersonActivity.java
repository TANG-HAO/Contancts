package com.example.contactlisttest;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddPersonActivity extends AppCompatActivity{
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addperson);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("添加联系人");  //添加标题
        //todo 添加勾并注册监听器
        //toolbar.set
        setSupportActionBar(toolbar);
        //显示NavigationIcon,这个方法是ActionBar的方法.Toolbar没有这个方法.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回键  //todo添加返回图片
        toolbar.setNavigationIcon(R.drawable.addperson_back);
        //设置返回键的监听
        toolbar.setNavigationOnClickListener(new OnNavigationListener());
    }

    class OnNavigationListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

        }
    }
}
