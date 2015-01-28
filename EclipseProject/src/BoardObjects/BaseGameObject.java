package BoardObjects;

import java.io.Serializable;

/**
 * 
 * @author M-L-N
 *
 */
public class BaseGameObject implements Serializable{
	protected int x,y;
	public BaseGameObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//Maskhare be nazar miad vali kari nemsihe kard.
	// maskhare nist seriallizablesh bedard mikhore
}
