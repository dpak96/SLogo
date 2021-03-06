package slogo.screen;

import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import slogo.character.MainCharacter;
import slogo.element.Commands;
import slogo.element.Console;
import slogo.element.Display;
import slogo.element.History;
import slogo.element.ObservableArrayList;
import slogo.element.Variables;
import slogo.interpreter.EngineController;
import slogo.interpreter.InterpreterException;

public class SlogoScreen extends AbstractScreen implements SlogoScreenInterface {

	private String language;
	private Console console;
	private History history;
	private Commands commands;
	private Variables variables;
	private Display map;
	private TabManager manager;
	private ResourceBundle slogoResources;
	private ObservableArrayList h;
	private ObservableArrayList c;
	private ObservableArrayList v;
	private EngineController myEngineController;

	public SlogoScreen(String language) {
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
		makeScene();
		myEngineController = new EngineController(this.language, this);
	}

	@Override
	public void run() {
		myEngineController = manager.getCurrentTab().getEngineController();
		map = manager.getCurrentTab().getDisplay();
		parameters.load(map.getCharacters(), map.getActiveIndices());
		Color BackgroundColor = parameters.getBackgroundColor();
		if (BackgroundColor != null) {
			map.changeColor(BackgroundColor);
		}
		List<MainCharacter> characters = map.getCharacters();
		HashSet<Integer> activeIndices = map.getActiveIndices();
		for (int i = 0; i < characters.size(); i++) {
			if (parameters.isShowActive() && !activeIndices.contains(i)) {
				characters.get(i).setOpacity(0.5);
			} else {
				characters.get(i).setOpacity(1);
			}
		}
		/**
		 * if (console.hasInput()) { String command = console.getInput(); try {
		 * myEngineController.runCommands(command); h.add(command); } catch
		 * (InterpreterException e) { showError("ERROR!", e.getMessage()); }
		 * 
		 * }
		 **/
		manager.getCurrentTab().run();
		map.updateCharacters();
		nextScreen = manager.getCurrentTab().getNextScreen();
	}

	private void makeScene() {
		GridPane title = makeTitle();
		GridPane.setColumnSpan(title, 2);
		root.add(title, 0, 0);
		/**
		 * GridPane mapPane = new GridPane(); map = new Display(mapPane);
		 * root.add(mapPane, 0, 1);
		 **/

		GridPane test = new GridPane();
		manager = new TabManager(test, language, this);
		map = manager.getCurrentTab().getDisplay();
		root.add(test, 0, 1);

		/**
		 * GridPane consolePane = new GridPane(); console = new
		 * Console(consolePane); GridPane.setColumnSpan(consolePane, 2);
		 * 
		 * root.add(consolePane, 0, 2);
		 **/

		// makeLists();

		root.setVgap(Integer.parseInt(slogoResources.getString("VGap")));
		setAlignment(root);
	}

	public GridPane makeTitle() {
		GridPane title = new GridPane();
		Text temp = createText("SLogo", Integer.parseInt(myResources.getString("smallTitle")));
		title.add(temp, 0, 0);
		return title;
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
		buttonPane.setHgap(Integer.parseInt(slogoResources.getString("HGap")));
		listPane.add(buttonPane, 0, 3);

		root.add(listPane, 1, 1);
	}

	public double clearMap() {
		return map.clear();
	}

	public History getHistoryObject() {
		return manager.getCurrentTab().getHistory();
	}

	public Variables getVariablesObject() {
		return manager.getCurrentTab().getVariables();
	}

	public Commands getCommandsObject() {
		return manager.getCurrentTab().getCommands();
	}

	public Display getDisplay() {
		return map;
	}
}
