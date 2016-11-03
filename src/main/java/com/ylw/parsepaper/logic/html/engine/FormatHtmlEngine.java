package com.ylw.parsepaper.logic.html.engine;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.utils.Res;

/**
 * 从html中解析出一个可以用来解析试题的对象
 * 
 * @author ylw
 *
 */
public class FormatHtmlEngine {

	private List<HtmlParagraph> paragraphs;
	private String style;
	private String text;
	private String html;

	public void parseStyles(String style) {
		this.style = style;
	}

	public void parseParagraphs(List<HtmlParagraph> paragraphs) {
		this.paragraphs = paragraphs;
		StrBuilder sbText = new StrBuilder();
		StrBuilder sbHtml = new StrBuilder();
		int start = 0;
		int end = 0;
		for (HtmlParagraph htmlParagraph : paragraphs) {
			sbText.append(htmlParagraph.text);
			end = start + htmlParagraph.text.length();
			htmlParagraph.setTextPostion(start, end);
			start = end + 1;
			sbText.append(htmlParagraph.text);
			sbHtml.append(htmlParagraph.html);
		}
		this.text = sbText.toString();
		this.html = sbHtml.toString();
	}

	public String getAllText(String htmlPath) {
		return text;
	}

	public String getAllHtml() {
		return html;
	}

	public String getHtmlPage() {
		String template = Res.getString("html/template.html");
		String result = MessageFormat.format(template, style, html);
		return result;
	}

}
