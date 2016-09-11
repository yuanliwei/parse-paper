package com.ylw.parsepaper.html.engine;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ylw.parsepaper.html.model.HtmlElement;

public class DomEngine {
	private static Log log = LogFactory.getLog(DomEngine.class);

	public List<HtmlElement> parse(String body) {
		// TODO Auto-generated method stub
		try {
			Document document = DocumentHelper.parseText(body);
			Element root = document.getRootElement();
			root.getName();
		} catch (DocumentException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
