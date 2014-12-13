package game;

import java.util.ArrayList;

import BoardObjects.*;


public class Player {
	private Board table;
//	private String name;// in vase faaze ba'd :D
	private ArrayList<Ship> ships;
	//
	private int[] antiAircrafts;
	private int index;
	public Player(int width, int height,int index) {
		this.table = new Board(width, height);
		this.ships = new ArrayList<Ship>();
		this.antiAircrafts = new int[2];
		antiAircrafts[0] = antiAircrafts[1] = -1;//avvalesh invalid bashe
		this.index = index;
	}
	public void addShip(Ship ship) {
		ships.add(ship);
	}
	
	
	public void setAntiAircraft(int row0) {
		antiAircrafts[0] = row0;
	}
	public void setAntiAircraft(int row0,int row1) {
		antiAircrafts[0] = row0;
		antiAircrafts[1] = row1;
	}
	public Board getBoard() {
		return table;
	}
	public ArrayList<Ship> getShips() {
		return ships;
	}
	public int getIndex() {
		return index;
	}
	/**
	 * @author M-L-N
	 * @param player playeri ke qarare rush radar bezane
	 * @param x 
	 * @param y x,y mokhtasate noqte radar zani
	 * @return age doresh kashti "ATTACK NASHODE" bud true mide
	 */
	public boolean radar(Player player, int x, int y) {
		for(int i=x-1;i<=x+1;i++)
			for(int j=y-1;j<=y+1;j++)
				if(player.getBoard().get(x, y).getClass().equals(Ship.class))
				{
					Ship a = (Ship) player.getBoard().get(x, y);
					if(a.isPartNonDamaged(x, y))
						return true;
				}
		return false;
	}
	
	
	/**
	 * @param player playeri ke behesh attack mishe
	 * @param x mokhtasat
	 * @param y mokhtasat
	 * @return age keshti zad 2 mide.age min zad 1 mide. age  hichi nazad sefr mide
	 */
	public char attack(Player player, int x, int y) {
		BaseGameObject o  = player.table.get(x, y);
		if(o.getClass().equals(Ship.class)) {
			Ship a = (Ship) o;
			if (a.damagePart(x, y))
				return '2';
			return '0';
		}
		if(o.getClass().equals(Mine.class)) {
			Mine m = (Mine) o;
			m.explode(this);
			return '1';
		}
		return '0';
	}
	/**
	 * 
	 * @param player playeri ke behesh attack mishe
	 * @param x mokhtasat
	 * @param y mokhtasat
	 * @return age anti aircraft nakhord ye reshte mide ke har charesh shabihe khorujie attacke ma'mulie
	 */
	public char[] aircraftAttack(Player player, int row) {
		if(this.antiAircrafts[0] == row) {
			this.antiAircrafts[0] = -1;
			return null;
		}
		if(this.antiAircrafts[1] == row) {
			this.antiAircrafts[1] = -1;
			return null;
		}
		char[] c = new char[player.table.getWidth()];
		for(int i=0;i<table.getWidth();i++) {
			c[i] = attack(player, i, row);
		}
		return c;
	}
}
