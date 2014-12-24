package BoardObjects;
/**
 * 
 * @author M-L-N
 *
 */
public class Ship extends BaseGameObject {
	private int length;
	private char polarity;
	public boolean[] parts;
	/**
	 * we want to find which element of this ship is in coordinate (x,y) (if there's a ship present there!)
	 * @param x coordinates
	 * @param y coordinates
	 * @return -1 if this ship has no elemnt in x,y and returns an index if there's and elemnt of (this) ship in that coordiantes
	 */
	private int getIndexOfPart(int x, int y) {
		if(polarity == 'H') {
			for(int i=0;i<this.length;i++) 
				if(this.x + i == x && this.y == y)
					return i;
		}
		if(polarity == 'V') {
			for(int i=0;i<this.length;i++) 
				if(this.y + i == y && this.x == x)
					return i;
		}
		return -1;//nabayd be inja berese. age resid exception mikhore mifahmim
	}
	
	public Ship(char polarity, int x, int y, int length, BaseGameObject cells[][]) {
		super(x,y);
		this.length = length;
		this.polarity = polarity;
		parts = new boolean[length];
		
		if(polarity == 'H') {
			for(int i=0;i<this.length;i++) {
				cells[x+i][y] = this;
				parts[i] = true;
			}
		}
		else {
			for(int i=0;i<this.length;i++) {
				cells[x][y+i] = this;
				parts[i] = true;
			}
		}
	}
	
	public boolean isDestroyed() {
		for(boolean a:parts) 
			if(a == true)
				return false;
		return true;
	}
	
	/**
	 * This method is used in radar
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isPartNonDamaged(int x, int y) {
		int index = getIndexOfPart(x, y);
		return parts[index];
	}
	/**
	 * @param player playeri ke rush radar mizane (a ya b)
	 */
	public String radar(int x, int y, char player) {
		String out = "";
		if(polarity == 'H') {
			for(int i=0;i<this.length;i++) {
				if( Math.abs(this.x+i -x) + Math.abs(this.y-y) <= 1) 
					out = out + "team " + player +" detected " + (x+i)+"," + y +"\n";
			}
		}
		else {
			for(int i=0;i<this.length;i++) {
				if( Math.abs(this.x -x) + Math.abs(this.y+i-y) <= 1) 
					out = out + "team " + player +" detected " + (x+i)+"," + y +"\n";
			}
		}
		return out;
	}
	
	/**
	 *
	 * @param x coordinates
	 * @param y coordinates
	 * @return true if there's a non damaged part of this ship in that part. Neither it returns false
	 */
	public boolean damagePart(int x, int y) {
		int index = getIndexOfPart(x, y);
		if(this.parts[index] == false)
			return false;// Age qablan hamle shode asan manteqi nist dobae hamle kone vali age kard false dige chon chizi nemiterek!
		this.parts[index] = false;
		return true;
	}
}

