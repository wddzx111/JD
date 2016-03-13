package com.jd.thread;

import android.os.Handler;
import android.os.Message;

import com.jd.util.DataUtil;

/**
 * 每隔一段时间，发射一次消息
 * @author 张廷修
 *
 */
public class MsgTimmingSendThread extends Thread{
	/**发射器*/
	private Handler handler;
	private int msgWhat;
	/**间隔时间==毫秒*/
	private Long intervalTime;
	private boolean isRun = true;
	
	
	/**
	 * 每隔 一段时间发送一次消息
	 * @param handler
	 * @param msgWhat
	 * @param intervalTime 间隔时间
	 * @param isRun
	 */
	public MsgTimmingSendThread(Handler handler, int msgWhat, Long intervalTime,
			boolean isRun) {
		this.handler = handler;
		this.msgWhat = msgWhat;
		this.intervalTime = intervalTime;
		this.isRun = isRun;
	}


	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}


	@Override
	public void run() {
		while (isRun) {
			try {//睡眠一段时间
				sleep(intervalTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Message msg = new Message();
			msg.what = msgWhat;
			handler.sendMessage(msg);
		}
	}
	

}
