package com.jd.activity;



import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.simplejingdong.R;
import com.jd.bean.ViewXY;
import com.jd.ui.fragment.ClassifyFragment;
import com.jd.ui.fragment.FindFragment;
import com.jd.ui.fragment.HomeFragment;
import com.jd.ui.fragment.MineFragment;
import com.jd.ui.fragment.ShoppingCartFragment;
import com.jd.util.DataUtil;

public class HomeActivity extends FragmentActivity implements OnClickListener,OnTouchListener {
	Fragment homeFragment;
	FragmentManager fragmentManager;
	List<Fragment> list = new ArrayList<Fragment>();
	
	/** home页菜单栏的菜单选项id */
	private static final int MENU_HOME = 0, MENU_CLASSIFY = 1, MENU_FIND = 2,
			MENU_SHOPPINT_CART = 3, MENU_MINE = 4;
	/** home页菜单栏 */
	ImageView iv_home_home, iv_home_classify,iv_home_find, iv_home_shopping_cart,
			iv_home_mine;
/**可移动button 显示的位置*/
	private float bt_lastX, bt_lastY;
	private int screenWidth;
	private int screenHeight;
	private Button bt_home_events ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initFragmentList();
		
		getScreenData();
		attach();
	}

	private void getScreenData() {
		DisplayMetrics dm = getResources().getDisplayMetrics();  
        screenWidth = dm.widthPixels;  
        screenHeight = dm.heightPixels-100;  
	}

	private void initFragmentList() {
		list.add(new HomeFragment());
		list.add(new ClassifyFragment());
		list.add(new FindFragment());
		list.add(new ShoppingCartFragment());
		list.add(new MineFragment());
	}

	/**
	 * 添加附件
	 */
	private void attach() {
		//添加bt_home_events
		bt_home_events = (Button)findViewById(R.id.bt_home_events);
		bt_home_events.setOnTouchListener(this);
		// 添加主页底部菜单
		iv_home_home = (ImageView) findViewById(R.id.iv_home_home);
		iv_home_classify = (ImageView) findViewById(R.id.iv_home_classify);
		iv_home_find = (ImageView) findViewById(R.id.iv_home_find);
		iv_home_shopping_cart = (ImageView) findViewById(R.id.iv_home_shopping_cart);
		iv_home_mine = (ImageView) findViewById(R.id.iv_home_mine);
		iv_home_home.setOnClickListener(this);
		iv_home_classify.setOnClickListener(this);
		iv_home_find.setOnClickListener(this);
		iv_home_shopping_cart.setOnClickListener(this);
		iv_home_mine.setOnClickListener(this);
		//添加默认fragment
		initFragment();
	}
	/**
	 * 添加默认fragment
	 */
	private void initFragment() {
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction;
		fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.fl_home_body, list.get(MENU_HOME));
		//fragmentTransaction.replace(R.id.fl_home_body, list.get(MENU_HOME));
		fragmentTransaction.commit();
	}

	/**
	 * 添加fragment
	 * @param index fragment 的编号
	 */
	private void changeFragment(int index) {
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction;
		fragmentTransaction = fragmentManager.beginTransaction();
		switch (index) {
		case MENU_HOME:
			fragmentTransaction.replace(R.id.fl_home_body, list.get(MENU_HOME));
			break;
		case MENU_CLASSIFY:
			fragmentTransaction.replace(R.id.fl_home_body, list.get(MENU_CLASSIFY));
			break;
		case MENU_FIND:
			fragmentTransaction.replace(R.id.fl_home_body, list.get(MENU_FIND));
			break;
		case MENU_SHOPPINT_CART:
			fragmentTransaction.replace(R.id.fl_home_body, list.get(MENU_SHOPPINT_CART));
			break;
		case MENU_MINE:
			fragmentTransaction.replace(R.id.fl_home_body, list.get(MENU_MINE));
			break;

		default:
			break;
		}
		System.out.println("fragment num : "+fragmentManager.getBackStackEntryCount());
		
		fragmentTransaction.commit();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_home_home:
			setIVBackgroundResource(MENU_HOME);
			changeFragment(MENU_HOME);
			break;
		case R.id.iv_home_classify:
			setIVBackgroundResource(MENU_CLASSIFY);
			changeFragment(MENU_CLASSIFY);
			break;
		case R.id.iv_home_find:
			setIVBackgroundResource(MENU_FIND);
			changeFragment(MENU_FIND);
			break;
		case R.id.iv_home_shopping_cart:
			setIVBackgroundResource(MENU_SHOPPINT_CART);
			changeFragment(MENU_SHOPPINT_CART);
			break;
		case R.id.iv_home_mine:
			setIVBackgroundResource(MENU_MINE);
			changeFragment(MENU_MINE);
			break;

		default:
			break;
		}

	}

	/**
	 * 设定菜单栏iv 的top图片
	 * 
	 * @param menuId
	 *            目标
	 */
	private void setIVBackgroundResource(int menuId) {
		switch (menuId) {
		case MENU_HOME:
			iv_home_home
					.setBackgroundResource(R.drawable.iv_home_home_selected);
			iv_home_classify
					.setBackgroundResource(R.drawable.iv_home_classify_default);
			iv_home_shopping_cart
					.setBackgroundResource(R.drawable.iv_home_shopping_cart_default);
			iv_home_mine.setBackgroundResource(R.drawable.iv_home_mine_default);
			iv_home_find.setBackgroundResource(R.drawable.iv_home_find_default);
			break;
		case MENU_CLASSIFY:
			iv_home_home
					.setBackgroundResource(R.drawable.iv_home_home_defaulet);
			iv_home_classify
					.setBackgroundResource(R.drawable.iv_home_classify_selected);
			iv_home_shopping_cart
					.setBackgroundResource(R.drawable.iv_home_shopping_cart_default);
			iv_home_mine.setBackgroundResource(R.drawable.iv_home_mine_default);
			iv_home_find.setBackgroundResource(R.drawable.iv_home_find_default);
			break;
		case MENU_FIND:
			iv_home_home
					.setBackgroundResource(R.drawable.iv_home_home_defaulet);
			iv_home_classify
					.setBackgroundResource(R.drawable.iv_home_classify_default);
			iv_home_shopping_cart
					.setBackgroundResource(R.drawable.iv_home_shopping_cart_default);
			iv_home_mine.setBackgroundResource(R.drawable.iv_home_mine_default);
			iv_home_find.setBackgroundResource(R.drawable.iv_home_find_selected);
			break;
		case MENU_SHOPPINT_CART:
			iv_home_home
					.setBackgroundResource(R.drawable.iv_home_home_defaulet);
			iv_home_classify
					.setBackgroundResource(R.drawable.iv_home_classify_default);
			iv_home_shopping_cart
					.setBackgroundResource(R.drawable.iv_home_shopping_cart_selected);
			iv_home_mine.setBackgroundResource(R.drawable.iv_home_mine_default);
			iv_home_find.setBackgroundResource(R.drawable.iv_home_find_default);
			break;
		case MENU_MINE:
			iv_home_home
					.setBackgroundResource(R.drawable.iv_home_home_defaulet);
			iv_home_classify
					.setBackgroundResource(R.drawable.iv_home_classify_default);
			iv_home_shopping_cart
					.setBackgroundResource(R.drawable.iv_home_shopping_cart_default);
			iv_home_mine
					.setBackgroundResource(R.drawable.iv_home_mine_selected);
			iv_home_find.setBackgroundResource(R.drawable.iv_home_find_default);
			break;

		default:
			break;
		}
	}
	
	/**
	 * 移动button线程---把button移动到activity的左边或右边
	 * @param v
	 */
	private void moveThread(final View v) {
		
		new Thread(){
			static final int dx = 150;
			static final  long dt = 50;
			@Override
			public void run() {
				if(bt_lastX>(screenWidth/2)){
					moveToRight();
				}else{
					moveToLeft();
				}
			}
			/**移动到左边界*/
			private void moveToLeft() {
				boolean isCanMove= true;
				float x;
				while(isCanMove){
					try {
						sleep(dt );//睡眠100毫秒
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//新值
					x = v.getX() - dx;
					 //判断是否越界
					if(x < 0){  
		                x = 0;  
		                isCanMove = false;
		            }                     
		            Message msg = new Message();
		            msg.what = DataUtil.MsgWhat.BT_CHANGE; 
		            msg.obj = new ViewXY(x,v.getY());
		            handler.sendMessage(msg);
				}
			}
			/**移动到右边界*/
			private void moveToRight() {
				boolean isCanMove= true;
				float x;
				float right;
				while(isCanMove){
					try {
						sleep(dt );//睡眠100毫秒
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//新值
					x = v.getX() + dx;
					right = x + v.getWidth();
					 //判断是否越界
					 if(right > screenWidth){  
			                x = screenWidth - v.getWidth();  
			                isCanMove = false;
			            } 
		            Message msg = new Message();
		            msg.what = DataUtil.MsgWhat.BT_CHANGE; 
		            msg.obj = new ViewXY(x,v.getY());
		            handler.sendMessage(msg);
		            
		            
				}
			}
		}.start();
		
	}
	/**
	 * 消息接收处理器
	 */
	Handler handler = new Handler(new Callback() {
		
		@Override
		public boolean handleMessage(Message msg) {
			if(msg.what == DataUtil.MsgWhat.BT_CHANGE){
				moveToBorderline(msg);
			}
			return false;
		}
	});
	/**
	 * 收到消息，移动button到最后位置bt_lastX，bt_lastX
	 * @param msg
	 */
	private void moveToBorderline(Message msg) {
		if(msg.obj instanceof ViewXY){
			ViewXY v= (ViewXY)msg.obj;
			bt_home_events.setX(v.getX());
			bt_home_events.setY(v.getY());
			//v.layout(bt_lastX, v.getTop(), bt_lastX+v.getWidth(), v.getBottom());
		}
		
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action=event.getAction();  
        switch(action){  
        case MotionEvent.ACTION_DOWN:  
            bt_lastX = event.getRawX();  
            bt_lastY = event.getRawY();  
            break;  
           
        case MotionEvent.ACTION_MOVE:  
            float dx =event.getRawX() - bt_lastX;  
            float dy =event.getRawY() - bt_lastY;  
          
            float x = v.getX() + dx;  
            float y = v.getY() + dy; 
            int right = (int)x+v.getWidth();
            int bottom = (int)y+v.getHeight();
            if(x < 0){  
                x = 0;  
            }                     
            if(right > screenWidth){  
                x = right - v.getWidth();  
            }                     
            if(y < 0){  
                y = 0;  
            }                     
            if(bottom > screenHeight){  
                y = bottom - v.getHeight();  
            }                     
            //v.layout(left, top, right, bottom); 
            v.setX(x);
            v.setY(y);
            Log.i("@@@@@@", "position��" + x +", " + y + ", " + right + ", " + bottom);     
            bt_lastX = event.getRawX();  
            bt_lastY = event.getRawY();                    
            break;  
        case MotionEvent.ACTION_UP:  
        	moveThread(v);
            break;                
        }  
	return false;
	}

}
