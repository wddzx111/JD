package com.jd.bean;
/**
 * 为商城的服务设定实体模型，以便构造超市功能GridView（网格视图）数据来源时使用；
 * 
 * @author 张廷修
 *
 */
public class ImageAndText {
	/**商城功能名称*/
	private String text;
	/**商城功能标志图片id*/
	private int id;
	public ImageAndText(String name, int id) {
		super();
		this.text = name;
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String name) {
		this.text = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
