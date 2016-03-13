package com.jd.adapter;

import java.util.List;

import com.example.simplejingdong.R;
import com.jd.bean.ImageAndText;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 适配：把一张图片和文字（图片名称）添加到item中
 * @author 张廷修
 *
 */
public class ImageTextAdapter extends BaseAdapter{
	private Context context;
	private List<ImageAndText> list;
	/**
	 * 构造方法
	 * @param contexst
	 * @param list
	 */
	public ImageTextAdapter(Context context, List<ImageAndText> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_image_and_text, null);
			holder = new Holder();
			holder.iv_function = (ImageView)convertView.findViewById(R.id.iv_image);
			holder.tv_function = (TextView)convertView.findViewById(R.id.tv_text);
			convertView.setTag(holder);
		}else{//视图已存在    获取视图附加对象
			holder = (Holder)convertView.getTag();
		}
		//控件设定值  
		setItemDate(holder, position);
		return convertView;
	}
	private void setItemDate(Holder holder, int position) {
		holder.tv_function.setText(list.get(position).getText());
		holder.iv_function.setBackgroundResource(list.get(position).getId());
	}
	class Holder{
		ImageView iv_function;
		TextView tv_function;
	}
}
