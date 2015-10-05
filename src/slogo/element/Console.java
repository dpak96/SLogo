package slogo.element;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class Console extends AbstractElement {
	private TextArea text;
	private String input = null;
	private Button enterButton;
	private Button clearButton;

	public Console(GridPane pane) {
		super(pane);
		makePane();
	}

	protected void makePane() {
		text = new TextArea();
		text.setPromptText(myResources.getString("prompt"));
		text.setMaxHeight(Double.MAX_VALUE);
		text.setPrefHeight(Integer.parseInt(myResources.getString("consoleHeight")));
		text.setFont(font);
		pane.add(text, 0, 0);
		GridPane.setRowSpan(text, 2);
		enterButton = makeButton(myResources.getString("enterButton"));
		clearButton = makeButton(myResources.getString("clearButton"));
		enterButton.setOnMouseClicked(e -> submit());
		clearButton.setOnMouseClicked(e -> clear());
		pane.add(enterButton, 1, 0);
		pane.add(clearButton, 1, 1);
	}

	public Button makeButton(String label) {
		Button button = new Button(label);
		button.setFont(font);
		button.setPrefHeight(Integer.parseInt(myResources.getString("consoleHeight")) / 2);
		button.setMaxHeight(Double.MAX_VALUE);
		return button;
	}

	private void submit() {
		this.input = text.getText();
	}

	private void clear() {
		text.clear();
	}

	public boolean hasInput() {
		return input != null;
	}

	public String getInput() {
		String output = input;
		input = null;
		return output;
	}

}