package com.ylw.parsepaper.logic.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.logic.html.ParseHtml;
import com.ylw.parsepaper.logic.utils.CacheUtil;
import com.ylw.parsepaper.logic.utils.FileUtil;
import com.ylw.parsepaper.logic.utils.PropUtils;

public class ParseMain {
	private static Log log = LogFactory.getLog(ParseMain.class);

	public static void main(String[] args) {
		log.debug("hello parse paper.");
		PropUtils.load();
		CacheUtil.initCache();

		String htmlPath = PropUtils.get("test_html_path");
		String html = FileUtil.getString(htmlPath);
		log.debug(html);
		ParseHtml parseHtml = new ParseHtml();
		String plantHtml = parseHtml.parse(html);
	}
}
