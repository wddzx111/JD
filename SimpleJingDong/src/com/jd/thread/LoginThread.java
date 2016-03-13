package com.jd.thread;

import android.content.Context;
import android.os.Message;

import com.jd.activity.LoginActivity;
import com.jd.util.DataUtil;
import com.jd.util.LoginUtil;

/**
 * 登录服务器
 * @author 张廷修
 *
 */
public class LoginThread extends Thread{
	Context context;
	private String user;
	private String password;
	
	/**
	 * 构造方法
	 * @param user
	 * @param password
	 */
	public LoginThread(Context context,String user, String password) {
		this.context = context;
		this.user = user;
		this.password = password;
	}
	
	@Override
	public void run() {
		if(user!=null&&password!=null){
			//b :登录结果
			boolean b= LoginUtil.login(context,user,password);
			if(context instanceof LoginActivity){
				Message msg = new Message();
				msg.what = DataUtil.MsgWhat.LOGIN;
				msg.obj = b;
				((LoginActivity)context).getHandler().sendMessage(msg);
			}
		}
	}
	
}
