package slogo.screen;

import java.util.ResourceBundle;

import XML.XMLEditor;
import XML.XMLReader;

import com.sun.javafx.scene.control.skin.ColorPalette;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import slogo.element.Commands;
import slogo.element.Console;
import slogo.element.Display;
import slogo.element.History;
import slogo.element.ObservableArrayList;
import slogo.element.Palette;
import slogo.element.Variables;
import slogo.interpreter.EngineController;
import slogo.interpreter.InterpreterException;

public class SlogoTab extends AbstractScreen {

	private String language;
	private Console console;
	private History history;
	private Commands commands;
	private Variables variables;
	private Display map;
	private Palette palette;
	private EngineController myEngineController;
	private ResourceBundle slogoResources;
	private ObservableArrayList h;
	private ObservableArrayList c;
	private ObservableArrayList v;
	private Tab myTab;
	private int tabID;

	public SlogoTab(String language, SlogoScreenInterface screen, int id) {
		this.language = language;
		slogoResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "slogo");
		WIDTH = Integer.parseInt(slogoResources.getString("width"));
		HEIGHT = Integer.parseInt(slogoResources.getString("height"));
		root = new GridPane();
		scene = new Scene(root, WIDTH, HEIGHT);
		try {
			title = slogoResources.getString((language));
		} catch (java.util.MissingResourceException e) {
			title = "SLogo";
		}
		tabID = id;
		myEngineController = new EngineController(this.language, screen);
		makeTab();
	}

	public void makeTab() {
		GridPane mapPane = new GridPane();
		map = new Display(mapPane);
		root.add(mapPane, 0, 0);
		GridPane consolePane = new GridPane();
		console = new Console(consolePane);
		root.add(consolePane, 0, 2);

		root.add(map.getPalettePane(), 1, 2);

		makeLists();

		root.setVgap(Integer.parseInt(slogoResources.getString("VGap")));
		setAlignment(root);
		// Name the tab. CHANGE TO RESOURCES LATER
		myTab = new Tab("Workspace " + (tabID + 1));
		myTab.setContent(root);
		myTab.setId(Integer.toString(tabID));
	}

	public void makeLists() {
		h = new ObservableArrayList();
		c = new ObservableArrayList();
		v = new ObservableArrayList();
		GridPane listPane = new GridPane();
		GridPane historyPane = new GridPane();
		history = new History(historyPane, h, console, myEngineController);
		listPane.add(historyPane, 0, 0);
		GridPane commandPane = new GridPane();
		commands = new Commands(commandPane, c, console, myEngineController);
		listPane.add(commandPane, 0, 1);
		GridPane varPane = new GridPane();
		variables = new Variables(varPane, v, console, myEngineController);
		listPane.add(varPane, 0, 2);
		listPane.setMaxHeight(Integer.parseInt(slogoResources.getString("mapHeight")));
		listPane.setVgap(Integer.parseInt(slogoResources.getString("VGap")));
		listPane.setAlignment(Pos.BASELINE_LEFT);
		GridPane buttonPane = new GridPane();
		buttonPane.add(makeBackButton(), 0, 0);
		buttonPane.add(makeHelpButton(), 1, 0);
		buttonPane.add(makeSettingsButton(), 2, 0);
		Button save = makeSaveButton();
		save.setOnMouseClicked(e -> editXML());
		Button load = makeLoadButton();
		load.setOnMouseClicked(e -> readXML());
		GridPane buttonPane2 = new GridPane();
		buttonPane2.add(save, 0, 0);
		buttonPane2.add(load, 1, 0);
		buttonPane.setHgap(Integer.parseInt(slogoResources.getString("HGap")));
		buttonPane2.setHgap(Integer.parseInt(slogoResources.getString("HGap")));
		listPane.add(buttonPane, 0, 3);
		listPane.add(buttonPane2, 0, 4);

		root.add(listPane, 1, 0);
	}

	public void editXML() {
		XMLEditor temp = new XMLEditor("XMLFiles/slogoStart.xml", parameters);
		temp.editFile();
		System.out.println("edit");
	}

	public void readXML() {
		XMLReader temp = new XMLReader("XMLFiles/slogoStart.xml");
		temp.readFile();
		System.out.println("read");
		for (String s : temp.getNumParams().keySet()) {
			parameters.setValue(s, temp.getNumParams().get(s));
		}
		parameters.setBackgroundColor(temp.getColorParams().get("Background"));
	}

	public Display getDisplay() {
		return map;
	}

	public Tab getTab() {
		return myTab;
	}

	public EngineController getEngineController() {
		return myEngineController;
	}

	@Override
	public void run() {
		if (console.hasInput()) {
			String command = console.getInput();
			// myEngineController.sendToInterpreter(command);
			// map.getCharacter(0).goTo(Double.parseDouble(command.split("
			// ")[0]), Double.parseDouble(command.split(" ")[1]));
			// showError("ERROR!", command);
			try {
				myEngineController.runCommands(command);
				if (settings != null) {
					settings.refresh();
				}
			} catch (InterpreterException e) {
				showError("ERROR!", e.getMessage());
			}
			h.add(command);
		}
	}

	public History getHistory() {
		return history;
	}

	public Variables getVariables() {
		return variables;
	}

	public Commands getCommands() {
		return commands;
	}

}
