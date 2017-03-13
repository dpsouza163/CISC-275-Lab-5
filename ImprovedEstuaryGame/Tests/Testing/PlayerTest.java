package Testing;

import static org.junit.Assert.*;
import org.junit.Test;

import characters.Player;
import main.Controller;
import main.ViewMain;

public class PlayerTest {

	Controller c = new Controller();
	ViewMain v = new ViewMain();
	@Test
	public void moveTest() {
		Player p = new Player(0, 0, 1, 1);
		p.setIntendedXLoc(10);
		p.setIntendedYLoc(20);
		p.move();
		assertEquals(p.getxLoc(),1,0);
	}
	
	@Test
	public void moveTest2() {
		Player p = new Player(0, 0, 1, 1);
		p.setIntendedXLoc(100);
		p.setIntendedYLoc(20);
		p.move();
		assertEquals(p.getyLoc(),1,0);
	}
	
	@Test
	public void moveTest3(){
		Player p = new Player(0, 0, 1, 1);
		p.setIntendedXLoc(100);
		p.setIntendedYLoc(20);
		p.move();
		assertEquals(p.getyLoc(),1,0);
		p.setIntendedYLoc(30);
		p.move();
		assertEquals(p.getyLoc(),2,0);
	}
	
	@Test
	public void moveTest4(){
		Player p = new Player(0, 0, 1, 1);
		p.setIntendedXLoc(100);
		p.setIntendedYLoc(20);
		p.move();
		assertEquals(p.getxLoc(),1,0);
		p.setIntendedXLoc(20);
		p.move();
		assertEquals(p.getxLoc(),2,0);
	}
	
	@Test
	public void bridgeTest(){
		Player p = new Player(0,0,1,1);
		p.setIntendedXLoc(2);
		p.setIntendedYLoc(2);
		p.bridge();
		assertEquals(p.getxLoc(), 0);
	}
	
	@Test
	public void bridgeTest2(){
		Player p = new Player(0,0,1,1);
		p.setIntendedXLoc(2);
		p.setIntendedYLoc(2);
		p.bridge();
		assertEquals(p.isWalking(), true);
	}
	
	@Test
	public void isWalkingTest(){
		Player p = new Player();
		p.setWalking(true);
		assertEquals(p.isWalking(),true);
	}
	
	@Test
	public void isxWalkingTest(){
		Player p = new Player();
		p.setxWalking(true);
		assertEquals(p.isxWalking(),true);
	}
	
	@Test
	public void isyWalkingTest(){
		Player p = new Player();
		p.setyWalking(true);
		assertEquals(p.isyWalking(),true);
	}

	@Test
	public void bridgeTest3(){
		Player p = new Player(0,0,1,1);
		p.setIntendedXLoc(30);
		p.setIntendedYLoc(2);
		p.bridge();
		p.move();
		assertEquals(p.isWalking(), true);
	}
	
	@Test
	public void setIndendedXLocTest(){
		Player p = new Player(0,0,1,1);
		p.setIntendedXLoc(ViewMain.SCREEN_WIDTH);
		assertEquals(p.getIntendedXLoc(), ViewMain.SCREEN_WIDTH-p.getWidth());
	}
	
	@Test
	public void setIndendedYLocTest(){
		Player p = new Player(0,0,1,1);
		p.setIntendedYLoc(ViewMain.SCREEN_HEIGHT);
		assertEquals(p.getIntendedYLoc(), ViewMain.SCREEN_HEIGHT-p.getHeight());
	}
	
	@Test
	public void moveTest1(){
		Player p = new Player(0,ViewMain.SCREEN_HEIGHT- 192,1,1);
		p.setIntendedXLoc(10);
		p.setIntendedYLoc(100);
		p.move();
		assertEquals(p.getxLoc(), 1);
		
	}
}
