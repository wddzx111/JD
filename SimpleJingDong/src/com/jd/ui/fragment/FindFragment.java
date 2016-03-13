package com.jd.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

public class FindFragment extends Fragment {
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_find, null);

		attach(view);

		return view;
	}
	/**
	 * 添加附件
	 * @param view
	 */
	private void attach(View view) {
		// TODO Auto-generated method stub
		
	}

	

}
