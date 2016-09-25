package com.ylw.parsepaper.html.engine;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.text.StrBuilder;

import com.ylw.parsepaper.html.model.HtmlParagraph;
import com.ylw.parsepaper.utils.FileUtil;

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

	public void getAllText(String htmlPath) {

	}

	public String getAllHtml() {
		return "";
	}

	public String getHtmlPage() {
		String template = FileUtil.
		String result = MessageFormat.format("At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
				planet, new Date(), event);

		return "";
	}

}
