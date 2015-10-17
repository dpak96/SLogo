package slogo.nodes;

import slogo.character.MainCharacter;
import slogo.commands.Command;
import slogo.interpreter.CommandLibrary;

public class UserCommandNode extends NodeObject {
	private String myName;
	private int numChildren;
	private CommandLibrary commandLibrary;

	public UserCommandNode(NodeObject parent, String name, int numParams, CommandLibrary commands) {
		super(parent);
		myName = name;
		numChildren = numParams;
		commandLibrary = commands;
		}

	@Override
	public boolean hasCompleteChildren() {
		return (myChildren.size() >= numChildren);
	}

	@Override
	public void addChild(NodeObject child) {
		myChildren.add(child);
	}

	@Override
	public boolean canAdd() {
//		System.out.println("can add? " + (myChildren.size() < numChildren));
		return (myChildren.size() < numChildren);
	}

	@Override
	public double traverseAndExecute(MainCharacter character) {
		Command action = commandLibrary.getCommand(myName);
		System.out.println(action);
		return action.doCommand(myChildren, character);
	}

}
