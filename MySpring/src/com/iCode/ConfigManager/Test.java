package com.iCode.ConfigManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.iCode.entity.A;
import com.iCode.entity.B;
import com.iCode.entity.C;

public class Test {
	public static void main(String[] args) {
//		AppliationContext ac = AppliationContext.getXMLAppicationContext("config/applicationContext.xml");
//		C c = (C)ac.getBean("C");
//		System.out.println(c.getB().getA().getName());
//		
		A a = new A();
		a.setName("≤‚ ‘");
		B b = new B();
		Class clzz = b.getClass();
		try {
			Method method = clzz.getMethod("setA", new Class[]{A.class});
			method.invoke(b, new Object[]{a});
			System.out.println(b.getA().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
