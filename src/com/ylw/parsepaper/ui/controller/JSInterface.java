package com.ylw.parsepaper.ui.controller;

import com.ylw.parsepaper.ui.MainApp;

public class JSInterface {
	private MainApp mainApp;

	public void log(String msg) {
		System.out.println("Console : " + msg);
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
}
