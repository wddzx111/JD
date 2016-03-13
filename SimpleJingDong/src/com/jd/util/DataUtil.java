package com.jd.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.simplejingdong.R;
import com.jd.bean.ImageAndText;
import com.jd.bean.SecondClassItemBean;

public class DataUtil {
	/**
	 * 消息类型常量
	 * @author EDUASK
	 *
	 */
	public class MsgWhat{
		public static final int VP_DATA_CHANGE = 1;
		public static final int VP_CHANGED = 0;
		public static final int BT_CHANGE = 2;
		public static final int LOGIN = 3;
	}
	/**模拟数据量*/
	private static final int MAX_DATA_COUNT = 20;
	/**hashMap key*/
	public static final String KEY_CLASSIFY = "classify";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_TEXT = "text";
	/**二次分类中grid view 的列数*/
	private static final int NUM_COLUMS_OF_SECOND_CLASS_GRID_VIEW = 3;
	private static final int GRID_VIEW_PADDING = 5;
	/**模拟数据list*/
	private static List<ImageAndText> imageAndTextList = new ArrayList<ImageAndText>();
	private static List<HashMap<String, String>> classifyTextList = new ArrayList<HashMap<String,String>>();
	private static List<HashMap<String, Object>> imageAndTextMap = new ArrayList<HashMap<String,Object>>();
	private static List<SecondClassItemBean>  secondClassItemBeanList = new ArrayList<SecondClassItemBean>();
	
	/**获得已经模拟出来的的商品类列表*/
	public static List<ImageAndText> getImageAndTextList() {
		if(imageAndTextList.size()<MAX_DATA_COUNT){
			initImageAndTextList();
		}
		
		return imageAndTextList;
	}
	private static void initImageAndTextList(){
		for (int i = 0;i<MAX_DATA_COUNT;i++){
			imageAndTextList.add(new ImageAndText("京东超市", R.drawable.ic_launcher));
		}
	}
	public static List<HashMap<String, String>> getClassifyTextList() {
		if(classifyTextList.size()<MAX_DATA_COUNT){
			for (int i = 0; i < MAX_DATA_COUNT; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put(KEY_CLASSIFY, "推荐分类"+(i+1));
				classifyTextList.add(map);
			}
		}
		
		return classifyTextList;
	}
	/**
	 * 
	 * @return
	 */
	public static List<SecondClassItemBean> getSecondClassItemBeanList() {
		// TODO Auto-generated method stub
		for (int i = 0; i < MAX_DATA_COUNT; i++) {
			if(imageAndTextMap.size()==0){
				getImageAndTextMap();
			}
			SecondClassItemBean bean  = new SecondClassItemBean("二次分类"+(i+1), imageAndTextMap);
			secondClassItemBeanList.add(bean);
		}
		
		return secondClassItemBeanList;
	}
	
	private static void getImageAndTextMap() {
		for (int i = 0; i < MAX_DATA_COUNT; i++) {
			HashMap< String, Object> map = new HashMap<String, Object>();
			map.put(DataUtil.KEY_TEXT, "商品"+(i+1));
			map.put(DataUtil.KEY_IMAGE, R.drawable.ic_launcher);
			imageAndTextMap.add(map);
			
		}
	}
	/**
	 * 设定grid view 的高度,默认为三列
	 * @param gridView
	 */
	public static void setGridViewLayoutHeight(GridView gridView) {
		 // 获取ListView对应的Adapter   
       ListAdapter listAdapter = gridView.getAdapter();   
       if (listAdapter == null) {   
           return;   
       }   
       View itemView = null;
       int tagHeight = 0;   
       int listCounts = gridView.getCount();
       int row = listCounts/NUM_COLUMS_OF_SECOND_CLASS_GRID_VIEW;
       if(listCounts%NUM_COLUMS_OF_SECOND_CLASS_GRID_VIEW!=0){
    	   row++;//当行数不能完全显示grid view 中的数据时，再添加1行
       }
       
       
       for (int i = 0; i < row; i++) {   
           // listAdapter.getCount()返回数据项的数目   
           itemView = listAdapter.getView(i, null, gridView);   
           // 计算子项View 的宽高   
           itemView.measure(0, 0);    
           // 统计所有子项的总高度   
           tagHeight += itemView.getMeasuredHeight();   
           
       }   
  
       ViewGroup.LayoutParams params = gridView.getLayoutParams();   
       params.height = tagHeight+GRID_VIEW_PADDING*2; 
       
       //System.out.println("gridView.getVerticalSpacing()-----"+gridView.getVerticalSpacing());
       // gridView.getListPaddingTop()获取子项间分隔符占用的高度   
       // params.height最后得到整个ListView完整显示需要的高度   
       gridView.setLayoutParams(params);   
	}
	
	/**
	 * 设定grid view 的高度,默认为三列
	 * @param gridView
	 * @param numColumns grid view所具有的列数
	 * @param numShowRow		设定显示多少行记录，不足则为实际行数---当前不起效
	 */
	public static void setGridViewLayoutHeight(GridView gridView,int numColumns,int showRow) {
		 // 获取ListView对应的Adapter   
       ListAdapter listAdapter = gridView.getAdapter();   
       if (listAdapter == null) {   
           return;   
       }   
       View itemView = null;
       int tagHeight = 0;   
       int listCounts = gridView.getCount();
       int row = listCounts/numColumns;
       if(listCounts%numColumns!=0){
    	   row++;//当行数不能完全显示grid view 中的数据时，再添加1行
       }
       
       
       for (int i = 0; i < row; i++) {   
           // listAdapter.getCount()返回数据项的数目   
           itemView = listAdapter.getView(i, null, gridView);   
           // 计算子项View 的宽高   
           itemView.measure(0, 0);    
           // 统计所有子项的总高度   
           tagHeight += itemView.getMeasuredHeight();   
           
       }   
  
       ViewGroup.LayoutParams params = gridView.getLayoutParams();   
       params.height = tagHeight+GRID_VIEW_PADDING*2; 
       // gridView.getListPaddingTop()获取子项间分隔符占用的高度   
       // params.height最后得到整个ListView完整显示需要的高度   
       gridView.setLayoutParams(params);   
	}
	
	/**
	 * 设定list view 的高度
	 * @param listView
	 */
	public static void setListViewLayoutHeight(ListView listView) {
		 // 获取ListView对应的Adapter   
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {   
            return;   
        }   
   
        int totalHeight = 0;   
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {   
            // listAdapter.getCount()返回数据项的数目   
            View listItem = listAdapter.getView(i, null, listView);   
            // 计算子项View 的宽高   
            listItem.measure(0, 0);    
            // 统计所有子项的总高度   
            totalHeight += listItem.getMeasuredHeight();    
        }   
   
        ViewGroup.LayoutParams params = listView.getLayoutParams();   
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));   
        // listView.getDividerHeight()获取子项间分隔符占用的高度   
        // params.height最后得到整个ListView完整显示需要的高度   
        listView.setLayoutParams(params);   
	}
	
	
}
