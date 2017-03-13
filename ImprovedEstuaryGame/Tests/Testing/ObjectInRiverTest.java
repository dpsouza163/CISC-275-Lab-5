package Testing;

import static org.junit.Assert.*;
import org.junit.Test;
import characters.ObjectInRiver;
import misc.TypeOfTrash;

public class ObjectInRiverTest {

	@Test
	public void getTrashTypeTest1() {
		ObjectInRiver o = new ObjectInRiver(-100,-100,0,0,TypeOfTrash.POOP);
		assertEquals(o.getTrashType(), TypeOfTrash.POOP);
	}
	
	@Test
	public void getTrashTypeTest2() {
		ObjectInRiver o = new ObjectInRiver(-100,-100,0,0,TypeOfTrash.PAPER);
		assertEquals(o.getTrashType(), TypeOfTrash.PAPER);
	}
	
	@Test
	public void setTrashTypeTest3() {
		ObjectInRiver o = new ObjectInRiver(-100,-100,0,0,TypeOfTrash.PLASTIC);
		assertEquals(o.getTrashType(), TypeOfTrash.PLASTIC);
	}
	
	@Test
	public void setVelocityTest(){
		ObjectInRiver o = new ObjectInRiver(-100,-100,0,0,TypeOfTrash.PAPER);
		o.setVelocity(0, 1);
		assertEquals(o.getyVel(),1,0);
	}
	
	@Test
	public void setIsTrashTest(){
		ObjectInRiver o = new ObjectInRiver(-100,-100,0,0,TypeOfTrash.PLASTIC);
		assertEquals(o.getIsTrash(),true);
	}
	
	@Test
	public void setIsTrashTest2(){
		ObjectInRiver o = new ObjectInRiver(-100,-100,0,0,TypeOfTrash.POOP);
		assertEquals(o.getIsTrash(),false);
	}
	
	@Test
	public void setScoreTrashTest(){
		ObjectInRiver o = new ObjectInRiver(-100,-100,0,0,TypeOfTrash.POOP);
		assertEquals(o.getScore(),10);
	}
	
	@Test
	public void setScoreTrashTest2(){
		ObjectInRiver o = new ObjectInRiver(-100,-100,0,0,TypeOfTrash.PLASTIC);
		assertEquals(o.getScore(),5);
	}
	
	@Test
	public void moveTest(){
		ObjectInRiver o = new ObjectInRiver(-100,10,0,1,TypeOfTrash.PLASTIC);
		o.move();
		assertEquals(o.getyLoc(),11,0);
	}
	
	@Test
	public void moveTest2(){
		ObjectInRiver o = new ObjectInRiver(-100,10,0,1,TypeOfTrash.POOP);
		o.move();
		assertEquals(o.getyLoc(),10,0);
	}
	
	@Test
	public void isTrashTest(){
		ObjectInRiver o = new ObjectInRiver(-100,10,0,1,TypeOfTrash.PAPER);
		assertEquals(o.getIsTrash(), true);
	}
	
	@Test
	public void isTrashTest2(){
		ObjectInRiver o = new ObjectInRiver(-100,10,0,1,TypeOfTrash.FISH);
		assertEquals(o.getIsTrash(), true);
	}
	
	@Test 
	public void getScoreTest(){
		ObjectInRiver o = new ObjectInRiver(-100,10,0,1,TypeOfTrash.FISH);
		assertEquals(o.getScore(),-5,0);
	}
	
	@Test 
	public void getScoreTest2(){
		ObjectInRiver o = new ObjectInRiver(-100,10,0,1,TypeOfTrash.PAPER);
		assertEquals(o.getScore(),5,0);
	}
	
	@Test
	public void setTrashTypeTest(){
		ObjectInRiver o = new ObjectInRiver();
		o.setTrashType(TypeOfTrash.POOP);
		assertEquals(o.getTrashType(),TypeOfTrash.POOP);
	}
}
