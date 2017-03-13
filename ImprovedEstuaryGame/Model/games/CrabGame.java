package games;

import java.util.ArrayList;

import java.util.Random;




public class CrabGame extends GameMain{

	public ArrayList<Food> food = new ArrayList<Food>();
	public ArrayList<Crab> crab = new ArrayList<Crab>();
	private int maxCrabOnScreen, foodOnScreen, mittenCrabsThatEaten, difficulty; 
	/** 
	private int spawn_rate = 120;
	private int combo = 1;
	public int getScore(){
	return this.score;
	}

	public void setScore(int score){
		this.score = score;
	}
	public ArrayList<Crab> getCrab() {
		return crab;
	}
	public void setCrab(ArrayList<Crab> crab) {
		this.crab = crab;
	}
	public ArrayList<Food> getFood() {
		return food;
	}
	public void setFood(ArrayList<Food> food) {

		this.food = food;

	}
	public static Food dropFood(Crab curr){
		Food f = new Food(curr.getxLoc(), curr.getyLoc());
		f.setSizeAndImg(110, 47, TypeOfCharacter.TESTFOOD);
		f.setTaken(false);
		return f;
	}
	/**
	public void setNumFood(int x){
		Food f = new Food(0,0);
		for(int i = 0; i < x; i++){
			food.add(f);
		}
	}

	/**
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
	
	public void spawnCrab(){
		Crab c = new Crab(0,0,0,0, false);

		TypeOfCharacter type = TypeOfCharacter.TESTCRAB;

		//easy - 1 tap 
		//medium - 2 taps 
		//hard - 3 taps
		c.setNumTaps(difficulty + 1);

		//blue or mitten

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
	/**
	public double distanceFormula(double x1,double y1, double x2,double y2){

		

		return Math.abs(Math.sqrt(Math.pow((x1 - x2),2) + Math.pow((y1 - y2),2)));

	}

	

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

		

	}
	public int findAngle(int x1, int y1, int x2, int y2){

		

		return (int)(Math.atan((y2-y1)/(x2-x1))*6.28);

	}
	/**
	public void start(){
		Controller.gameWon = false;

		score = 0;
		Controller.HIGHSCORE = getHighScore();








		difficulty = Controller.difficulty;
		
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
	/** 
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
	/**
	public void end(){

		crab.clear();

		food.clear();
		
		
		if (score > getHighScore()){
		//display high score

		//store high score 

		//go to map

	}
	/**
	public int getFoodSize(){

		return food.size();

	}

	

	public Crab findCrab(int i){

		return crab.get(i);

	}

	

	public Food findFood(int i){

		return food.get(i);

	}

	

	public int getCrabSize(){

		return crab.size();

	}



	

	//setters 

	public void addFood(int x, int y){

		Food f = new Food(0,0);

		f.setxLoc(x);

		f.setyLoc(y);

		food.add(f);

	}

	

	public void addCrab(int x, int y){

		Crab c = new Crab(0,0,0,0, false);

		c.setxLoc(x);

		c.setyLoc(y);

		crab.add(c);

	}

	

	public void addCrab(int x, int y, int xVel, int yVel, boolean isMitten){

		Crab c = new Crab(0,0,0,0, false);

		c.setxLoc(x);

		c.setyLoc(y);

		c.setxVel(xVel);

		c.setyVel(yVel);

		c.setMitten(isMitten);

		crab.add(c);

	}

	

	public void addFood(Food f){

		food.add(f);

	}

	
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