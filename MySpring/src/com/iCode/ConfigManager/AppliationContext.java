package com.iCode.ConfigManager;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AppliationContext {
	private static Map<String,Bean> CONTEXT;;
	
	public static AppliationContext getXMLAppicationContext(String path){
		AppliationContext ac =new AppliationContext();
		CONTEXT= Manager.getBeanContext(path);
		return  ac;
	}
	public Object getBean(String id) {
		Set<Entry<String, Bean>> set = CONTEXT.entrySet();
		Bean bean = null;
		for (Entry<String, Bean> entry : set) {
			if(entry.getKey().equals(id)){
				bean= entry.getValue();
				break;
			}
		}
		if(bean==null){
			throw new RuntimeException("未找到"+id);
		}
		String className= bean.getClassName();
		Class<?> clzz =null;
		Object obj = null;
		try {
			clzz=Class.forName(className);
			 obj = clzz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("没找到"+className);
		}
		
		List<Property> list = bean.getPropertys();
		if(list.size()!=0){
			for (Property property : list) {
				String proName = property.getName();
				String proValue = property.getValue();
				String proRel = property.getRel();
				Class<?>  parameterType = null;
				Object para = null;
				if(proValue!=null){
					para = proValue;
					parameterType = String.class;
				}else{
					para= getBean(proRel);
					parameterType = para.getClass();
				}
				 char[] cs=proName.toCharArray();
			     cs[0]-=32;  
				String methodName = "set"+ String.valueOf(cs);
				try{
					Method method = clzz.getMethod(methodName, new Class[]{parameterType});
					method.invoke(obj, new Object[]{para});		
				}catch(Exception e){
					throw new RuntimeException("set注入属性失败");
				}
			}
		}
		return obj;
	}
}
