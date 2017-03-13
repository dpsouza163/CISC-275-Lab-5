package misc;

import main.ViewMain;

public class Button {
	private int xLoc, yLoc, width, height, drawWidth, drawHeight;
	private TypeOfButton buttonType;
	
	
	public Button(int x, int y){
		xLoc = x;
		yLoc = y;
		
	}
	
	public void setSizeAndImg(int width , int height , TypeOfButton buttonType){
		this.width = width;
		this.height = height;
		this.drawWidth = (int)(ViewMain.XSCALE * width);
		this.drawHeight = (int)(ViewMain.YSCALE * height);
		this.buttonType = buttonType;
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

	public TypeOfButton getButtonType() {
		return buttonType;
	}

	public void setButtonType(TypeOfButton buttonType) {
		this.buttonType = buttonType;
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
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
}
