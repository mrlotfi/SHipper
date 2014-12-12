package game;

import java.util.ArrayList;

import BoardObjects.Board;
import BoardObjects.Ship;

public class Player {
	private Board table;
	private String name;// in vase faaze ba'd :D
	private ArrayList<Ship> ships;
	
	
	public Player() {
		this.ships = new ArrayList<Ship>();
	}
	public void addShip(Ship ship) {
		ships.add(ship);
	}
	public void addAntiAircraft(int row) {
		
	}
	public Board getBoard() {
		return table;
	}
	
	/**
	 * @author M-L-N
	 * @param player playeri ke qarare rush radar bezane
	 * @param x 
	 * @param y x,y mokhtasate noqte radar zani
	 * @return age doresh kashti "ATTACK NASHODE" bud true mide
	 */
	public boolean radar(Player player, int x, int y) {
		for(int i=x-1;i<=x+1;)
	}
	
	
	/**
	 * @param player playeri ke behesh attack mishe
	 * @param x mokhtasat
	 * @param y mokhtasat
	 * @return age keshti zad true mide
	 */
	public boolean attack(Player player, int x, int y) {
		
	}
	/**
	 * 
	 * @param player playeri ke behesh attack mishe
	 * @param x mokhtasat
	 * @param y mokhtasat
	 * @return age anti aircraft nakhord true mide
	 */
	public boolean aircraftAttack(Player player, int x, int y) {
		
	}
}
