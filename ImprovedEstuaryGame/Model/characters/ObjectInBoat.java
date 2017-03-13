package characters;

import misc.TypeOfFish;

public class ObjectInBoat extends CharacterMain{
	private TypeOfFish fishType;
	
	/*
	 * create a ObjectInBoat that is of a certain type of fish
	 */
	public ObjectInBoat(TypeOfFish fish){
		
		fishType= fish;
	}
	
	/*
	 * set the type of fish
	 */
	public void setFishType(TypeOfFish fish){
		fishType = fish;
	}
	
	/*
	 * returns the type of fish
	 */
	public TypeOfFish getFishType(){
		return fishType;
	}
	
	/*
	 * moves the object in boat
	 */
	public void move(){
		xLoc += xVel;
		yLoc += yVel;
		
	}
}
