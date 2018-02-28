package com.gemalto.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class ConfigHandler {
    private List<Map<String, String>> arrayProps;  
    private String[] listProp; //接收prop1里面的属性值  
	public List<Map<String, String>> getArrayProps() {
		return arrayProps;
	}
	public void setArrayProps(List<Map<String, String>> arrayProps) {
		this.arrayProps = arrayProps;
	}
	public String[] getListProp() {
		return listProp;
	}
	public void setListProp(String[] listProp) {
		this.listProp = listProp;
	}
	@Override
	public String toString() {
		return "ConfigHandler [arrayProps=" + arrayProps + ", listProp=" + Arrays.toString(listProp) + "]";
	}
    
}
