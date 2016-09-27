package com.ylw.parsepaper.logic.paper.engine;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.paper.model.Part;

public class PartEngine {

	private List<HtmlParagraph> paragraph;
	public List<Part> parts = new ArrayList<>();

	public void parse(List<HtmlParagraph> ps) {
		this.paragraph = ps;
		Point index = new Point();

		ps.forEach((paragraph) -> {
			parts.add(new Part(index.x++, paragraph));
		});
	}

}
