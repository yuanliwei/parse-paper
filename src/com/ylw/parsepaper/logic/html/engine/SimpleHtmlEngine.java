package com.ylw.parsepaper.logic.html.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.HeadTag;
import org.htmlparser.tags.StyleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;

/**
 * 简化html代码
 * 
 * @author ylw
 *
 */
public class SimpleHtmlEngine {
	private static Log log = LogFactory.getLog(SimpleHtmlEngine.class);
	private List<HtmlParagraph> paragraphs;
	private String style;

	public void parse(String html) {
		log.debug("SimpleHtmlEngine.parse()");
		html = simpleHtml(html);
		parseContent(html);
	}

	public String simpleHtml(String html) {
		html = deleteComment(html);
		html = deleteRange(html, "mso-", ";", 80);
		html = deleteStr(html, "<o:p></o:p>");
		// html = deleteStr(html, "<o:p>&nbsp;</o:p>");
		// html = deleteRange(html, "<o:p>", "</o:p>", 30);
		html = replaceRange(html, "<o:p>", "</o:p>", 30, "<br>");
		// html = deleteRange(html, "<meta ", ">", 100);
		// html = replaceStr(html,
		// "地理：2011年普通高等学校招生全国统一考试文综地理部分（新课标全国卷）","pics");
		html = html.replaceAll("(\r\n)+", "\n");
		return html;
	}

	private void parseContent(String html) {
		paragraphs = Collections.emptyList();
		try {
			Parser parser = new Parser(html);
			NodeList list = parser.parse(null);

			Node node = list.elementAt(0);
			NodeList sublist = node.getChildren();

			Node head = null;
			Node style = null;
			Node body = null;

			for (int i = 0; i < sublist.size(); i++) {
				Node n = sublist.elementAt(i);
				if (n instanceof HeadTag) {
					NodeList headList = n.getChildren();
					for (int j = 0; j < headList.size(); j++) {
						Node n1 = headList.elementAt(j);
						if (n1 instanceof StyleTag) {
							style = n1;
							break;
						}
					}
					continue;
				}
				if (n instanceof BodyTag) {
					body = n;
					break;
				}
			}

			if (style == null) {
				throw new IllegalStateException("未找到样式表。");
			}
			if (body == null) {
				throw new IllegalStateException("未找到Body。");
			}

			this.style = style.toHtml();

			// body struct : body -> (div)+ -> paragraphs
			NodeList blist = body.getChildren();
			paragraphs = new ArrayList<>();
			for (int i = 0; i < blist.size(); i++) {
				Node n = blist.elementAt(i);
				if (n instanceof Div) {
					SimpleNodeIterator ps = n.getChildren().elements();
					while (ps.hasMoreNodes()) {
						paragraphs.add(new HtmlParagraph(ps.nextNode()));
					}
				}
			}

		} catch (ParserException e) {
			log.error(e.getMessage(), e);
		}

	}

	public List<HtmlParagraph> getParagraphs() {
		return paragraphs;
	}

	public String getStyle() {
		return style;
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
