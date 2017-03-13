package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import characters.ObjectInRiver;
import games.TrashGame;
import misc.TypeOfTrash;
import main.ViewMain;
import main.Controller;

public class TrashGameTest {
	Controller c = new Controller();
	ViewMain v = new ViewMain();
	

	@Test
	public void moveObjectsTest(){
		TrashGame t = new TrashGame();
		t.start();
		t.spawnObjectOnScreen();
		t.moveObjectsInRiver();
		assertEquals(t.getObjectsInRiver().get(0).getyLoc(),2);
	}
	
	@Test
	public void removeObjectsTest(){
		TrashGame t = new TrashGame();
		t.start();
		t.spawnObjectOnScreen();
		assertEquals(t.getObjectsInRiver().size(),1);
		t.removeTrash(t.getObjectsInRiver().get(0));
		assertEquals(t.getObjectsInRiver().size(),0);
	}
	
	@Test
	public void removeObjectsTest2(){
		TrashGame t = new TrashGame();
		ObjectInRiver o = new ObjectInRiver(0,0,0,0,TypeOfTrash.POOP);
		t.start();
		t.getObjectsOnLand().add(o);
		assertEquals(t.getObjectsOnLand().size(),1);
		t.removeTrash(t.getObjectsOnLand().get(0));
		assertEquals(t.getObjectsOnLand().size(),0);
	}
	
	@Test
	public void spawnWalkerTest(){
		TrashGame t = new TrashGame();
		t.start();
		t.spawnWalker();
		assertEquals(t.getIsWalkerOnScreen(),true);
	}
	
	@Test
	public void trashOutOfBoundsTest(){
		TrashGame t = new TrashGame();
		ObjectInRiver o = new ObjectInRiver(0,0,0,0,TypeOfTrash.PAPER);
		t.start();
		t.getObjectsInRiver().add(o);
		assertEquals(t.trashOutOfBounds(o),false);
	}
	
	@Test
	public void endTest(){
		TrashGame t = new TrashGame();
		t.start();
		t.end();
	}
	
	@Test
	public void collisionTest(){
		TrashGame t = new TrashGame();
		t.start();
		ObjectInRiver o = new ObjectInRiver(t.player.getxLoc()+1,t.player.getyLoc()+1,0,0,TypeOfTrash.PAPER);
		t.getObjectsInRiver().add(o);
		t.collision();
		assertEquals(t.getObjectsInRiver().size(),0);
	}
	
	@Test
	public void collisionTest2(){
		TrashGame t = new TrashGame();
		t.start();
		ObjectInRiver o = new ObjectInRiver(t.player.getxLoc()+1,t.player.getyLoc()+1,0,0,TypeOfTrash.POOP);
		t.getObjectsOnLand().add(o);
		t.collision();
		assertEquals(t.getObjectsOnLand().size(),0);
	}
	
	@Test
	public void collisionTest3(){
		TrashGame t = new TrashGame();
		t.start();
		ObjectInRiver o = new ObjectInRiver(0,0,0,0,TypeOfTrash.POOP);
		t.getObjectsOnLand().add(o);
		t.collision();
		assertEquals(t.getObjectsOnLand().size(),1);
	}
	
	@Test
	public void collisionTest4(){
		TrashGame t = new TrashGame();
		t.start();
		ObjectInRiver o = new ObjectInRiver(0,ViewMain.SCREEN_HEIGHT+1,0,0,TypeOfTrash.PAPER);
		t.getObjectsInRiver().add(o);
		t.collision();
		assertEquals(t.getObjectsInRiver().size(),0);
	}
	
	@Test
	public void updateTest(){
		TrashGame t = new TrashGame();
		t.start();
		c.init();
		t.spawnWalker();
		t.update();
	}

	@Test
	public void spawnObjectTest(){
		TrashGame t = new TrashGame();
		t.start();
		t.spawnObjectOnScreen();
	}
	
	@Test
	public void spawnObjectTest2(){
		TrashGame t = new TrashGame();
		t.start();
		t.spawnWalker();
		t.spawnObjectOnScreen();
	
	}
	
	@Test
	public void spawnObjectTest3(){
		TrashGame t = new TrashGame();
		t.start();
		t.spawnWalker();
		t.spawnObjectOnScreen();
	
	}
	
	@Test
	public void setHighScoreTest(){
		TrashGame t = new TrashGame();
		t.start();
		t.updateScore(10);
		t.setHighScore();
	
	}
	
	@Test
	public void updateTest2(){
		TrashGame t = new TrashGame();
		t.start();
	}
	
	@Test
	public void gettersTest(){
		TrashGame t = new TrashGame();
		t.start();
		t.getPlayer();
		t.getWalker();
	}

}
