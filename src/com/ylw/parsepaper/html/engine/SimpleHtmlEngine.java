package com.ylw.parsepaper.html.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 简化html代码
 * 
 * @author ylw
 *
 */
public class SimpleHtmlEngine {
	private static Log log = LogFactory.getLog(SimpleHtmlEngine.class);

	public String parse(String html) {
		html = deleteComment(html);
		html = deleteRange(html, "mso-", ";", 80);
		return html;
	}

	/**
	 * 删除指定范围的注释
	 * 
	 * @param html
	 * @param string
	 * @param string2
	 * @param i
	 * @return
	 */
	private String deleteRange(String html, String prefix, String endfix, int maxLength) {
		int curIndex = 0;
		List<Pos> arr = new ArrayList<>();
		do {
			int start = html.indexOf(prefix, curIndex);
			if (start < 0)
				break;
			curIndex = start + prefix.length();
			int end = html.indexOf(endfix, curIndex) + endfix.length();
			if (end < 0) {
				end = html.length();
				break;
			}
			if (maxLength <= 0) {
				arr.add(new Pos(start, end));
			} else if (end - start < maxLength) {
				arr.add(new Pos(start, end));
			}
			try {
				log.debug("start:" + start + " end:" + end + " \t|" + html.substring(start, end) + "|\t\t");
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			curIndex = end;
		} while (true);
		if (arr.size() == 0)
			return html;

		StringBuilder sb = new StringBuilder();

		int start = 0;
		for (int i = 0; i < arr.size(); i++) {
			Pos pos = arr.get(i);
			if (i == 0 && pos.x == 0) {
				start = pos.y;
				if (arr.size() == 1) {
					sb.append(html.substring(pos.y));
					break;
				} else {
					continue;
				}
			}
			sb.append(html.substring(start, pos.x));
			start = pos.y;
			if (i == arr.size() - 1) {
				sb.append(html.substring(start, html.length()));
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 删除注释
	 * 
	 * @param html
	 * @return
	 */
	private String deleteComment(String html) {
		// return html.replaceAll("<!--(.|[\r\n])*?-->", "");
		int curIndex = 0;
		List<Pos> arr = new ArrayList<>();
		do {
			int start = html.indexOf("<!--", curIndex);
			if (start < 0)
				break;
			curIndex += 3;
			int end = html.indexOf("-->", curIndex) + 3;
			if (end < 0) {
				end = html.length();
				break;
			}
			arr.add(new Pos(start, end));
			curIndex = end;
		} while (true);
		if (arr.size() == 0)
			return html;

		StringBuilder sb = new StringBuilder();

		int start = 0;
		for (int i = 0; i < arr.size(); i++) {
			Pos pos = arr.get(i);
			if (i == 0 && pos.x == 0) {
				start = pos.y;
				if (arr.size() == 1) {
					sb.append(html.substring(pos.y));
					break;
				} else {
					continue;
				}
			}
			sb.append(html.substring(start, pos.x));
			start = pos.y;
			if (i == arr.size() - 1) {
				sb.append(html.substring(start, html.length()));
				break;
			}
		}
		return sb.toString();
	}

	class Pos {

		int x;
		int y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
