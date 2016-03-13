package com.jd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.simplejingdong.R;
import com.jd.thread.LoginThread;
import com.jd.util.DataUtil;

public class LoginActivity extends FragmentActivity implements OnClickListener,
		TextWatcher {
	/** 返回 */
	private ImageView iv_login_back;
	/** 登录 */
	private Button bt_login;
	/** 用户 */
	private EditText et_login_user;
	/** 密码 */
	private EditText et_login_password;
	/** 验证栏 */
	private LinearLayout ll_login_validate;
	/** 验证栏输入框 */
	private EditText et_login_validate;
	
	/**ui处理器*/
	private Handler handler = new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			if(msg.what==DataUtil.MsgWhat.LOGIN){
				boolean b = (Boolean) msg.obj;
				if(b){//登录成功
					Toast.makeText(LoginActivity.this, "登录成功", 0).show();
				}else{
					Toast.makeText(LoginActivity.this, "登录失败", 0).show();
				}
			}
			return false;
		}
	});
	public Handler getHandler(){
		return handler;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		attach();
	}

	/**
	 * 添加附件
	 */
	private void attach() {
		// 绑定控件
		iv_login_back = (ImageView) findViewById(R.id.iv_login_back);
		bt_login = (Button) findViewById(R.id.bt_login);
		et_login_user = (EditText) findViewById(R.id.et_login_user);
		et_login_password = (EditText) findViewById(R.id.et_login_password);
		ll_login_validate = (LinearLayout) findViewById(R.id.ll_login_validate);
		et_login_validate = (EditText) findViewById(R.id.et_login_validate);

		// 监听
		iv_login_back.setOnClickListener(this);
		bt_login.setOnClickListener(this);
		et_login_user.addTextChangedListener(this);
		et_login_password.addTextChangedListener(this);
		et_login_validate.addTextChangedListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_login_back:// 返回图片视图
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			break;
		case R.id.bt_login://登录按钮
			LoginThread t = new LoginThread(this,et_login_user.getText().toString(),et_login_password.getText().toString());
			t.start();
			break;
		default:
			break;
		}
	}

	/** 设定登录button是否可点击 */
	private void setLoginButtonClickable() {
		String user, pwd, check;
		if (ll_login_validate.getVisibility() == LinearLayout.GONE) {// 没有验证栏
			user = et_login_user.getText().toString();
			pwd = et_login_password.getText().toString();
			if ((user.length() < 1) || (pwd.length() < 1)) {// 输入框有空的
				// System.out.println("输入框有空的"+((user.length()<1)||(pwd.length()<1)));
				bt_login.setEnabled(false);
			} else {// 输入框不为空
				bt_login.setEnabled(true);
			}
		} else {// 有验证栏
			user = et_login_user.getText().toString();
			pwd = et_login_password.getText().toString();
			check = et_login_validate.getText().toString();
			if ((user.length() < 1) || (pwd.length() < 1)
					|| (check.length() < 1)) {// 输入框有空的
				bt_login.setEnabled(false);
			} else {// 输入框不为空
				bt_login.setEnabled(true);
			}
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTextChanged(Editable s) {
		setLoginButtonClickable();
	}

}
