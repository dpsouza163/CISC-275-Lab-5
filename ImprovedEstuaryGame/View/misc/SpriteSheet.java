package misc;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import main.Controller;


public class SpriteSheet implements Iterable<BufferedImage>{
	
	private ArrayList<BufferedImage> sheet;
	public Iterator it;
	

	public int maxFrames;
	private int currIndex;
	
	/**
	 * Constructor for the SpriteSheet
	 * @param maxFrames The amount of images in the sprite sheet
	 */
	public SpriteSheet(int maxFrames){
		sheet = new ArrayList<BufferedImage>();
		this.maxFrames = maxFrames;
		it = this.iterator();
	}
	
	/**
	 * Gets the current index of the sprite sheet
	 * @return The current index
	 */
	public int getCurrIndex() {
		return currIndex;
	}

	/**
	 * Sets the current index of the sprite sheet
	 * @param currIndex The current index
	 */
	public void setCurrIndex(int currIndex) {
		this.currIndex = currIndex;
	}
	
	/**
	 * Gets the current sheet, an arrayList of images, of the sprite sheet
	 * @return The sheet of the sprite sheet
	 */
	public ArrayList<BufferedImage> getSheet() {
		return sheet;
	}
	
	/**
	 * Sets the current sheet, an arrayList of images, of the sprite sheet
	 * @param The ArrayList of images of the sprite sheet
	 */
	public void setSheet(ArrayList<BufferedImage> sheet) {
		this.sheet = sheet;
	}
	
	public Iterator<BufferedImage> iterator() {
		return new SpriteIterator();
	}
	
	/**
	 * Gets the current image according to the current index and max frames
	 * @return the appropriate image of the array list of images
	 */
	public BufferedImage currentImg(){
		return sheet.get(currIndex % maxFrames);
	}
	
	//Custom iterator
	private class SpriteIterator implements Iterator<BufferedImage> {
		

		
		public SpriteIterator(){
		}
		
		
		public boolean hasNext() {
			
			return true;
		}
		
		public BufferedImage next() {
			if (Controller.ANIMATION_COUNT % 5 == 0 && !Controller.gamePaused){
				currIndex++;
			}
			return sheet.get(currIndex % maxFrames);
		}
		
		
        public void remove() {
            throw new RuntimeException("Error");
        }
	}
}
