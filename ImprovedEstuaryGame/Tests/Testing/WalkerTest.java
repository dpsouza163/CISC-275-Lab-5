package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import characters.Walker;

public class WalkerTest {
	
	@Test
	public void moveTest(){
		Walker w = new Walker(0,55,1,1);
		w.move();
		assertEquals(w.getyLoc(),56);
	}
	
	@Test
	public void moveTest2(){
		Walker w = new Walker(0,51,1,1);
		w.move();
		assertEquals(w.getyLoc(),52);
	}
	
	@Test
	public void poopTest(){
		Walker w = new Walker(0,55,1,1);
		w.move();
		assertEquals(w.poop().getIsTrash(),false);
	}
	
	@Test
	public void poopTest2(){
		Walker w = new Walker(0,55,1,1);
		w.move();
		assertEquals(w.poop().getyLoc(),56);
	}
	
	@Test
	public void constructorTest(){
		Walker w = new Walker();
	}
	

}
