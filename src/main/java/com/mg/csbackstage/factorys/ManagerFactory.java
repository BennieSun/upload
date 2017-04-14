package com.mg.csbackstage.factorys;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *Service 工厂
 *采用单例模式
 */
public class ManagerFactory {

	
	private static Properties prop;
	
	private static Logger logger;
	
	//存储已创建的对象，key必须要唯一，不能重复
	private static Map<String,Object> serviceMap;
	
	static{
		
		try {
			
			serviceMap=new HashMap<String, Object>();
			
			logger= Logger.getLogger(ManagerFactory.class);
			
			prop = new Properties();
			
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("manager.properties"));
		
		} catch (IOException e) {
			
			logger.info(e.getMessage());
		
			e.printStackTrace();
		}
        
	}
	
	
	public static <T>T getInstance(String serviceName,Class<T> interfaceType){
		
		T obj=null;
		
		try {
			
			
			if(serviceMap.get(serviceName)==null){
				
				logger.info("initService:"+serviceName+","+interfaceType.getName());
				
				obj=(T) interfaceType.cast(Class.forName(prop.getProperty(serviceName)).newInstance());
				
				
				serviceMap.put(serviceName, obj);
			}else{
				
				obj=(T) serviceMap.get(serviceName);
			}
			
			
		} catch (Exception e) {
			
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		
		return obj;
	}
}
