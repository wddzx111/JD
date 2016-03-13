package com.jd.ui.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.simplejingdong.R;
import com.jd.activity.LoginActivity;
public class MineFragment extends Fragment implements OnClickListener{
	private ImageView iv_mine_login;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mine, null);
		attach(view);
		return view;
	}

	/** 添加空件 */
	private void attach(View view) {
		iv_mine_login = (ImageView) view.findViewById(R.id.iv_mine_login);
		iv_mine_login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_mine_login://点击登录图片
			Intent intent = new Intent(getActivity(),LoginActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
		
	}

	

}
