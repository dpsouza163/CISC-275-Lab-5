package games;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import characters.Boat;
import main.Controller;
import main.ViewMain;
import misc.Button;
import misc.TypeOfButton;
import misc.TypeOfCharacter;


public class BoatGame extends GameMain{
	Random gen = new Random();
	
	public static Button theRule, BGRF1, BGRF2, BGRF3, BGRF4, BGRHSC, nextRound;
	
	public static int roundCount;
	
	public static int fish1, fish2, fish3, fish4, crabs;
	private ArrayList<Boat> boats = new ArrayList<Boat>();
	private int difficulty = 0;
	public static boolean pause = false;

	
	/*
	 * returns the array of boats
	 */
	public ArrayList<Boat> getBoats() {
		return boats;
	}
	
	/*
	 * sets the array of boats
	 */
	public void setBoats(ArrayList<Boat> boats) {
		this.boats = boats;
	}
	/*
	 * (non-Javadoc)
	 * @see games.GameMain#tutorial()
	 */
	public void tutorial(){
		this.inTutorial = true;

		boats.clear();
		Controller.allChars.clear();

		Boat goodBoat = new Boat(ViewMain.SCREEN_WIDTH / 2 - (int)(384 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0);
		goodBoat.setSizeAndImg(256,	128, TypeOfCharacter.TESTBOAT);
		
		goodBoat.addTutorialFishGood();
		
		Boat badBoat = new Boat(ViewMain.SCREEN_WIDTH / 2 + (int)(128 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0);
		badBoat.setSizeAndImg(256, 128, TypeOfCharacter.TESTBOAT);
		
		badBoat.addTutoriaFishBad();

		boats.add(goodBoat);
		boats.add(badBoat);
		Controller.allChars.addAll(boats);
		for(Boat boat : boats){
			Controller.allChars.addAll(boat.getObjectsInBoat());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see games.GameMain#update()
	 */
	@Override
	public void update(){
		int base = 1 + difficulty;
		
		if(!pause){
			Controller.allChars.clear();
			createBoat();
			placeFishInBoat();
			checkOverfished();
			moveBoats();
			removeBoat();
			//checkOnClick();
			Controller.allChars.addAll(boats);
			for(Boat boat : boats){
				Controller.allChars.addAll(boat.getObjectsInBoat());
			}
			
			
			if (ViewMain.timeRemaining == 0 && roundCount <= 3){

				difficulty++;
				pause = true;
				fish1 = gen.nextInt(3)+(base);
				fish2 = gen.nextInt(3)+(base);
				fish3 = gen.nextInt(3)+(base);
				fish4 = gen.nextInt(3)+(base);
				crabs = gen.nextInt(3)+(base);

				Controller.allButtons.add(nextRound);
				Controller.timer.cancel();
				roundCount++;
				
				Controller.allChars.clear();
				boats.clear();
			}
		}
		
	}
	/*
	 * (non-Javadoc)
	 * @see games.GameMain#start()
	 */
	@Override
	public void start(){
		difficulty = Controller.difficulty;
		
		Controller.boatGameScoreThreshold = 500 + 500 * difficulty;
		
		tutorial();
		Controller.gameWon = false;
		timer = new Timer();
		Controller.HIGHSCORE = getHighScore();
		difficulty = 0;
		roundCount = 1;
		init();
		
	}
	/*
	 * creates a new series of initial boats, and sets when the round will change
	 */
	public void init(){
		theRule = new Button (ViewMain.SCREEN_WIDTH / 4, 0);
		theRule.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 15 / ViewMain.YSCALE), TypeOfButton.BOATGAMERULE);
		
		BGRF1 = new Button ((int)(theRule.getxLoc() + (theRule.getDrawWidth() / 38 * 2)), theRule.getDrawHeight() / 8);
		BGRF1.setSizeAndImg((int)(theRule.getWidth() / 19 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 20 / ViewMain.YSCALE), TypeOfButton.BGRF1);
		
		BGRF2 = new Button ((int)(theRule.getxLoc() + (theRule.getDrawWidth() / 38 * 10)), theRule.getDrawHeight() / 8);
		BGRF2.setSizeAndImg((int)(theRule.getWidth() / 19 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 20 / ViewMain.YSCALE), TypeOfButton.BGRF2);
		
		BGRF3 = new Button ((int)(theRule.getxLoc() + (theRule.getDrawWidth() / 38 * 17)), theRule.getDrawHeight() / 8);
		BGRF3.setSizeAndImg((int)(theRule.getWidth() / 19 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 20 / ViewMain.YSCALE), TypeOfButton.BGRF3);
		
		BGRF4 = new Button ((int)(theRule.getxLoc() + (theRule.getDrawWidth() / 38 * 24)), theRule.getDrawHeight() / 8);
		BGRF4.setSizeAndImg((int)(theRule.getWidth() / 19 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 20 / ViewMain.YSCALE), TypeOfButton.BGRF4);
		
		BGRHSC = new Button ((int)(theRule.getxLoc() + (theRule.getDrawWidth() / 38 * 31)), theRule.getDrawHeight() / 8);
		BGRHSC.setSizeAndImg((int)(theRule.getWidth() / 19 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 20 / ViewMain.YSCALE), TypeOfButton.BGRHSC);
		
		nextRound = new Button (ViewMain.SCREEN_WIDTH / 4 , ViewMain.SCREEN_HEIGHT / 4 ); 
		nextRound.setSizeAndImg((int)(ViewMain.SCREEN_WIDTH / 2 / ViewMain.XSCALE), (int)(ViewMain.SCREEN_HEIGHT / 2 / ViewMain.XSCALE), TypeOfButton.NEXTROUND);
		
		Controller.allButtons.add(theRule);
		Controller.allButtons.add(BGRF1);
		Controller.allButtons.add(BGRF2);
		Controller.allButtons.add(BGRF3);
		Controller.allButtons.add(BGRF4);
		Controller.allButtons.add(BGRHSC);

		
		
		int base = 1+difficulty;
		fish1 = gen.nextInt(3)+(base);
		fish2 = gen.nextInt(3)+(base);
		fish3 = gen.nextInt(3)+(base);
		fish4 = gen.nextInt(3)+(base);
		crabs = gen.nextInt(3)+(base);
		boats.clear();
		Boat temp1 = new Boat(3, difficulty);
		temp1.setSizeAndImg(256, 96, TypeOfCharacter.TESTBOAT);
		Boat temp2 = new Boat(7, difficulty);
		temp2.setSizeAndImg(256, 96, TypeOfCharacter.TESTBOAT);
		Boat temp3 = new Boat(11, difficulty);
		temp3.setSizeAndImg(256, 96, TypeOfCharacter.TESTBOAT);
		Boat temp4 = new Boat(15, difficulty);
		temp4.setSizeAndImg(256, 96, TypeOfCharacter.TESTBOAT);
		
		boats.add(temp1);
		boats.add(temp2);
		boats.add(temp3);
		boats.add(temp4);
		
		
	}
	/*
	 * (non-Javadoc)
	 * @see games.GameMain#end()
	 */
	@Override
	public void end(){
		boats.clear();
		
		Controller.timer.cancel();
		
		Controller.SCORE = this.score;
		if(score > HighScore){
			setHighScore();
		}
		timer.cancel();
		if(score > Controller.boatGameScoreThreshold){		
			Controller.boatGameWin = true;		
			Controller.gameWon = true;
		}
	}
	
	/*
	 * creates a boat and places it in a lane whenever a lane is empty
	 */
	public void createBoat(){
		if(boats.size() < 5){
			Boat temp1 = new Boat(3, difficulty);
			temp1.setSizeAndImg(256, 96, TypeOfCharacter.TESTBOAT);
			Boat temp2 = new Boat(7, difficulty);
			temp2.setSizeAndImg(256, 96, TypeOfCharacter.TESTBOAT);
			Boat temp3 = new Boat(11, difficulty);
			temp3.setSizeAndImg(256, 96, TypeOfCharacter.TESTBOAT);
			Boat temp4 = new Boat(15, difficulty);
			temp4.setSizeAndImg(256, 96, TypeOfCharacter.TESTBOAT);
			
			//boats.add(temp);
			boolean l1 = false;
			boolean l2 = false;
			boolean l3 = false;
			boolean l4 = false;
			for(Boat b : boats){
				if(b.getyLoc() == ViewMain.SCREEN_HEIGHT/20 * 3){
					l1 = true;
				}else if(b.getyLoc() == ViewMain.SCREEN_HEIGHT/20*7){
					l2 = true;
				}else if(b.getyLoc() == ViewMain.SCREEN_HEIGHT/20*11){
					l3 = true;
				}else if(b.getyLoc() == ViewMain.SCREEN_HEIGHT/20*15){
					l4 = true;
				}
			}
			if(!l1){
				boats.add(temp1);
			}else if(!l2){
				boats.add(temp2);
			}else if(!l3){
				boats.add(temp3);
			}else if(!l4){
				boats.add(temp4);
			}
			
		}
	}
	
	/*
	 * schedules a timer task for each boat that, when called at a random time, will call the s.fishing()
	 */
	public void placeFishInBoat(){
		for(Boat s : boats){
			s.animate();
			if(s.getCanFish()){
				s.setCanFish(false);
				long rand = (long)((Math.random() * 4000));
				s.boatTimer.schedule(new TimerTask(){

					@Override
					public void run() {
						
						s.fishing();
						
					}
				}, rand);
			}
		}
	}
	
	/*
	 * calls the boats move function if the boat is not currently fishing
	 */
	public void moveBoats(){
		for(Boat s : boats){
			if(s.getisFishing() == false){
				s.move();
			}
		}
	}
	
	/*
	 * removes the boat once it has reached the end of the screen
	 */
	public void removeBoat(){
		ArrayList<Boat> b = new ArrayList<Boat>();
		for(Boat s : boats){
			if(s.getxLoc() > ViewMain.SCREEN_WIDTH){
				s.getObjectsInBoat().clear();
				
				b.add(s);
				if(s.getIsOverfished()){
					score-=50;
				}
				
			}
		}
		for(Boat s : b){
			boats.remove(s);
			
			
		}
		b.clear();
	}
	
	/*
	 * checks if the boat is clicked, and if overfished, increases that boats speed and gives the player points.
	 * if the boat is not overfised, it reduces the players score.
	 */
	public void checkOnClick(double x, double y){
		for(Boat s : boats){
			int finalx = s.getxLoc()+256;
			int finaly = s.getyLoc()+96;
			for(int a = s.getyLoc(); a < finaly; a++){
				for(int c = s.getxLoc(); c < finalx; c++){
					if (x==c && y == a && s.getIsOverfished()){
						s.getObjectsInBoat().clear();
						s.setxVel(50);
						s.setCanFish(false);
						s.setFishing(false);
						s.boatTimer.cancel();
						score+=100;
						s.setIsOverfished(false);
					}else if(x==c && y == a && !s.getIsOverfished()){
						score-=10;
					}
				}
			}
		}
	}
	
	/*
	 * calls isOverfished for each boat in the Boats array
	 */
	public void checkOverfished(){
		for(Boat s : boats){
			s.isOverfishing(fish1, crabs, fish2, fish3, fish4);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see games.GameMain#setHighScore()
	 */
	@Override
	public void setHighScore(){
		try{
			File boatHS = new File("BoatHighScores.txt");
			FileOutputStream boatHSStream = new FileOutputStream(boatHS, false);
			byte[] hsBytes = Integer.toString(score).getBytes();
			boatHSStream.write(hsBytes);
			boatHSStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * returns the highscore from the boathighscore text file.
	 */
	public int getHighScore(){
		int highScore = 0;
		try {
	        File boatHS = new File("BoatHighScores.txt");
	        FileReader boatHSFR = new FileReader(boatHS);
	        BufferedReader boatHSBR = new BufferedReader(boatHSFR);
	        StringBuffer boatHSSB = new StringBuffer();
	        String savedScore;
	        
	        savedScore = boatHSBR.readLine();
	        boatHSSB.append(savedScore);
			highScore = Integer.parseInt(savedScore);
			boatHSFR.close();
	        } catch (IOException e){
	            e.printStackTrace();
	        }
		return highScore;
	}
}
