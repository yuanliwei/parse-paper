package com.ylw.parsepaper.html.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.TextUtils;

public class HtmlElement {

	/**
	 * 把并列的标签分割为字符串
	 * 
	 * @param html
	 * @return
	 */
	public static List<String> splitTag(String html) {
		List<String> tags = new ArrayList<>();

		int start = 0;
		do {
			TagElement tag = getOneTag(html, start);
			if (tag.type == TagElement.SINGLE) {
				tags.add(tag.fullText);
				start = tag.end;
				continue;
			}
			if (tag.type == TagElement.START) {
				int deep = 0;
				int start1 = tag.end;
				int end1 = start1;
				do {
					TagElement tag1 = getOneTag(html, start1);
					start1 = tag1.end;
					if (tag1.type == TagElement.SINGLE) {
						continue;
					}
					if (tag1.type == TagElement.START) {
						deep++;
					} else if (tag1.type == TagElement.END) {
						deep--;
						if (deep == -1) {
							end1 = tag1.end;
							break;
						}
					} else {
						end1 = tag1.end;
						break;
					}
				} while (true);
				String text = html.substring(tag.start, end1 + 1);
				if (!TextUtils.isBlank(text))
					tags.add(text.trim());
				start = end1 + 1;
				continue;
			}
			if (tag.type == TagElement.NONE) {
				String text = html.substring(tag.start, tag.end);
				if (!TextUtils.isBlank(text))
					tags.add(text.trim());
				break;
			}
		} while (true);

		return tags;
	}

	private static TagElement getOneTag(String html, int indexStart) {
		TagElement tagElement = new TagElement();
		tagElement.parse(html, indexStart);
		return tagElement;
	}

	public List<HtmlElement> childs = new ArrayList<>();
	public String name;

	public void parse(String html) {
		// TODO 解析出标签名 子标签 和 标签的属性

	}

}
