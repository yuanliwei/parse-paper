package com.ylw.parsepaper.ui.view;

import com.ylw.parsepaper.ui.MainApp;
import com.ylw.parsepaper.ui.controller.BaseController;
import com.ylw.parsepaper.ui.controller.JSInterface;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebErrorEvent;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MainViewController extends BaseController {
	@FXML
	WebView webView;
	private MainApp mainApp;
	public WebEngine webEngine;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {
		// Initialize the person table with the two columns.
		webEngine = webView.getEngine();

		JSInterface jsObj = new JSInterface();
		JSObject window = (JSObject) webEngine.executeScript("window");
		window.setMember("jsObj", jsObj);

		// webEngine.load("file:///C:/Users/ylw/Desktop/tempout/Paragraphs.html");
		webEngine.load("file:///C:/Users/ylw/Desktop/tempout/format.html");

		webEngine.setOnError(event -> {
			System.out.println(event.getMessage());
		});

		webEngine.setOnAlert((event) -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.titleProperty().set("来自网页的信息");
			alert.headerTextProperty().set("来自网页的信息");
			alert.contentTextProperty().set(event.getData());
			alert.showAndWait();
		});
	}

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}

}
