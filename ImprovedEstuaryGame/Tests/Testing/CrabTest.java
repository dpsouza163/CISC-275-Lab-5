package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import characters.Crab;

public class CrabTest {

	@Test
	public void grabFoodTest1() {
		Crab c = new Crab(0,0,0,0,false);
		assertEquals(false,c.isHasFood());
		c.grabFood(null);
		assertEquals(true,c.isHasFood());
	}
	
	@Test
	public void setVelocityTest1(){
		Crab c = new Crab(0,0,0,0,false);
		c.setVelocity(5,5);
		assertEquals(5,c.getxVel(),0.01);
		assertEquals(5,c.getyVel(),0.01);
		
	}
	
	@Test
	public void setVelocityTest2(){
		Crab c = new Crab(0,0,0,0,false);
		c.setVelocity(5,5);
		assertEquals(5,c.getxVel(),0.01);
		assertEquals(5,c.getyVel(),0.01);
		
	}
	
	@Test
	public void moveToPointTest1(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(0);
		c.setNearestFoodY(0);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest2(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(1);
		c.setNearestFoodY(0);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest3(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(2);
		c.setNearestFoodY(0);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest4(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(0);
		c.setNearestFoodY(1);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest5(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(1);
		c.setNearestFoodY(1);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest6(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(2);
		c.setNearestFoodY(1);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest7(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(0);
		c.setNearestFoodY(2);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest8(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(1);
		c.setNearestFoodY(2);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest9(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(2);
		c.setNearestFoodY(2);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest10(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(-100);
		c.setNearestFoodY(-100);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest11(){
		Crab c = new Crab(); 
		c.setVelocity(1,1);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(1);
		c.setNearestFoodY(500);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test
	public void moveToPointTest12(){
		Crab c = new Crab(); 
		c.setVelocity(2,2);
		c.setxLoc(1);
		c.setyLoc(1);
		c.setNearestFoodX(-1);
		c.setNearestFoodY(-1);
		c.moveToFood();
		assertEquals(1,c.getxLoc(),0.01);
		assertEquals(1,c.getyLoc(),0.01);
	}
	
	@Test 
	public void moveTheCrabTest1(){
		Crab c = new Crab(0,0,1,1,false);
		c.moveTheCrab();
		assertEquals(1,c.getxLoc());
		assertEquals(1,c.getyLoc());
	}
	
	@Test 
	public void noMoreTapsTest1(){
		Crab c = new Crab();
		c.setNumTaps(1);
		assertEquals(false,c.noMoreTaps());
	}
	
	@Test 
	public void noMoreTapsTest2(){
		Crab c = new Crab();
		c.setNumTaps(0);
		assertEquals(true,c.noMoreTaps());
	}
	
	@Test 
	public void grabFoodTest2(){
		Crab c = new Crab(0,0,0,0,true);
		assertEquals(false,c.isHasFood());
		c.grabFood(null);
		assertEquals(true,c.isHasFood());
		
	}
	
	@Test 
	public void moveOffScreenTest1(){
		Crab c = new Crab(0,0,1,1,false);
		c.moveOffScreen();
		assertEquals(-2,c.getxVel());
		assertEquals(-2,c.getyVel());


	}
	

}