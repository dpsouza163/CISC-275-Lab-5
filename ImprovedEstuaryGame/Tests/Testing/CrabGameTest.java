package Testing;

import static org.junit.Assert.*;
import misc.TypeOfCharacter;

import org.junit.Test;

import games.CrabGame;

public class CrabGameTest {
	
	@Test
	public void setNumFoodTest1() {
		CrabGame c = new CrabGame();
		int x = 5;
		c.setNumFood(x);
		assertEquals(x,c.getFoodSize());	
	}
	
	@Test
	public void setNumFoodTest2() {
		CrabGame c = new CrabGame();
		int x = 0;
		c.setNumFood(x);
		assertEquals(x,c.getFoodSize());	
	}
	
	@Test 
	public void distanceFormulaTest1(){
		CrabGame c = new CrabGame(); 
		assertEquals(1.414,c.distanceFormula(0,0,1,1),0.001);
	}
	
	@Test 
	public void distanceFormulaTest2(){
		CrabGame c = new CrabGame(); 
		assertEquals(5,c.distanceFormula(-3,-4,0,0),0.001);
	}
	
	@Test 
	public void findNearestFoodTest1(){
		CrabGame c = new CrabGame();
		c.addCrab(2,2);
		c.addFood(0,2);
		c.addFood(3,2);
		c.addFood(2,4);
		c.addFood(5,7);
		assertEquals(3,c.findNearestFood(c.findCrab(0)).getxLoc(),0.001);
		assertEquals(2,c.findNearestFood(c.findCrab(0)).getyLoc(),0.001);
	}
	
	@Test 
	public void dropFoodTest1(){
		CrabGame c = new CrabGame();
		c.addCrab(2,3);
		c.addFood(CrabGame.dropFood(c.findCrab(0)));
		assertEquals(2,c.findFood(0).getxLoc());
		assertEquals(3,c.findFood(0).getyLoc());
	}
	
	@Test 
	public void changeCrabDirectionTest1(){
		CrabGame c = new CrabGame();
		c.addCrab(2,2);
		c.addFood(2,3);
		c.findNearestFood(c.findCrab(0));
		System.out.println(c.findCrab(0).getyLoc()-c.findFood(0).getyLoc());
		c.changeCrabDirection(c.findCrab(0));
		assertEquals(45,c.findCrab(0).getDirection(),0.01);
	}
	
	@Test 
	public void spawnCrabTest1(){
		CrabGame c = new CrabGame();
		c.spawnCrab();
		assertEquals(1,c.getCrabSize());
	}
	
	@Test 
	public void spawnFoodTest(){
		CrabGame c = new CrabGame();
		c.spawnFood();
		assertEquals(1,c.getFoodSize());
	}
	
	@Test 
	public void findAngleTest1(){
		CrabGame c = new CrabGame();
		assertEquals(4,c.findAngle(0,0,1,1));
		
	}
	
	@Test 
	public void startTest(){
		CrabGame c = new CrabGame();
		c.start();
	}
	
	@Test 
	public void checkCrabsTest1(){
		CrabGame c = new CrabGame();
		c.addCrab(0,0);
		assertEquals(c.crab.size(), 1);
	}
	
	@Test
	public void highScoreTest(){
		CrabGame c = new CrabGame();
		c.setScore(100);
		c.setHighScore();
		assertEquals(c.getHighScore(), 100);
	}
	
	@Test
	public void miscTest(){
		CrabGame c = new CrabGame();
		c.end();
		assertEquals(c.food.size(), 0);
		assertEquals(c.crab.size(), 0);
		
		c.update();
		c.addCrab(-10000, 0, 0, 0, true);
		c.crab.get(0).isMovingOffScreen = true;
		c.crab.get(0).setHasFood(true);
		c.addCrab(10000, 0, 0, 0, false);
		c.crab.get(1).isMovingOffScreen = true;
		c.crab.get(1).setHasFood(true);
		c.addCrab(10000, 0, 0, 0, false);
		c.crab.get(2).isMovingOffScreen = true;
		c.crab.get(2).setHasFood(false);
		c.addCrab(0, -10000, 0, 0, true);
		c.crab.get(3).isMovingOffScreen = true;
		c.addCrab(0, 10000, 0, 0, true);
		c.crab.get(4).isMovingOffScreen = false;
		c.addCrab(0, 10000, 0, 0, true);
		c.crab.get(5).isMovingOffScreen = true;
		
		c.deleteOffScreenCrabs();
		c.deleteOffScreenCrabs();
		c.deleteOffScreenCrabs();
		
		assertEquals(c.crab.size(), 1);

	}
	
	@Test
	public void checkCrabTapsTest(){
		CrabGame c = new CrabGame();
		c.addCrab(5, 5, 0, 0, true);
		c.crab.get(0).setNumTaps(0);
		c.crab.get(0).hasAlreadyPickedUpFood = true;
		c.addCrab(5, 5, 0, 0, true);
		c.crab.get(1).setNumTaps(0);
		c.crab.get(1).hasAlreadyPickedUpFood = false;
		c.addCrab(5, 5, 0, 0, true);
		c.crab.get(2).setNumTaps(1);
		c.crab.get(2).hasAlreadyPickedUpFood = true;
		c.checkCrabTaps();
	}
	
	@Test
	public void moveCrabsTest(){
		CrabGame c = new CrabGame();
		c.addCrab(5, 5, 0, 0, true);
		c.crab.get(0).setNumTaps(1);
		c.crab.get(0).setHasFood(false);
		c.addCrab(5, 5, 0, 0, true);
		c.crab.get(1).setNumTaps(1);
		c.crab.get(1).setHasFood(true);
		c.moveCrabs();
	}
	
	@Test
	public void checkCrabsTest(){
		CrabGame c = new CrabGame();
		c.addCrab(5, 5, 1, 1, true);
		c.addFood(5, 5);
		c.crab.get(0).setSizeAndImg(1, 1, TypeOfCharacter.TESTBADCRAB);
		c.food.get(0).setSizeAndImg(1, 1, TypeOfCharacter.TESTCRABFOOD);
		c.addCrab(5, 5, 1, 1, true);
		c.addFood(5, 5);
		c.crab.get(1).setSizeAndImg(1, 1, TypeOfCharacter.TESTBADCRAB);
		c.food.get(1).setSizeAndImg(1, 1, TypeOfCharacter.TESTCRABFOOD);
		c.crab.get(1).setHasFood(true);
		c.crab.get(1).isMovingOffScreen = true;
		c.addCrab(5, 5, 1, 1, true);
		c.addFood(5, 5);
		c.crab.get(2).setSizeAndImg(1, 1, TypeOfCharacter.TESTBADCRAB);
		c.food.get(2).setSizeAndImg(1, 1, TypeOfCharacter.TESTCRABFOOD);
		c.crab.get(2).setHasFood(false);
		c.crab.get(2).isMovingOffScreen = true;
		c.addCrab(5, 5, 1, 1, true);
		c.addFood(5, 5);
		c.crab.get(3).setSizeAndImg(1, 1, TypeOfCharacter.TESTBADCRAB);
		c.food.get(3).setSizeAndImg(1, 1, TypeOfCharacter.TESTCRABFOOD);
		
		c.crab.get(3).setMitten(false);

		c.addCrab(5, 5, 1, 1, true);
		c.addFood(5, 5);
		c.checkCrabs();
	}
}