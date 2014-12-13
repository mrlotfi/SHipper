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
		return -1;//nabayd be inja berese
	}
	
	public Ship(char polarity, int x, int y, int length, Board board) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.polarity = polarity;
		parts = new boolean[length];
		
		if(polarity == 'H') {
			for(int i=0;i<this.length;i++) {
				board.set(this, x+i, y);
				parts[i] = true;
			}
		}
		else {
			for(int i=0;i<this.length;i++) {
				board.set(this, x, y+i);
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
	
	public boolean isPartNonDamaged(int x, int y) {
		int index = getIndexOfPart(x, y);
		return parts[index];
	}
	
	public boolean damagePart(int x, int y) {
		int index = getIndexOfPart(x, y);
		if(this.parts[index] == false)
			return false;// Age qablan hamle shode asan manteqi nist dobae hamle kone vali age kard false dige chon chizi nemiterek!
		this.parts[index] = false;
		return true;
	}
}

