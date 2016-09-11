package com.ylw.parsepaper.html.engine;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.html.model.HtmlElement;

public class HtmlEngine {
	private static Log log = LogFactory.getLog(HtmlEngine.class);

	public List<HtmlElement> parse(String html) {
		// TODO Auto-generated method stub
		log.debug("======================\n" + html + "\n------------------------------------");

		List<String> tagHtmls = HtmlElement.splitTag(html);
		HtmlElement root = new HtmlElement();
		root.name = "root";

		for (int i = 0; i < tagHtmls.size(); i++) {
			HtmlElement htmlElement = new HtmlElement();
			htmlElement.parse(html);
			root.childs.add(htmlElement);
		}
		return null;
	}

}
