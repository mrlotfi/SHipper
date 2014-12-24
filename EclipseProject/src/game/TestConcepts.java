package game;

import BoardObjects.Mine;
import BoardObjects.Ship;

/**
 * 
 * @author M-L-N
 * JUnit balad nistam bekhoda :(
 */
public class TestConcepts {
	public static void main(String args[]) {
		Player p1 = new Player(10,10,0);
		Player p2 = new Player(10,10,1);
		Ship a = new Ship('V',1,3,4,p1);
		Ship b = new Ship('V',0,0,1,p2);
		p1.addShip(a);
		
		/*
		p2.addShip(b);
		p1.addMine(0, 0);
		p1.addMine(2, 0);
		p2.addAntiAircraft(2);
		System.out.print(p2.attack(p1, 2, 0));
		//
		System.out.print(p2.attack(p1, 0, 0));
		System.out.print(p2.attack(p1, 0, 0));
		*/
		/* in vase attacke mamuli o radar bud
		System.out.print(p2.radar(p1, 1, 4));
		System.out.print(p2.attack(p1, 1, 5));
		System.out.print(p2.attack(p1, 1, 5));
		System.out.print(p2.attack(p1, 1, 4)); */
	}
}
