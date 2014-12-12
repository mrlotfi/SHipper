package BoardObjects;

public class Board {
	private int width, height;
	private BaseGameObject[][] table;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		table = new BaseGameObject[height][width]; // yedemun bashe [h][w] hast na [w][h]
	}
	public void set(BaseGameObject object,int x, int y) {
		table[y][x] = object;
	}
	public BaseGameObject get(int x, int y){
		return table[y][x];
	}
}
