package main;

import games.BoatGame;
import games.CrabGame;
import games.GameMain;
import games.TrashGame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import misc.Button;
import misc.DelayTask;
import misc.ExpireTask;
import misc.GameState;
import misc.TypeOfButton;
import misc.TypeOfCharacter;
import characters.testCharacter;
import characters.CharacterMain;

public class Controller implements KeyListener, MouseListener, MouseMotionListener{
	
	
	private testCharacter testChar;
	private testCharacter testCollision, testCollision2;
	
	private Button testResetButton;
	private Button testColorButton;
	//private ViewMain testView = new ViewMain();
	
	
	private ViewMain view;
	private ViewMain trashView;
	private ViewMain boatView;
	private ViewMain crabView;
	private ViewMain mapView;
	public static int SCORE = 0;
	public static int HIGHSCORE = 0;
	public static int ANIMATION_COUNT = 0;
	
	public static ArrayList<CharacterMain> allChars = new ArrayList<CharacterMain>();
	
	public ArrayList<CharacterMain> overWorldChars;
	public ArrayList<CharacterMain> trashGameChars;
	public ArrayList<CharacterMain> crabGameChars;
	public ArrayList<CharacterMain> boatGameChars;
	public ArrayList<Button> overWorldButtons;
	public ArrayList<Button> trashGameButtons;
	public ArrayList<Button> crabGameButtons;
	public ArrayList<Button> boatGameButtons;

	public static ArrayList<Button> allButtons;
	private Button trashGameButton;
	private Button crabGameButton;
	private Button boatGameButton;
	private Button menuButton;
	private Button pauseScreen;
	private Button resumeButton;
	private Button instructionsButton;
	private Button overworldButton;
	private Button instructionsScreenBoat;
	private Button instructionsScreenCrab;
	private Button instructionsScreenTrash;
	public static Button endScreen;
	private Button startScreen;
	private Button difficultyMenu;
	private Button easy, med, hard;
	public static Button checkButton, xButton;
	private Button resetButton;
	private Button yesButton, noButton, resetConfirmation;
	private Button victoryScreen1, victoryScreen2;
	
	public static GameState gameState;
	public static GameMain currGame;
	private GameMain overWorld;
	private TrashGame trashGame;
	private BoatGame boatGame;
	private CrabGame crabGame;
	
	public static Timer timer = new Timer();
	public static Timer delayTimer = new Timer();
	public static int delay;
	
	public static boolean gamePaused;
	public static boolean instructionsOpen;
	public static boolean gameOver;
	public static boolean firstLoad;
	public static boolean showStartScreen;
	public static boolean inGame;
	public static boolean boatGameWin;		
	public static boolean crabGameWin;		
	public static boolean trashGameWin;
	public static boolean endScreenCheck;
	public static boolean checkOrXDisplayed;
	public static boolean resetCheck;
	
	public Dimension screenSize;
	
	
	public static int difficulty;
	public static int boatGameScoreThreshold;
	public static int crabGameScoreThreshold;
	public static int trashGameScoreThreshold;
	public static boolean gameWon;
	
	
	public static ArrayList<CharacterMain> getAllChars() {
		return allChars;
	}
	public static ArrayList<Button> getAllButtons() {
		return allButtons;
	}
	
	public ArrayList<Button> getOverWorldButtons() {
		return overWorldButtons;
	}
	public ArrayList<Button> getTrashGameButtons() {
		return trashGameButtons;
	}
	public ArrayList<Button> getCrabGameButtons() {
		return crabGameButtons;
	}
	public ArrayList<Button> getBoatGameButtons() {
		return boatGameButtons;
	}
	
	
	public static void setAllChars(ArrayList<CharacterMain> allChars) {
		Controller.allChars = allChars;
	}
	public static void setAllButtons(ArrayList<Button> allButtons) {
		Controller.allButtons = allButtons;
	}
	/**
	 * Adds the menu button to the Boat Game screen
	 */
	public void setBoatGameButtons() {
		boatGameButtons.add(menuButton);
	}
	/**
	 * Adds the menu button to the Crab Game screen
	 */
	public void setCrabGameButtons() {
		crabGameButtons.add(menuButton);
	}
	/**
	 * Adds the menu and game buttons to the Overworld screen
	 */
	public void setOverWorldButtons() {
		overWorldButtons.add(boatGameButton);
		overWorldButtons.add(crabGameButton);
		overWorldButtons.add(trashGameButton);
		overWorldButtons.add(difficultyMenu);
		overWorldButtons.add(easy);
		overWorldButtons.add(med);
		overWorldButtons.add(hard);
		overWorldButtons.add(resetButton);
	
	}
	/**
	 * Adds the menu button to the Trash Game screen
	 */
	public void setTrashGameButtons() {
		trashGameButtons.add(menuButton);
	}
	
	/**
	 *  Initializes all of the button objects used throughout the game,
	 *  initializes the instance of ViewMain,
	 *  adds listeners to the instance of ViewMain
	 */
	public void init(){
		//delay = 2;
		endScreenCheck = false;
		gameWon = false;
		
		
		difficulty = 0;
		boatGameScoreThreshold = 100;
		crabGameScoreThreshold = 100;
		trashGameScoreThreshold = 100;
		
		ViewMain.timeRemaining = 60;
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ViewMain.SCREEN_WIDTH = (int) screenSize.getWidth();
		ViewMain.SCREEN_HEIGHT = (int) screenSize.getHeight();
		
		ViewMain.XSCALE = ((double)ViewMain.SCREEN_WIDTH / ViewMain.intendedScreenWidth);
		ViewMain.YSCALE = ((double)ViewMain.SCREEN_HEIGHT / ViewMain.intendedScreenHeight);		
		
		boatGameWin = false;				
		crabGameWin = false;		
		trashGameWin = false;
		
		//System.out.println(ViewMain.SCREEN_WIDTH + " " + ViewMain.SCREEN_HEIGHT);
		
		view = new ViewMain();
		
		trashView = new TrashGameView();
		boatView = new BoatGameView();
		crabView = new CrabGameView();
		mapView = new MapView();
		
		currGame = new GameMain();
		overWorld = new GameMain();
		trashGame = new TrashGame();
		crabGame = new CrabGame();
		boatGame = new BoatGame();
		
		allButtons = new ArrayList<Button>();
		overWorldButtons = new ArrayList<Button>();
		trashGameButtons = new ArrayList<Button>();
		crabGameButtons = new ArrayList<Button>();
		boatGameButtons = new ArrayList<Button>();
		
		
		allChars = new ArrayList<CharacterMain>();
		overWorldChars = new ArrayList<CharacterMain>();
		crabGameChars = new ArrayList<CharacterMain>();
		boatGameChars = new ArrayList<CharacterMain>();
		trashGameChars = new ArrayList<CharacterMain>();
		
		trashGameButton = new Button(ViewMain.SCREEN_WIDTH / 8 , ViewMain.SCREEN_HEIGHT / 5 * 3); 
				trashGameButton.setSizeAndImg(150, 60, TypeOfButton.TRASHGAME);
		crabGameButton = new Button((int)(ViewMain.SCREEN_WIDTH / 2 - (40 * ViewMain.XSCALE)), ViewMain.SCREEN_HEIGHT / 2 + (int)(40 * ViewMain.YSCALE)); 
				crabGameButton.setSizeAndImg(150, 60, TypeOfButton.CRABGAME);
		boatGameButton = new Button((int)(ViewMain.SCREEN_WIDTH / 3 * 2 - (70 * ViewMain.XSCALE)), ViewMain.SCREEN_HEIGHT / 3 + (int) (50 * ViewMain.YSCALE)); 
				boatGameButton.setSizeAndImg(150, 60, TypeOfButton.BOATGAME);
		menuButton = new Button(0, (ViewMain.SCREEN_HEIGHT));
				menuButton.setSizeAndImg(100, 40, TypeOfButton.MENU);
				menuButton.setyLoc(menuButton.getyLoc() - menuButton.getDrawHeight());
				
		resetButton = new Button(0, (ViewMain.SCREEN_HEIGHT));
				resetButton.setSizeAndImg(100, 40, TypeOfButton.RESET);
				resetButton.setyLoc(resetButton.getyLoc() - resetButton.getDrawHeight());
		resetConfirmation = new Button ((int)(ViewMain.SCREEN_WIDTH / 4 ), ViewMain.SCREEN_HEIGHT / 3 ); 
				resetConfirmation.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 3 / ViewMain.YSCALE), TypeOfButton.RESETCONFIRMATION);
		yesButton = new Button ((int)(ViewMain.SCREEN_WIDTH / 16 * 5 ), (int)(ViewMain.SCREEN_HEIGHT / 2)); 
				yesButton.setSizeAndImg(210 , 120 , TypeOfButton.YES);
		noButton = new Button ((int)(ViewMain.SCREEN_WIDTH / 16 * 9 ), (int)(ViewMain.SCREEN_HEIGHT / 2 )); 
				noButton.setSizeAndImg(210 , 120 , TypeOfButton.NO);
		
		pauseScreen = new Button (ViewMain.SCREEN_WIDTH / 4 , ViewMain.SCREEN_HEIGHT / 4 ); 
				pauseScreen.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 2 / ViewMain.YSCALE), TypeOfButton.PAUSESCREEN);
		
		endScreen = new Button (ViewMain.SCREEN_WIDTH / 4 , ViewMain.SCREEN_HEIGHT / 4 ); 
				endScreen.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 2 / ViewMain.YSCALE) , TypeOfButton.ENDSCREEN);
				
		resumeButton = new Button ((int)(ViewMain.SCREEN_WIDTH / 16 * 5 ), ViewMain.SCREEN_HEIGHT / 3 ); 
				resumeButton.setSizeAndImg(210 , 120 , TypeOfButton.RESUME);
		
		instructionsButton = new Button ((int)(ViewMain.SCREEN_WIDTH / 16 * 9 ), ViewMain.SCREEN_HEIGHT / 3 ); 
				instructionsButton.setSizeAndImg(210 , 120 , TypeOfButton.INSTRUCTIONS);
		
		overworldButton = new Button ((int)(ViewMain.SCREEN_WIDTH / 2 - (105 / ViewMain.XSCALE)), (int)(ViewMain.SCREEN_HEIGHT * (5.75 / 10) )); 
				overworldButton.setSizeAndImg(210 , 120 , TypeOfButton.OVERWORLD);
		
		
		instructionsScreenBoat = new Button (ViewMain.SCREEN_WIDTH / 4 , 0 ); 
				instructionsScreenBoat.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), 
						(int)(ViewMain.SCREEN_HEIGHT / 3 / ViewMain.YSCALE), TypeOfButton.INSTRUCTIONSBOAT);
		instructionsScreenCrab = new Button (ViewMain.SCREEN_WIDTH / 4 , 0 ); 
				instructionsScreenCrab.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), 
						(int)(ViewMain.SCREEN_HEIGHT / 3 / ViewMain.YSCALE), TypeOfButton.INSTRUCTIONSCRAB);
		instructionsScreenTrash = new Button (ViewMain.SCREEN_WIDTH / 4 , 0 ); 
				instructionsScreenTrash.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), 
						(int)(ViewMain.SCREEN_HEIGHT / 3 / ViewMain.YSCALE), TypeOfButton.INSTRUCTIONSTRASH);
		
		startScreen = new Button (0, 0); 
				startScreen.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / ViewMain.YSCALE), TypeOfButton.STARTSCREEN);
			
		difficultyMenu = new Button (0, 0);
				difficultyMenu.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 5 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 20 * 3 / ViewMain.YSCALE), TypeOfButton.DIFFICULTYEASY);
				
		easy = new Button (difficultyMenu.getxLoc() + difficultyMenu.getDrawWidth() / 3 * 0, difficultyMenu.getyLoc() + difficultyMenu.getDrawHeight() / 5 * 1);
			easy.setSizeAndImg(difficultyMenu.getWidth() / 3, difficultyMenu.getHeight() / 5 * 4, TypeOfButton.BLANK);
			
		med = new Button (difficultyMenu.getxLoc() + difficultyMenu.getDrawWidth() / 3 * 1, difficultyMenu.getyLoc() + difficultyMenu.getDrawHeight() / 5 * 1);
			med.setSizeAndImg(difficultyMenu.getWidth() / 3, difficultyMenu.getHeight() / 5 * 4, TypeOfButton.BLANK);
			
		hard = new Button (difficultyMenu.getxLoc() + difficultyMenu.getDrawWidth() / 3 * 2, difficultyMenu.getyLoc() + difficultyMenu.getDrawHeight() / 5 * 1);
			hard.setSizeAndImg(difficultyMenu.getWidth() / 3, difficultyMenu.getHeight() / 5 * 4, TypeOfButton.BLANK);
			
		checkButton = new Button (ViewMain.SCREEN_WIDTH / 2 - (int)(81 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 3+ (int)(20 / ViewMain.YSCALE));
			checkButton.setSizeAndImg(150, 135, TypeOfButton.CHECK);
		xButton = new Button (ViewMain.SCREEN_WIDTH / 2 - (int)(69 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 3 + (int)(20 / ViewMain.YSCALE));
			xButton.setSizeAndImg(150, 135, TypeOfButton.X);
			
		victoryScreen1 = new Button(0, 0);
			victoryScreen1.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / ViewMain.YSCALE), TypeOfButton.VICTORYSCREEN1);
		victoryScreen2 = new Button ((int)(ViewMain.SCREEN_WIDTH / 4 ), ViewMain.SCREEN_HEIGHT / 3 ); 
			victoryScreen2.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 3 / ViewMain.YSCALE), TypeOfButton.VICTORYSCREEN2);
		
		
		
		setBoatGameButtons();
		setCrabGameButtons();
		setOverWorldButtons();
		setTrashGameButtons();

		


		gameState = GameState.STARTSCREEN;
		
		gamePaused = false;
		instructionsOpen = false;
		gameOver = false;
		
		
		
		
		testChar = new testCharacter(239, 239, TypeOfCharacter.TESTBLUE);
		testCollision = new testCharacter (200, 200, TypeOfCharacter.TESTCOLLISION);
		testCollision2 = new testCharacter (300, 200, TypeOfCharacter.TESTCOLLISION);
		/*
		allChars.add(testChar);
		allChars.add(testCollision);
		allChars.add(testCollision2);
		*/
		
		/*
		testResetButton = new Button(400, 460, TypeOfButton.TESTRESET);
		testColorButton = new Button(0, 460, TypeOfButton.TESTCOLOR);
		*/
		
		/*
		allButtons.add(testResetButton);
		allButtons.add(testColorButton);
		*/
		
		mapView.setFocusable(true);
		mapView.addKeyListener(this);
		mapView.addMouseListener(this);
		mapView.addMouseMotionListener(this);
		
		boatView.setFocusable(true);
		boatView.addKeyListener(this);
		boatView.addMouseListener(this);
		boatView.addMouseMotionListener(this);
		
		crabView.setFocusable(true);
		crabView.addKeyListener(this);
		crabView.addMouseListener(this);
		crabView.addMouseMotionListener(this);
		
		trashView.setFocusable(true);
		trashView.addKeyListener(this);
		trashView.addMouseListener(this);
		trashView.addMouseMotionListener(this);
		
		view.setFocusable(true);
		view.addKeyListener(this);
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		//view.allChars = allChars;
		//view.allButtons = allButtons;
		
		showStartScreen = true;
		
		view.init();
	}
	/**
	 * Function used to swap state of the game
	 * @param gameState Current state of the game (OverWorld, BoatGame, TrashGame, CrabGame, StartScreen)
	 */
	public void setGameState(GameState gameState){
		//System.out.println(gameState);
		Controller.gameState = gameState;
	}
	/**
	 * Reads in the set value of GameState and tells the view and model to update accordingly
	 */
	public void readGameState(){

		allChars.clear();
		allButtons.clear();
		
		gameOver = false;
		gamePaused = true;
		instructionsOpen = true;
		firstLoad = true;
		
		switch (gameState){
			case STARTSCREEN:
				inGame = false;
				allButtons.add(startScreen);
				view = mapView;
				view.init();
				break;
			case OVERWORLD:
				
				
				ViewMain.timeRemaining = 60;
				inGame = false;
				currGame.end();
				view = mapView;
				currGame = overWorld;
				currGame.start();
				addAllButtons(overWorldButtons);
				//addAllChars(overWorldChars);
				if (boatGameWin && crabGameWin && trashGameWin){
					allButtons.add(victoryScreen1);
				}
				view.init();
				break;
			case TRASHGAME:
				inGame = true;
				view = trashView;
				currGame = trashGame;
				//currGame.start();
				trashGame.start();
				trashGame.tutorial();

				allButtons.add(instructionsScreenTrash);
				addAllButtons(trashGameButtons);
				//addAllChars(trashGameChars);
				//trashView.init();
				view.init();
				break;
			case CRABGAME:
				inGame = true;
				view = crabView;
				currGame = crabGame;
				crabGame.start();
				allButtons.add(instructionsScreenCrab);
				addAllButtons(crabGameButtons);
				//addAllChars(crabGameChars);
				view.init();
				//crabView.init();
				break;
			case BOATGAME:
				inGame = true;
				view = boatView;
				currGame = boatGame;
				boatGame.start();
				boatGame.tutorial();
				allButtons.add(instructionsScreenBoat);
				addAllButtons(boatGameButtons);
				//addAllChars(boatGameChars);
				view.init();
				//boatView.init();
				break;
			
			default:
				break;
		}
	}
	/**
	 * Takes in a list of Buttons and adds them to the global container, allButtons
	 * @param list The list of Buttons to be added
	 */
	public void addAllButtons(ArrayList<Button> list){
		for (int i = 0; i < list.size(); ++i){
			allButtons.add(list.get(i));
		}
	}
	/**
	 * Takes in a list of Characters and adds them to the global container, allButtons
	 * @param list The list of Characters to be added
	 */
	public void addAllChars(ArrayList<CharacterMain> list){
		for (int i = 0; i < list.size(); ++i){
			allChars.add(list.get(i));
		}
	}
	
	/**
	 * Function that is called 30 times a second; the engine of the game
	 */
	public void onTick(){
		if (!gamePaused) {
			currGame.update();
			SCORE = currGame.updateScore();
			ANIMATION_COUNT++;
		}
		
		
		
		if (ViewMain.timeRemaining == 0 && gameOver == false && gameState != GameState.BOATGAME){
			gamePaused = true;
			currGame.end();
			gameOver = true;
			allButtons.add(endScreen);
			endScreenCheck = true;
			
			
		}
		else if (ViewMain.timeRemaining == 0 && gameOver == false && gameState == GameState.BOATGAME && BoatGame.roundCount > 3){
			gamePaused = true;
			currGame.end();
			gameOver = true;
			allButtons.add(endScreen);
			endScreenCheck = true;
		}
		
		
		
		view.repaint();
		
	}

	/**
	 * Used to open and close the menu screen when inside of a game
	 * The menu screen is a button as a background with three buttons overlayed on top of it
	 * @param m The current input MouseEvent to be parsed
	 */
	public void menuOpened(MouseEvent m){
		int mouseX = m.getX();
		int mouseY = m.getY();
		if(mouseX > resumeButton.getxLoc() && mouseX < resumeButton.getxLoc() + resumeButton.getDrawWidth()){
			if(mouseY > resumeButton.getyLoc() && mouseY < resumeButton.getyLoc() + resumeButton.getDrawHeight()){
				gamePaused = false;
				allButtons.remove(resumeButton);
				allButtons.remove(instructionsButton);
				allButtons.remove(overworldButton);
				allButtons.remove(pauseScreen);
				
				timer = new Timer();
				timer.scheduleAtFixedRate(new ExpireTask(), 1000, 1000);
			}
		}
		if(mouseX > instructionsButton.getxLoc() && mouseX < instructionsButton.getxLoc() + instructionsButton.getDrawWidth()){
			if(mouseY > instructionsButton.getyLoc() && mouseY < instructionsButton.getyLoc() + instructionsButton.getDrawHeight()){
				allButtons.remove(resumeButton);
				allButtons.remove(instructionsButton);
				allButtons.remove(overworldButton);
				allButtons.remove(pauseScreen);
				
				instructionsOpen = true;
				
				if (gameState == GameState.BOATGAME){
					allButtons.add(instructionsScreenBoat);
				}
				else if (gameState == GameState.CRABGAME){
					allButtons.add(instructionsScreenCrab);
				}
				else if (gameState == GameState.TRASHGAME){
					allButtons.add(instructionsScreenTrash);
				}
			}
		}
		if(mouseX > overworldButton.getxLoc() && mouseX < overworldButton.getxLoc() + overworldButton.getDrawWidth()){
			if(mouseY > overworldButton.getyLoc() && mouseY < overworldButton.getyLoc() + overworldButton.getDrawHeight()){
				setGameState(GameState.OVERWORLD);
				readGameState();
				timer.cancel();
			}
		}
	}
	
	/**
	 * Used to parse events that need to happen whenever a certain area / object
	 * on the screen is clicked with the mouse
	 * @param m The current input MouseEvent to be parsed
	 */
	@Override
	public void mouseClicked(MouseEvent m) {
		int mouseX, mouseY;
		mouseX = m.getX();
		mouseY = m.getY();
		
		switch(gameState){
			case STARTSCREEN:
					if(mouseX > startScreen.getxLoc() && mouseX < startScreen.getxLoc() + startScreen.getDrawWidth()){
						if(mouseY > startScreen.getyLoc() && mouseY < startScreen.getyLoc() + startScreen.getDrawHeight()){
							showStartScreen = false;
							setGameState(GameState.OVERWORLD);
							readGameState();
							
						}
					}
				break;
			case OVERWORLD:

				currGame = currGame;
				for(int i = 0 ; i< overWorldButtons.size() ; i++){
					Button curr = overWorldButtons.get(i);
					if(mouseX > curr.getxLoc() && mouseX < curr.getxLoc() + curr.getDrawWidth()){
						if(mouseY > curr.getyLoc() && mouseY < curr.getyLoc() + curr.getDrawHeight()){
							SCORE = 0;
							if(i == 0){
								setGameState(GameState.BOATGAME);
								readGameState();
							}
							if(i == 1){
								setGameState(GameState.CRABGAME);
								readGameState();
							}
							if(i == 2){
								setGameState(GameState.TRASHGAME);
								readGameState();
								//System.out.println("Loaded");
							}
							if (i == 4){
								difficultyMenu.setButtonType(TypeOfButton.DIFFICULTYEASY);
								difficulty = 0;
							}
							if (i == 5){
								difficultyMenu.setButtonType(TypeOfButton.DIFFICULTYMED);
								difficulty = 1;
							}
							if (i == 6){
								difficultyMenu.setButtonType(TypeOfButton.DIFFICULTYHARD);
								difficulty = 2;

							}
							if (i == 7){
								
								allButtons.add(resetConfirmation);
								allButtons.add(yesButton);
								allButtons.add(noButton);
								
								overWorldButtons.add(resetConfirmation);
								overWorldButtons.add(yesButton);
								overWorldButtons.add(noButton);
								

							}
							if (i == 9){
								this.showStartScreen = true;

								this.gameState = GameState.STARTSCREEN;
								this.init();
								
								this.readGameState();
							}
							if (i == 10){
								allButtons.remove(resetConfirmation);
								allButtons.remove(yesButton);
								allButtons.remove(noButton);
								
								overWorldButtons.remove(resetConfirmation);
								overWorldButtons.remove(yesButton);
								overWorldButtons.remove(noButton);

							}

						}
					}
				}
				for(int i = 0 ; i< allButtons.size() ; i++){
					Button curr = allButtons.get(i);
					if(mouseX > curr.getxLoc() && mouseX < curr.getxLoc() + curr.getDrawWidth()){
						if(mouseY > curr.getyLoc() && mouseY < curr.getyLoc() + curr.getDrawHeight()){
							
							if (allButtons.get(i) == victoryScreen1){
								allButtons.remove(victoryScreen1);
								allButtons.add(victoryScreen2);
								
								/*
								allButtons.add(boatGameButton);
								allButtons.add(crabGameButton);
								allButtons.add(trashGameButton);
								allButtons.add(difficultyMenu);
								allButtons.add(resetButton);
								*/
								
							}
							else if (allButtons.get(i) == victoryScreen2){
								allButtons.remove(victoryScreen2);
								
								
							}


						}
					}
				}
				break;

			case BOATGAME:
				if (gameOver) {
					if(mouseX > endScreen.getxLoc() && mouseX < endScreen.getxLoc() + endScreen.getDrawWidth()){
						if(mouseY > endScreen.getyLoc() && mouseY < endScreen.getyLoc() + endScreen.getDrawHeight()){
							allButtons.remove(endScreen);
							endScreenCheck = false;
							setGameState(GameState.OVERWORLD);
							readGameState();
							timer.cancel();
							
						}
					}
				}
				else if(gamePaused){
					if (!instructionsOpen){
						menuOpened(m);
					}
					else if(currGame.isInTutorial()){
						if(mouseX > instructionsScreenBoat.getxLoc() && mouseX < instructionsScreenBoat.getxLoc() + instructionsScreenBoat.getDrawWidth()){
							if(mouseY > instructionsScreenBoat.getyLoc() && mouseY < instructionsScreenBoat.getyLoc() + instructionsScreenBoat.getDrawHeight()){
								boatGame.start();
								instructionsOpen = false;
								currGame.setInTutorial(false);
								allButtons.remove(instructionsScreenBoat);

								firstLoad = false;
								timer = new Timer();
								timer.scheduleAtFixedRate(new ExpireTask(), 1000, 1000);
								gamePaused = false;

							}
						}
						for(int i = 0 ; i < boatGame.getBoats().size() ; i++){
							if(mouseX > boatGame.getBoats().get(i).getxLoc() && mouseX < boatGame.getBoats().get(i).getxLoc() + boatGame.getBoats().get(i).getDrawWidth()){
								if(mouseY > boatGame.getBoats().get(i).getyLoc() && mouseY < boatGame.getBoats().get(i).getyLoc() + boatGame.getBoats().get(i).getDrawHeight()){
									//System.out.println(boatGame.getBoats().get(i).getObjectsInBoat().size());
									if (boatGame.getBoats().get(i).getObjectsInBoat().size() > 4){
										if (!checkOrXDisplayed){
											allButtons.add(xButton);
											this.checkOrXDisplayed = true;
										}
										delayTimer.scheduleAtFixedRate(new DelayTask(), 0, 1000);
									}
									else {
										if (!checkOrXDisplayed){
											allButtons.add(checkButton);
											this.checkOrXDisplayed = true;
										}
										delayTimer.scheduleAtFixedRate(new DelayTask(), 0, 1000);
									}
								}
							}
						}
					}
					else {
						if(mouseX > instructionsScreenBoat.getxLoc() && mouseX < instructionsScreenBoat.getxLoc() + instructionsScreenBoat.getDrawWidth()){
							if(mouseY > instructionsScreenBoat.getyLoc() && mouseY < instructionsScreenBoat.getyLoc() + instructionsScreenBoat.getDrawHeight()){
								instructionsOpen = false;
								allButtons.remove(instructionsScreenBoat);
								
								if(!firstLoad){
									allButtons.add(pauseScreen);
									allButtons.add(resumeButton);
									allButtons.add(instructionsButton);
									allButtons.add(overworldButton);
								}
								else{
									firstLoad = false;
									timer = new Timer();
									timer.scheduleAtFixedRate(new ExpireTask(), 1000, 1000);
									gamePaused = false;
								}
							}
						}
					}
				}
				else if (BoatGame.pause && BoatGame.roundCount <= 3){
					if(mouseX > BoatGame.nextRound.getxLoc() && mouseX < BoatGame.nextRound.getxLoc() + BoatGame.nextRound.getDrawWidth()){
						if(mouseY > BoatGame.nextRound.getyLoc() && mouseY < BoatGame.nextRound.getyLoc() + BoatGame.nextRound.getDrawHeight()){
							allButtons.remove(BoatGame.nextRound);
							BoatGame.pause = false;
							timer = new Timer();
							timer.scheduleAtFixedRate(new ExpireTask(), 1000, 1000);
							ViewMain.timeRemaining = 60;
						}
					}
						
				}
				else{
					boatGame.checkOnClick(mouseX, mouseY);
					if(mouseX > menuButton.getxLoc() && mouseX < menuButton.getxLoc() + menuButton.getDrawWidth()){
						if(mouseY > menuButton.getyLoc() && mouseY < menuButton.getyLoc() + menuButton.getDrawHeight()){
							gamePaused = true;
							allButtons.add(pauseScreen);
							allButtons.add(resumeButton);
							allButtons.add(instructionsButton);
							allButtons.add(overworldButton);

							timer.cancel();
						}
					}
				}
				break;
			case CRABGAME:
				
				
				if (gameOver) {
					if(mouseX > pauseScreen.getxLoc() && mouseX < pauseScreen.getxLoc() + pauseScreen.getDrawWidth()){
						if(mouseY > pauseScreen.getyLoc() && mouseY < pauseScreen.getyLoc() + pauseScreen.getDrawHeight()){
							setGameState(GameState.OVERWORLD);
							readGameState();
							timer.cancel();
						}
					}
				}
				else if(gamePaused){
					if (!instructionsOpen){
						menuOpened(m);
					}
					else if (currGame.isInTutorial()){
						
						if(mouseX > instructionsScreenCrab.getxLoc() && mouseX < instructionsScreenCrab.getxLoc() + instructionsScreenCrab.getDrawWidth()){
							if(mouseY > instructionsScreenCrab.getyLoc() && mouseY < instructionsScreenCrab.getyLoc() + instructionsScreenCrab.getDrawHeight()){
								instructionsOpen = false;
								currGame.setInTutorial(false);
								allButtons.remove(instructionsScreenCrab);
								crabGame.crab.clear();

								firstLoad = false;
								timer = new Timer();
								timer.scheduleAtFixedRate(new ExpireTask(), 1000, 1000);
								gamePaused = false;
								
							}
						}

						for(int i = 0 ; i < crabGame.crab.size() ; i++){
							
							if(mouseX > crabGame.crab.get(i).getxLoc() && mouseX < crabGame.crab.get(i).getxLoc() + crabGame.crab.get(i).getDrawWidth()){
								if(mouseY > crabGame.crab.get(i).getyLoc() && mouseY < crabGame.crab.get(i).getyLoc() + crabGame.crab.get(i).getDrawHeight()){
									if (crabGame.crab.get(i).isMitten()){
										if (!checkOrXDisplayed){
											allButtons.add(xButton);
											this.checkOrXDisplayed = true;
										}
										delayTimer.scheduleAtFixedRate(new DelayTask(), 0, 1000);
									}
									else {
										if (!checkOrXDisplayed){
											allButtons.add(checkButton);
											this.checkOrXDisplayed = true;
										}
										delayTimer.scheduleAtFixedRate(new DelayTask(), 0, 1000);
									}
								}
							}
						}
					}
					else {
						if(mouseX > instructionsScreenCrab.getxLoc() && mouseX < instructionsScreenCrab.getxLoc() + instructionsScreenCrab.getDrawWidth()){
							if(mouseY > instructionsScreenCrab.getyLoc() && mouseY < instructionsScreenCrab.getyLoc() + instructionsScreenCrab.getDrawHeight()){
								instructionsOpen = false;
								allButtons.remove(instructionsScreenCrab);
								
								if(!firstLoad){
									allButtons.add(pauseScreen);
									allButtons.add(resumeButton);
									allButtons.add(instructionsButton);
									allButtons.add(overworldButton);
								}
								else{
									firstLoad = false;
									
									currGame.setInTutorial(false);
									
									timer = new Timer();
									timer.scheduleAtFixedRate(new ExpireTask(), 1000, 1000);
									gamePaused = false;
									
								}
							}
						}
					}
				}
				else {
					if(mouseX > menuButton.getxLoc() && mouseX < menuButton.getxLoc() + menuButton.getDrawWidth()){
						if(mouseY > menuButton.getyLoc() && mouseY < menuButton.getyLoc() + menuButton.getDrawHeight()){
							gamePaused = true;
							allButtons.add(pauseScreen);
							allButtons.add(resumeButton);
							allButtons.add(instructionsButton);
							allButtons.add(overworldButton);

							timer.cancel();
						}
					}
					for (int i = 0; i < crabGame.getCrab().size(); i++){
						if ((mouseX > crabGame.getCrab().get(i).getxLoc()) 
								&& (mouseX < crabGame.getCrab().get(i).getxLoc() 
										+ crabGame.getCrab().get(i).getDrawWidth())){
							if ((mouseY > crabGame.getCrab().get(i).getyLoc()) 
									&& (mouseY < crabGame.getCrab().get(i).getyLoc() 
											+ crabGame.getCrab().get(i).getDrawHeight())){
								
								if (crabGame.getCrab().get(i).getNumTaps() > 0){
									crabGame.getCrab().get(i).setNumTaps(crabGame.getCrab().get(i).getNumTaps() - 1);
						
								}
								if (crabGame.getCrab().get(i).noMoreTaps()){
									if (crabGame.getCrab().get(i).isMitten()){
										crabGame.getCrab().get(i).setCharType(TypeOfCharacter.TESTBADCRAB);
										if (!crabGame.getCrab().get(i).hasAlreadyGottenScoreFromClick){
											crabGame.setScore(crabGame.getScore() + 50);
											crabGame.getCrab().get(i).hasAlreadyGottenScoreFromClick = true;
										}
										
									}
									else {
										crabGame.getCrab().get(i).setCharType(TypeOfCharacter.TESTCRAB);
										if (!crabGame.getCrab().get(i).hasAlreadyGottenScoreFromClick){
											crabGame.setScore(crabGame.getScore() - 25);
											crabGame.getCrab().get(i).hasAlreadyGottenScoreFromClick = true;
										}

									}
									crabGame.getCrab().get(i).setHasFood(false);
									if (!crabGame.getCrab().get(i).isMovingOffScreen){
										crabGame.getCrab().get(i).moveOffScreen();
										crabGame.getCrab().get(i).isMovingOffScreen = true;
										crabGame.getCrab().get(i).moveTheCrab();

									}
									if (crabGame.getCrab().get(i).hasAlreadyPickedUpFood){
										if (!crabGame.getCrab().get(i).hasAlreadyDroppedFood){
											crabGame.getFood().add(CrabGame.dropFood(crabGame.getCrab().get(i)));
											crabGame.getCrab().get(i).setHasFood(false);
											crabGame.getCrab().get(i).hasAlreadyDroppedFood = true;
										}
									}

								}
							}

						}
					}
				}
				break;
			case TRASHGAME:
				if (gameOver) {
					if(mouseX > pauseScreen.getxLoc() && mouseX < pauseScreen.getxLoc() + pauseScreen.getDrawWidth()){
						if(mouseY > pauseScreen.getyLoc() && mouseY < pauseScreen.getyLoc() + pauseScreen.getDrawHeight()){
							setGameState(GameState.OVERWORLD);
							readGameState();
							timer.cancel();
						}
					}
				}
				else if(gamePaused){
					if (!instructionsOpen){
						menuOpened(m);
					}
					else if(currGame.isInTutorial()){

						if(mouseX > instructionsScreenTrash.getxLoc() && mouseX < instructionsScreenTrash.getxLoc() + instructionsScreenTrash.getDrawWidth()){
							if(mouseY > instructionsScreenTrash.getyLoc() && mouseY < instructionsScreenTrash.getyLoc() + instructionsScreenTrash.getDrawHeight()){
								trashGame.start();
								instructionsOpen = false;
								currGame.setInTutorial(false);
								allButtons.remove(instructionsScreenTrash);

								firstLoad = false;
								timer = new Timer();
								timer.scheduleAtFixedRate(new ExpireTask(), 1000, 1000);
								gamePaused = false;

							}
						}

						for(int i = 0 ; i < trashGame.getObjectsInRiver().size() ; i++){

							if(mouseX > trashGame.getObjectsInRiver().get(i).getxLoc() && mouseX < trashGame.getObjectsInRiver().get(i).getxLoc() + trashGame.getObjectsInRiver().get(i).getDrawWidth()){
								if(mouseY > trashGame.getObjectsInRiver().get(i).getyLoc() && mouseY < trashGame.getObjectsInRiver().get(i).getyLoc() + trashGame.getObjectsInRiver().get(i).getDrawHeight()){
									if (trashGame.getObjectsInRiver().get(i).getCharType() != TypeOfCharacter.TRASHFISH){
										if (!checkOrXDisplayed){
											allButtons.add(xButton);
											this.checkOrXDisplayed = true;
										}
										delayTimer.scheduleAtFixedRate(new DelayTask(), 0, 1000);
									}
									else {
										if (!checkOrXDisplayed){
											allButtons.add(checkButton);
											this.checkOrXDisplayed = true;
										}
										delayTimer.scheduleAtFixedRate(new DelayTask(), 0, 1000);
									}
								}
							}
						}
					}
					else {
						if(mouseX > instructionsScreenTrash.getxLoc() && mouseX < instructionsScreenTrash.getxLoc() + instructionsScreenTrash.getDrawWidth()){
							if(mouseY > instructionsScreenTrash.getyLoc() && mouseY < instructionsScreenTrash.getyLoc() + instructionsScreenTrash.getDrawHeight()){
								instructionsOpen = false;
								allButtons.remove(instructionsScreenTrash);

								if(!firstLoad){
									allButtons.add(pauseScreen);
									allButtons.add(resumeButton);
									allButtons.add(instructionsButton);
									allButtons.add(overworldButton);
								}
								else{
									firstLoad = false;
									timer = new Timer();
									timer.scheduleAtFixedRate(new ExpireTask(), 1000, 1000);
									gamePaused = false;
								}
							}
						}
					}
				}
				else {
					if(mouseX > menuButton.getxLoc() && mouseX < menuButton.getxLoc() + menuButton.getDrawWidth()){
						if(mouseY > menuButton.getyLoc() && mouseY < menuButton.getyLoc() + menuButton.getDrawHeight()){
							gamePaused = true;
							allButtons.add(pauseScreen);
							allButtons.add(resumeButton);
							allButtons.add(instructionsButton);
							allButtons.add(overworldButton);

							timer.cancel();
						}
					}
					trashGame.player.setIntendedXLoc(mouseX);
					trashGame.player.setIntendedYLoc(mouseY);
				}
			default:
				break;
		}


		/*
		if (mouseX >= 400 && mouseX <= 500){
			if (mouseY >= 460 && mouseY <= 500){
				testChar.resetPos(239, 239);
				if (!(allChars.contains(testCollision))){
					allChars.add(testCollision);
				}
				if (!(allChars.contains(testCollision2))){
					allChars.add(testCollision2);
				}
				testCollision.resetPos(200, 200);
				testCollision2.resetPos(300, 200);

			}
		}
		else if (mouseX >= 0 && mouseX <= 100){
			if (mouseY >= 460 && mouseY <= 500){
				testChar.changeColor();
			}
		}
		 */
	}
	@Override
	public void mouseEntered(MouseEvent m) {
		
	}
	@Override
	public void mouseExited(MouseEvent m) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		switch(gameState){
		case TRASHGAME:
			mouseClicked(arg0);
			break;
			default:
				break;
		}
	}
	@Override
	public void mouseMoved(MouseEvent m) {
		/*
		if (m.getX() >= 400 && m.getX() <= 500 && m.getY() >= 460 && m.getY() <= 500){
				testResetButton.setButtonType(TypeOfButton.TESTRESETHOVER);
		}
		else {
			testResetButton.setButtonType(TypeOfButton.TESTRESET);
		}
		*/
		
	}
	/**
	 * Used to parse activity when a particular key is pressed
	 * In this case, this is only used for the ESC key
	 * @param e The current input KeyEvent to be parsed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
			case KeyEvent.VK_UP:
				testChar.moveUp();
				break;
			case KeyEvent.VK_DOWN:
				testChar.moveDown();
				break;
			case KeyEvent.VK_LEFT:
				testChar.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				testChar.moveRight();
				break;
			case KeyEvent.VK_ESCAPE:
				ViewMain.frame.dispatchEvent(new WindowEvent(ViewMain.frame, WindowEvent.WINDOW_CLOSING));
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	//Test Main
	
	/**
	 * The "main" used to run the game, game speed is 
	 * set to 30 fps
	 */
	public static void main(String[] args) {
		Controller test = new Controller();	
		test.init();
		test.readGameState();
		//int i = 0;
		while (true){
			test.onTick();
			
			
			try {
				
    			Thread.sleep(33);
    			//System.out.println(i);
    			//System.out.println(ViewMain.background);
    			//i++;
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		
    		
    		
		}
	}
	
	
}

