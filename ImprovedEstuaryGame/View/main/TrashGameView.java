package main;

public class TrashGameView extends ViewMain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3640721684797064879L;

	@Override
	/**
	 * Sets the proper background, time remaining in the game and initializes the frame for the Boat Game
	 */
	public void init(){
		frame.pack();		
		frame.getContentPane().add(this);

		frame.setVisible(true);

		
		
		ViewMain.timeRemaining = 60;

		
		setBackground(trashGame.get(0));
	   
		
	}
}
