package slogo.nodes;

import slogo.interpreter.EngineController;

public class GroupEndNode extends NodeObject {

	public GroupEndNode(String name, String rawString, NodeObject parent) {
		super(name, rawString, parent);
	}

	@Override
	public boolean hasCompleteChildren() {
		return true;
	}

	@Override
	public void addChild(NodeObject node) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canAdd() {
		return false;
	}

	@Override
	public double traverseAndExecute(EngineController controller) {
		return 0;
	}

}
