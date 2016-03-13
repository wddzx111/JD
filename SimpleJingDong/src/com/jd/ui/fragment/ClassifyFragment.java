package com.jd.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.simplejingdong.R;
import com.jd.adapter.SecondClassAdapter;
import com.jd.bean.SecondClassItemBean;
import com.jd.util.DataUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ClassifyFragment extends Fragment{
	
	/**fragment view*/
	private View view;
	private ListView firstClassListView;
	private ListView secondClassListView;
	private List<HashMap<String, String>> fistClassList = new ArrayList<HashMap<String, String>>();
	private List<SecondClassItemBean>  secondClassItemBeanList = new ArrayList<SecondClassItemBean>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_classify, null);
		attach(view);
		return view;
	}
	/**
	 * 添加控件
	 * @param view
	 */
	private void attach(View view) {
		//分类名称列表
		this.firstClassListView = (ListView)view.findViewById(R.id.lv_first_class);
		initFistClassList();
		SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), fistClassList,R.layout.item_text, 
				new String[]{DataUtil.KEY_CLASSIFY}, new int[]{R.id.tv_item_text});
		this.firstClassListView.setAdapter(simpleAdapter);
		//二次分类列表
		this.secondClassListView = (ListView)view.findViewById(R.id.lv_second_class);
		secondClassItemBeanList = DataUtil.getSecondClassItemBeanList();
		SecondClassAdapter secondClassAdapter = new SecondClassAdapter(getActivity(), secondClassItemBeanList);
		this.secondClassListView.setAdapter(secondClassAdapter);
		DataUtil.setListViewLayoutHeight(this.secondClassListView);
	}
	/**实例化*/
	private void initFistClassList() {
		fistClassList = DataUtil.getClassifyTextList();
	}
}
