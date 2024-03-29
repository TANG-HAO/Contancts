package com.example.contactlisttest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

public class ClearEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {
//    删除按钮的引用
    private Drawable mClearDrawable;
    public ClearEditText(Context context) {
        this(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context,attrs,android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //获取editTest的DrawableRight,若没有设置我们就使用默认的图片
        mClearDrawable= getCompoundDrawables()[2];
        if(mClearDrawable==null){
            mClearDrawable=getResources().getDrawable(R.drawable.sorlistview_emotionstore_progresscancelbtn);
        }
        mClearDrawable.setBounds(0,0,mClearDrawable.getIntrinsicWidth(),mClearDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    //设置清楚图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
    private void setClearIconVisible(boolean visible) {
        Drawable right=visible ? mClearDrawable:null;
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],right,getCompoundDrawables()[3]);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View view, boolean b) {
        if(hasFocus()){
            setClearIconVisible(getText().length()>0);
        }else{
            setClearIconVisible(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
        setClearIconVisible(charSequence.length()>0);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
     * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(getCompoundDrawables()[2]!=null){
            if(event.getAction()==MotionEvent.ACTION_UP){
              boolean touchable= event.getX() >(getWidth()-getPaddingRight()-mClearDrawable.getIntrinsicWidth())&&(event.getX()<(getWidth()-getPaddingRight()));
              if (touchable){
                  this.setText("");
              }
            }
        }
        return super.onTouchEvent(event);
    }
    /**
     * 设置晃动动画
     */
    public void setSharkeAnimation(){
        this.setAnimation(sharkeAnimation(5));
    }
    /**
     * 晃动动画
     *
     * @param counts
     *            1秒钟晃动多少下
     * @return
     */
    private static Animation sharkeAnimation(int counts) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }


}
