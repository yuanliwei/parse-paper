package com.ylw.parsepaper.html.engine;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;

import com.ylw.parsepaper.html.model.StyleElement;

public class StyleEngine {
	private static Log log = LogFactory.getLog(StyleEngine.class);

	public Map<String, StyleElement> parse(String style) {
		Map<String, StyleElement> styleMap = new HashMap<>();
		String[] styles = style.split("\\}");
		for (int i = 0; i < styles.length; i++) {
			String s = styles[i];
			if (TextUtils.isBlank(s))
				continue;

			StyleElement element = new StyleElement();
			element.parseString(s + "}");

			StyleElement old = styleMap.get(element.getName());
			if (old != null) {
				old.appendAttrs(element);
			} else {
				styleMap.put(element.getName(), element);
			}
		}
		return styleMap;
	}

}
