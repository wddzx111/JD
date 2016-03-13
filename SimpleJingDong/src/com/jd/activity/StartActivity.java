package com.jd.activity;

import com.example.simplejingdong.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class StartActivity extends Activity {

	protected static final int MSG_START_NEW＿ACTIVITY_OK = 0;
	/**页面停留时间*/
	protected static final long SLEEP_TIME = 2500;
	
	private ImageView iv_jd_load_point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		//启动新主页面
		new Thread(){
			public void run() {
				try {
					sleep(SLEEP_TIME);
					Message msg = new Message();
					msg.what = MSG_START_NEW＿ACTIVITY_OK;
					handler.sendMessage(msg );
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent = new Intent(StartActivity.this,HomeActivity.class);
				startActivity(intent);
				
				
			};
		}.start();
		
		//添加附件
		attach();
	}
	/**添加附件*/
	private void attach() {
		iv_jd_load_point = (ImageView) findViewById(R.id.iv_jd_load_line_point);
		// 设定进度条动画
		
		Bitmap bm_load_line_point = BitmapFactory.decodeResource(getResources(), R.drawable.jd_load_point);
		int moveWidth = iv_jd_load_point.getLayoutParams().width - bm_load_line_point.getWidth();
		TranslateAnimation translateAnimation = new TranslateAnimation(0, moveWidth, 0, 0);
		translateAnimation.setDuration(SLEEP_TIME);
		translateAnimation.setFillAfter(true);
		iv_jd_load_point.setAnimation(translateAnimation);
	}

	Handler handler = new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			if(MSG_START_NEW＿ACTIVITY_OK== msg.what){
				finish();
			}
			
			return false;
		}
	});
	
	

}
