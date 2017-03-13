package main;





import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import misc.Button;
import misc.GameState;
import misc.SpriteSheet;
import misc.TypeOfButton;
import misc.TypeOfCharacter;
import characters.CharacterMain;
import games.BoatGame;

 

public class ViewMain extends JPanel {
	
	private static final long serialVersionUID = 1819325080462162793L;
	//testing
	private BufferedImage testBackground;
	private BufferedImage testPlayerRed;
	private BufferedImage testPlayerGreen;
	private BufferedImage testPlayerBlue;
	private BufferedImage testColorButton;
	private BufferedImage testResetButton;
	private BufferedImage testResetHover;
	private BufferedImage testCollision;
	
	
	protected static ArrayList<BufferedImage> overWorld;
	protected static ArrayList<BufferedImage> boatGame;
	protected static ArrayList<BufferedImage> trashGame;
	protected static ArrayList<BufferedImage> crabGame;
	protected static ArrayList<BufferedImage> menuButtons;
	
	protected static ArrayList<SpriteSheet> boatGameAnimations;		
	protected static ArrayList<SpriteSheet> crabGameAnimations;			
	protected static ArrayList<SpriteSheet> trashGameAnimations;	
	protected static ArrayList<BufferedImage> boat;
	protected static ArrayList<BufferedImage> fish;
	protected static ArrayList<BufferedImage> trash;
	protected static ArrayList<BufferedImage> player;
	protected static ArrayList<BufferedImage> food;
	
	protected static BufferedImage background;
	protected static BufferedImage pauseBackground;
	protected static BufferedImage instructionsBackground;
	protected static BufferedImage endScreen;
	
	public static JFrame frame = new JFrame("Club Estuary");
	public JLabel score = new JLabel();
	
	public static int intendedScreenWidth = 1920;
	public static int intendedScreenHeight = 1080;
	
	public static double XSCALE;
	public static double YSCALE;
	
	public static int SCREEN_WIDTH = 0;
	public static int SCREEN_HEIGHT = 0;
	
	public static int timeRemaining;
	
	
	/* New variables to replace magic numbers*/
	public static final int widthOffset = 38;
	public static final int rectDimension = 25;
	
	public static final Color color1 = new Color(220, 220, 220); //gray
	public static final Color color2 = new Color(218, 165, 32); //orange?
	
	/*Font offsets*/
	public static final int highScoreOffset = 20;
	public static final int creditOffset = 30;
	public static final int initialOffset = 50;
	
	
	/**
	 * Draws all images to the screen
	 * @param g The Graphics object drawing to the screen
	 */
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(background, 0, 0, SCREEN_WIDTH , SCREEN_HEIGHT, Color.BLACK , null);
		
		
		drawCharacters(Controller.getAllChars(), g);
	
		
		drawButtons(Controller.getAllButtons(), g);
		
		
		
		if (Controller.gameState == GameState.BOATGAME  && !Controller.currGame.isInTutorial() && !Controller.instructionsOpen){
			g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(BoatGame.BGRF1.getDrawHeight() * YSCALE)));
			g.drawString("x " + BoatGame.fish1, BoatGame.BGRF1.getxLoc() + BoatGame.BGRF1.getDrawWidth() + (BoatGame.theRule.getDrawWidth() / widthOffset), BoatGame.theRule.getDrawHeight() / 16 * 13);
			g.drawString("x " + BoatGame.fish2, BoatGame.BGRF2.getxLoc() + BoatGame.BGRF2.getDrawWidth() + (BoatGame.theRule.getDrawWidth() / widthOffset), BoatGame.theRule.getDrawHeight() / 16 * 13);
			g.drawString("x " + BoatGame.fish3, BoatGame.BGRF3.getxLoc() + BoatGame.BGRF3.getDrawWidth() + (BoatGame.theRule.getDrawWidth() / widthOffset), BoatGame.theRule.getDrawHeight() / 16 * 13);
			g.drawString("x " + BoatGame.fish4, BoatGame.BGRF4.getxLoc() + BoatGame.BGRF4.getDrawWidth() + (BoatGame.theRule.getDrawWidth() / widthOffset), BoatGame.theRule.getDrawHeight() / 16 * 13);
			g.drawString("x " + BoatGame.crabs, BoatGame.BGRHSC.getxLoc() + BoatGame.BGRHSC.getDrawWidth() + (BoatGame.theRule.getDrawWidth() / widthOffset), BoatGame.theRule.getDrawHeight() / 16 * 13);

		}

		if (Controller.gameState != GameState.OVERWORLD && Controller.gameState != GameState.STARTSCREEN){
			
			g.setColor(color1);
			g.fillRect(SCREEN_WIDTH - (int)(rectDimension * XSCALE), 0, (int)(rectDimension * XSCALE), SCREEN_HEIGHT);
			g.setColor(color2);
			g.fillRect(SCREEN_WIDTH - (int)(rectDimension * XSCALE), SCREEN_HEIGHT - (SCREEN_HEIGHT * timeRemaining / 60),(int)(rectDimension * XSCALE), SCREEN_HEIGHT * timeRemaining / 60); //presumably, divide by 60 to get seconds - is that a magic number?
			g.setColor(Color.black);
			g.drawRect(SCREEN_WIDTH - (int)(rectDimension * XSCALE), 0, (int)(rectDimension * XSCALE), SCREEN_HEIGHT);
			int fontSize = (int)(initialOffset * YSCALE);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(highScoreOffset * YSCALE)));
			g.drawString(String.valueOf(Controller.HIGHSCORE), 0, highScoreOffset  + fontSize );

		    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		     
		    g.setColor(Color.black);
			g.drawString(String.valueOf("Score:" + Controller.SCORE), 0, fontSize );
			
			fontSize = (int)(creditOffset * YSCALE);
		    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));

			
			if (Controller.endScreenCheck && Controller.gameOver) {
				if (Controller.gameWon){
					//switch statement?
					if (Controller.gameState == GameState.BOATGAME) {
						winGameString(Controller.boatGameScoreThreshold,g,fontSize);
					}
					else if (Controller.gameState == GameState.CRABGAME) {
						winGameString(Controller.crabGameScoreThreshold,g,fontSize);
					}
					else if (Controller.gameState == GameState.TRASHGAME) {
						winGameString(Controller.trashGameScoreThreshold,g,fontSize);
					}
				}
				else {
					//switch statement?
					if (Controller.gameState == GameState.BOATGAME) {
						loseGameString(Controller.boatGameScoreThreshold,g,fontSize);
					}
					else if (Controller.gameState == GameState.CRABGAME) {
						loseGameString(Controller.crabGameScoreThreshold,g,fontSize);
					}
					else if (Controller.gameState == GameState.TRASHGAME) {
						loseGameString(Controller.trashGameScoreThreshold,g,fontSize);
					}
					
					
				}
			}
		}
		
	}
	
	public void winGameString(int threshhold, Graphics g, int fontSize){
		g.drawString("Congratulations, you win!", 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 20 * 7, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2);
		g.drawString("You needed a score of: ", 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 50 * 19, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + fontSize + (fontSize / 2));
		g.drawString("" + threshhold, 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 25 * 12, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + (3 * fontSize));
		g.drawString("to save the estuary!", 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 5 * 2, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + (int)(4.5 * fontSize));
		g.drawString("Your Score: " + Controller.SCORE, 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 5 * 2, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + (6 * fontSize));
		g.drawString("Tap Here to Continue", 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 50 * 19, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + (int)(7.5 * fontSize));
	}
	
	public void loseGameString(int threshhold, Graphics g, int fontSize){
		g.drawString("Sorry, try again!", 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 50 * 21, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2);
		g.drawString("You need a score of: ", 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 5 * 2 - 10, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + fontSize + (fontSize / 2));
		g.drawString("" + threshhold, 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 25 * 12, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + (3 * fontSize));
		g.drawString("to save the estuary!", 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 5 * 2, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + (int)(4.5 * fontSize));
		g.drawString("Your Score: " + Controller.SCORE, 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 5 * 2, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + (6 * fontSize));
		g.drawString("Tap Here to Continue", 
				Controller.endScreen.getxLoc() + Controller.endScreen.getDrawWidth() / 50 * 19, 
				Controller.endScreen.getyLoc() + Controller.endScreen.getDrawHeight() / 2 + (int)(7.5 * fontSize));
	}

	
	
	/**
	 * Initializes all view objects including the JFrame, all images and setting appropriate constants
	 */
	public void init(){
		
		

		timeRemaining = -1;
		
		/*
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_WIDTH = (int) screenSize.getWidth();
		SCREEN_HEIGHT = (int) screenSize.getHeight();
		SCALE = 10;
		*/
		
		loadImages();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		
		score.setLocation(0, 0);
		
		

		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.dispose();

		frame.setUndecorated(true);
		
		frame.getContentPane().add(this);
		
		frame.pack();
		
		frame.setVisible(true);


		pauseBackground = overWorld.get(4);
		instructionsBackground = overWorld.get(8);
		endScreen = overWorld.get(9);

	}
	/**
	 * sets the background static variable to be a specific image
	 * @param img A bufferedImage that is the background
	 */
	public void setBackground(BufferedImage img){
		ViewMain.background = img;
	}
	
	/**
	 * Creates a SpriteSheet object from a specified image
	 * @param img A BufferedImage that represents the sprite sheet
	 * @param frameCount An int that shows how many images are in the sprite sheet
	 * @param width An int that is the width of each frame of the image
	 * @param height An int that is the height of each frame of the image
	 * @return The SpriteSheet object corresponding to the image
	 */
	public SpriteSheet loadSpriteSheet(BufferedImage img, int frameCount, int width, int height){
		SpriteSheet returnVal = new SpriteSheet(frameCount);
		
		for (int i = 0; i < frameCount; i++){
			returnVal.getSheet().add(img.getSubimage(width * i, 0, width, height));
		}
		
		
		return returnVal;
	}
	
	/**
	 * Loads all image resources from the resource folder and stores them in the proper ArrayList
	 */
	public void loadImages(){
		
		//load map images
		overWorld = new ArrayList<BufferedImage>();
		
		overWorld.add(loadImage("full_overworld","OverWorld"));
		overWorld.add(loadImage("boatGameButton", "OverWorld"));
		overWorld.add(loadImage("crabGameButton", "OverWorld"));
		overWorld.add(loadImage("trashGameButton", "OverWorld"));
		overWorld.add(loadImage("pauseScreen", "OverWorld"));
		overWorld.add(loadImage("resumeButton", "OverWorld"));
		overWorld.add(loadImage("instructionsButton", "OverWorld"));
		overWorld.add(loadImage("overworldButton", "OverWorld"));
		overWorld.add(loadImage("testInstructions", "OverWorld"));
		overWorld.add(loadImage("endScreen", "OverWorld"));
		overWorld.add(loadImage2("startScreen", "OverWorld"));
		overWorld.add(loadImage("blank", "OverWorld"));
		overWorld.add(loadImage("difficultyMenuEasy", "OverWorld"));
		overWorld.add(loadImage("difficultyMenuMed", "OverWorld"));
		overWorld.add(loadImage("difficultyMenuHard", "OverWorld"));
		overWorld.add(loadImage("background_left", "OverWorld")); //15				
		overWorld.add(loadImage("background_middle", "OverWorld")); //16				
		overWorld.add(loadImage("background_right", "OverWorld")); //17				
		overWorld.add(loadImage("background_leftdirty", "OverWorld"));		
		overWorld.add(loadImage("background_middledirty", "OverWorld"));				
		overWorld.add(loadImage("background_rightdirty", "OverWorld"));
		overWorld.add(loadImage("check", "OverWorld"));
		overWorld.add(loadImage("x", "OverWorld"));
		overWorld.add(loadImage("instructionsBoat", "OverWorld"));
		overWorld.add(loadImage("instructionsCrab", "OverWorld"));
		overWorld.add(loadImage("instructionsTrash", "OverWorld"));
		overWorld.add(loadImage("resetButton", "OverWorld"));
		overWorld.add(loadImage("yesButton", "OverWorld"));
		overWorld.add(loadImage("noButton", "OverWorld"));
		overWorld.add(loadImage("resetConfirmation", "OverWorld"));
		overWorld.add(loadImage("victoryScreen1", "OverWorld"));
		overWorld.add(loadImage("victoryScreen2", "OverWorld"));




		

		


		
		//load boat images
		
		
		boatGame = new ArrayList<BufferedImage>();
		boatGameAnimations = new ArrayList<SpriteSheet>();
		
		boatGame.add(loadImage2("boatBackground","BoatGame"));
		boatGame.add(loadImage("boat","BoatGame"));
		boatGame.add(loadImage("fish1","BoatGame"));
		boatGame.add(loadImage("fish2","BoatGame"));
		boatGame.add(loadImage("fish3", "BoatGame"));
		boatGame.add(loadImage("fish4", "BoatGame"));
		boatGame.add(loadImage("HSCrab", "BoatGame"));
		boatGame.add(loadImage("fishing1","BoatGame"));
		boatGame.add(loadImage("fishing2","BoatGame"));
		boatGame.add(loadImage("fishing3","BoatGame"));
		boatGame.add(loadImage("fishing4","BoatGame"));
		boatGame.add(loadImage("fishingHSC","BoatGame"));
		boatGame.add(loadImage("ruleBackground", "BoatGame"));
		boatGame.add(loadImage("nextRound", "BoatGame"));
		
		boatGameAnimations.add(loadSpriteSheet(boatGame.get(7), 4 , 256, 128));
		boatGameAnimations.add(loadSpriteSheet(boatGame.get(8), 4 , 256, 128));
		boatGameAnimations.add(loadSpriteSheet(boatGame.get(9), 4 , 256, 128));
		boatGameAnimations.add(loadSpriteSheet(boatGame.get(10), 4 , 256, 128));
		boatGameAnimations.add(loadSpriteSheet(boatGame.get(11), 4 , 256, 128));
		

		//load crab images
		crabGame = new ArrayList<BufferedImage>();
		crabGameAnimations = new ArrayList<SpriteSheet>();

		crabGame.add(loadImage("crabBackground2","CrabGame"));
		crabGame.add(loadImage("mittenCrab","CrabGame"));
		crabGame.add(loadImage("blueCrab","CrabGame"));
		crabGame.add(loadImage("blueCrabFood","CrabGame"));
		crabGame.add(loadImage("mittenCrabFood","CrabGame"));
		crabGame.add(loadImage("crabFood","CrabGame"));


		crabGameAnimations.add(loadSpriteSheet(crabGame.get(1), 3, 248, 124)); // mitten no food
		crabGameAnimations.add(loadSpriteSheet(crabGame.get(2), 4, 248, 124)); // blue no food
		crabGameAnimations.add(loadSpriteSheet(crabGame.get(3), 4, 248, 124)); // blue food
		crabGameAnimations.add(loadSpriteSheet(crabGame.get(4), 3, 248, 124)); // mitten food



		//load trash images
		trashGame = new ArrayList<BufferedImage>();
		trashGameAnimations = new ArrayList<SpriteSheet>();
		

		
		trashGame.add(loadImage("trashBackground","TrashGame"));
		trashGame.add(loadImage("player","TrashGame"));
		trashGame.add(loadImage("turd","TrashGame"));
		trashGame.add(loadImage("apple","TrashGame"));
		trashGame.add(loadImage("banana","TrashGame"));
		trashGame.add(loadImage("dogWalking","TrashGame"));
		trashGame.add(loadImage("can","TrashGame"));
		trashGame.add(loadImage("crushedCan","TrashGame"));
		trashGame.add(loadImage("fish","TrashGame"));
		trashGame.add(loadImage("pickUpRightApple","TrashGame"));
		trashGame.add(loadImage("pickUpRightBanana","TrashGame"));
		trashGame.add(loadImage("pickUpRightCan","TrashGame"));
		trashGame.add(loadImage("pickUpRightCrushedCan","TrashGame"));
		trashGame.add(loadImage("pickUpRightFish","TrashGame"));
		trashGame.add(loadImage("pickUpLeftApple","TrashGame"));
		trashGame.add(loadImage("pickUpLeftBanana","TrashGame"));
		trashGame.add(loadImage("pickUpLeftCan","TrashGame"));
		trashGame.add(loadImage("pickUpLeftCrushedCan","TrashGame"));
		trashGame.add(loadImage("pickUpLeftFish","TrashGame"));
		trashGame.add(loadImage("playerWalkUp","TrashGame"));
		trashGame.add(loadImage("playerWalkDown","TrashGame"));
		trashGame.add(loadImage("playerWalkLeft","TrashGame"));
		trashGame.add(loadImage("playerWalkRight","TrashGame"));
		trashGame.add(loadImage("pickUpRightPoop","TrashGame"));
		trashGame.add(loadImage("pickUpLeftPoop","TrashGame"));
		
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(5), 2, 70, 175)); //Dog Walk
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(9), 5, 171, 192)); //Apple Right
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(10), 5, 171, 192)); //Banana Right
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(11), 5, 171, 192)); //Can Right
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(12), 5, 171, 192)); //CrushedCan Right
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(13), 5, 171, 192)); //Fish Right
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(14), 5, 171, 192)); //Apple Left
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(15), 5, 171, 192)); //Banana Left
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(16), 5, 171, 192)); //Can Left
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(17), 5, 171, 192)); //CrushedCan Left
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(18), 5, 171, 192)); //Fish Left
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(19), 4, 171, 192)); //Player Walk Up
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(20), 4, 171, 192)); //Player Walk Down
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(21), 4, 171, 192)); //Player Walk Left
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(22), 4, 171, 192)); //Player Walk Right
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(23), 5, 171, 192)); //Poop Right
		trashGameAnimations.add(loadSpriteSheet(trashGame.get(24), 5, 171, 192)); //Poop Left


		//menu buttons
		menuButtons = new ArrayList<BufferedImage>();
		
		menuButtons.add(loadImage("menuButton","OverWorld"));

		
		
		
		testBackground = loadImage("testBackground", "Test");
		testPlayerRed = loadImage("testPlayerRed", "Test");
		testPlayerGreen = loadImage("testPlayerGreen", "Test");
		testPlayerBlue = loadImage("testPlayerBlue", "Test");
		testColorButton = loadImage("testColorButton", "Test");
		testResetButton = loadImage("testResetButton", "Test");
		testResetHover = loadImage("testResetButtonHover", "Test");
		testCollision = loadImage("testCollision", "Test");
		
		
	}
	
	/**
	 * Creates a BufferedImage object based on a png file
	 * @param fileName A string representing the file name of the resource
	 * @param folder A string representing the folder name within the resources folder
	 * @return A BufferedImage according to the file from the resource folder
	 */
	public BufferedImage loadImage(String fileName, String folder){
		BufferedImage returnImg = null;
		
		try {
			returnImg = ImageIO.read(new File("Resources/" + folder + "/" + fileName + ".png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return returnImg;
	}
	/**
	 * Creates a BufferedImage object based on a JPG file
	 * @param fileName A string representing the file name of the resource
	 * @param folder A string representing the folder name within the resources folder
	 * @return A BufferedImage according to the file from the resource folder
	 */
	public BufferedImage loadImage2(String fileName, String folder){
		BufferedImage returnImg = null;
		
		try {
			returnImg = ImageIO.read(new File("Resources/" + folder + "/" + fileName + ".jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return returnImg;
	}
	
    /**
     * Returns the proper image of the SpriteSheet according to the current state of the animation
     * @param sheet The appropriate SpriteSheet for the Object
     * @param obj The proper object to be animated
     * @param frames The amount of frames to be animated through
     * @return The proper image of the animation sequence
     */
	public BufferedImage animate(SpriteSheet sheet, Object obj,int frames){
		BufferedImage img;
		//sheet = boatGameAnimations.get(1);
		sheet.setCurrIndex(((characters.CharacterMain) obj).getAnimationCount());
		if(!Controller.gamePaused){
			img = (BufferedImage) sheet.it.next();
		}
		else{
			img = (BufferedImage) sheet.currentImg();
		}
		((characters.CharacterMain) obj).setAnimationCount(sheet.getCurrIndex());
		if(((characters.CharacterMain) obj).getAnimationCount()% frames == 1){
			((characters.CharacterMain) obj).startAnimate = false;
		}
		return img;
	}
	/**
     * Returns the proper image of the SpriteSheet according to the current state of the animation specifically for the player walking
     * @param sheet The appropriate SpriteSheet for the Object
     * @param obj The proper object to be animated
     * @param frames The amount of frames to be animated through
     * @return The proper image of the animation sequence
     */
	public BufferedImage playerWalkAnimate(SpriteSheet sheet, Object obj,int frames){
		BufferedImage img;
		//sheet = boatGameAnimations.get(1);
		sheet.setCurrIndex(((characters.Player) obj).getWalkingCounter());
		if(!Controller.gamePaused){
			img = (BufferedImage) sheet.it.next();
		}
		else{
			img = (BufferedImage) sheet.currentImg();
		}
		((characters.Player) obj).setWalkingCounter(sheet.getCurrIndex());
		if(((characters.Player) obj).getWalkingCounter()% frames == 1){
			((characters.Player) obj).startAnimate = false;
		}
		return img;
	}
	/**
     * Returns the proper image of the SpriteSheet according to the current state of the animation specific to the player picking up an object
     * @param sheet The appropriate SpriteSheet for the Object
     * @param obj The proper object to be animated
     * @param frames The amount of frames to be animated through
     * @return The proper image of the animation sequence
     */
	public BufferedImage playerPickingUpAnimate(SpriteSheet sheet, Object obj,int frames){
		BufferedImage img;
		//sheet = boatGameAnimations.get(1);
		sheet.setCurrIndex(((characters.Player) obj).getPickingUpCounter());
		if(!Controller.gamePaused){
			img = (BufferedImage) sheet.it.next();
		}
		else{
			img = (BufferedImage) sheet.currentImg();
		}
		((characters.Player) obj).setPickingUpCounter(sheet.getCurrIndex());
		if(((characters.Player) obj).getPickingUpCounter()% frames == 1){
			((characters.Player) obj).startAnimate = false;
		}
		return img;
	}
	
	
	/**
	 * Reads the TypeOfButton or TypeOfCharacter ENUM and return the appropriate BufferedImage to be displayed on the screen
	 * @param obj The object, either a CharacterMain or Button, being handled
	 * @return The image to be displayed for the specific object
	 */
	private BufferedImage getImage(Object obj){
		if(obj instanceof misc.Button){
			TypeOfButton buttonType = ((misc.Button) obj).getButtonType();
			switch (buttonType){
			    case TESTRESET:
			    	return testResetButton;
			    case TESTRESETHOVER:
			    	return testResetHover;
			    case TESTCOLOR:
			    	return testColorButton;
			    case BOATGAME:
			    	return overWorld.get(1);
			    case CRABGAME:
			    	return overWorld.get(2);
			    case TRASHGAME:
			    	return overWorld.get(3);
			    case MENU:
			    	return menuButtons.get(0);
			    case PAUSESCREEN:
			    	return overWorld.get(4);
			    case RESUME:
			    	return overWorld.get(5);
			    case INSTRUCTIONS:
			    	return overWorld.get(6);
			    case OVERWORLD:
			    	return overWorld.get(7);
			    case TESTINSTRUCTIONSSCREEN:
			    	return overWorld.get(8);
			    case ENDSCREEN:
			    	return overWorld.get(9);
			    case STARTSCREEN:
			    	return overWorld.get(10);
			    case BLANK:
			    	return overWorld.get(11);
			    case BOATGAMERULE:
			    	return boatGame.get(12);
			    case BGRF1:
			    	return boatGame.get(2);
			    case BGRF2:
			    	return boatGame.get(3);
			    case BGRF3:
			    	return boatGame.get(4);
			    case BGRF4:
			    	return boatGame.get(5);
			    case BGRHSC:
			    	return boatGame.get(6);
			    case NEXTROUND:
			    	return boatGame.get(13);
			    case DIFFICULTYEASY:
			    	return overWorld.get(12);
			    case DIFFICULTYMED:
			    	return overWorld.get(13);
			    case DIFFICULTYHARD:
			    	return overWorld.get(14);
			    case CHECK:
			    	return overWorld.get(21);
			    case X:
			    	return overWorld.get(22);
			    case INSTRUCTIONSBOAT:
			    	return overWorld.get(23);
			    case INSTRUCTIONSCRAB:
			    	return overWorld.get(24);
			    case INSTRUCTIONSTRASH:
			    	return overWorld.get(25);
			    case RESET:
			    	return overWorld.get(26);
			    case YES:
			    	return overWorld.get(27);
			    case NO:
			    	return overWorld.get(28);
			    case RESETCONFIRMATION:
			    	return overWorld.get(29);
			    case VICTORYSCREEN1:
			    	return overWorld.get(30);
			    case VICTORYSCREEN2:
			    	return overWorld.get(31);
				default:
					break;
			}
		}
		else if (obj instanceof characters.CharacterMain){
			TypeOfCharacter charType = ((characters.CharacterMain) obj).getCharType();
			BufferedImage img;
			SpriteSheet sheet;
			switch (charType){
				case TESTRED:
					return testPlayerRed;
				case TESTBLUE:
					return testPlayerBlue;
				case TESTGREEN:
					return testPlayerGreen;
				case TESTCOLLISION:
					return testCollision;
					
					//BOAT GAME
				case TESTBOAT:
					return boatGame.get(1);
				case TESTFISH1:
					return boatGame.get(2);
				case TESTFISH2:
					return boatGame.get(3);
				case TESTFISH3:
					return boatGame.get(4);
				case TESTFISH4:
					return boatGame.get(5);
				case TESTHSCRAB:
					return boatGame.get(6);
				//Start Animations
				case FISHING1:
					sheet = boatGameAnimations.get(0);
					img = animate(sheet, obj , 4);
					/*
					sheet = boatGameAnimations.get(0);
					sheet.setCurrIndex(((characters.CharacterMain) obj).getAnimationCount());
					if(!Controller.gamePaused){
						img = (BufferedImage) sheet.it.next();
					}
					else{
						img = (BufferedImage) sheet.currentImg();
					}
					((characters.CharacterMain) obj).setAnimationCount(sheet.getCurrIndex());;
					if(((characters.CharacterMain) obj).getAnimationCount() % 4 == 1){
						((characters.CharacterMain) obj).startAnimate = false;
					}
					*/
					return img;
				case FISHING2:
					sheet = boatGameAnimations.get(1);
					img = animate(sheet, obj , 4);
					return img;
				case FISHING3:
					sheet = boatGameAnimations.get(2);
					img = animate(sheet, obj , 4);
					return img;
				case FISHING4:
					sheet = boatGameAnimations.get(3);
					img = animate(sheet, obj , 4);
					return img;
				case FISHINGHSC:
					sheet = boatGameAnimations.get(4);
					img = animate(sheet, obj , 4);
					return img;
					
					
					
				//CRAB GAME
				case TESTBADCRAB:
					sheet = crabGameAnimations.get(0);
					img = animate(sheet, obj , 3);
					return img;
				case TESTCRAB:
					sheet = crabGameAnimations.get(1);
					img = animate(sheet, obj , 4);
					return img;
				case TESTFOOD:
					return crabGame.get(5);
				case TESTBADCRABFOOD:
					sheet = crabGameAnimations.get(3);
					img = animate(sheet, obj , 3);
					return img;
				case TESTCRABFOOD:
					sheet = crabGameAnimations.get(2);
					img = animate(sheet, obj , 4);
					return img;
				
				//TRASH GAME
				case TESTCHAR:
					return trashGame.get(1);
				case TESTPOOP:
					return trashGame.get(2);
				case TESTTRASH1:
					return trashGame.get(3);
				case TESTTRASH2:
					return trashGame.get(4);
				case TRASH3:
					return trashGame.get(6);
				case TRASH4:
					return trashGame.get(7);
				case TRASHFISH:
					return trashGame.get(8);
				case TESTWALKER:
					sheet = trashGameAnimations.get(0);
					img = animate(sheet, obj , 2);
					return img;
		
				
				//Player Animations
				case PICKUPRIGHTTRASH1:
					sheet = trashGameAnimations.get(1);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPRIGHTTRASH2:
					sheet = trashGameAnimations.get(2);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPRIGHTTRASH3:
					sheet = trashGameAnimations.get(3);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPRIGHTTRASH4:
					sheet = trashGameAnimations.get(4);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPRIGHTTRASHFISH:
					sheet = trashGameAnimations.get(5);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPLEFTTRASH1:
					sheet = trashGameAnimations.get(6);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPLEFTTRASH2:
					sheet = trashGameAnimations.get(7);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPLEFTTRASH3:
					sheet = trashGameAnimations.get(8);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPLEFTTRASH4:
					sheet = trashGameAnimations.get(9);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPLEFTTRASHFISH:
					sheet = trashGameAnimations.get(10);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPRIGHTPOOP:
					sheet = trashGameAnimations.get(15);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PICKUPLEFTPOOP:
					sheet = trashGameAnimations.get(16);
					img = playerPickingUpAnimate(sheet, obj , 5);
					return img;
				case PLAYERWALKUP:
					sheet = trashGameAnimations.get(11);
					img = playerWalkAnimate(sheet, obj , 4);
					return img;
				case PLAYERWALKDOWN:
					sheet = trashGameAnimations.get(12);
					img = playerWalkAnimate(sheet, obj , 4);
					return img;
				case PLAYERWALKLEFT:
					sheet = trashGameAnimations.get(13);
					img = playerWalkAnimate(sheet, obj , 4);
					return img;
				case PLAYERWALKRIGHT:
					sheet = trashGameAnimations.get(14);
					img = playerWalkAnimate(sheet, obj , 4);
					return img;
					
				default:
					break;
			}
		}

		return null;
	}

	/**
	 * Draws every character on the screen
	 * @param obj The ArrayList representing all the CharacterMain objects on the screen
	 * @param g The Graphics object drawing to the screen
	 */
	public void drawCharacters(ArrayList<CharacterMain> obj , Graphics g){
		for(int i=0 ; i< obj.size() ; i++){
			
			g.drawImage(getImage(obj.get(i)), obj.get(i).getxLoc() , obj.get(i).getyLoc() ,
					//(obj.get(i).getxLoc() + obj.get(i).getDrawWidth()), (obj.get(i).getyLoc() + obj.get(i).getDrawHeight()), 
					obj.get(i).getDrawWidth(), obj.get(i).getDrawHeight(), null);
		}
	}
	
	/**
	 * Draws every Button on the screen
	 * @param buttons The ArrayList representing all the Button objects on the screen
	 * @param g The Graphics object drawing to the screen
	 */
	public void drawButtons (ArrayList<Button> buttons, Graphics g){
		for (int i = 0; i < buttons.size(); i++){
			g.drawImage(getImage(buttons.get(i)), //img
					buttons.get(i).getxLoc(), buttons.get(i).getyLoc(), 
					//(buttons.get(i).getxLoc() + buttons.get(i).getDrawWidth()) , buttons.get(i).getyLoc() + buttons.get(i).getDrawHeight() , 
					// 
					buttons.get(i).getDrawWidth() , buttons.get(i).getDrawHeight(),  null);
			
		}
	}
}