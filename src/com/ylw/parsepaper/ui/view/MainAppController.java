package com.ylw.parsepaper.ui.view;

import com.ylw.parsepaper.ui.MainApp;
import com.ylw.parsepaper.ui.controller.BaseController;
import com.ylw.parsepaper.ui.model.ListItemData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class MainAppController extends BaseController {
	@FXML
	MenuBar menuBar;
	@FXML
	ListView<ListItemData> listView;

	ObservableList<ListItemData> data = FXCollections.observableArrayList();

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	protected void initialize() {
		data.add(new ListItemData("reload", "window.location.reload()"));
		data.add(new ListItemData("alert", "alert('hello')"));
		data.add(new ListItemData("getSelection", "alert(window.getSelection())"));
		data.add(new ListItemData("getSelection.parentElement",
				"window.getSelection().anchorNode.parentElement.innerHTML"));

		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<ListItemData>, ListCell<ListItemData>>() {

			@Override
			public ListCell<ListItemData> call(ListView<ListItemData> param) {
				// TODO Auto-generated method stub
				return new ButtonCell();
			};
		});
		// listView.setOnMouseClicked(value -> {
		// String v = listView.getSelectionModel().getSelectedItem();
		// System.out.println("fffff:" +
		// mainApp.mainViewController.webEngine.executeScript(map.get(v)));
		// });

		// listView.getCellFactory().

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
					// System.out.println(item.getJsData());
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

}
