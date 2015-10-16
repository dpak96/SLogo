package slogo.interpreter;

import java.util.ArrayList;
import java.util.List;

import slogo.nodes.Node;
import slogo.nodes.NodeFactory;
import slogo.nodes.RootNode;


public class TreeBuilder {
	private Node myRootNode;
	private CommandLibrary myCommands;
	private VariableLibrary myVariables;
	private NodeFactory	myFactory;
	private List<String> myInput;


	public TreeBuilder(CommandLibrary commandLibrary, VariableLibrary variables) {
		//myTranslator = new Parser(language);
		// Change to something besides command node
		myFactory = new NodeFactory(commandLibrary, variables);
		myRootNode = new RootNode(null);
		myCommands = commandLibrary;
		myVariables = variables;
		myInput = new ArrayList<>();
	}

	public Node buildTreeFromInput(List<String> input) {
		//List<String> parsed = myTranslator.parse(input);
		//System.out.println(parsed);
		myInput = input;
		buildTreeNodes(0, myRootNode);
		return myRootNode;
	}
	
	private void buildTreeNodes(int index, Node root) {
		Node current = root;
		if (index >= myInput.size()){
			// TODO check complete?
			return;
		}
		if(current.canAdd()) {
			// TODO throw exception if action doesn't exist
			Node node = myFactory.create(myInput.get(index), current);
			current.addChild(node);
			buildTreeNodes(index+1, node);
		} else {
			if (current.getParent() != null) {
				buildTreeNodes(index, current.getParent());
			} else {
				// TODO throw an exception here if there is nowhere to add the next node
			}
		}
		// now check whether each function has enough parameters
	}
	
	private boolean treeComplete(Node current) {
		// move up the tree from the current node checking whether each parent
		// has enough parameters
		while (current != null) {
			if (! current.hasCompleteChildren()) return false;
			current = current.getParent();
		}
		return true;
	}

/*
	public void run() {
		myRootNode.traverseAndExecute();
	}	
*/

}
