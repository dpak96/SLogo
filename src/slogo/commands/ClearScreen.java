package slogo.commands;

import java.util.List;

import slogo.interpreter.EngineController;
import slogo.nodes.NodeObject;


public class ClearScreen extends Command {
	

	public double doCommand(List<NodeObject> params, EngineController engine) {
		return engine.getScreen().clearMap();
	}

	@Override
	public int getNumChildrenRequired() {
		// TODO Auto-generated method stub
		return 0;
	}

}
