package com.hengyuan.hicash.service.query;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.experian.stratman.datasources.runtime.IData;

public class IDatas implements IData{
	
	private String layout;
	Map<String, Object> areaContents;
	
	public IDatas(String layout){
		this.layout = layout;
		areaContents =new HashMap<String, Object>();
	}

	@Override
	public String getLayout() {
		// TODO Auto-generated method stub
		return this.layout;
	}

	@Override
	public Object getValue(String name) {
		// TODO Auto-generated method stub
		return areaContents.get(name);
	}

	@Override
	public void setValue(String name, Object o) {
		// TODO Auto-generated method stub
		areaContents.put(name, o);
	}
	
	public Map<String, Object> getAreaContents() {
		return areaContents;
	}
	
	public void setAreaContents(Map<String, Object> areaContents) {
		if (areaContents == null) areaContents = new HashMap<String, Object>();
		this.areaContents = areaContents;
	}
	
	public String toString(){
		Set<String> keys = areaContents.keySet();
		String s = "";
		for(String key: keys){
			Object obj = areaContents.get(key);
			s = s + key + " = " + obj.toString() + ",";
		}
		return s;
	}

}
