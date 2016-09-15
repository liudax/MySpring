package com.iCode.ConfigManager;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Manager {
	
	public static Map<String,Bean> getBeanContext(String path){
		Map<String,Bean> beans =new  HashMap<String,Bean>();
		SAXReader reader = new SAXReader();
		Document doc =null;
		try {
			doc = reader.read(new File(path));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		String xpath = "//bean";
		@SuppressWarnings("unchecked")
		List<Element> list = doc.selectNodes(xpath);
		if(list!=null){
			for (Element ele : list) {
				Bean bean = new Bean();
				String name = ele.attributeValue("id");
				String className = ele.attributeValue("class");
				bean.setName(name);
				bean.setClassName(className);
				@SuppressWarnings("unchecked")
				List<Element> propertys = ele.elements("property");
				if(propertys!=null){
					for (Element elp : propertys) {
						Property pro = new Property();
						String proName= elp.attributeValue("name");
						String proValue= elp.attributeValue("value");
						String proRel= elp.attributeValue("rel");
						pro.setName(proName);
						pro.setValue(proValue);
						pro.setRel(proRel);
						bean.getPropertys().add(pro);
					}
				}
			beans.put(name, bean);
			}
		}
		return beans;
	}

}
