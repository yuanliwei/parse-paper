package com.ylw.parsepaper.ui;

import java.io.IOException;

import com.ylw.parsepaper.ui.utils.ResUtil;
import com.ylw.parsepaper.ui.view.MainAppController;
import com.ylw.parsepaper.ui.view.MainViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private BorderPane root;
	public MainViewController mainViewController;
	public MainAppController mainAppController;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("解析试卷");
		primaryStage.getIcons().add(ResUtil.getImageFromRes("icon.png"));

		FXMLLoader loader = ResUtil.getFXMLLoader("MainApp.fxml");
		try {
			root = loader.load();
			mainAppController = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 1000, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		initCenter();

		initController();
	}

	private void initCenter() {
		FXMLLoader loader = ResUtil.getFXMLLoader("MainView.fxml");
		try {
			BorderPane center = loader.load();
			mainViewController = loader.getController();
			root.setCenter(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initController() {
		mainAppController.setMainApp(this);
		mainViewController.setMainApp(this);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
