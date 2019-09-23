package com.example.contactlisttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainActivity extends TabActivity {
    private  TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);
        //获取Tabhost组件
        tabHost=getTabHost();
        //创建并添加Tab页面
        addTap("tab1","通话",ContactingActivity.class);
        addTap("tab2","联系人",LinkedPersonActivity.class);

//注意这个就是改变Tabhost默认样式的地方，一定将这部分代码放在上面这段代码的下面，不然样式改变不了
        for (int i =0; i < tabHost.getTabWidget().getChildCount(); i++) {
            //修改Tabhost高度和宽度
            //tabHost.getTabWidget().getChildAt(i).getLayoutParams().height = 60;
            //tabHost.getTabWidget().getChildAt(i).getLayoutParams().width = 65;
            //修改显示字体大小
            //tabHost.setBackgroundColor(Color.parseColor("#F0F0F0"));
            TextView tv = (TextView)tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            //tv.setTypeface(Typeface.SERIF, 2);
            //tv.setBackgroundColor(Color.parseColor("#F0F0F0"));
            //tv.setBackgroundResource(R.color.white);
            tv.setTextSize(15);
            tv.setTextColor(this.getResources().getColorStateList(android.R.color.holo_blue_dark));
        }
        //为标签页的添加单击事件，改变字体颜色
        tabHost.setOnTabChangedListener(new OnTabChangedListener());
    }
    //将创建tab页面与添加tab封装成方法
    private void addTap(String tag, String title, Class clazz){
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag).setIndicator(title).setContent(new Intent(this, clazz));
        tabHost.addTab(tabSpec);
    }

    //重写Ontab的接口
    class OnTabChangedListener implements TabHost.OnTabChangeListener {

        @Override
        public void onTabChanged(String tabId) {
            updateTab(tabHost);
            System.out.println("1");
        }
    }
//todo 完善了点击tab项字体颜色变化
    private void updateTab(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View view = tabHost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            //tv.setTextSize(16);
            //tv.setTypeface(Typeface.SERIF, 2); // 设置字体和风格
            if (tabHost.getCurrentTab() == i) {//选中
               // view.setBackgroundDrawable(getResources().getDrawable(R.drawable.category_current));//选中后的背景
                tv.setTextColor(this.getResources().getColorStateList(
                        android.R.color.holo_blue_light));
            } else {//不选中
               // view.setBackgroundDrawable(getResources().getDrawable(R.drawable.category_bg));//非选择的背景
                tv.setTextColor(this.getResources().getColorStateList(
                        android.R.color.black));
            }
        }
    }





}
