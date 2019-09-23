package com.hoperunn.lanuchactivitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends LauncherActivity {
    String[] names={"设置程序参数","查看星际特种兵"};//定义两个activity的名称
    //定义两个activity的实现类
    Class<?>[] clazzs={PreferenceActivityTest.class,ExpandableListActivityTest.class};

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(MainActivity.this,clazzs[position]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        //设置窗口显示的列表所需的adapter
        setListAdapter(arrayAdapter);
        //根据列表项返回指定Activity对应的Intent
    }
}
