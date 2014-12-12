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
	
	public Ship(char polarity, int x, int y, int length, Board board) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.polarity = polarity;
		parts = new boolean[length];
		
		if(polarity == 'H') {
			for(int i=0;i<length;i++) {
				board.set(this, x+i, y);
				parts[i] = true;
			}
		}
		else {
			for(int i=0;i<length;i++) {
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
}

