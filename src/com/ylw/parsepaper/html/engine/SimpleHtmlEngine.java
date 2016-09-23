package com.ylw.parsepaper.html.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.html.model.HtmlParagraph;

/**
 * 简化html代码
 * 
 * @author ylw
 *
 */
public class SimpleHtmlEngine {
	private static Log log = LogFactory.getLog(SimpleHtmlEngine.class);

	public String parse(String html) {
		log.debug("SimpleHtmlEngine.parse()");
		html = deleteComment(html);
		html = deleteRange(html, "mso-", ";", 80);
		html = deleteStr(html, "<o:p></o:p>");
		// html = deleteStr(html, "<o:p>&nbsp;</o:p>");
		// html = deleteRange(html, "<o:p>", "</o:p>", 30);
		html = replaceRange(html, "<o:p>", "</o:p>", 30, "<br>");
		// html = replaceStr(html,
		// "地理：2011年普通高等学校招生全国统一考试文综地理部分（新课标全国卷）","pics");
		html = html.replaceAll("(\r\n)+", "\n");
		return html;
	}

	public List<HtmlParagraph> getParagraphs(String html) {
		String result = parse(html);
		String prefix = "<p";
		String endfix = "</p>";
		int maxLength = 0;
		List<String> paragraphs = findRanges(result, prefix, endfix, maxLength);
		List<HtmlParagraph> results = new ArrayList<>(paragraphs.size());
		for (String string : paragraphs) {
			results.add(new HtmlParagraph(string));
		}
		return results;

	}

	/**
	 * 替换文本中指定范围的字符串
	 * 
	 * @param html
	 * @param prefix
	 * @param endfix
	 * @param maxLength
	 * @param newString
	 * @return
	 */
	public String replaceRange(String html, String prefix, String endfix, int maxLength, String newString) {
		int curIndex = 0;
		List<Pos> arr = new ArrayList<>();
		do {
			int start = html.indexOf(prefix, curIndex);
			if (start < 0)
				break;
			curIndex = start + prefix.length();
			int end = html.indexOf(endfix, curIndex);
			if (end < 0) {
				break;
			}
			end += endfix.length();

			if (maxLength <= 0) {
				arr.add(new Pos(start, end));
			} else if (end - start < maxLength) {
				arr.add(new Pos(start, end));
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
					sb.append(newString);
					sb.append(html.substring(pos.y));
					break;
				} else {
					continue;
				}
			}
			sb.append(html.substring(start, pos.x));
			sb.append(newString);
			start = pos.y;
			if (i == arr.size() - 1) {
				sb.append(html.substring(start, html.length()));
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 替换文本中指定的字符串
	 * 
	 * @param html
	 * @param string
	 * @return
	 */
	public String replaceStr(String html, String oldString, String newString) {
		int curIndex = 0;
		List<Pos> arr = new ArrayList<>();
		do {
			int start = html.indexOf(oldString, curIndex);
			if (start < 0)
				break;
			curIndex = start + oldString.length();
			int end = start + oldString.length();
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
					sb.append(newString);
					sb.append(html.substring(pos.y));
					break;
				} else {
					continue;
				}
			}
			sb.append(html.substring(start, pos.x));
			sb.append(newString);

			start = pos.y;
			if (i == arr.size() - 1) {
				sb.append(html.substring(start, html.length()));
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * 删除文本中指定的字符串
	 * 
	 * @param html
	 * @param string
	 * @return
	 */
	public String deleteStr(String html, String string) {
		int curIndex = 0;
		List<Pos> arr = new ArrayList<>();
		do {
			int start = html.indexOf(string, curIndex);
			if (start < 0)
				break;
			curIndex = start + string.length();
			int end = start + string.length();
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

	/**
	 * 删除指定范围的字符串
	 * 
	 * @param html
	 * @param prefix
	 *            起始字符
	 * @param endfix
	 *            结束字符
	 * @param maxLength
	 *            查找到的字符串长度最大值，maxLength < 1 是时没有长度限制
	 * @return
	 */
	public String deleteRange(String html, String prefix, String endfix, int maxLength) {
		int curIndex = 0;
		List<Pos> arr = new ArrayList<>();
		do {
			int start = html.indexOf(prefix, curIndex);
			if (start < 0)
				break;
			curIndex = start + prefix.length();
			int end = html.indexOf(endfix, curIndex);
			if (end < 0) {
				break;
			}
			end += endfix.length();

			if (maxLength <= 0) {
				arr.add(new Pos(start, end));
			} else if (end - start < maxLength) {
				arr.add(new Pos(start, end));
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
	 * 查找指定范围内的字符串
	 * 
	 * @param html
	 * @param prefix
	 * @param endfix
	 * @param maxLength
	 * @return
	 */
	public List<String> findRanges(String html, String prefix, String endfix, int maxLength) {
		List<String> results = new ArrayList<>();

		int curIndex = 0;
		List<Pos> arr = new ArrayList<>();
		do {
			int start = html.indexOf(prefix, curIndex);
			if (start < 0)
				break;
			curIndex = start + prefix.length();
			int end = html.indexOf(endfix, curIndex);
			if (end < 0) {
				break;
			}
			end += endfix.length();

			if (maxLength <= 0) {
				arr.add(new Pos(start, end));
			} else if (end - start < maxLength) {
				arr.add(new Pos(start, end));
			}
			curIndex = end;
		} while (true);
		if (arr.size() == 0)
			return results;

		for (int i = 0; i < arr.size(); i++) {
			Pos pos = arr.get(i);
			results.add(html.substring(pos.x, pos.y));
		}
		return results;
	}

	/**
	 * 删除注释
	 * 
	 * @param html
	 * @return
	 */
	public String deleteComment(String html) {
		String prefix = "<!--";
		String endfix = "-->";
		// return html.replaceAll("<!--(.|[\r\n])*?-->", "");
		return deleteRange(html, prefix, endfix, 0);
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
