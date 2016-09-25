package com.ylw.parsepaper.logic.html.model;

public class TagElement {
	public static final int NONE = -1;
	public static final int START = 0;
	public static final int END = 1;
	public static final int SINGLE = 2;
	public static final int TEXT = 3;

	public int type = START;
	public int start;
	public int end;
	public String text;
	public String fullText;

	public void parse(String html, int indexStart) {
		start = html.indexOf("<", indexStart);
		end = html.indexOf(">", start);

		if (start < 0 || end < 0) {
			type = NONE;
			start = indexStart;
			end = html.length();
			return;
		}

		text = html.substring(start + 1, end);
		fullText = html.substring(start, end + 1);

		if (text.startsWith("/")) {
			type = END;
			text = text.substring(1);
		} else if (text.endsWith("/")) {
			type = SINGLE;
			text = text.substring(0, text.length() - 1);
		} else if ("br,hr,".indexOf(text.toLowerCase() + ",") != -1) {
			type = SINGLE;
		} else {
			type = START;
		}
	}
}
