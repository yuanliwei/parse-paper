package com.ylw.parsepaper.views;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LayoutSansTears extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Region headerLeftSpring = new Region();

		Group root = new Group();
		Scene s = new Scene(root, Color.WHITE);

		// Rectangle r = new Rectangle(25, 25, 250, 250);
		// r.setFill(Color.BLUE);
		//
		// root.getChildren().add(r);

		// HBox hbox = new HBox(8); // spacing = 8
		// hbox.getChildren().addAll(new Label("Name"), new TextField());
		// primaryStage.setTitle("Layout Sans Tears: Exercise");
		// root.getChildren().add(hbox);

		// HBox hbox = new HBox();
		// Button button1 = new Button("Add");
		// Button button2 = new Button("Remove");
		// HBox.setHgrow(button1, Priority.ALWAYS);
		// HBox.setHgrow(button2, Priority.ALWAYS);
		// button1.setMaxWidth(Double.MAX_VALUE);
		// button2.setMaxWidth(Double.MAX_VALUE);
		// hbox.getChildren().addAll(button1, button2);

		HBox hbox = new HBox();
		TextField field = new TextField();
		HBox.setHgrow(field, Priority.ALWAYS);
		hbox.getChildren().addAll(new Label("Search:"), field, new Button("Go"));

		root.getChildren().add(hbox);

		// Scene scene = new Scene(root);
		primaryStage.setScene(s);
		primaryStage.show();
	}
}