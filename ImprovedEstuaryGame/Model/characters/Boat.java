package characters;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import main.ViewMain;
import misc.TypeOfCharacter;
import misc.TypeOfFish;


public class Boat extends CharacterMain{
	
	private ArrayList<ObjectInBoat> objectsInBoat = new ArrayList<ObjectInBoat>();
	private boolean isFishing= false;
	private boolean canFish = true;
	private boolean isOverFished = false;
	public Timer boatTimer = new Timer();
	
	/* 
	 * creates boat with a custom boat with an x position, y position, x velocity, and y velocity
	 */
	public Boat(int x, int y, int xvel, int yvel){
		super(x, y, xvel, yvel);
	}
	/*
	 * creates a boat that spawns in a random lane
	 */
	public Boat(){
		super(0,ViewMain.SCREEN_HEIGHT/7*((int)((Math.random()*5)+1)), 1, 0); 
	}
	/*
	 * creates a boat that places a boat in a specific lane, and controls the speed of the boat using difficulty
	 */
	public Boat(int lane, int d){
		
		super(0,ViewMain.SCREEN_HEIGHT/20*lane, 2 + d, 0);
	}
	
	/*
	 * moves the boat and all of its attached fish
	 */
	public void move(){
		xLoc += xVel;
		yLoc += yVel;
		
		for(ObjectInBoat f : objectsInBoat){
			f.move();
		}
		
	}
	
	/*
	 * sets states for the fishing animation
	 */
	public void animate(){
		
		if (animationCount % 4 == 0 && !startAnimate){
			this.setCharType(TypeOfCharacter.TESTBOAT);
		}
	}
	public void addTutorialFishGood(){
		ObjectInBoat temp1 = new ObjectInBoat(TypeOfFish.FISH1);
		temp1.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH1);
		objectsInBoat.add(temp1);
		
		ObjectInBoat temp2 = new ObjectInBoat(TypeOfFish.FISH4);
		temp2.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH4);
		objectsInBoat.add(temp2);
		
		ObjectInBoat temp3 = new ObjectInBoat(TypeOfFish.FISH4);
		temp3.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH4);
		objectsInBoat.add(temp3);
		
		this.setCharType(TypeOfCharacter.FISHING4);
		
		int x = 0;
		int y = 0;
		int size = objectsInBoat.size();
		for(int i = 0; i<size; i++){
			objectsInBoat.get(i).setxLoc(xLoc+x);
			objectsInBoat.get(i).setyLoc(yLoc+y);
			objectsInBoat.get(i).setxVel(xVel);
			objectsInBoat.get(i).setyVel(yVel);
			x += 32;
			if(x > (256-32)){
			y += 32;
			x = 0;
			}
		}

	}
	
	/*
	 * creates a boat that is overfished and places it for the tutorial
	 */
	public void addTutoriaFishBad(){
		ObjectInBoat temp1 = new ObjectInBoat(TypeOfFish.FISH2);
		temp1.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH2);
		objectsInBoat.add(temp1);
		
		ObjectInBoat temp2 = new ObjectInBoat(TypeOfFish.FISH4);
		temp2.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH4);
		objectsInBoat.add(temp2);
		
		ObjectInBoat temp3 = new ObjectInBoat(TypeOfFish.FISH4);
		temp3.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH4);
		objectsInBoat.add(temp3);
		
		ObjectInBoat temp4 = new ObjectInBoat(TypeOfFish.FISH1);
		temp4.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH1);
		objectsInBoat.add(temp4);
		
		ObjectInBoat temp5 = new ObjectInBoat(TypeOfFish.FISH4);
		temp5.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH4);
		objectsInBoat.add(temp5);
		
		
		this.setCharType(TypeOfCharacter.FISHING4);
		
		int x = 0;
		int y = 0;
		int size = objectsInBoat.size();
		for(int i = 0; i<size; i++){
			objectsInBoat.get(i).setxLoc(xLoc+x);
			objectsInBoat.get(i).setyLoc(yLoc+y);
			objectsInBoat.get(i).setxVel(xVel);
			objectsInBoat.get(i).setyVel(yVel);
			x += 32;
			if(x > (256-32)){
			y += 32;
			x = 0;
			}
		}
	}
	
	/*
	 * adds fish to boat and sets the fish position based on how many fish are in the boat
	 */
	public void addFishToBoat(){
		int random = (int)(Math.random() * 5);
		startAnimate = true;
		if(random == 0){
			ObjectInBoat temp = new ObjectInBoat(TypeOfFish.FISH1);
			temp.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH1);
			objectsInBoat.add(temp);
			this.setCharType(TypeOfCharacter.FISHING1);
		}else if(random == 1){
			ObjectInBoat temp = new ObjectInBoat(TypeOfFish.FISH2);
			temp.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH2);
			objectsInBoat.add(temp);
			this.setCharType(TypeOfCharacter.FISHING2);

		}else if(random == 2){
			ObjectInBoat temp = new ObjectInBoat(TypeOfFish.FISH3);
			temp.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH3);
			objectsInBoat.add(temp);
			this.setCharType(TypeOfCharacter.FISHING3);

		}else if(random == 3){
			ObjectInBoat temp = new ObjectInBoat(TypeOfFish.FISH4);
			temp.setSizeAndImg(32, 32, TypeOfCharacter.TESTFISH4);
			objectsInBoat.add(temp);
			this.setCharType(TypeOfCharacter.FISHING4);

		}else{
			ObjectInBoat temp = new ObjectInBoat(TypeOfFish.CRAB);
			temp.setSizeAndImg(32, 32, TypeOfCharacter.TESTHSCRAB);
			objectsInBoat.add(temp);
			this.setCharType(TypeOfCharacter.FISHINGHSC);

		}
		int x = 0;
		int y = 0;
		int size = objectsInBoat.size();
		for(int i = 0; i<size; i++){
			objectsInBoat.get(i).setxLoc(xLoc+x);
			objectsInBoat.get(i).setyLoc(yLoc+y);
			objectsInBoat.get(i).setxVel(xVel);
			objectsInBoat.get(i).setyVel(yVel);
			x += objectsInBoat.get(i).getDrawWidth();
			if(x > (this.getDrawWidth()- objectsInBoat.get(i).getDrawWidth())){
			y += objectsInBoat.get(i).getDrawHeight();
			x = 0;
			}
		}
	}
	
	/*
	 * adds a timer task to the boats timer that, at a random time, stops the boat and calls addFishToBoat()
	 */
	public void fishing(){
		isFishing = true;
		canFish = false;
		if(objectsInBoat.size() < 30){
			
		
		long rand = (long)((Math.random() * 1000)+2000);
		boatTimer.schedule(new TimerTask(){

			@Override
			public void run() {
				addFishToBoat();
				isFishing = false;
				canFish = true;
			}
			
		}, rand);
		
		}
		
		
		/*
		isFishing = true;
		for(int random = (int)(Math.random() * 1000000000+1); random > 0 ; random --){
			if(random == 1){
				addFishToBoat();
			}
		}
		isFishing = false;
		//isOverfished = isOverfishing();
		 */
	}
	
	/*
	 * checks to see if the boat has to many of a particular fish or crab, then sets the overfishing attribute accordingly
	 */
	public void isOverfishing(int fish1, int crabs, int fish2, int fish3, int fish4){
		int f1=0;
			int f2=0;
			int f3=0;
			int f4=0;
			int c=0;
			for(ObjectInBoat o : objectsInBoat){
				switch(o.getFishType()){
					case FISH1:
						f1++;
						break;
					case FISH2:
						f2++;
						break;
					case FISH3:
						f3++;
						break;
					case FISH4:
						f4++;
						break;
					case CRAB:
						c++;
						break;
				default:
						
				}
				
			}
			if(f1 > fish1 || f2 > fish2 || f3>fish3 || f4 > fish4 || c > crabs){
				setIsOverfished(true);
			}
	}

	/*
	 * returns if the boat is fishing
	 */
	public boolean getisFishing(){
		return isFishing;
	}

	/*
	 * returns the objectsInBoat
	 */
	public ArrayList<ObjectInBoat> getObjectsInBoat() {
		return objectsInBoat;
	}

	/*
	 * sets objectsInBoat to a new arraylist<ObjectInBoat>
	 */
	public void setObjectsInBoat(ArrayList<ObjectInBoat> objectsInBoat) {
		this.objectsInBoat = objectsInBoat;
	}

	/*
	 * set whether the boat is fishing
	 */
	public void setFishing(boolean isFishing) {
		this.isFishing = isFishing;
	}
	
	/*
	 * set whether the boat is overfished
	 */
	public void setIsOverfished(boolean o){
		this.isOverFished = o;
	}
	
	/*
	 * returns whether the boat is overfished
	 */
	public boolean getIsOverfished(){
		return isOverFished;
	}

	/*
	 * returns whether the boat is able to fish
	 */
	public boolean getCanFish() {
		return canFish;
	}

	/*
	 * set whether the boat can fish
	 */
	public void setCanFish(boolean canFish) {
		this.canFish = canFish;
	}
}

