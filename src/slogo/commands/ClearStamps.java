package slogo.commands;

import java.util.List;

import slogo.interpreter.EngineController;
import slogo.nodes.NodeObject;

public class ClearStamps extends Command {
	private final int CHILDREN_REQUIRED = 0;
	
	@Override
	public double doCommand(List<NodeObject> params, ControlInterface controller) {
		return 0;
	}

	@Override
	public int getNumChildrenRequired() {
		return CHILDREN_REQUIRED;
	}

}
