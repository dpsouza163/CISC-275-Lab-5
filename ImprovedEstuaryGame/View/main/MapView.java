package main;

import java.awt.image.BufferedImage;

public class MapView extends ViewMain{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8592817878888362705L;

	@Override
	/**
	 * Sets the proper background and initializes the frame for the Boat Game
	 */
	public void init(){
		frame.pack();		
		frame.getContentPane().add(this);

		frame.setVisible(true);
		BufferedImage img = makeBackground();
		setBackground(img);

	}
	
	/**
	 * Splices three images together to make the complete background
	 * @return The final background
	 */
	public BufferedImage makeBackground(){
		BufferedImage finalImage = new BufferedImage(1400,800,6);
		if(Controller.trashGameWin){
			finalImage.createGraphics().drawImage(overWorld.get(15), 0, 0, 468, 800, null);
		}
		else{
			finalImage.createGraphics().drawImage(overWorld.get(18), 0, 0, 468, 800, null);
		}
		if(Controller.boatGameWin){
			finalImage.createGraphics().drawImage(overWorld.get(17), 936, 0, 464, 800, null);
		}
		else{
			finalImage.createGraphics().drawImage(overWorld.get(20), 936, 0, 464, 800, null);

		}
		if(Controller.crabGameWin){
			finalImage.createGraphics().drawImage(overWorld.get(16), 468, 0, 468, 800, null);
		}
		else{
			finalImage.createGraphics().drawImage(overWorld.get(19), 468, 0, 468, 800, null);
		}
		
		return finalImage;
	}
}
