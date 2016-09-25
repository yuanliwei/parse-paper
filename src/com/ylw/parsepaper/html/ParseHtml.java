package com.ylw.parsepaper.html;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.html.model.HtmlElement;
import com.ylw.parsepaper.html.model.StyleElement;

public class ParseHtml {
	private static Log log = LogFactory.getLog(ParseHtml.class);
	private Map<String, StyleElement> styleElements;
	private List<HtmlElement> htmlElements;

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

	public List<HtmlElement> getHtmlElements() {
		return htmlElements;
	}

	public void setHtmlElements(List<HtmlElement> htmlElements) {
		this.htmlElements = htmlElements;
	}
	
	

}
