package com.mg.csbackstage.configs;

import com.mg.util.core.PropertityHelper;

public class HttpConfig extends PropertityHelper {
	
	private static HttpConfig instance;
	
	public static HttpConfig getInstance() {
		if (instance == null) {
			instance = new HttpConfig();
		}
		return instance;
	}
	
	
	public HttpClass parseData(int code) throws ClassNotFoundException {
		//com.cy.httpServices.ServerHttp.reloadConfig
		String s = this.getConfig(code+"");
		if (s == null) {
			throw new ClassNotFoundException("Http Code not register:"+code);
		}
		String className = s.substring(0,s.lastIndexOf('.'));
    	String method = s.substring(s.lastIndexOf('.')+1);
		return new HttpClass(className,method);
	}
	
	
	public static class HttpClass {
		public Class<?> hclass;
		public String method;
		
		public HttpClass(String className,String methodName) throws ClassNotFoundException {
			hclass = Class.forName(className);
			method = methodName;
		}
	}


	@Override
	public int reload() throws Exception {
		super.loadDefaultConfig();
		return 1;
	}
}
