package com.ylw.parsepaper.logic.html.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HtmlEngine {
	private static Log log = LogFactory.getLog(HtmlEngine.class);
	private SimpleHtmlEngine simpleHtmlEngine;
	private String htmlPage;

	public void parse(String html) {
		log.debug("HtmlEngine.parse()");
		simpleHtmlEngine = new SimpleHtmlEngine();
		FormatHtmlEngine formatHtmlEngine = new FormatHtmlEngine();

		simpleHtmlEngine.parse(html);

		formatHtmlEngine.parseStyles(simpleHtmlEngine.getStyle());
		formatHtmlEngine.parseParagraphs(simpleHtmlEngine.getParagraphs());

		htmlPage = formatHtmlEngine.getHtmlPage();

	}

	public String getHtmlPage() {
		return htmlPage;
	}

}
