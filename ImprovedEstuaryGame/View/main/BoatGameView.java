package main;

public class BoatGameView extends ViewMain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5420515361536236140L;

	@Override
	/**
	 * Sets the proper background, time remaining in the game and initializes the frame for the Boat Game
	 */
	public void init(){
		
		frame.pack();		
		frame.getContentPane().add(this);

		frame.setVisible(true);

		
		ViewMain.timeRemaining = 60;
		
		setBackground(boatGame.get(0));
		
		
	   
	}
}
