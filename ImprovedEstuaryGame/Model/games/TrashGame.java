package games;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import characters.CharacterMain;
import characters.ObjectInRiver;
import characters.Player;
import characters.Walker;
import main.Controller;
import main.ViewMain;
import misc.TypeOfCharacter;
import misc.TypeOfTrash;

/**
 * @author Team 0
 *
 */
public class TrashGame extends GameMain {
	public Player player;
	private ArrayList<ObjectInRiver> objectsInRiver = new ArrayList<ObjectInRiver>();
	private ArrayList<ObjectInRiver> objectsOnLand;
	private Walker walker;
	public static boolean isPickingUpTrash, isWalkerOnScreen;
	public int difficulty;
/**
 * this method is a generates a tutorial for the user to see and interact with
 * 
 * @return void Nothing is being returned 
 */
	public void tutorial(){

		this.inTutorial = true;

		objectsInRiver.clear();
		Controller.allChars.clear();

		ObjectInRiver fish = new ObjectInRiver(ViewMain.SCREEN_WIDTH / 2 - (int)(212 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0, TypeOfTrash.FISH);
		fish.setSizeAndImg(50, 50, TypeOfCharacter.TRASHFISH);
		objectsInRiver.add(fish);
		ObjectInRiver trash1 = new ObjectInRiver(ViewMain.SCREEN_WIDTH / 2 - (int)(137 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0, TypeOfTrash.PLASTIC);
		trash1.setSizeAndImg(50, 50, TypeOfCharacter.TESTTRASH1);
		objectsInRiver.add(trash1);
		ObjectInRiver trash2 = new ObjectInRiver(ViewMain.SCREEN_WIDTH / 2 - (int)(62 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0, TypeOfTrash.PLASTIC);
		trash2.setSizeAndImg(50, 50, TypeOfCharacter.TESTTRASH2);
		objectsInRiver.add(trash2);
		ObjectInRiver trash3 = new ObjectInRiver(ViewMain.SCREEN_WIDTH / 2 + (int)(12 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0, TypeOfTrash.PLASTIC);
		trash3.setSizeAndImg(50, 50, TypeOfCharacter.TRASH3);
		objectsInRiver.add(trash3);
		ObjectInRiver trash4 = new ObjectInRiver(ViewMain.SCREEN_WIDTH / 2 + (int)(87 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0, TypeOfTrash.PLASTIC);
		trash4.setSizeAndImg(50, 50, TypeOfCharacter.TRASH4);
		objectsInRiver.add(trash4);
		ObjectInRiver trash5 = new ObjectInRiver(ViewMain.SCREEN_WIDTH / 2 + (int)(162 / ViewMain.XSCALE), ViewMain.SCREEN_HEIGHT / 2, 0, 0, TypeOfTrash.PLASTIC);
		trash5.setSizeAndImg(50, 50, TypeOfCharacter.TESTPOOP);
		objectsInRiver.add(trash5);

		Controller.allChars.addAll(objectsInRiver);
	}
	
	/**
	 * this method moves all objects that are trash or have score on the screen
	 * 
	 * @return void Nothing is being returned 
	 */
	public void moveObjectsInRiver()
	{
		for (ObjectInRiver trash : objectsInRiver)
		{
			trash.move();
		}
	}

	/**
	 * this method removes trash from the land array or the river array
	 * 
	 * @param trash
	 * @return void Nothing is being returned 
	 */
	public void removeTrash(ObjectInRiver trash) 
	{
		switch (trash.getTrashType())
		{
		case POOP:
			objectsOnLand.remove(trash);
			break;
		default:
			objectsInRiver.remove(trash);
			break;
		}
	}

	/**
	 * this method spawns a walker and puts it in the field of view
	 * 
	 * @return void Nothing is being returned 
	 */
	public void spawnWalker() 
	{
		// Spawn at bottom of screen and walk up
		walker = new Walker(ViewMain.SCREEN_WIDTH - ViewMain.SCREEN_WIDTH/5, 0, 1, 1);
		
		walker.setSizeAndImg(70, 175, TypeOfCharacter.TESTWALKER);
		
		isWalkerOnScreen = true;
		
		Controller.allChars.add(walker);
		
		walker.move();
	}

	/**
	 * this method randomly generates an object in river that is added to the array
	 * 
	 * @return void Nothing is being returned 
	 */
	public void spawnObjectOnScreen() 
	{
		int chance = (int) (Math.random() * 100);
		
		int x = ((int) (Math.random() * 550))+150;

		// Walker should only poop about 10 percent of the time spawn is called
		
		if (isWalkerOnScreen && chance > 90) 
		{
			objectsOnLand.add(walker.poop());
		} else 
		{
			if (chance >= 80) 
			{
				ObjectInRiver temp = new ObjectInRiver(x, 0 , 1, 2, TypeOfTrash.PAPER);
				
				temp.setSizeAndImg(32, 32, TypeOfCharacter.TESTTRASH1);
				
				objectsInRiver.add(temp);
			} 
			else if (chance >= 60 && chance < 80) 
			{
				ObjectInRiver temp = new ObjectInRiver(x, 0 , 1, 2, TypeOfTrash.PAPER);
				
				temp.setSizeAndImg(32, 32, TypeOfCharacter.TESTTRASH2);
				
				objectsInRiver.add(temp);
			} 
			else if (chance >= 40 && chance < 60) 
			{
				ObjectInRiver temp = new ObjectInRiver(x, 0 , 1, 2, TypeOfTrash.PAPER);
				
				temp.setSizeAndImg(32, 32, TypeOfCharacter.TRASH3);
				
				objectsInRiver.add(temp);
			} 
			else if (chance >= 20 && chance < 40) 
			{
				ObjectInRiver temp = new ObjectInRiver(x, 0 , 1, 2, TypeOfTrash.PAPER);
				
				temp.setSizeAndImg(32, 32, TypeOfCharacter.TRASH4);
				
				objectsInRiver.add(temp);
			} 
			else if (chance >= 0 && chance < 20) 
			{
				ObjectInRiver temp = new ObjectInRiver(x, 0 , 1, 2, TypeOfTrash.PAPER);
				
				temp.setSizeAndImg(32, 32, TypeOfCharacter.TRASHFISH);
				
				objectsInRiver.add(temp);
			} 
		}
	}

	/**
	 * this method checks to see if the trash has fallen out of the field of view
	 * @param trash
	 * @return Boolean
	 */
	public boolean trashOutOfBounds(ObjectInRiver trash)
	{
			return(trash.getyLoc()>=ViewMain.SCREEN_HEIGHT);
	}

	/**
	 * this method gets the river array
	 * 
	 * @return ArrayList
	 */
	public ArrayList<ObjectInRiver> getObjectsInRiver() 
	{
		return objectsInRiver;
	}

	/**
	 * this method gets the land array
	 * 
	 * @return ArrayList
	 */
	public ArrayList<ObjectInRiver> getObjectsOnLand()
	{
		return objectsOnLand;
	}

	/**
	 * this method gets the player
	 * 
	 * @return Player
	 */
	public Player getPlayer() 
	{
		return player;
	}

	/**
	 * this method sets the player array
	 * 
	 * @return void Nothing is being returned 
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}

	/**
	 * this method gets isWalkerOnScreen
	 * 
	 * @return void Nothing is being returned 
	 */
	public boolean getIsWalkerOnScreen() 
	{
		return this.isWalkerOnScreen;
	}

	/**
	 * this method gets the Walker
	 * 
	 * @return Walker
	 */
	public Walker getWalker()
	{
		return this.walker;
	}
	
	
	/**
	 * This method generates animations acording to the orientation of the player
	 * 
	 * @param frames 
	 * @param character
	 * @param count
	 */
	public void animate(int frames, Player character , int count){
		if (count % frames == 0 && !character.startAnimate){
			character.setCharType(TypeOfCharacter.TESTCHAR);
		}
		//System.out.println("Picking Up: " + isPickingUpTrash);
		if (isPickingUpTrash){
			//character.setCharType(TypeOfCharacter.TESTCHAR);
			if(character.getPickingUpCounter() % 5 == 4){
				isPickingUpTrash = false;
			}
		}
		
		
	}


	/**
	 * This method initializes all the variables needed to start the game
	 * 
	 * @return void
	 */
	public void start() 
	{
		difficulty = Controller.difficulty;
		Controller.trashGameScoreThreshold = 100 + (25 * difficulty);
		
		Controller.gameWon = false;
		
		isPickingUpTrash = false;
		
		objectsInRiver = new ArrayList<ObjectInRiver>();
		
		objectsOnLand = new ArrayList<ObjectInRiver>();
		
		setPlayer(new Player(0, (ViewMain.SCREEN_HEIGHT- (int)((192 * ViewMain.YSCALE))), 5, 5));
		
		player.setSizeAndImg(171,192,TypeOfCharacter.TESTCHAR);
		
		player.setIntendedXLoc(0);
		
		player.setIntendedYLoc(ViewMain.SCREEN_HEIGHT- 192);
		
		isWalkerOnScreen = false;
		
		Controller.HIGHSCORE = getHighScore();
	}

	/**
	 * This method is the game engine.
	 * 
	 * @return void
	 */
	public void update() 
	{
		if(player.getCharType().ordinal() >= 30 && player.getCharType().ordinal() <= 33){
			animate(4, player , player.walkingCounter);
		}
		else{
			animate(5, player , player.pickingUpCounter);
		}
		
		int Chance = (int) (Math.random() * 100);

		if (Chance > 97) 
		{
			spawnObjectOnScreen();
		}
		
		else if (Chance < 5 && !(isWalkerOnScreen)) 
		{
			spawnWalker();
		}

		else if (isWalkerOnScreen && 15 < Chance) 
		{
			walker.move();
		}
		
		if(isWalkerOnScreen && walker.getyLoc()<0-walker.getHeight())
		{
			walker = null;
			
			isWalkerOnScreen = false;
		}
		

		moveObjectsInRiver();
		
		collision();
		
		Controller.allChars.clear();
		
		if(isWalkerOnScreen)
		{
			Controller.allChars.add(walker);
		}
		
		Controller.allChars.add(player);
		
		Controller.allChars.addAll(objectsInRiver);
		
		Controller.allChars.addAll(objectsOnLand);
		
		player.move();
	}

	/**
	 * This method is called to end the game and checks a baseline score and checks score 
	 * against highscore
	 * 
	 * @return void
	 */
	public void end() 
	{
		objectsInRiver = null;
		
		objectsOnLand = null;
		
		walker = null;
		
		player = null;
		
		Controller.SCORE = this.score;

		
		if(score>HighScore)
		{
			setHighScore();
		}
		
		if(score > Controller.trashGameScoreThreshold){		
			Controller.trashGameWin = true;		
			Controller.gameWon = true;
		}
		
		score = 0;
	}

	/**
	 * This method checks and deals with any type of collision between the players and the 
	 * objects in river
	 * 
	 * @return void
	 */
	public void collision()
	{
		
		
		ArrayList<ObjectInRiver> temp = new ArrayList<ObjectInRiver>();
		
		for (ObjectInRiver trash : objectsInRiver) 
		{
			if (player.collisionDetect(trash)) 
			{
				player.startAnimate = true;
				isPickingUpTrash = true;
				if (trash.getCharType() != TypeOfCharacter.TRASHFISH){
					updateScore(trash.getScore());
					switch(trash.getCharType()){
					case TESTTRASH1:
						if (Player.facingLeft){
							player.setCharType(TypeOfCharacter.PICKUPLEFTTRASH1);
						}
						else{
							player.setCharType(TypeOfCharacter.PICKUPRIGHTTRASH1);
						}
							
						break;
					case TESTTRASH2:
						if (Player.facingLeft){
							player.setCharType(TypeOfCharacter.PICKUPLEFTTRASH2);
						}
						else{
							player.setCharType(TypeOfCharacter.PICKUPRIGHTTRASH2);
						}
						break;
					case TRASH3:
						if (Player.facingLeft){
							player.setCharType(TypeOfCharacter.PICKUPLEFTTRASH3);
						}
						else{
							player.setCharType(TypeOfCharacter.PICKUPRIGHTTRASH3);
						}
						break;
					case TRASH4:
						if (Player.facingLeft){
							player.setCharType(TypeOfCharacter.PICKUPLEFTTRASH4);
						}
						else{
							player.setCharType(TypeOfCharacter.PICKUPRIGHTTRASH4);
						}
						break;
					default:
				}
					
				}
				else {
					updateScore(-trash.getScore());
					if (Player.facingLeft){
						player.setCharType(TypeOfCharacter.PICKUPLEFTTRASHFISH);
					}
					else{
						player.setCharType(TypeOfCharacter.PICKUPRIGHTTRASHFISH);
					}
				}
				
				temp.add(trash);
				
			} 
			else if (trashOutOfBounds(trash))
			{
				if (trash.getCharType() != TypeOfCharacter.TRASHFISH){
					updateScore(-trash.getScore());

				}
				else {
					updateScore(trash.getScore());

				}
				
				temp.add(trash);
			}
		}
		
		for (ObjectInRiver trash : objectsOnLand)
		{
			
			if (player.collisionDetect(trash)) 
			{
				player.startAnimate = true;
				isPickingUpTrash = true;
				updateScore(trash.getScore());
				if (Player.facingLeft){
					player.setCharType(TypeOfCharacter.PICKUPLEFTPOOP);
				}
				else{
					player.setCharType(TypeOfCharacter.PICKUPRIGHTPOOP);
				}
				temp.add(trash);
			} 		
		}
		for(ObjectInRiver trash: temp)
		{
			if(trash.getIsTrash())
			{
				objectsInRiver.remove(trash);
			}
			else
			{
				objectsOnLand.remove(trash);
			}
		}
		temp = null;
	}

	/**
	 * This method adds score to the overall score
	 * @param score
	 * @return void
	 */
	public void updateScore(int score)
	{
		this.score+=score;
	}
	
	/**
	 * This method is called when a highscore is achieved
	 * 
	 * @return void
	 */
	public void setHighScore()
	{
		try{
			File trashHS = new File("TrashHighScores.txt");
			
			FileOutputStream trashHSStream = new FileOutputStream(trashHS, false);
			
			byte[] hsBytes = Integer.toString(score).getBytes();
			
			trashHSStream.write(hsBytes);
			
			trashHSStream.close();
			} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is called to check current score against the highscore 
	 * 
	 * @return int The high score
	 */
	public int getHighScore()
	{
		int highScore = 0;
		try {
	        File trashHS = new File("TrashHighScores.txt");
	        
	        FileReader trashHSFR = new FileReader(trashHS);
	        
	        BufferedReader trashHSBR = new BufferedReader(trashHSFR);
	        
	        StringBuffer trashHSSB = new StringBuffer();
	        
	        String savedScore;
	        
	        savedScore = trashHSBR.readLine();
	        
	        trashHSSB.append(savedScore);
	        
			highScore = Integer.parseInt(savedScore);
			
	        trashHSFR.close();
	        } 
		catch (IOException e)
		{
	            e.printStackTrace();
	        }
		return highScore;
	}
}
