package com.ylw.parsepaper.html.engine;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.html.model.HtmlElement;
import com.ylw.parsepaper.html.model.LevelTag;

public class HtmlEngine {
	private static Log log = LogFactory.getLog(HtmlEngine.class);

	public List<HtmlElement> parse(String html) {
		// TODO Auto-generated method stub
		log.debug("======================\n" + html + "\n------------------------------------");

		List<LevelTag> lTags = HtmlElement.splitTag(html);
		HtmlElement root = new HtmlElement();
		root.name = "root";

		for (LevelTag lTag : lTags) {
			HtmlElement htmlElement = new HtmlElement();
			htmlElement.parse(html, lTag);
			root.childs.add(htmlElement);
		}

		return null;
	}

}
