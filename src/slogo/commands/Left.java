package slogo.commands;

import java.util.List;

import slogo.character.MainCharacter;
import slogo.interpreter.EngineController;


public class Left extends TurtleCommand {
	
	private final int CHILDREN_REQUIRED = 1;
	
//	@Override
//	public double doCommand(List<NodeObject> params, EngineController controller) {
//		List<Double> parameters = recurseToGetParameters(params, controller);
//		double degrees = parameters.get(0)*(-1);
//		return controller.getMainCharacter().rotateCharacter(degrees);
//	}

	@Override
	public int getNumChildrenRequired() {
		return CHILDREN_REQUIRED;
	}

@Override
public double doTurtling(MainCharacter turtle, EngineController controller) {
	List<Double> parameters = recurseToGetParameters(myParams, controller);
	double degrees = parameters.get(0)*(-1);
	return turtle.rotateCharacter(degrees);
}

}
