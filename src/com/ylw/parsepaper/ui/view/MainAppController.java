package com.ylw.parsepaper.ui.view;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ylw.parsepaper.logic.main.ParseMain;
import com.ylw.parsepaper.logic.utils.FileUtil;
import com.ylw.parsepaper.logic.utils.PropUtils;
import com.ylw.parsepaper.ui.MainApp;
import com.ylw.parsepaper.ui.controller.BaseController;
import com.ylw.parsepaper.ui.model.ListItemData;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import javafx.util.Duration;

public class MainAppController extends BaseController {
	private static Log log = LogFactory.getLog(MainAppController.class);

	@FXML
	MenuBar menuBar;
	@FXML
	ListView<ListItemData> listView;

	ProgressBar progressBar;
	ProgressIndicator progressIndicator;

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
		data.add(new ListItemData("编辑", "jsObj.edit()"));
		data.add(new ListItemData("保存", "jsObj.hideEdit(true)"));
		data.add(new ListItemData("取消", "jsObj.hideEdit(false)"));
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

	private boolean hasLoadPage = false;

	private void loadFile(File selectedFile) {
		showProgress();
		PropUtils.put("last_open_doc_path", selectedFile.getParent());
		PropUtils.store();
		Task<Integer> task = new Task<Integer>() {

			@Override
			protected Integer call() throws Exception {
				hasLoadPage = false;
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
				hasLoadPage = true;
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
			progressBar.setPrefSize(250, 80);
			progressIndicator = new ProgressIndicator();
			progressIndicator.setPrefSize(250, 250);
			StackPane.setMargin(progressBar, new Insets(30, 30, 30, 30));
			StackPane.setMargin(progressIndicator, new Insets(30, 30, 30, 30));
		}
		stackPane.getChildren().removeAll(center, progressBar, progressIndicator, htmlEditor);
		stackPane.getChildren().addAll(progressBar, progressIndicator);
	}

	public void showWebView() {

		stackPane.getChildren().add(center);
		FadeTransition ft = new FadeTransition(Duration.millis(1000), center);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.currentTimeProperty().addListener((a, s, d) -> {
			if (d.toMillis() == 1000) {
				stackPane.getChildren().removeAll(progressBar, progressIndicator, htmlEditor);
			}
		});
		ft.play();
	}

	HTMLEditor htmlEditor;

	/**
	 * 显示编辑器
	 */
	public void showEdit() {
		if (!hasLoadPage) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.titleProperty().set("信息");
			alert.headerTextProperty().set("请先打开一个word文档再点编辑按钮");
			alert.showAndWait();
			return;
		}
		if (mainApp.mainViewController.webEngine.getLoadWorker().getState() != State.SUCCEEDED) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.titleProperty().set("信息");
			alert.headerTextProperty().set("页面加载完毕后才能编辑");
			alert.showAndWait();
			return;
		}
		if (htmlEditor == null) {
			htmlEditor = new HTMLEditor();
		}
		htmlEditor.setHtmlText(parseMain.formatHtmlEngine.getHtmlPage());
		stackPane.getChildren().remove(htmlEditor);
		stackPane.getChildren().add(htmlEditor);
		FadeTransition ft = new FadeTransition(Duration.millis(500), htmlEditor);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.play();
	}

	/**
	 * 隐藏编辑器
	 */
	public void hideEdit(boolean save) {
		if (save) {
			if (!hasLoadPage) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.titleProperty().set("信息");
				alert.headerTextProperty().set("请先打开一个word文档再点保存按钮");
				alert.showAndWait();
				return;
			}
			if (mainApp.mainViewController.webEngine.getLoadWorker().getState() != State.SUCCEEDED) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.titleProperty().set("信息");
				alert.headerTextProperty().set("页面加载完毕后才能编辑保存");
				alert.showAndWait();
				return;
			}
			String result = htmlEditor.getHtmlText();
			String outPath = PropUtils.get("temp_out_path") + "temp_edit.html";
			FileUtil.saveFullPathFile(outPath, result);
			// 重新解析html，解析完后重新加载网页
			parseMain.parseHtmlText(result, parseMain.getHtmlPath());
			mainApp.mainViewController.load(parseMain.getHtmlPath());
		}
		if (!save) {
			if (!hasLoadPage) {
				return;
			}
			if (mainApp.mainViewController.webEngine.getLoadWorker().getState() != State.SUCCEEDED) {
				return;
			}
		}
		FadeTransition ft = new FadeTransition(Duration.millis(500), htmlEditor);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.setCycleCount(1);
		ft.currentTimeProperty().addListener((a, s, d) -> {
			if (d.toMillis() == 500) {
				stackPane.getChildren().removeAll(htmlEditor);
			}
		});
		ft.play();
	}

}
