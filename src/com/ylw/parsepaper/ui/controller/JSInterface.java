package com.ylw.parsepaper.ui.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.logic.db.TextTypeValue;
import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
import com.ylw.parsepaper.logic.paper.model.PartType;
import com.ylw.parsepaper.logic.utils.ormliteutils.OrmLiteUtils;
import com.ylw.parsepaper.ui.MainApp;

public class JSInterface {
	private static Log log = LogFactory.getLog(JSInterface.class);

	private MainApp mainApp;

	public void log(String msg) {
		log.debug("Console : " + msg);
	}

	public void edit() {
		mainApp.mainAppController.showEdit();
	}

	public void hideEdit(boolean save) {
		mainApp.mainAppController.hideEdit(save);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setParagraphsType(int type, String indexStr) {
		log.debug("setParagraphsType type : " + type + " " + indexStr);
		List<HtmlParagraph> ps = mainApp.mainAppController.parseMain.simpleHtmlEngine.getParagraphs();
		List<TextTypeValue> typeValues = new ArrayList<>();
		// 4,7,8,10,13
		Arrays.asList(indexStr.split(",")).forEach(num -> {
			if (StringUtils.isNoneBlank(num)) {
				HtmlParagraph p = ps.get(Integer.valueOf(num));
				p.type = PartType.get(type);
				log.debug(p.text);
				typeValues.add(new TextTypeValue(p.type, p.text));
			}
		});
		OrmLiteUtils.saveOrUpdateAll(typeValues);
		StrBuilder strBuilder = new StrBuilder();
		ps.forEach((p) -> {
			strBuilder.append(MessageFormat.format("\n\t{0} - {1}", p.index, p.type));
		});
		strBuilder.append("\n");
		// log.debug(strBuilder.toString());
		mainApp.mainAppController.parseMain.parsePaperStruct();
	}
	public void getParagraphsTypeStr() {
		log.debug("getParagraphsType " );
		List<HtmlParagraph> ps = mainApp.mainAppController.parseMain.simpleHtmlEngine.getParagraphs();
		
		
		
		StrBuilder strBuilder = new StrBuilder();
		ps.forEach((p) -> {
			strBuilder.append(p.type.value).append(",");
		});
		strBuilder.deleteCharAt(strBuilder.length());
		strBuilder.append("\n");
		// log.debug(strBuilder.toString());
		mainApp.mainAppController.parseMain.parsePaperStruct();
	}
}
/*
 * sample data : 0 - 1, 1 - -1, 2 - 101, 3 - 201, 4 - 202, 5 - 203, 6 - 204, 7 -
 * 204, 8 - 205, 9 - 206, 10 - 201, 11 - 202, 12 - 203, 13 - 204, 14 - 205, 15 -
 * 206, 16 - 201, 17 - 202, 18 - 203, 19 - 204, 20 - 204, 21 - 204, 22 - 204, 23
 * - 204, 24 - 204, 25 - 204, 26 - 204, 27 - 204, 28 - 205, 29 - 206, 30 - 201,
 * 31 - 202, 32 - 203, 33 - 204, 34 - 205, 35 - 206, 36 - 201, 37 - 202, 38 -
 * 203, 39 - 204, 40 - 204, 41 - 205, 42 - 206, 43 - 201, 44 - 202, 45 - 203, 46
 * - 204, 47 - 204, 48 - 204, 49 - 204, 50 - 204, 51 - 204, 52 - 204, 53 - 204,
 * 54 - 204, 55 - 204, 56 - 204, 57 - 205, 58 - 206, 59 - 102, 60 - 201, 61 -
 * 204, 62 - 204, 63 - 204, 64 - 205, 65 - 206, 66 - 201, 67 - 204, 68 - 204, 69
 * - 204, 70 - 204, 71 - 204, 72 - 204, 73 - 204, 74 - 204, 75 - 204, 76 - 205,
 * 77 - 206, 78 - 201, 79 - 204, 80 - 204, 81 - 205, 82 - 206, 83 - 201, 84 -
 * 201, 85 - 204, 86 - 204, 87 - 204, 88 - 204, 89 - 204, 90 - 204, 91 - 204, 92
 * - 204, 93 - 204, 94 - 204, 95 - 204, 96 - 204, 97 - 204, 98 - 204, 99 - 204,
 * 100 - 204, 101 - 204, 102 - 204, 103 - 204, 104 - 204, 105 - 204, 106 - 204,
 * 107 - 204, 108 - 204, 109 - 204, 110 - 204, 111 - 204, 112 - 204, 113 - 204,
 * 114 - 204, 115 - 204, 116 - 205, 117 - 206, 118 - -1, 119 - -1, 120 - -1, 121
 * - -1, 122 - -1, 123 - -1, 124 - -1, 125 - -1, 126 - -1, 127 - -1, 128 - -1
 */
