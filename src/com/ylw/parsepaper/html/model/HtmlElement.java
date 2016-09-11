package com.ylw.parsepaper.html.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.TextUtils;

public class HtmlElement {
	private static Log log = LogFactory.getLog(HtmlElement.class);

	/**
	 * 把并列的标签分割为字符串
	 * 
	 * @param html
	 * @return
	 */
	public static List<LevelTag> splitTag(String html) {
		// List<String> tags = new ArrayList<>();
		List<LevelTag> lTags = new ArrayList<>();
		int start = 0;
		do {
			TagElement tag = getOneTag(html, start);
			if (tag.type == TagElement.SINGLE) {
				// tags.add(tag.fullText);
				start = tag.end;
				LevelTag lTag = new LevelTag();
				lTag.start = tag.start;
				lTag.end = tag.end;
				lTags.add(lTag);
				continue;
			}
			if (tag.type == TagElement.START) {
				int deep = 0;
				int start1 = tag.end;
				int end1 = start1;
				LevelTag lTag = new LevelTag();
				lTag.start = tag.start;
				lTag.end = tag.end;
				lTags.add(lTag);
				LevelTag curTag = lTag;
				do {
					TagElement tag1 = getOneTag(html, start1);
					start1 = tag1.end;
					if (tag1.type == TagElement.SINGLE) {
						LevelTag lTag0 = new LevelTag();
						lTag0.start = tag1.start;
						lTag0.end = tag1.end;
						lTag0.pTag = curTag;
						curTag.subs.add(lTag0);
						continue;
					}
					if (tag1.type == TagElement.START) {
						deep++;
						LevelTag lTag1 = new LevelTag();
						lTag1.start = tag1.start;
						lTag1.end = tag1.end;
						lTag1.pTag = curTag;
						curTag.subs.add(lTag1);
						curTag = lTag1;
					} else if (tag1.type == TagElement.END) {
						deep--;
						curTag.end = tag1.end;
						curTag = curTag.pTag;
						if (deep == -1) {
							end1 = tag1.end;
							break;
						}
					} else {
						end1 = tag1.end;
						break;
					}
				} while (true);
				// String text = html.substring(tag1.start, end1 + 1);
				// if (!TextUtils.isBlank(text)) {
				//// tags.add(text.trim());
				// LevelTag lTag2 = new LevelTag();
				// lTag2.start = tag.start;
				// lTag2.end = end1;
				// lTags.add(lTag2);
				// }
				start = end1 + 1;
				continue;
			}
			if (tag.type == TagElement.NONE) {
				String text = html.substring(tag.start, tag.end);
				if (!TextUtils.isBlank(text)) {
					// tags.add(text.trim());
					LevelTag lTag3 = new LevelTag();
					lTag3.start = tag.start;
					lTag3.end = tag.end;
					lTags.add(lTag3);
				}
				break;
			}
		} while (true);

		return lTags;
	}

	private static TagElement getOneTag(String html, int indexStart) {
		TagElement tagElement = new TagElement();
		tagElement.parse(html, indexStart);
		return tagElement;
	}

	public List<HtmlElement> childs = new ArrayList<>();
	public String name;
	public Map<String, String> attrs = new HashMap<>();

	/**
	 * <pre>
	   <p
	    dir="ltr"
	    class="pt-a-000004">
	    <span
	      xml:space="preserve"
	      class="pt-a0-000005">    A．</span>
	    <span
	      lang="zh-CN"
	      class="pt-a0-000003">公式:rId88</span>
	    <span
	      lang="zh-CN"
	      class="pt-a0-000003">公式:rId94</span>
	  </p>
	 * </pre>
	 * 
	 * @param html
	 */
	public void parse(String html, LevelTag lTag) {
		// TODO 解析出标签名 子标签 和 标签的属性
		// log.debug(html);
		String text = lTag.substr(html);
		TagElement t = getOneTag(text, 0);
		log.debug(t.fullText);
		for (LevelTag lTag0 : lTag.subs) {
			HtmlElement htmlElement = new HtmlElement();
			htmlElement.parse(html, lTag0);
			childs.add(htmlElement);
		}
	}

}
