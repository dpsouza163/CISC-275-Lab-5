package games;

import java.util.ArrayList;

import java.util.Random;import characters.Crab;import characters.Food;import main.Controller;import main.ViewMain;import misc.TypeOfCharacter;

import java.io.BufferedReader;import java.io.File;import java.io.FileOutputStream;import java.io.FileReader;import java.io.IOException;import java.lang.Math.*;


public class CrabGame extends GameMain{

	public ArrayList<Food> food = new ArrayList<Food>();
	public ArrayList<Crab> crab = new ArrayList<Crab>();
	private int maxCrabOnScreen, foodOnScreen, mittenCrabsThatEaten, difficulty; 
	/** 	 * Tutorial handling function	 * Creates the tutorial screen for the crab game 	 */	public void tutorial(){		this.inTutorial = true;				food.clear();		crab.clear();		Controller.allChars.clear();			Crab blue = new Crab(ViewMain.SCREEN_WIDTH / 2 - (int)(372 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0, false);			blue.setSizeAndImg(248,	124, TypeOfCharacter.TESTCRAB);		Crab mitten = new Crab(ViewMain.SCREEN_WIDTH / 2 + (int)(124 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0, true);			mitten.setSizeAndImg(248, 124, TypeOfCharacter.TESTBADCRAB);				crab.add(blue);		crab.add(mitten);		Controller.allChars.addAll(crab);		//System.out.println(crab.size());			}	
	private int spawn_rate = 120;
	private int combo = 1;	/**	 * getter for score 	 * @return the current game score 	 */
	public int getScore(){
	return this.score;
	}
	/**	 * Setter for game score	 * @param takes in int as new score 	 */
	public void setScore(int score){
		this.score = score;
	}	/**	 * Getter for Crab array	 * @return crab 	 */
	public ArrayList<Crab> getCrab() {
		return crab;
	}	/** 	 * Setter for crab array	 * @param crab to add to array list	 */
	public void setCrab(ArrayList<Crab> crab) {
		this.crab = crab;		
	}	/** 	 * getter for food array list	 * @return food 	 */
	public ArrayList<Food> getFood() {
		return food;
	}	/**	 * getter for food array 	 * @param food to add	 */
	public void setFood(ArrayList<Food> food) {

		this.food = food;

	}	/**	 * Makes the crab drop its current food	 * @param crab to drop food	 * @return food dropped at crab's location 	 */
	public static Food dropFood(Crab curr){
		Food f = new Food(curr.getxLoc(), curr.getyLoc());
		f.setSizeAndImg(110, 47, TypeOfCharacter.TESTFOOD);
		f.setTaken(false);
		return f;
	}
	/**	 * Setter for number of food on screen	 * @param int for number of food 	 */
	public void setNumFood(int x){
		Food f = new Food(0,0);
		for(int i = 0; i < x; i++){
			food.add(f);
		}
	}

	/**	 * Changes the crab direction towards the closest food	 * @param crab to change direction of 	 */
	public void changeCrabDirection(Crab c){
		int o = c.getxLoc() - c.getNearestFoodX();
		int a = c.getyLoc() - c.getNearestFoodY();
		if (o == 0){
			if (a < 0){
				c.setDirection(270);
			}
			else{
				c.setDirection(45);
			}
		}
		else{
		try {
		c.setDirection(Math.atan2(a,o));
		}
		catch (ArithmeticException e){
			e.printStackTrace();
		}
		}
		//looping the degrees 
		while (c.getDirection() >= 360)
		{
			c.setDirection(c.getDirection() % 360);
		}
	}
		/*	 * spawns crab and adds it to crab array list 	 */
	public void spawnCrab(){
		Crab c = new Crab(0,0,0,0, false);

		TypeOfCharacter type = TypeOfCharacter.TESTCRAB;

		//easy - 1 tap 
		//medium - 2 taps 
		//hard - 3 taps
		c.setNumTaps(difficulty + 1);

		//blue or mitten		Random gen = new Random();

		if (gen.nextInt(3) == 0){

			c.setMitten(true);

			c.setSpeed(5+(int)Math.random());

			type = TypeOfCharacter.TESTBADCRAB;

			//c.setVelocity(2,2); //mitten crabs speed is 2

		}

		

		else{

			c.setMitten(false);

			c.setSpeed(2+(int)Math.random());

			c.setVelocity(1,1); //blue crab speed is 1

		}

		

		//has food is false

		c.setHasFood(false);

		

		//spawn area NEEDS TO BE FIXED 
		/*
		 * n = places where crab can spawn 
		 * ^ = screen 
		 * 
		 * 		*0000*
		 * 		2^^^^3
		 * 		2^^^^3
		 * 		*1111*
		 * 
		 */

		

		int spawnArea = (int)(Math.random () * 4);

		switch (spawnArea){

		

			case 0: 

			c.setxLoc((int)(Math.random() * ViewMain.SCREEN_WIDTH));

			c.setyLoc(-200);

			break;

			

			case 1: 

			c.setxLoc((int)(Math.random() * ViewMain.SCREEN_WIDTH));

			c.setyLoc(ViewMain.SCREEN_HEIGHT + 200);

			break; 

			

			case 2: 

			c.setxLoc(-200);

			c.setyLoc((int)(Math.random() * ViewMain.SCREEN_HEIGHT));

			break;

			

			case 3: 

			c.setxLoc(ViewMain.SCREEN_WIDTH + 200);

			c.setyLoc((int)(Math.random() * ViewMain.SCREEN_HEIGHT));

		

		}

		

		c.setSizeAndImg(248, 124, type);

		

		//adding new crab to list

		crab.add(c);

		

	}

	
	/**	 * Spawns food and adds it to food array 	 */
	public void spawnFood(){

		

		Food f = new Food(0,0);

		

		f.setSizeAndImg(110, 47, TypeOfCharacter.TESTFOOD);

		

		//spawn area

		f.setxLoc((int)((Math.random() * (ViewMain.SCREEN_WIDTH/2)) + (ViewMain.SCREEN_WIDTH/4)));
		f.setyLoc((int)((Math.random() * (ViewMain.SCREEN_HEIGHT/2)) + (ViewMain.SCREEN_HEIGHT/4)));
		//f.setxLoc(ViewMain.SCREEN_WIDTH/2);
		//f.setyLoc(ViewMain.SCREEN_HEIGHT/2);
		
		
		
		f.setTaken(false);

		

		food.add(f);

	}
	/**	 * Finds mathematical distance between two points 	 * @param x1	 * @param y1	 * @param x2	 * @param y2	 * @return the distance between (x1,y1) and (y1,y2)	 */
	public double distanceFormula(double x1,double y1, double x2,double y2){

		

		return Math.abs(Math.sqrt(Math.pow((x1 - x2),2) + Math.pow((y1 - y2),2)));

	}

	
	/**	 * Finds the nearest food for a crab	 * @param crab to be searching for food 	 * @return the closest food 	 */
	public Food findNearestFood(Crab crab){

		

		Food closestFood = new Food(0,0);

		double minimalDistance = 99999999;

		double currentDistance = 0;

		

		for(int i = 0; i < food.size(); i++){

			

			currentDistance = distanceFormula(food.get(i).getxLoc(),food.get(i).getyLoc(),crab.getxLoc(),crab.getyLoc());

			

			if (minimalDistance > currentDistance){

				

				if (food.get(i).isTaken() == false){

					

					closestFood = food.get(i);

					minimalDistance = currentDistance;

					

				}

	

			}

			

		}

		

		return closestFood;

		

	}	/**	 * Calculates the angle between two given points 	 * @param x1	 * @param y1	 * @param x2	 * @param y2	 * @return calculates the angle between (x1,y1) and (x2,y2) 	 */
	public int findAngle(int x1, int y1, int x2, int y2){

		

		return (int)(Math.atan((y2-y1)/(x2-x1))*6.28);

	}
	/**	 * called at start of game 	 */
	public void start(){										
		Controller.gameWon = false;

		score = 0;
		Controller.HIGHSCORE = getHighScore();








		difficulty = Controller.difficulty;		Controller.crabGameScoreThreshold = 1000 + (1000 * difficulty);
		
		switch (difficulty){
			
		case 0: 
			spawn_rate = 140;
			break;
			
		case 1: 
			spawn_rate = 120;
			break;
			
		case 2:
			spawn_rate = 100;
			break;
		}
		tutorial();
	}

	

	//checks if crabs are on food

	public void checkCrabs(){



		for(int i = 0; i < crab.size(); i++){

			for(int j = 0; j < food.size(); j++){



				if ((crab.get(i).collisionDetect(food.get(j))) 

						&& (crab.get(i).isHasFood() == false)

						&& (crab.get(i).isMovingOffScreen == false)){



					if (crab.get(i).isMitten() == true){

						mittenCrabsThatEaten++;

					}



					crab.get(i).grabFood(food.get(j));

					crab.get(i).isMovingToFood = false;

					crab.get(i).hasAlreadyPickedUpFood = true;

					food.get(j).setTaken(true);

					food.remove(j);



					

					crab.get(i).moveOffScreen();

					crab.get(i).isMovingOffScreen = true;

					

				} 

			}

			

			changeCrabDirection(crab.get(i));

		}

	}
	/** 	 * moves all crabs within the crab array list	 * updates their locations	 */
	public void moveCrabs(){
		

		for(int i = 0; i < crab.size(); i++){



			//move to food if crab is still hungry

			if (!crab.get(i).noMoreTaps()) {

				if (crab.get(i).isHasFood() == false){

					crab.get(i).setNearestFoodX(findNearestFood(crab.get(i)).getxLoc());

					crab.get(i).setNearestFoodY(findNearestFood(crab.get(i)).getyLoc());

					//changeCrabDirection(crab.get(i));

					crab.get(i).moveToFood();

					crab.get(i).isMovingToFood = true;

					crab.get(i).moveTheCrab();



				}

				else

				{

					//running away with food

					if (!crab.get(i).isMovingOffScreen){

						crab.get(i).moveOffScreen();

						crab.get(i).isMovingOffScreen = true;

					}

					crab.get(i).moveTheCrab();

					

					

					

				}

			}

			

		}

	}

	
	/** 	 * checks the number of taps on all crabs in the crab array 	 */
	public void checkCrabTaps(){

		for (int i = 0; i < crab.size(); i++){

			/*

			if (crab.get(i).isMovingToFood) {

				if (crab.get(i).noMoreTaps()){

					if (crab.get(i).isMitten()){

						crab.get(i).setCharType(TypeOfCharacter.TESTBADCRAB);

					}

					else {

						crab.get(i).setCharType(TypeOfCharacter.TESTCRAB);

					}

					crab.get(i).setHasFood(false);

					if (!crab.get(i).isMovingOffScreen){

						crab.get(i).moveOffScreen();

						crab.get(i).isMovingOffScreen = true;

						crab.get(i).moveTheCrab();



					}



				}

			}

			else {

				if (crab.get(i).noMoreTaps()){

					if (crab.get(i).isMitten()){

						crab.get(i).setCharType(TypeOfCharacter.TESTBADCRAB);

					}

					else {

						crab.get(i).setCharType(TypeOfCharacter.TESTCRAB);

					}

					

					if (!crab.get(i).isMovingOffScreen){

						crab.get(i).moveOffScreen();

						crab.get(i).isMovingOffScreen = true;

						crab.get(i).moveTheCrab();



					}

					food.add(dropFood(crab.get(i)));

				}

			}

			*/

			

			if (crab.get(i).noMoreTaps()){

				

				if (crab.get(i).hasAlreadyPickedUpFood){

					crab.get(i).moveTheCrab();

				}

				else {

					crab.get(i).moveTheCrab();

				}



			}

			

		}

	}

	
	/**	 * deletes the crabs off screen that take up memory 	 */
	public void deleteOffScreenCrabs(){

		for (int i = 0; i < crab.size(); ++i){

			if (crab.get(i).isMovingOffScreen){

				if (crab.get(i).getxLoc() > ViewMain.SCREEN_WIDTH

						|| crab.get(i).getxLoc() < 0 - crab.get(i).getWidth()

						|| crab.get(i).getyLoc() > ViewMain.SCREEN_HEIGHT

						|| crab.get(i).getyLoc() < 0 - crab.get(i).getHeight()) {



					if (!crab.get(i).isMitten() && crab.get(i).isHasFood()){

						score += 50 * combo;
						
						if (combo < 5)
						{
							combo++;
						}
						
					}

					else if (crab.get(i).isMitten() && crab.get(i).isHasFood()){

						score -= 250;
						combo = 1;

					}

					crab.remove(i);



				}

			}



		}

	}
	
	
	/**	 * called 30 times a second 	 * updates crabs and food 	 */
	public void update(){

	

		

		if ((((int)(Math.random() * spawn_rate)) <= 1) && (food.size() > 0)){

			spawnCrab();

		}

		

		if ((((int)(Math.random() * spawn_rate)) <= 1) && (food.size() <= crab.size())){

			spawnFood();

		}

		

		//difficulty increasing as time passes
		/*switch (difficulty){
		
			case 0: 
				if (((int)(Math.random() * 48)) == 1){
					spawn_rate -= 1;
				}
				break;
			
			case 1:
				if (((int)(Math.random() * 44)) == 1){
					spawn_rate -= 1;
				}
				break;
			
			default:
				if (((int)(Math.random() * 40)) == 1){
					spawn_rate -= 1;
				}
				break;
		}*/

		moveCrabs();

		checkCrabs();

		checkCrabTaps();

		deleteOffScreenCrabs();

		

		

		

		Controller.allChars.clear();

		Controller.allChars.clear();

		

		Controller.allChars.addAll(crab);

		Controller.allChars.addAll(food);

		

		   

	}
	/**	 * called when game ends and player returns to main map 	 */
	public void end(){

		crab.clear();

		food.clear();		Controller.SCORE = this.score;
				if(score > Controller.crabGameScoreThreshold){							Controller.crabGameWin = true;				Controller.gameWon = true;		}
		
		if (score > getHighScore()){			setHighScore();		}
		//display high score

		//store high score 

		//go to map

	}
	/**	 * getter for food size 	 * @return the size of the food array list	 */
	public int getFoodSize(){

		return food.size();

	}

	
	/**	 * finds crab within the crab array list	 * @param i index of crab you are looking for 	 * @return the crab at that index 	 */
	public Crab findCrab(int i){

		return crab.get(i);

	}

	
	/**	 * finds food within the food array list 	 * @param i index of the food you are looking for 	 * @return the food at that index 	 */
	public Food findFood(int i){

		return food.get(i);

	}

	
	/**	 * gets the size of the crab array list 	 * @return the size of the crab array list 	 */
	public int getCrabSize(){

		return crab.size();

	}



	

	//setters 
	/**	 * adds a food to the given x and y 	 * @param x	 * @param y	 */
	public void addFood(int x, int y){

		Food f = new Food(0,0);

		f.setxLoc(x);

		f.setyLoc(y);

		food.add(f);

	}

	
	/**	 * adds crab to the given x and y 	 * @param x	 * @param y	 */
	public void addCrab(int x, int y){

		Crab c = new Crab(0,0,0,0, false);

		c.setxLoc(x);

		c.setyLoc(y);

		crab.add(c);

	}

	
	/**	 * adds crab to given x and y, with xVel yVel, and isMitten set 	 * @param x	 * @param y	 * @param xVel	 * @param yVel	 * @param isMitten	 */
	public void addCrab(int x, int y, int xVel, int yVel, boolean isMitten){

		Crab c = new Crab(0,0,0,0, false);

		c.setxLoc(x);

		c.setyLoc(y);

		c.setxVel(xVel);

		c.setyVel(yVel);

		c.setMitten(isMitten);

		crab.add(c);

	}

	
	/**	 * adds given food to the food array list 	 * @param f	 */
	public void addFood(Food f){

		food.add(f);

	}

		/**	 * writes crab high score to file 	 */
	public void setHighScore(){
		try{
			File crabHS = new File("CrabHighScores.txt");
			FileOutputStream crabHSStream = new FileOutputStream(crabHS, false);
			byte[] hsBytes = Integer.toString(score).getBytes();
			crabHSStream.write(hsBytes);
			crabHSStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		/**	 * gets high score from file 	 * @return the high score from file 	 */
	public int getHighScore(){
		int highScore = 0;
		try {
	        File crabHS = new File("CrabHighScores.txt");
	        FileReader crabHSFR = new FileReader(crabHS);
	        BufferedReader crabHSBR = new BufferedReader(crabHSFR);
	        StringBuffer crabHSSB = new StringBuffer();
	        String savedScore;
	        
	        savedScore = crabHSBR.readLine();
	        crabHSSB.append(savedScore);
			highScore = Integer.parseInt(savedScore);
			crabHSFR.close();
	        } catch (IOException e){
	            e.printStackTrace();
	        }
		return highScore;
	}


	





}