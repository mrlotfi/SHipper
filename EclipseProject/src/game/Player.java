package game;

import java.util.ArrayList;

import BoardObjects.*;


public class Player {
	private BaseGameObject[][] table;
//	private String name;// in vase faaze ba'd :D
	private ArrayList<Ship> ships;
	private int index;// 0 ya 1
	private int width,height;
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
		if(table[x][y] == null)
			table[x][y] = new Mine(x,y, this);
	}
	public void addAntiAircraft(int y) {
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
		int downX,downY;
		downX = x-1;downY = y-1; // age sefr budan az array nazane birun
		if(x == 0)
			downX = x;
		if(y==0)
			downY = y;
		for(int i=downX;i<=x+1;i++) {
			for(int j=downY;j<=y+1;j++) {
				if(player.getBoard()[i][j] == null) 
					continue;
				if(player.getBoard()[i][j].getClass().equals(Ship.class)) {
					Ship s =(Ship) player.getBoard()[i][j];
					if(s.isPartNonDamaged(i, j)) 
						returnVal = returnVal + "team "+toChar()+" detected "+(i+1)+","+(j+1)+"\n";
				}
			}
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
		if(player.table[0][row] != null) {
			if(player.table[0][row].getClass().equals(AntiAircraft.class)) {
				player.table[0][row] = null;
				return "aircraft unsuccessful\n"; // manteqish ine team ro ham chap kone :/
			}
		}
		String out = "";
		for(int i=0;i<width;i++) 
			out = out + attack(player, i , row);
		return out;
	}
}
