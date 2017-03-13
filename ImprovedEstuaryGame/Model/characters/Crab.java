package characters;

import misc.TypeOfCharacter;


public class Crab extends CharacterMain{



	private boolean hasFood,isMitten, isTapped;

	private int score, numTaps;

	private int nearestFoodX, nearestFoodY,speed;

	private double direction;

	

	public boolean isMovingOffScreen, isMovingToFood, hasAlreadyPickedUpFood,

					hasAlreadyDroppedFood, hasAlreadyGottenScoreFromClick;

	

	
	/**
	 * crab constructor 
	 */
	public Crab(){

		super();

		

		this.setNumTaps(2);

		

		isMovingOffScreen = false;

		isMovingToFood = true;

		hasAlreadyPickedUpFood = false;

		hasAlreadyDroppedFood = false;

		hasAlreadyGottenScoreFromClick = false;

	}

	
	/**
	 * crab constructor to set all of the crab fields 
	 * @param x
	 * @param y
	 * @param xVel
	 * @param yVel
	 * @param isMitten
	 */
	public Crab(int x, int y, int xVel, int yVel, boolean isMitten) {

		super(x, y, xVel, yVel);

		this.isMitten = isMitten;

		

		this.setNumTaps(2);

		

		isMovingOffScreen = false;

		isMovingToFood = true;

		hasAlreadyPickedUpFood = false;

		hasAlreadyDroppedFood = false;

		hasAlreadyGottenScoreFromClick = false;



	}

	
	/**
	 * moves the crab's x and y location using its current x and y vel 
	 */
	public void moveTheCrab(){

		

		//vel adding to position

		this.setxLoc(this.xLoc + this.xVel);

		this.setyLoc(this.yLoc + this.yVel);

	}

	
	/**
	 * returns a boolean of whether or not the crab has more taps 
	 * @return boolean of whether or not the crab has more taps 
	 */
	public boolean noMoreTaps(){

		if (numTaps == 0){

			return true;

		}

		else {

			return false;

		}

	}

	

	
	/**
	 * called to make crab grab food that it is on
	 * @param f food to grab
	 */
	public void grabFood(Food f){

		hasFood = true;

		

		if (isMitten){

			this.setCharType(TypeOfCharacter.TESTBADCRABFOOD);

		}

		else {

			this.setCharType(TypeOfCharacter.TESTCRABFOOD);

		}

	}

	
	/**
	 * sets x and y velocity of the crab 
	 * @param x
	 * @param y
	 */
	public void setVelocity(int x, int y){

		xVel = x;

		yVel = y;

	}

	
	/**
	 * tells the crab to move off of the screen by modifying its x and y velocity 
	 */
	public void moveOffScreen(){

		xVel = -xVel * 2;

		yVel = -yVel * 2;

		

		isMovingOffScreen = true;

	}

	
	/**
	 * tells the crab to move towards the closest food
	 * sets the x and y vel to the direction 
	 */
	public void moveToFood(){

		if (isMovingToFood){

			xVel = (int) (Math.cos(direction) * -speed);

			yVel = (int) (Math.sin(direction) * -speed);

			

	}

		

	}


	/**
	 * getter for has food 
	 * @return boolean of whether or not the crab has food 
	 */
	public boolean isHasFood() {

		return hasFood;

	}


	/**
	 * setter for has food 
	 * @param hasFood boolean of whether or not the crab has food 
	 */
	public void setHasFood(boolean hasFood) {

		this.hasFood = hasFood;

	}


	/**
	 * getter for whether or not the crab is mitten 
	 * @return the crab is mitten or not mitten 
	 */
	public boolean isMitten() {

		return isMitten;

	}


	/**
	 * seeter for isMitten 
	 * @param isMitten boolean for whether or not the crab is mitten 
	 */
	public void setMitten(boolean isMitten) {

		this.isMitten = isMitten;

	}


	/**
	 * getter for the current score 
	 * @return the current score 
	 */
	public int getScore() {

		return score;

	}


	/**
	 * setter for the current score 
	 * @param score to set the score variable to 
	 */
	public void setScore(int score) {

		this.score = score;

	}


	/**
	 * getter for the number of taps the crab can receive 
	 * @return the number of taps the crab can receive 
	 */
	public int getNumTaps() {

		return numTaps;

	}


	/**
	 * setter for the number of taps the crab can receive 
	 * @param numTaps the number of taps the crab can receive 
	 */
	public void setNumTaps(int numTaps) {

		this.numTaps = numTaps;

	}


	/**
	 * getter for the nearest food x 
	 * @return
	 */
	public int getNearestFoodX() {

		return nearestFoodX;

	}


	/**
	 * setter for the nearest food x 
	 * @param nearestFoodX
	 */
	public void setNearestFoodX(int nearestFoodX) {

		this.nearestFoodX = nearestFoodX;

	}


	/**
	 * getter for the nearest food y 
	 * @return
	 */
	public int getNearestFoodY() {

		return nearestFoodY;

	}


	/**
	 * setter for the nearest food y 
	 * @param nearestFoodY
	 */
	public void setNearestFoodY(int nearestFoodY) {

		this.nearestFoodY = nearestFoodY;

	}


	/**
	 * getter for whether or not the crab is tapped 
	 * @return
	 */
	public boolean isTapped() {

		return isTapped;

	}


	/**
	 * setter for whether or not the crab is tapped 
	 * @param isTapped
	 */
	public void setTapped(boolean isTapped) {

		this.isTapped = isTapped;

	}


	/**
	 * getter for the direction of the crab 
	 * @return
	 */
	public double getDirection() {

		return direction;

	}


	/**
	 * setter for the direction of the crab 
	 * @param direction
	 */
	public void setDirection(double direction) {

		this.direction = direction;

	}


	/** 
	 * getter for the current crab speed 
	 * @return
	 */
	public int getSpeed() {

		return speed;

	}


	/**
	 * setter for the current crab speed 
	 * @param speed
	 */
	public void setSpeed(int speed) {

		this.speed = speed;

	}

	

	

	

	

	

	

	

}
