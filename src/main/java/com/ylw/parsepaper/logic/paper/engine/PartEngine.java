package com.ylw.parsepaper.logic.paper.engine;

import java.util.ArrayList;
import java.util.List;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.paper.model.Part;
import com.ylw.parsepaper.logic.paper.model.PartType;

public class PartEngine {

	private List<HtmlParagraph> paragraph;
	public List<Part> parts = new ArrayList<>();

	public void parse(List<HtmlParagraph> ps) {
		this.paragraph = ps;
		parts.clear();

		int index = 0;
		// 相同的paragraph合并为同一个Part
		for (int i = 0; i < ps.size(); i++) {
			HtmlParagraph p = ps.get(i);

			PartType type = p.type;
			List<HtmlParagraph> tem = new ArrayList<>();
			tem.add(p);
			while (i + 1 < ps.size()) {
				p = ps.get(i + 1);
				if (p.type != type) {
					break;
				}
				tem.add(p);
				i++;
			}
			if (type == Part.T_TYPE_NONE)
				continue;
			parts.add(new Part(index++, (HtmlParagraph[]) tem.toArray(new HtmlParagraph[tem.size()])));
		}
	}

	public List<Part> getParts() {
		return parts;
	}

	public List<Part> getFormatLevelParts() {
		List<Part> ps = new ArrayList<>();
		for (int i = 0; i < parts.size(); i++) {
			Part p = parts.get(i);

		}
		return ps;
	}

}
