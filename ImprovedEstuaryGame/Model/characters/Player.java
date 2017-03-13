package characters;

import games.TrashGame;
import main.ViewMain;
import misc.TypeOfCharacter;
/**
 * @author Team 0
 *
 */
public class Player extends CharacterMain{

	public int walkingCounter;
	public int pickingUpCounter;
	public static boolean facingLeft = true;
	private int intendedXLoc, intendedYLoc;
	private boolean isWalking , xWalking , yWalking;

	int buffer = 8;

	/**
	 * Constructor for player
	 */
	public Player(){
		super();
		walkingCounter = 0;
		pickingUpCounter = 0;
	}

	/**
	 * Constructor for player
	 * @param x
	 * @param y
	 * @param xVel
	 * @param yVel
	 */
	public Player(int x, int y, int xVel, int yVel) {
		super(x, y, xVel, yVel);
		walkingCounter = 0;
		pickingUpCounter = 0;
	}

	/**
	 * gets walkingCounter
	 * @return int
	 */
	public int getWalkingCounter() {
		return walkingCounter;
	}

	
	/**
	 * sets walkingCounter
	 * @return void
	 */
	public void setWalkingCounter(int walkingCounter) {
		this.walkingCounter = walkingCounter;
	}

	/**
	 * gets pickingUpCounter
	 * @return int
	 */
	public int getPickingUpCounter() {
		return pickingUpCounter;
	}

	/**
	 * sets pickingUpCounter
	 * @return void
	 */
	public void setPickingUpCounter(int pickingUpCounter) {
		this.pickingUpCounter = pickingUpCounter;
	}


/**
 * Moves the player object to the value set by intendedxLoc and intendedYLoc
 * 
 * @return void
 */
	public void move()
	{
		bridge();
		if(isWalking)
		{
			if(xWalking)
			{
				if(intendedXLoc!=xLoc)
				{
					if(intendedXLoc>xLoc)
					{
						if (!TrashGame.isPickingUpTrash){
							facingLeft = false;
							this.setCharType(TypeOfCharacter.PLAYERWALKRIGHT);
						}
						xLoc+=xVel;
					}
					else
					{
						if (!TrashGame.isPickingUpTrash){
							facingLeft = true;
							this.setCharType(TypeOfCharacter.PLAYERWALKLEFT);
						}
						xLoc-=xVel;
					}
				}
			}
			if(yWalking)
			{
				if(intendedYLoc!=yLoc)
				{
					if(intendedYLoc>yLoc)
					{
						if (!TrashGame.isPickingUpTrash){
							this.setCharType(TypeOfCharacter.PLAYERWALKDOWN);
						}
						yLoc+=yVel;
					}
					else
					{
						if (!TrashGame.isPickingUpTrash){
							this.setCharType(TypeOfCharacter.PLAYERWALKUP);
						}
						yLoc-=yVel;
					}
				}
			}
		}
	}

	/**
	 * gets the IntendedXLoc
	 * @return int
	 */
	public int getIntendedXLoc() 
	{
		return intendedXLoc;
	}

	/**
	 * sets the IntendedXLoc and doesn't allow it to be set off screen
	 * @return void
	 */
	public void setIntendedXLoc(int intendedXLoc) 
	{
		if(intendedXLoc>ViewMain.SCREEN_WIDTH-width)
		{
			this.intendedXLoc = ViewMain.SCREEN_WIDTH-width;
		}

		else
		{
			this.intendedXLoc = intendedXLoc;
		}

	}

	/**
	 * gets the IntendedYLoc
	 * @return int
	 */
	public int getIntendedYLoc() 
	{
		return intendedYLoc;
	}

	/**
	 * sets the IntendedYLoc and doesn't allow it to be set off screen
	 * @return void
	 */
	public void setIntendedYLoc(int intendedYLoc){

		if((intendedYLoc>ViewMain.SCREEN_HEIGHT-drawHeight) 
				|| (yLoc<ViewMain.SCREEN_HEIGHT - drawHeight && intendedXLoc< ViewMain.SCREEN_WIDTH/2))
		{
			this.intendedYLoc = ViewMain.SCREEN_HEIGHT-drawHeight;
		}

		else
		{
			this.intendedYLoc = intendedYLoc;
		}
	}
	
	/**
	 * This method prevents the player from walking in the river
	 * 
	 * @return void
	 */
	public void bridge(){
		isWalking = true;
		xWalking = true;
		yWalking = true;
		/*
		 * Constraints for bridge and river movement 
		 * Player is not allowed in river
		 */

		if((intendedYLoc<=yLoc+yVel && intendedYLoc>=yLoc-yVel) 
				&& (intendedXLoc<=xLoc + xVel) && intendedXLoc>=xLoc - xVel)
		{
			isWalking = false;
		}

		if(intendedYLoc<=yLoc+yVel && intendedYLoc>=yLoc-yVel)
		{
			yWalking = false;
		}

		if(intendedXLoc<=xLoc+xVel && intendedXLoc>=xLoc-xVel)
		{
			xWalking = false;
		}

		if(intendedYLoc<ViewMain.SCREEN_HEIGHT-height-buffer && xLoc < ViewMain.SCREEN_WIDTH/2)
		{
			yWalking = false;
		}
		if(intendedXLoc < ViewMain.SCREEN_WIDTH/2)
		{
			if(yLoc<ViewMain.SCREEN_HEIGHT-height-buffer && xLoc == ViewMain.SCREEN_WIDTH/2)
			{
				xWalking = false;
			}

		}

	}

	/**
	 * gets the isWalking
	 * @return boolean
	 */
	public boolean isWalking() 
	{
		return isWalking;
	}


	/**
	 * sets the isWalking
	 * @param isWalking
	 * @return void
	 */
	public void setWalking(boolean isWalking)
	{
		this.isWalking = isWalking;
	}

	/**
	 * gets the xWalking
	 * @return boolean
	 */
	public boolean isxWalking() 
	{
		return xWalking;
	}


	/**
	 * sets the xWalking
	 * @param xWalking
	 * @return void
	 */
	public void setxWalking(boolean xWalking) 
	{
		this.xWalking = xWalking;
	}

	/**
	 * gets the yWalking
	 * @return boolean
	 */
	public boolean isyWalking()
	{
		return yWalking;
	}

	/**
	 * sets the yWalking
	 * @param yWalking
	 * @return void
	 */
	public void setyWalking(boolean yWalking) 
	{
		this.yWalking = yWalking;
	}





}
