package slogo.element;

import javafx.scene.layout.GridPane;

public class History extends AbstractList {

	public History(GridPane pane, ObservableArrayList list, Console console) {
		super(pane, list, console);
	}

	protected void click() {
		console.overwrite(list.getSelectionModel().getSelectedItem());
	}
}
