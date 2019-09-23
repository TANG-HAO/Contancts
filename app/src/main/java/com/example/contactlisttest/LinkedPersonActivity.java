package com.example.contactlisttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.contactlisttest.utils.SystemConstants.EDITE_ACTION;
import static com.example.contactlisttest.utils.SystemConstants.EDITE_CATEGORY;

public class LinkedPersonActivity extends Activity {
    private TextView textDialog;//点击sideBar中的字母弹出相应的提示框
    private SideBar sideBar;  //侧边sidebar
    private ListView listView;  //列表项组件
    private SortAdapter adapter;  //排序适配器
    private ClearEditText mclearEditText;//搜索栏

    private CharacterParser characterParser;  //汉字转拼音类
    private List<SortModel> sourceDateList;

    private PinYinComparator pinYinComparator;//根据拼音来排序ListView里面的数据


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//去除键盘挤压的影响
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化排序好的数据



    }
    //初始化数据  往ListView中塞入数据 adapter  //todo 改用数据库
    private void initView() {
        //实例化汉子转拼音
        characterParser=CharacterParser.getInstance();
        pinYinComparator = new PinYinComparator();

        sideBar=(SideBar)findViewById(R.id.sidebar);
        textDialog=(TextView)findViewById(R.id.dialog);
        sideBar.setTextViewDialog(textDialog);//传入参数，实现点击弹出相应字母

        //为右侧sidebar添加点击监听事件
        sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangeListener());

        //todo 点击listView中的列表项弹出相应text（toast）
        listView=(ListView)findViewById(R.id.personifo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
               // Toast.makeText(getApplication(),((SortModel) adapter.getItem(position)).getName(),Toast.LENGTH_SHORT).show();
                //bundle绑定数据
                Bundle bundle = new Bundle();
                System.out.println(((SortModel)adapter.getItem(position)).getName());
                bundle.putString("name",((SortModel)adapter.getItem(position)).getName());
                Intent intent = new Intent();
                intent.setAction(EDITE_ACTION);
                intent.addCategory(EDITE_CATEGORY);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //todo 优化数据从数据库读取出来
        System.out.println("source中的数组长度=====>"+getResources().getStringArray(R.array.date).length);

        sourceDateList=filledData(getResources().getStringArray(R.array.date));  //初始数据

        //根据a-z排序元数据  赛数据
        Collections.sort(sourceDateList,pinYinComparator);
        adapter=new SortAdapter(this,sourceDateList);
        listView.setAdapter(adapter);
        //搜索栏  根据输入框的输入值的改变来过滤搜索
        mclearEditText=(ClearEditText)findViewById(R.id.filter_edit);
        mclearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int coun) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    /**
     * 为ListView填充数据
     *
     * @param data
     * @return
     */
    private List<SortModel> filledData(String[] data) {
        List<SortModel> mSortList=new ArrayList<SortModel>();
        for (int i = 0; i <data.length; i++) {
            SortModel sortModel = new SortModel();
            sortModel.setName(data[i]);
            System.out.println("data[i]======"+data[i]);
            //汉字转为拼音
            String pinyin=characterParser.getSelling(data[i]);
            System.out.println("pinyin===============>"+pinyin);
            String sortString = pinyin.substring(0,1).toUpperCase();

            //正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    class OnTouchingLetterChangeListener implements SideBar.OnTouchingLetterChangedListener{
        @Override
        public void onTouchingLetterChanged(String s) {
            //该字母首次出现的位置
            System.out.println("s.charAt(0)========>"+s.charAt(0));
            int position=adapter.getPositionForSection(s.charAt(0));
            System.out.println("position=======>"+position);
            if(position!=-1){
                listView.setSelection(position);
            }
        }
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<SortModel> filterDateList = new ArrayList<>();

        if(TextUtils.isEmpty(filterStr)){
            filterDateList=sourceDateList;
        }else{
            filterDateList.clear();
            for(SortModel sortModel:sourceDateList){
                String name=sortModel.getName();
                if(name.indexOf(filterStr.toString())!=-1||characterParser.getSelling(name).startsWith(filterStr.toString())){
                    filterDateList.add(sortModel);
                }
            }
        }
        Collections.sort(filterDateList,pinYinComparator);
        adapter.updateListView(filterDateList);//TODO 更新界面出现问题
    }

}
