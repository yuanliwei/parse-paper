package com.ylw.parsepaper.ui.view;

import java.awt.Label;
import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.logic.main.ParseMain;
import com.ylw.parsepaper.logic.utils.PropUtils;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class MainAppController extends BaseController {
	private static Log log = LogFactory.getLog(MainAppController.class);

	@FXML
	MenuBar menuBar;
	@FXML
	ListView<ListItemData> listView;

	ProgressBar progressBar;

	ObservableList<ListItemData> data = FXCollections.observableArrayList();

	private MainApp mainApp;
	private ParseMain parseMain;

	@FXML
	public StackPane stackPane;

	private BorderPane center;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	protected void initialize() {
		data.add(new ListItemData("alert", "alert('hello')"));
		data.add(new ListItemData("initView", "window.initView()"));
		data.add(new ListItemData("log", "mlog('helllo      jjjh')"));
		data.add(new ListItemData("log2", "jsObj.log('helllo      jjjh')"));
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
					mainApp.mainViewController.exec(item.getJsData());
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
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("打开一个word文档");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Word Files", "*.doc", "*.docx"),
				new ExtensionFilter("All Files", "*.*"));

		String lastPath = PropUtils.get("last_open_doc_path");
		if (StringUtils.isNotBlank(lastPath)) {
			fileChooser.setInitialDirectory(new File(lastPath));
		}

		File selectedFile = fileChooser.showOpenDialog(mainApp.primaryStage);
		if (selectedFile != null) {
			loadFile(selectedFile);
		}
	}

	private void loadFile(File selectedFile) {
		showProgress();
		PropUtils.put("last_open_doc_path", selectedFile.getParent());
		PropUtils.store();
		Task<Integer> task = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				int iterations = 0;
				parseMain = new ParseMain();
				parseMain.parse(selectedFile.getAbsolutePath());
				return iterations;
			}

			@Override
			protected void succeeded() {
				super.succeeded();
				updateMessage("Done!");
				showWebView();
				mainApp.mainViewController.load(parseMain.getHtmlPath());
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
			progressBar.setPrefSize(stackPane.getWidth() - 100, 100);
		}
		stackPane.getChildren().removeAll(center, progressBar);
		stackPane.getChildren().add(progressBar);
	}

	public void showWebView() {
		stackPane.getChildren().removeAll(center, progressBar);
		stackPane.getChildren().add(center);
	}

}
