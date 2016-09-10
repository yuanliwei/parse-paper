package com.ylw.parsepaper.html;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.html.engine.HtmlEngine;
import com.ylw.parsepaper.html.engine.StyleEngine;
import com.ylw.parsepaper.html.model.HtmlElement;
import com.ylw.parsepaper.html.model.StyleElement;

public class ParseHtml {
	private static Log log = LogFactory.getLog(ParseHtml.class);

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
		Map<String, StyleElement> styleElements = styleEngine.parse(style);

		// 解析出body中的文本
		String body = getBodyText(html);
		HtmlEngine htmlEngine = new HtmlEngine();
		List<HtmlElement> htmlElements = htmlEngine.parse(html);
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

}
