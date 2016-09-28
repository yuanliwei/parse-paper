package com.ylw.parsepaper.logic.paper.engine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.paper.model.Part;

public class PartEngine {

	private List<HtmlParagraph> paragraph;
	public List<Part> parts = new ArrayList<>();

	public void parse(List<HtmlParagraph> ps) {
		this.paragraph = ps;
		Point index = new Point();
		parts.clear();

		index.x = 0;
		for (int i = 0; i < ps.size(); i++) {
			HtmlParagraph p = ps.get(i);
			if (Part.isPaper(p.type)) {
				parts.add(new Part(index.x++, p));
			}
			if (Part.isBig(p.type)) {
				parts.add(new Part(index.x++, p));
			}
			if (Part.isSmall(p.type)) {
				List<HtmlParagraph> tem = new ArrayList<>();
				tem.add(p);
				int cindex = i + 1;
				do {
					HtmlParagraph temP = ps.get(cindex);
					if (!Part.isSmall(temP.type)) {
						i = cindex - 1;
						break;
					}
					tem.add(temP);
					cindex++;
				} while (cindex < ps.size());
				parts.add(new Part(index.x++, (HtmlParagraph[]) tem.toArray()));
			}
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
