package characters;

public class Food extends CharacterMain{

	/**
	 * constructor for food object 
	 * @param x
	 * @param y
	 */
	public Food(int x, int y) {
		this.xLoc = x;
		this.yLoc = y;
		
		testFlag = false;
	}

	private boolean isTaken;
	
	public boolean testFlag;
	
	/**
	 * getter for is taken 
	 * @return
	 */
	public boolean isTaken() {
		return isTaken;
	}
	
	/** 
	 * setter for is taken 
	 * @param isTaken
	 */
	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
	
	

}
