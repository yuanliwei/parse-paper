package com.ylw.parsepaper.logic.html.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;

public class HtmlParagraph {
	private static Log log = LogFactory.getLog(HtmlParagraph.class);

	public String raw;// 原始html文本
	public String text; // 段落内部文本
	public String html; // 段落内部html
	public String pStyle; // 段落样式
	public int tLength; // 纯文本的长度
	public int hLength; // html的长度
	public int index; // 段落的索引号
	public int type; // 段落的类型   = Part.T_XXXX
	public int textStartPos;
	public int textEndPos;

	public Node node;

	public HtmlParagraph(String string) {
		// TODO Auto-generated constructor stub
		// <p class=MsoNormal style="layout-grid-mode:char;text-align:left;" >
		// <span style="font-family:宋体;font-size:10.5000pt;" >&#9313;海水淡化。
		// 该国经济比较发达，四周环海，可选适当地点，建海水淡化厂，生产淡水。</span>
		// <span style="font-family:宋体;font-size:10.5000pt;" ></span></p>
		raw = string;
		text = string.replaceAll("<.*?>", "");
		tLength = text.length();
		html = string.substring(string.indexOf(">") + 1, string.length() - "</p>".length());
		hLength = html.length();
		pStyle = string.substring("<p".length(), string.indexOf(">")).trim();
		log.debug(string);
	}

	public HtmlParagraph(Node node, int index2) {
		this.node = node;
		this.index = index2;
		text = node.toPlainTextString();
		html = node.toHtml();
	}

	public void initPos() {

	}

	public void setTextPostion(int start, int end) {
		this.textStartPos = start;
		this.textEndPos = end;
	}

	public boolean isMe(int postion) {
		if (textStartPos <= postion && textEndPos > postion) {
			return true;
		} else {
			return false;
		}
	}

}
