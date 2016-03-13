package com.jd.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.simplejingdong.R;
import com.jd.adapter.ImageTextAdapter.Holder;
import com.jd.bean.ImageAndText;
import com.jd.bean.SecondClassItemBean;
import com.jd.util.DataUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class SecondClassAdapter extends BaseAdapter{
	private Context context;
	private List<SecondClassItemBean> list;
	
	public SecondClassAdapter(Context context, List<SecondClassItemBean> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if(null == convertView){//视图不存在  ，填充视图，设定视图tag（附加对象）
			convertView = LayoutInflater.from(context).inflate(R.layout.item_second_class, null);
			holder = new Holder();
			holder.gridView = (GridView)convertView.findViewById(R.id.gv_second_class);
			holder.textView = (TextView)convertView.findViewById(R.id.tv_second_class);
			convertView.setTag(holder);
		}else{//视图已存在    获取视图附加对象
			holder = (Holder)convertView.getTag();
		}
		//控件设定值  
		setItemDate(holder, position);
		return convertView;
	}
	/**
	 * 控件附件设定值
	 * @param holder
	 * @param position
	 */
	private void setItemDate(Holder holder, int position) {
		holder.textView.setText(list.get(position).getText());
		
		SimpleAdapter adapter = new SimpleAdapter(context, 
				list.get(position).getList(), 
				R.layout.item_image_and_text, 
				new String[]{DataUtil.KEY_IMAGE,DataUtil.KEY_TEXT}, new int[]{R.id.iv_image,R.id.tv_text});
		holder.gridView.setAdapter(adapter);
		DataUtil.setGridViewLayoutHeight(holder.gridView);
	}
	class Holder{
		TextView textView;
		GridView gridView;
	}
}
