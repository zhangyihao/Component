package com.zhangyihao.component;

import com.zhangyihao.component.controls.ClearEditText;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

public class ClearEditTextActivity extends Activity {

	private ClearEditText mUserNameEditText;
	private ClearEditText mPwdEditText;
	
	private Drawable mUserNameSelDrawable;
	private Drawable mUserNameNorDrawable;
	private Drawable mPwdSelDrawable;
	private Drawable mPwdNorDrawable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clear_edit_text);
		
		initDrawable();
		initView();
		initListener();
	}
	
	private void initView() {
		mUserNameEditText = (ClearEditText) findViewById(R.id.activity_login_username);
		mUserNameEditText.requestFocus();
		mPwdEditText = (ClearEditText) findViewById(R.id.activity_login_password);
	}
	
	private void initDrawable() {
		mUserNameSelDrawable = ClearEditTextActivity.this.getResources().getDrawable(R.drawable.login_username_sl);
		mUserNameSelDrawable.setBounds(0, 0, mUserNameSelDrawable.getMinimumWidth(), mUserNameSelDrawable.getMinimumHeight());//必须设置图片大小，否则不显示
		
		mUserNameNorDrawable = ClearEditTextActivity.this.getResources().getDrawable(R.drawable.login_username);
		mUserNameNorDrawable.setBounds(0, 0, mUserNameNorDrawable.getMinimumWidth(), mUserNameNorDrawable.getMinimumHeight());
		
		mPwdSelDrawable = ClearEditTextActivity.this.getResources().getDrawable(R.drawable.login_pwd_sl);
		mPwdSelDrawable.setBounds(0, 0, mPwdSelDrawable.getMinimumWidth(), mPwdSelDrawable.getMinimumHeight());
		
		mPwdNorDrawable = ClearEditTextActivity.this.getResources().getDrawable(R.drawable.login_pwd);
		mPwdNorDrawable.setBounds(0, 0, mPwdNorDrawable.getMinimumWidth(), mPwdNorDrawable.getMinimumHeight());
	}
	
	private void initListener() {
		mUserNameEditText.addOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					mUserNameEditText.setCompoundDrawables(mUserNameSelDrawable, mUserNameEditText.getCompoundDrawables()[1],
							mUserNameEditText.getCompoundDrawables()[2], mUserNameEditText.getCompoundDrawables()[3]);
				} else {
					mUserNameEditText.setCompoundDrawables(mUserNameNorDrawable, mUserNameEditText.getCompoundDrawables()[1],
							mUserNameEditText.getCompoundDrawables()[2], mUserNameEditText.getCompoundDrawables()[3]);
				}
			}
		});
		mPwdEditText.addOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					mPwdEditText.setCompoundDrawables(mPwdSelDrawable, mPwdEditText.getCompoundDrawables()[1],
							mPwdEditText.getCompoundDrawables()[2], mPwdEditText.getCompoundDrawables()[3]);
				} else {
					mPwdEditText.setCompoundDrawables(mPwdNorDrawable, mPwdEditText.getCompoundDrawables()[1],
							mPwdEditText.getCompoundDrawables()[2], mPwdEditText.getCompoundDrawables()[3]);
				}
			}
		});
	}
}
