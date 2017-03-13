package characters;



import misc.TypeOfTrash;
/**
 * @author Team 0
 *
 */
public class ObjectInRiver extends CharacterMain{
	private TypeOfTrash trashType;
	private boolean isTrash;
	private int score;
	
	
	/**
	 * Constructor for ObjectInRiver
	 */
	public ObjectInRiver(){
		super();
	}
	
	/**
	 * Constructor for ObjectInRiver
	 * @param x xLoc
	 * @param y yLoc
	 * @param xVel Speed x
	 * @param yVel Speed y
	 */
	public ObjectInRiver(int x, int y, int xVel, int yVel,TypeOfTrash t) {
		super(x, y, xVel, yVel);
		this.trashType = t;
	}
	
	
	/**
	 * setter for velocity
	 * @param x Xvel
	 * @param y Yvel
	 */
	public void setVelocity(int x, int y){
		this.setxVel(x);
		this.setyVel(y);
	}
	
	/**
	 * setter for type of trash
	 * @param t Enum TypeOfTrash
	 */
	public void setTrashType(TypeOfTrash t){
		this.trashType = t;
	}
	
	/**
	 * get type of trash
	 * @return void
	 */
	public TypeOfTrash getTrashType(){
		return this.trashType;
	}

	
	/**
	 * returns if the object is trash or not depending on the enum
	 * @return boolean
	 */
	public boolean getIsTrash()
	{
		switch(trashType){
		case POOP: return isTrash = false;
		case PLASTIC: return isTrash = true;
		case PAPER: return isTrash = true;
		case FISH: return isTrash = true;
		default: return isTrash = false;
			}
	}

	/**
	 * returns the score for the trash depending on the enum
	 * @return int
	 */
	public int getScore()
	{
		switch(trashType){
		case POOP: return score = 50;
		case PLASTIC: return score = 10;
		case PAPER: return score = 15;
		case FISH: return score = 10;
		default: return score = 0;
		}
	}
	
	/**
	 * Gets the xloc of the object
	 * @return int
	 */
	public int getxLoc(){
		return this.xLoc;
	}
	
	/**
	 * Gets the yloc of the object
	 * @return int
	 */
	public int getyLoc(){
		return this.yLoc;
	}
	
	
	/**
	 * this method moves trash down the river
	 * @return void
	 */
	public void move(){
		if(trashType!=TypeOfTrash.POOP){
			yLoc += yVel;
		}
	}
	
	
}
