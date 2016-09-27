package com.ylw.parsepaper.ui.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.logic.html.model.HtmlParagraph;
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
		// 4,7,8,10,13
		Arrays.asList(indexStr.split(",")).forEach(num -> {
			ps.get(Integer.valueOf(num)).type = type;
		});
		
	}
}
