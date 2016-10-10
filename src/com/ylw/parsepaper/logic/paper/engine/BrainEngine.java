package com.ylw.parsepaper.logic.paper.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import com.ylw.parsepaper.logic.db.TextTypeValue;
import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.paper.model.Part;
import com.ylw.parsepaper.logic.utils.ormliteutils.OrmLiteUtils;

public class BrainEngine {

	public void guessParagraphType(List<HtmlParagraph> ps) {
		// TODO 这里用来自动判断段落的类型
		List<TextTypeValue> textTypeValues = OrmLiteUtils.queryForAll(TextTypeValue.class);
		Map<String, TextTypeValue> map = new HashMap<>();
		textTypeValues.forEach(action -> {
			map.put(action.getGuid(), action);
		});
		ps.forEach((p) -> {
			TextTypeValue typeValue = new TextTypeValue(Part.T_TYPE_NONE, StringEscapeUtils.unescapeHtml4(p.text));
			if (map.containsKey(typeValue.getGuid())) {
				p.type = map.get(typeValue.getGuid()).getPartType();
			} else {
				p.type = typeValue.getPartType();
			}
		});
	}

	public void guessPartType(List<Part> parts) {
		// TODO 判断Part的类型

	}

}
