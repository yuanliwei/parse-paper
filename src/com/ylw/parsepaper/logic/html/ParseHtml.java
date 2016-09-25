package com.ylw.parsepaper.logic.html;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.logic.html.model.StyleElement;

public class ParseHtml {
	private static Log log = LogFactory.getLog(ParseHtml.class);
	private Map<String, StyleElement> styleElements;

	/**
	 * 解析html
	 * 
	 * @param html
	 * @return
	 */
	public String parse(String html) {

		return null;
	}

	public Map<String, StyleElement> getStyleElements() {
		return styleElements;
	}

	public void setStyleElements(Map<String, StyleElement> styleElements) {
		this.styleElements = styleElements;
	}

}
