package com.jd.bean;

import java.util.HashMap;
import java.util.List;

public class SecondClassItemBean {
	private String text;
	private List<HashMap<String,Object>> list;
	
	public SecondClassItemBean(String text, List<HashMap<String,Object>> list) {
		super();
		this.text = text;
		this.list = list;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<HashMap<String,Object>> getList() {
		return list;
	}
	public void setList(List<HashMap<String,Object>> list) {
		this.list = list;
	}
	
}
