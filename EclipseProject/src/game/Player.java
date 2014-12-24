package game;

import java.util.ArrayList;

import BoardObjects.*;


public class Player {
	private BaseGameObject[][] table;
//	private String name;// in vase faaze ba'd :D
	private ArrayList<Ship> ships;
	private int index;// 0 ya 1
	private int width, height;
	public Player(int width, int height,int index) {
		this.table = new BaseGameObject[width][height];
		this.ships = new ArrayList<Ship>();
		this.width = width;
		this.height = height;
		this.index = index;
	}
	public char toChar() {
		if(index == 0)
			return 'a';
		return 'b';
	}
	public void addShip(Ship ship) {// ino avaz mikonim age lazem shod (x,y,polarity , length begire)
		ships.add(ship);
	}
	public void addMine(int x,int y) {
		table[x][y] = new Mine(x,y, this);
	}
	public void addAntiAircraft(int x) {
		table[x][0] = new AntiAircraft(x, 0);
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
			returnVal = returnVal + hisShip.radar(x, y, this);
		}
		return returnVal;
	}
	
	
	/**
	 * @param player playeri ke behesh attack mishe
	 * @param x mokhtasat
	 * @param y mokhtasat
	 * @return age keshti zad 2 mide.age min zad 1 mide. age  hichi nazad sefr mide
	 */ 
	public String attack(Player player, int x, int y) {
		BaseGameObject o  = player.table[x][y];
		if(o == null)
			return "";
		if(o.getClass().equals(Ship.class)) {
			Ship a = (Ship) o;
			if (a.damagePart(x, y))
				return "team "+toChar()+" explode "+ (x+1) + "," + (y+1) +"\n";
			return "";
		}
		if(o.getClass().equals(Mine.class)) {
			Mine m = (Mine) o;
			String out = m.explode(x, y, this);
			player.table[x][y] = null;// dg mini vojud nadare
			return out;
		}
		if(o.getClass().equals(AntiAircraft.class)) {
			player.table[x][y] = null;
			return "team " + player.toChar()  +" anti aircraft row " +  (x+1) + " exploded"+ "\n" ;
		}
		return "";
	} 
	/**
	 * 
	 * @param player playeri ke behesh attack mishe
	 * @param x mokhtasat
	 * @param y mokhtasat
	 * @return age anti aircraft nakhord ye reshte mide ke har charesh shabihe khorujie attacke ma'mulie
	 */ 
	public String aircraftAttack(Player player, int row) {
		if(player.table[row][0].getClass().equals(AntiAircraft.class)) {
			player.table[row][0] = null;
			return "aircraft unsuccessful\n"; // manteqish ine team ro ham chap kone :/
		}
		String out = "";
		for(int i=0;i<height;i++) 
			out = out + attack(player, row , i);
		return out;
	}
}
