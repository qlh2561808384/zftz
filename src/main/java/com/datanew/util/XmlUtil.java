package com.datanew.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {
	@SuppressWarnings({ "rawtypes" })
	/**
	 * 返回添加好参数的xml
	 * @param map
	 * @return
	 */
	public static String addParams(Map<String, String> map){
		Document document = DocumentHelper.createDocument(); 
    	Element root =document.addElement("Root");
    	  Element request =root.addElement("REQUEST");
    	  Set<String> key = map.keySet();
          for (Iterator it = key.iterator(); it.hasNext();) {
              String s = (String) it.next();
              Element param = request.addElement("PARAM");
              param.addAttribute("NAME", s);
              param.setText(map.get(s));
          }
		return document.asXML();
	}
	/**
	 * 把参数xml转换为map对象
	 * @param xml
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getParams(String xml) {
		Map map = new HashMap();

		try {
			Document document = DocumentHelper.parseText(xml);
			List<Element> list = document.selectNodes("ROOT/REQUEST/PARAM");
			for (Element element : list) {
				map.put(element.attributeValue("NAME"), element.getTextTrim());
				//System.out.println(element.attributeValue("NAME") + ":"+ element.getTextTrim());
			}
		} catch (Exception e) {

		}
		return map;
	}
	public static Map getParams(Element element) {
		Map map = new HashMap();
		List list = element.elements();
		for (Object o : list) {
			Element e = (Element) o;
			if (e.elements().size() == 0) {
				map.put(e.getName(), e.getTextTrim() == null ? "" : e.getTextTrim());
			}
		}
		return map;
	}
	/**
	 * 根据路径然烦此节点的值
	 * @param str
	 * @return
	 */
	public static String getTextByStr(String xml ,String str) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element element= (Element) document.selectSingleNode(str);
			if(element!=null){
				return  element.getTextTrim();
			}else{
				return null;
			}
		} catch (Exception e) {
           return null;
		}
	}
	
	
	/**
	 * 根据路径然烦此节点的值
	 * @param str
	 * @return
	 */
	public static String getValueByAttname(String xml ,String str,String attrivutename) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element element= (Element) document.selectSingleNode(str);
			if(element!=null){
				return  element.attributeValue(attrivutename);
			}else{
				return null;
			}
		} catch (Exception e) {
           return null;
		}
	}
	
	
	
	/**
	 * 返回设置好issuccess编码的document
	 * @param xml
	 * @param code
	 * @param text
	 * @return
	 * @throws DocumentException 
	 */
    public static Document setIsSuccess(String xml,String code,String text) throws DocumentException{
    	Document document = DocumentHelper.parseText(xml);
    	Element root = document.getRootElement();
		Element is_success = root.addElement("IS_SUCCESS");
		is_success.addAttribute("CODE", code);
		is_success.setText(text);
    	return document;
    }
    
    
	public static void main(String args[]) {
		String string = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" 
	            + "<ROOT>"
				 + "<REQUEST>" 
				    + "<PARAM NAME =\"AAA\">1111</PARAM>"
				    + "<PARAM NAME =\"BBB\">2222</PARAM>"
				    + "<PARAM NAME =\"CCC\">3333</PARAM>"
				    + "<PARAM NAME =\"DDD\">4444</PARAM>"
				 + "</REQUEST>" 
			    + "</ROOT>";
		Document document;
		try {
			document = XmlUtil.setIsSuccess(string, "00", "测试用");
			System.out.println(document.asXML());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static   <T>T assembleEntity(Class<T>clazz, Map map, String datePattern, Map mapping1) throws Exception {
		Map mapping;
		if (mapping1 == null) {
			mapping = new HashMap();
			mapping.put("REGICODE", "F_REGICODE");
			mapping.put("PAYER", "PAYERNAME");
			//mapping.put("ENTERCODE", "F_ENTEGUID");
			mapping.put("ORIGINALNOTICENO", "BUSINESSNO");
			mapping.put("YWCODE", "BUSINESSCODE");
			mapping.put("PAYERSFZ", "PAYERID");
			mapping.put("EXPIREDPAYDATE", "EXPIREDDATE");
			mapping.put("ISTRUENAME", "USEREALNAME");
			mapping.put("CHITCODE", "F_CHITCODE");


		} else {
			mapping = mapping1;
		}
		for (Iterator i = mapping.keySet().iterator(); i.hasNext(); ) {
			String key = (String) i.next();
			String value = (String) map.get(key);
			if ((value) != null) {
				map.remove(key);
				map.put(mapping.get(key), value);
			}
		}
		Object o = clazz.newInstance();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method m : methods) {
			String meName = m.getName();
			if (meName.contains("set")) {
				String paramName = meName.substring(3).toUpperCase();
				Class pType = m.getParameterTypes()[0];

				if (map.get(paramName) != null && !"".equals(map.get(paramName))) {
					Object paramObject = null;
					if (pType.getName().contains("Date")) {
						String dateStr=(String) map.get(paramName);
						if(dateStr != null && !"".equals(dateStr)&&!"null".equals(dateStr)){
							SimpleDateFormat format = new SimpleDateFormat(datePattern);
							paramObject = format.parse(dateStr);
						}

						//System.out.println((String) map.get(paramName));
					} else {
						Constructor c = pType.getDeclaredConstructor(String.class);
						if (c != null) {
							paramObject = c.newInstance(map.get(paramName));
						} else {
							throw new Exception("含有无法转换的数据类型:该数据为类" + clazz.getName() + "的" + paramName + "属性");
						}

					}
					m.invoke(o, paramObject);

				}
			}
		}

		return (T)o;
	}
}
