package Testing;

import junit.framework.TestCase;
import misc.TypeOfFish;

import java.util.ArrayList;

import characters.ObjectInBoat;
import games.*;


public class BoatGameTest extends TestCase{
	
	public void testStartUp(){
		BoatGame b = new BoatGame();
		b.start();
		b.update();
		b.setHighScore();
		b.end();
		}

	
	public void testCreateBoat() {
	
		BoatGame b = new BoatGame();
		assertEquals(b.getBoats().isEmpty(), true);
		b.createBoat();
		assertEquals(b.getBoats().isEmpty(), false);
		b.createBoat();
		b.createBoat();
		b.createBoat();
		b.createBoat();
		b.createBoat();
		assertEquals(b.getBoats().size(), 6);
		b.createBoat();
		assertEquals(b.getBoats().size(), 6);
		
	}
	
	public void testPlaceFish(){
		BoatGame b = new BoatGame();
		b.createBoat();
		assertEquals(b.getBoats().get(0).getObjectsInBoat().isEmpty(), true);
		b.placeFishInBoat();

	}
	
	public void testMoveBoats(){
		BoatGame b = new BoatGame();
		b.createBoat();
		int oldy = b.getBoats().get(0).getyLoc();
		assertEquals(b.getBoats().get(0).getyLoc(), oldy);
		b.moveBoats();
		b.getBoats().get(0).setFishing(true);
		b.moveBoats();

	}
	
	public void testRemoveBoat(){
		BoatGame b = new BoatGame();
		b.createBoat();
		b.removeBoat();
		assertEquals(b.getBoats().isEmpty(), false);
		for(int i = 0; i < 5000; i++){
			b.moveBoats();
		}
		assertEquals(b.getBoats().isEmpty(), false);
		b.removeBoat();
		assertEquals(b.getBoats().isEmpty(), true);
		b.createBoat();
		b.getBoats().get(0).setIsOverfished(true);
		for(int i = 0; i < 5000; i++){
			b.moveBoats();
		}
		b.removeBoat();
	}
	
	public void testCheckOnClick(){
		BoatGame b = new BoatGame();
		b.createBoat();
		assertEquals(b.getBoats().isEmpty(), false);
		b.checkOnClick(50, 50);
		assertEquals(b.getBoats().isEmpty(), false);
		b.checkOnClick(0, b.getBoats().get(0).getyLoc());
		assertEquals(b.getBoats().isEmpty(), false);
		b.getBoats().get(0).setIsOverfished(true);
		b.checkOnClick(0, b.getBoats().get(0).getyLoc());

	}
	
	public void testcheckOverfished(){
		BoatGame b = new BoatGame();
		ArrayList<ObjectInBoat> o = new ArrayList<ObjectInBoat>();
		for(int i =0; i<10; i++){
			o.add(new ObjectInBoat(TypeOfFish.FISH1));
			o.add(new ObjectInBoat(TypeOfFish.FISH2));
			o.add(new ObjectInBoat(TypeOfFish.FISH3));
			o.add(new ObjectInBoat(TypeOfFish.FISH4));
			o.add(new ObjectInBoat(TypeOfFish.CRAB));
		}
		b.createBoat();
		//b.setCrabsAllowed(5);
		//b.setFishAllowed(5);
		b.checkOverfished();
		assertFalse(b.getBoats().get(0).getIsOverfished());
		b.getBoats().get(0).setObjectsInBoat(o);
		b.checkOverfished();
		assertEquals(b.getBoats().get(0).getIsOverfished(), true);
		o.clear();
		for(int i =0; i<4; i++){
			o.add(new ObjectInBoat(TypeOfFish.FISH1));
			o.add(new ObjectInBoat(TypeOfFish.FISH2));
			o.add(new ObjectInBoat(TypeOfFish.FISH3));
			o.add(new ObjectInBoat(TypeOfFish.FISH4));
			o.add(new ObjectInBoat(TypeOfFish.CRAB));
		}
		assertEquals(b.getBoats().get(0).getIsOverfished(), true);
		//b.getFishAllowed();
		//b.getCrabsAllowed();
		b.setBoats(null);
	}
}
