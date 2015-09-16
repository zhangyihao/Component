package com.zhangyihao.component.controls;


import com.zhangyihao.component.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class ClearEditText extends EditText implements OnFocusChangeListener, TextWatcher {
	/** 删除按钮的引用*/
    private Drawable mClearDrawable;
    /** 添加焦点监听事件 */
    private OnFocusChangeListener onFocusChangeListener;
    
    public ClearEditText(Context context) { 
    	this(context, null); 
    } 
 
    public ClearEditText(Context context, AttributeSet attrs) { 
    	//这里构造方法也很重要，不加这个很多属性不能再XML里面定义
    	this(context, attrs, android.R.attr.editTextStyle); 
    } 
    
    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    private void init() {
    	this.setFocusable(true);
    	this.setFocusableInTouchMode(true);
    	//获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
    	mClearDrawable = getCompoundDrawables()[2]; 
        if (mClearDrawable == null) { 
        	mClearDrawable = getResources().getDrawable(R.drawable.login_delete); 
        } 
        
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight()); 
        //默认设置隐藏图标
        setClearIconVisible(false); 
        //设置焦点改变的监听
        setOnFocusChangeListener(this); 
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this); 
    }
    
    public void addOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
    	this.onFocusChangeListener = onFocusChangeListener;
    }
 
    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @SuppressLint("ClickableViewAccessibility")
	@Override 
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (getCompoundDrawables()[2] != null) {

				boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
				if (touchable) {
					//里面写上自己想做的事情，也就是DrawableRight的触发事件
					this.setText("");
				}
			}
		}

		return super.onTouchEvent(event);
	}

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override 
    public void onFocusChange(View v, boolean hasFocus) {
    	if(this.onFocusChangeListener!=null) {
    		this.onFocusChangeListener.onFocusChange(v, hasFocus);
    	}
    	focusChangedEvent(hasFocus);
    }
    
    private void focusChangedEvent(boolean focused) {
    	if (focused) {
        	String s = this.getText().toString();
        	if(s!=null && !"".equals(s)) {
        		setClearIconVisible(true);
        		return;
        	}
        }
        setClearIconVisible(false); 
    }

	/**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) { 
    	//如果你想让它一直显示DrawableRight的图标，并且还需要让它触发事件，直接把null改成mClearDrawable即可
        Drawable right = visible ? mClearDrawable : null; 
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
//        invalidate();
    } 
    
    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override 
    public void onTextChanged(CharSequence s, int start, int count, int after) {
    	if(s==null || "".equals(s) || s.length()<1      ) {
    		setClearIconVisible(false);
    	} else {
    		setClearIconVisible(true);
    	}
    } 
 
    @Override 
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { 
         
    } 
 
    @Override 
    public void afterTextChanged(Editable s) { 
         
    } 
    
}
