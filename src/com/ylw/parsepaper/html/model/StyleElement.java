package com.ylw.parsepaper.html.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;

public class StyleElement {
	private static Log log = LogFactory.getLog(StyleElement.class);

	String name;
	Map<String, String> attrs = new HashMap<>();

	public String getName() {
		return name;
	}

	/**
	 * <pre>
		h1.pt-a6 {
			margin-top: 12pt;
			margin-bottom: 3pt;
			text-align: center;
			font-family: 宋体;
			font-size: 16pt;
			line-height: 108%;
			margin-left: 0;
			margin-right: 0;
		}
	 * </pre>
	 * 
	 * @param string
	 */
	public void parseString(String string) {
		// TODO Auto-generated method stub
		// log.debug(string);
		String[] ss = string.split("\\{");
		if (ss.length != 2 || TextUtils.isBlank(ss[0]) || TextUtils.isBlank(ss[1])) {
			throw new IllegalStateException("样式表格式错误：" + string);
		}
		name = ss[0].trim();
		String valueStr = ss[1].trim();
		String[] ss1 = valueStr.split("(\\n|;)");
		// log.debug("ss1 " + ss1.length);

		for (int i = 0; i < ss1.length; i++) {
			String s = ss1[i];
			if (TextUtils.isBlank(s) || s.length() < 3) {
				continue;
			}
			String[] ss2 = s.split(":");
			if (ss2.length != 2 || TextUtils.isBlank(ss2[0]) || TextUtils.isBlank(ss2[1])) {
				continue;
			}
			String name = ss2[0].trim();
			String value = ss2[1].trim();
			attrs.put(name, value);
		}

	}

	public void appendAttrs(StyleElement element) {
		attrs.putAll(element.attrs);
	}
}
