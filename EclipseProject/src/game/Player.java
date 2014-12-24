package game;

import java.util.ArrayList;

import BoardObjects.*;


public class Player {
	private BaseGameObject[][] table;
//	private String name;// in vase faaze ba'd :D
	private ArrayList<Ship> ships;
	private int index;// 0 ya 1
	public Player(int width, int height,int index) {
		this.table = new BaseGameObject[width][height];
		for(int i=0;i<width;i++) 
			for(int j=0;j<height;j++) 
				table[i][j] = new BaseGameObject(i,j);
		this.ships = new ArrayList<Ship>();
		this.index = index;
	}
	public void addShip(Ship ship) {// ino avaz mikonim age lazem shod (x,y,polarity , length begire)
		ships.add(ship);
	}
	public void setAntiAircraft(int y) {
		table[0][y] = new AntiAircraft(0, y);
	}
	public BaseGameObject[][] getBoard() {
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
	 * @return Ye reshte ke har khune kashtie salem ke doresh hast ro ba x,y (har kodum to ye khat) neshun mide
	 */
	public String radar(Player player, int x, int y) {
		String returnVal = "";
		for(Ship hisShip : player.ships) {
			returnVal = returnVal + hisShip.radar(x, y);
		}
		return returnVal;
	}
	
	
	/**
	 * @param player playeri ke behesh attack mishe
	 * @param x mokhtasat
	 * @param y mokhtasat
	 * @return age keshti zad 2 mide.age min zad 1 mide. age  hichi nazad sefr mide
	 */ /*
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
	 */ /*
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
	}*/
}
