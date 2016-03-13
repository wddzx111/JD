package com.jd.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.simplejingdong.R;
import com.jd.adapter.VPGDrawableAdapter;
import com.jd.thread.MsgTimmingSendThread;
import com.jd.util.DataUtil;

public class HomeFragment extends Fragment implements OnClickListener {
	/** 京东home页面顶部的view pager */
	private ViewPager vp_home_top_advertisement;
	/** fragment 底部的小圆圈图片 */
	private ImageView iv_home_top_circle01, iv_home_top_circle02,
			iv_home_top_circle03, iv_home_top_circle04, iv_home_top_circle05,
			iv_home_top_circle06;
	/** fragment 底部的小圆圈图片列表 */
	private List<ImageView> circleList = new ArrayList<ImageView>();
	private int[] circles = {R.id.iv_home_top_circle01,
			R.id.iv_home_top_circle02,
			R.id.iv_home_top_circle03,
			R.id.iv_home_top_circle04,
			R.id.iv_home_top_circle05,
			R.id.iv_home_top_circle06};
	private List<Bitmap> bm_list = new ArrayList<Bitmap>();
	/** 给 ViewPager 设定 适配器 */
	private VPGDrawableAdapter vPGDrawableAdapter;
	private Handler handler = new Handler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			
			if (msg.what == DataUtil.MsgWhat.VP_DATA_CHANGE) {
				
				currentPage = (currentPage += increament) >= maxValue ? 0
						: currentPage;
				//vp_home_top_advertisement.setCurrentItem(currentPage);
				setClickImage(currentPage);
				return true;
			}

			return false;
		}
	});
	/** view pager 的上一个item */
	private static int oldFragmentId;
	/** 每次发送消息的间隔时间（在改变view pager 的current item时，使用） */
	private static final long SendMsgTime = 2000;
	private static final Integer increament = 1;
	/** view pager 的最大数量 */
	private Integer maxValue = 6;
	private static Integer currentPage = 0;
	private MsgTimmingSendThread msgTimmingSendThread;

	/** home 页面的超市服务，服务集中超市的各各功能 */
	/* private GridView gl_home_function; */
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_home, null);
		initBmList();
		
		return view;
	}
	@Override
	public void onResume() {
		super.onResume();
		
		attach(view);
		// 启动线程定时发送vp 更新消息
		msgTimmingSendThread = new MsgTimmingSendThread(handler,
				DataUtil.MsgWhat.VP_DATA_CHANGE, SendMsgTime, true);
		msgTimmingSendThread.start();
	}

	/** 初始化bm——list */
	private void initBmList() {
		Bitmap bm1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.image01);
		Bitmap bm2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.image02);
		Bitmap bm3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.image03);
		Bitmap bm4 = BitmapFactory.decodeResource(getResources(),
				R.drawable.image04);
		Bitmap bm5 = BitmapFactory.decodeResource(getResources(),
				R.drawable.image05);
		Bitmap bm6 = BitmapFactory.decodeResource(getResources(),
				R.drawable.image06);
		bm_list.add(bm1);
		bm_list.add(bm2);
		bm_list.add(bm3);
		bm_list.add(bm4);
		bm_list.add(bm5);
		bm_list.add(bm6);
	}

	/** 添加空件 */
	private void attach(View view) {
		circleList.clear();
		//广告轮播控制按钮
		iv_home_top_circle01 = (ImageView) view.findViewById(R.id.iv_home_top_circle01);
		iv_home_top_circle02 = (ImageView) view.findViewById(R.id.iv_home_top_circle02);
		iv_home_top_circle03 = (ImageView) view.findViewById(R.id.iv_home_top_circle03);
		iv_home_top_circle04 = (ImageView) view.findViewById(R.id.iv_home_top_circle04);
		iv_home_top_circle05 = (ImageView) view.findViewById(R.id.iv_home_top_circle05);
		iv_home_top_circle06 = (ImageView) view.findViewById(R.id.iv_home_top_circle06);
		iv_home_top_circle01.setOnClickListener(this);
		iv_home_top_circle02.setOnClickListener(this);
		iv_home_top_circle03.setOnClickListener(this);
		iv_home_top_circle04.setOnClickListener(this);
		iv_home_top_circle05.setOnClickListener(this);
		iv_home_top_circle06.setOnClickListener(this);
		
		circleList.add(iv_home_top_circle01);
		circleList.add(iv_home_top_circle02);
		circleList.add(iv_home_top_circle03);
		circleList.add(iv_home_top_circle04);
		circleList.add(iv_home_top_circle05);
		circleList.add(iv_home_top_circle06);
		
		// 广告轮播控件
		vp_home_top_advertisement = (ViewPager) view
				.findViewById(R.id.vp_home_top_advertisement);
		vp_home_top_advertisement
				.setOnPageChangeListener(new OnPageChangeListener() {

					@Override
					public void onPageSelected(int currentId) {
						setClickImage(oldFragmentId,currentId);
						oldFragmentId = currentId;
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {

					}
				});
		vPGDrawableAdapter = new VPGDrawableAdapter(bm_list, getActivity());
		vp_home_top_advertisement.setAdapter(vPGDrawableAdapter);

		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_home_top_circle01:
			currentPage = 0;
			setClickImage(currentPage);
			break;
		case R.id.iv_home_top_circle02:
			currentPage = 1;
			setClickImage(currentPage);
			break;
		case R.id.iv_home_top_circle03:
			currentPage = 2;
			setClickImage(currentPage);
			break;
		case R.id.iv_home_top_circle04:
			currentPage = 3;
			setClickImage(currentPage);
			break;
		case R.id.iv_home_top_circle05:
			currentPage = 4;
			setClickImage(currentPage);
			break;
		case R.id.iv_home_top_circle06:
			currentPage = 5;
			setClickImage(currentPage);
			break;

		default:
			break;
		}
	}

	/** 改变home——fragment 中小圆点的背景图片 ,改变当前广告图片*/
	private void setClickImage(int newIndex) {
		/*int oldIndex = vp_home_top_advertisement.getCurrentItem();
		ImageView iv0 = (ImageView) view.findViewById(circles[oldIndex]);
		iv0.setImageResource(R.drawable.vp_jd_false);
		ImageView iv1 = (ImageView) view.findViewById(circles[newIndex]);
		iv1.setImageResource(R.drawable.vp_jd_true);
		*/
		vp_home_top_advertisement.setCurrentItem(newIndex);
		vPGDrawableAdapter.notifyDataSetChanged();
	}

	/** 改变home——fragment 中小圆点的背景图片 ，不改变广告*/
	private void setClickImage(int oldIndex, int newIndex) {
		circleList.get(oldIndex).setImageResource(R.drawable.vp_jd_false);
		circleList.get(newIndex).setImageResource(R.drawable.vp_jd_true);
	}

	@Override
	public void onPause() {
		// System.out.println("onPause---");
		// 停止线程
		msgTimmingSendThread.setRun(false);
		try {
			msgTimmingSendThread.join();
			Thread.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.onPause();
	}

}
