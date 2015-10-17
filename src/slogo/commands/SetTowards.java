package slogo.commands;

import java.util.List;

import slogo.character.MainCharacter;
import slogo.nodes.*;

public class SetTowards extends Command {
	
	@Override
	public double doCommand(List<NodeObject> params, MainCharacter character) {
		// needs to return the number of degrees that the turtle has turned.
		// therefore needs front end to have done that part of their project.
		System.out.println("Setting towards " + params.get(0) + " " + params.get(1));
		return 100;
	}

	@Override
	public int getNumChildrenRequired() {
		// TODO Auto-generated method stub
		return 2;
	}

}
