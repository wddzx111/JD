
package com.jd.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 给home页面的广告轮播view pager做适配
 * @author 张廷修
 *
 */
public class VPGDrawableAdapter extends PagerAdapter{

	List<Bitmap> bm_list;
	Context context;
	/**构造方法*/
	public VPGDrawableAdapter(List<Bitmap> bm_list,Context context) {
		this.bm_list = bm_list;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return bm_list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg1==arg0;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		//System.out.println("position======="+position);
		ImageView imageView = new ImageView(context);
		imageView.setScaleType(ScaleType.FIT_XY);
		try {
			
			imageView.setImageBitmap(bm_list.get(position));
		} catch (Exception e) {
			
		}
		container.addView(imageView);
		return imageView;
	}
	
	

}








