package Testing;

import characters.*;
import junit.framework.TestCase;


public class BoatTest extends TestCase{
	
	public void testMove(){
		Boat b = new Boat(0,0,10,10);
		Boat bc = new Boat(1, 0);
		assertEquals(b.getxLoc(), 0);
		assertEquals(b.getyLoc(), 0);
		bc.move();
		b.move();
		assertEquals(b.getxLoc(),10);
		assertEquals(b.getyLoc(),10);
	}
	
	public void testAddFish(){
		Boat b = new Boat();
		assertEquals(b.getObjectsInBoat().isEmpty(), true);
		//b.isOverfishing(3, 3);
		b.fishing();
		for(int i = 0; i < 100; i++){
			b.addFishToBoat();
			
		}
		assertEquals(b.getObjectsInBoat().isEmpty(), false);
		b.fishing();
		//b.isOverfishing(3, 3);
		b.move();
	}
	
	public void testSetterAndGetter(){
		Boat b = new Boat(0,0,10,10);
		assertEquals(b.getisFishing(), false);
		assertEquals(b.getIsOverfished(), false);
		assertEquals(b.getCanFish(), true);
		assertEquals(b.getxVel(), 10);
		assertEquals(b.getyVel(), 10);
		b.setFishing(true);
		b.setIsOverfished(true);
		b.setObjectsInBoat(null);
		b.setCanFish(false);
		assertEquals(b.getisFishing(), true);
		assertEquals(b.getIsOverfished(), true);
		assertEquals(b.getObjectsInBoat(), null);
		assertEquals(b.getCanFish(), false);
	}
}
