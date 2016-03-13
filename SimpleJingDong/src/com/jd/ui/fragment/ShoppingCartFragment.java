package com.jd.ui.fragment;

import com.example.simplejingdong.R;
import com.jd.adapter.ImageTextAdapter;
import com.jd.util.DataUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
/**
 * 购物车界面（未登录）
 * @author 张廷修
 *
 */
public class ShoppingCartFragment extends Fragment implements OnClickListener {
	/**未登录见面的商品推荐展示GridView*/
	private GridView gv_unlogin_shopping_cart;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shopping_cart_unlogin, null);

		attach(view);

		return view;
	}

	/** 添加空件 */
	private void attach(View view) {
		gv_unlogin_shopping_cart = (GridView)view.findViewById(R.id.gv_unlogin_shopping_cart);
		ListAdapter adapter = new ImageTextAdapter(getActivity(), DataUtil.getImageAndTextList());
		gv_unlogin_shopping_cart.setAdapter(adapter );
		DataUtil.setGridViewLayoutHeight(gv_unlogin_shopping_cart,2,0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
