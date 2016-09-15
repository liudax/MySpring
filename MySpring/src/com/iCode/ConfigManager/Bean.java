package com.iCode.ConfigManager;

import java.util.ArrayList;
import java.util.List;

public class Bean {
	//中文乱码测试
	private String name;
	private String className;
	private List<Property> propertys = new ArrayList<Property>();
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Property> getPropertys() {
		return propertys;
	}
	public void setPropertys(List<Property> propertys) {
		this.propertys = propertys;
	}

}
