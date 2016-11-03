package com.ylw.parsepaper.ui.utils;

import java.io.IOException;

import com.ylw.parsepaper.ui.MainApp;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

public class ResUtil {
	public static Image getImageFromRes(String resName) {
		Image image = new Image(MainApp.class.getResourceAsStream("images/" + resName));
		return image;
	}

	public static <T> T loadFXML(String layout) {
		try {
			if (!layout.endsWith(".fxml")) {
				layout += ".fxml";
			}
			return FXMLLoader.load(MainApp.class.getResource("view/" + layout));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static FXMLLoader getFXMLLoader(String layout) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/" + layout));
		return loader;
	}
}
