package com.ylw.parsepaper.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.html.ParseHtml;
import com.ylw.parsepaper.utils.CacheUtil;
import com.ylw.parsepaper.utils.FileUtil;
import com.ylw.parsepaper.utils.PropUtils;

public class ParseMain {
	private static Log log = LogFactory.getLog(ParseMain.class);

	public static void main(String[] args) {
		log.debug("hello parse paper.");
		PropUtils.load();
		CacheUtil.initCache();

		String htmlPath = "C:\\Users\\ylw\\Desktop\\挑战项目\\doc文件\\Microsoft_Office_Word_97_-_2003___1.html";
		String html = FileUtil.getString(htmlPath);
		log.debug(html);
		ParseHtml parseHtml = new ParseHtml();
		String plantHtml = parseHtml.parse(html);
	}
}
