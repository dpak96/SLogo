package slogo.commands;

import java.util.List;

import slogo.character.MainCharacter;

public class Home extends Command {
	
	@Override
	public double doCommand(MainCharacter character, List<Double> params) {
		double currentX = character.getImageView().getX();
		double currentY = character.getImageView().getY();
		double destinationX = 0;
		double destinationY = 0;
		double distance = Math.sqrt((destinationX - currentX)*(destinationX - currentX)
				+ (destinationY - currentY)*(destinationY - currentY));
		System.out.println("Went home. Distance moved: " + distance);
		return distance;
	}

	@Override
	public int getNumChildrenRequired() {
		// TODO Auto-generated method stub
		return 0;
	}

}