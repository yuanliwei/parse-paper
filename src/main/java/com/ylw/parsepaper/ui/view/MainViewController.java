package com.ylw.parsepaper.ui.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.ui.MainApp;
import com.ylw.parsepaper.ui.controller.BaseController;
import com.ylw.parsepaper.ui.controller.JSInterface;

import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MainViewController extends BaseController {
	private static Log log = LogFactory.getLog(MainViewController.class);

	@FXML
	WebView webView;
	private MainApp mainApp;
	public WebEngine webEngine;
	private JSInterface jsObj;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	protected void initialize() {
		// Initialize the person table with the two columns.
		webEngine = webView.getEngine();
		jsObj = new JSInterface();
		jsObj.setMainApp(mainApp);

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

		webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
			JSObject window = (JSObject) webEngine.executeScript("window");
			System.out.println("sssssssss  - " + newState + "   " + window.getMember("jsObj"));
			window.setMember("jsObj", jsObj);
			if (newState == State.SUCCEEDED) {
				exec("onPageLoaded()");
			}
		});

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		jsObj.setMainApp(mainApp);
	}

	public void load(String filePath) {
		webEngine.load("file:///" + filePath);

	}

	public void exec(String jsData) {
		log.debug("exec : " + jsData);
		webEngine.executeScript(jsData);
	}

}
