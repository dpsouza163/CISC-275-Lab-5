package characters;

import misc.TypeOfCharacter;
import misc.TypeOfTrash;
/**
 * @author Team 0
 *
 */
public class Walker extends CharacterMain {
	
	/**
	 * Constructor for Walker
	 */
	public Walker(){
		super();
	}
	
	/**
	 * Constructor for Walker
	 * 
	 * @param x xLoc
	 * @param y yLoc
	 * @param xVel Speed x
	 * @param yVel Speed y
	 */
	public Walker(int x, int y, int xVel, int yVel) {
		super(x, y, xVel, yVel);
	}
	
	/**
	 * this method moves the walker down the path
	 * 
	 * @return void
	 */
	public void move(){
			this.yLoc += this.yVel;
		}
	
	
	/**
	 * This method generates a poop at the current x and y of the walker
	 * 
	 * @return ObjectInRiver
	 */
	public ObjectInRiver poop(){
		ObjectInRiver temp = new ObjectInRiver(this.xLoc,this.yLoc,0,0,TypeOfTrash.POOP);
		temp.setSizeAndImg(32, 32,TypeOfCharacter.TESTPOOP);
		return temp;
	}
}
