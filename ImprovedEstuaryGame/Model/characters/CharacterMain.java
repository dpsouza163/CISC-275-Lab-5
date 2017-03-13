package characters;

import main.ViewMain;

import misc.TypeOfCharacter;

public class CharacterMain {
	protected int xLoc, yLoc, xVel, yVel, width, height, drawWidth, drawHeight;
	protected TypeOfCharacter charType;
	protected int animationCount;
	public boolean startAnimate = false;
	
	
	public CharacterMain(){
		this.animationCount = 0;
		xLoc = 0;
		yLoc = 0;
		xVel = 0;
		yVel = 0;
	}
	public CharacterMain(int x, int y, int xVel, int yVel){
		this.animationCount = 0;
		this.xLoc=x;
		this.yLoc=y;
		this.xVel=xVel;
		this.yVel=yVel;
	}
	
	public void setSizeAndImg(int width , int height, TypeOfCharacter charType){
		this.width = width;
		this.height = height;
		this.drawWidth = (int)(ViewMain.XSCALE * width);
		this.drawHeight = (int)(ViewMain.YSCALE * height);
		this.charType = charType;
	}
	
	public int getAnimationCount() {
		return animationCount;
	}
	public void setAnimationCount(int animationCount) {
		this.animationCount = animationCount;
	}
	public void increaseAnimationCount(){
		animationCount ++;
	}
	public int getDrawWidth() {
		return drawWidth;
	}
	public void setDrawWidth(int drawWidth) {
		this.drawWidth = drawWidth;
	}
	public int getDrawHeight() {
		return drawHeight;
	}
	public void setDrawHeight(int drawHeight) {
		this.drawHeight = drawHeight;
	}
	public TypeOfCharacter getCharType() {
		return charType;
	}

	public void setCharType(TypeOfCharacter charType) {
		this.charType = charType;
	}

	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public int getxVel() {
		return xVel;
	}

	public void setxVel(int xVel) {
		this.xVel = xVel;
	}

	public int getyVel() {
		return yVel;
	}

	public void setyVel(int yVel) {
		this.yVel = yVel;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	private void move(){
		
	}
	
	public void tutorialClick(){
		
	}
	public boolean collisionDetect(CharacterMain c){
		if (((xLoc < c.getxLoc() + c.getWidth()) && (xLoc + width > c.getxLoc()) &&
				   (yLoc < c.getyLoc() + c.getHeight()) && (yLoc + height > c.getyLoc()))){
			
					return true; 
			}
				
				else{
					
					return false;
				}
	}
	
	

	
	
}
