package characters;

import misc.TypeOfCharacter;

public class testCharacter extends CharacterMain{
	
	public testCharacter(int x, int y, TypeOfCharacter charType){
		this.xLoc = x;
		this.yLoc = y;
		this.charType = charType;
	}
	
	public void moveUp(){
		setyLoc(getyLoc() - 4);
	}
	public void moveDown(){
		setyLoc(getyLoc() + 4);
	}
	public void moveLeft(){
		setxLoc(getxLoc() - 4);
	}
	public void moveRight(){
		setxLoc(getxLoc() + 4);
	}
	
	public void resetPos(int x, int y){
		setyLoc(y);
		setxLoc(x);
	}
	
	public void changeColor(){
		switch (charType){
			case TESTRED:
				charType = TypeOfCharacter.TESTBLUE;
				break;
			case TESTBLUE:
				charType = TypeOfCharacter.TESTGREEN;
				break;
			case TESTGREEN:
				charType = TypeOfCharacter.TESTRED;
				break;
			case TESTCOLLISION:
				charType = TypeOfCharacter.TESTCOLLISION;
				break;
			default:
				break;
		}
	}
}
