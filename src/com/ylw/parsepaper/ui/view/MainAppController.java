package com.ylw.parsepaper.ui.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.ui.MainApp;
import com.ylw.parsepaper.ui.controller.BaseController;
import com.ylw.parsepaper.ui.model.ListItemData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainAppController extends BaseController {
	private static Log log = LogFactory.getLog(MainAppController.class);

	@FXML
	MenuBar menuBar;
	@FXML
	ListView<ListItemData> listView;

	ProgressBar progressBar;

	ObservableList<ListItemData> data = FXCollections.observableArrayList();

	private MainApp mainApp;

	@FXML
	public StackPane stackPane;

	private BorderPane center;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	protected void initialize() {
		data.add(new ListItemData("alert", "alert('hello')"));
		data.add(new ListItemData("getSelection", "alert(window.getSelection())"));
		data.add(new ListItemData("getSelection.parentElement",
				"window.getSelection().anchorNode.parentElement.innerHTML"));

		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<ListItemData>, ListCell<ListItemData>>() {

			@Override
			public ListCell<ListItemData> call(ListView<ListItemData> param) {
				return new ButtonCell();
			};
		});

	}

	class ButtonCell extends ListCell<ListItemData> {
		Button button;

		public ButtonCell() {
			super();
			button = new Button();
			button.setTextAlignment(TextAlignment.LEFT);
			button.setAlignment(Pos.BASELINE_LEFT);
			button.setPrefWidth(listView.getPrefWidth() - 20);
		}

		@Override
		protected void updateItem(ListItemData item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				button.setText(item.getName());
				button.setOnAction(value -> {
					mainApp.mainViewController.webEngine.executeScript(item.getJsData());

					System.out.println(button.getParent());
				});
				setGraphic(button);
			}
		}
	}

	@FXML
	public void onClose(ActionEvent event) {
		System.out.println(((MenuItem) event.getSource()).getUserData());
	}

	@FXML
	public void onOpenUrl(ActionEvent event) {
		mainApp.mainViewController.webEngine.load((String) ((MenuItem) event.getSource()).getUserData());
	}

	@FXML
	public void onRefresh() {
		mainApp.mainViewController.webEngine.reload();
	}

	@FXML
	public void onAlert() {
		mainApp.mainViewController.webEngine.executeScript("alert('heeeeeello')");
	}

	@FXML
	public void onOpenFile() {
		log.debug("click open file");
		showProgress();
		Task<Integer> task = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				int iterations = 0;
				for (iterations = 0; iterations < 100; iterations++) {
					if (isCancelled()) {
						break;
					}
					Thread.sleep(100);
					updateProgress(iterations, 100);
					System.out.println("Iteration " + iterations);
				}
				return iterations;
			}

			@Override
			protected void succeeded() {
				super.succeeded();
				updateMessage("Done!");
				showWebView();
			}

			@Override
			protected void cancelled() {
				super.cancelled();
				updateMessage("Cancelled!");
			}

			@Override
			protected void failed() {
				super.failed();
				updateMessage("Failed!");
			}
		};
		progressBar.progressProperty().bind(task.progressProperty());
		new Thread(task).start();
	}

	public void setCenter(BorderPane center) {
		this.center = center;
	}

	public void showProgress() {
		if (progressBar == null) {
			progressBar = new ProgressBar();
			progressBar.setPrefSize(500, 100);
		}
		stackPane.getChildren().remove(center);
		stackPane.getChildren().add(progressBar);
	}

	public void showWebView() {
		stackPane.getChildren().remove(progressBar);
		stackPane.getChildren().add(center);
	}

}
