package com.ylw.parsepaper.html.model;

import java.util.ArrayList;
import java.util.List;

public class LevelTag {
	public int start;
	public int end;
	public List<LevelTag> subs = new ArrayList<>();
	public LevelTag pTag;

	public String substr(String html) {
		return html.substring(start, end + 1);
	}

}
