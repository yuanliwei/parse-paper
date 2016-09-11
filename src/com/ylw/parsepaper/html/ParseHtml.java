package com.ylw.parsepaper.html;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.html.engine.DomEngine;
import com.ylw.parsepaper.html.engine.HtmlEngine;
import com.ylw.parsepaper.html.engine.StyleEngine;
import com.ylw.parsepaper.html.model.HtmlElement;
import com.ylw.parsepaper.html.model.StyleElement;
import com.ylw.parsepaper.utils.CacheUtil;

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
		// TODO Auto-generated method stub
		// 解析出style标签中的文本
		String style = getStyleText(html);
		StyleEngine styleEngine = new StyleEngine();
		styleElements = styleEngine.parse(style);

		// 解析出body中的文本
		String body = getBodyText(html);
		DomEngine domEngine = new DomEngine();
		List<HtmlElement> treeHtmlElements = domEngine.parse(body);
		
		HtmlEngine htmlEngine = new HtmlEngine();
		htmlElements = htmlEngine.parse(body);
		return null;
	}

	/**
	 * 解析出body标签中的文本
	 * 
	 * @param html
	 * @return
	 */
	private String getBodyText(String html) {
		int start = html.indexOf("<body>") + "<body>".length();
		int end = html.lastIndexOf("</body>");
		String result = html.substring(start, end);
		return result;
	}

	/**
	 * 解析出Style标签中的文本
	 * 
	 * @param html
	 * @return
	 */
	private String getStyleText(String html) {
		int start = html.indexOf("<style>") + "<style>".length();
		int end = html.indexOf("</style>");
		String result = html.substring(start, end);
		return result;
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
