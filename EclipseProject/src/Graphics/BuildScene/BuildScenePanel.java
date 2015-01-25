package Graphics.BuildScene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BoardObjects.*;
/**
 * 
 * @author M-L-N
 *
 */
/*
 * cherta table ? chon tashkise conflict ha rahat tare
 * tedad chi? uno to build scene handle mikonim
 */
public class BuildScenePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1691059120532892737L;
	private int width, height;
	int[][] table;
	int polarityState;
	int state;
	int currentX, currentY;
	private boolean hasConflict;
	ArrayList<int[]> ships;
	ArrayList<int[]> mines;// chon unvar add mine ba x,y bud !
	ArrayList<int[]> aircrafts;
	int remainingMines;
	int remainingAirs;
	int[] remainingShips;
	private SidePanel sidePane;
	public BuildScenePanel(final int width, final int height) {
		polarityState = 0;
		hasConflict = false;
		this.width = width;
		this.height = height;
		table = new int[width][height];
		ships = new ArrayList<int[]>();
		mines = new ArrayList<int[]>();
		aircrafts = new ArrayList<int[]>();
		this.setBounds(10, 10, 540, 540);
		this.setBackground(Color.white);
		//
		remainingMines = 5;
		remainingShips = new int[] {1,1,1,1,1,1,1};
		remainingAirs = 2;
		//
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(state != 0) {
					int i = arg0.getX()/(540/width);
					int j = arg0.getY()/(540/height);
					if(i>=width)
						i = width - 1;
					if(j>= height)
						j = height - 1;
					changeAfterMouseMove(i,j);
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickAction();
			}
		});
	}
	public void clickAction() {
		if( state != 0) {
			if(hasConflict)
				JOptionPane.showMessageDialog(BuildScenePanel.this, "Your Equipment has conflict with others!\n"
						+ "Please retry.");
			else {
				
				for(int x=currentX;x<=currentX+polarityState*(getLength(state)-1);x++) 
					for(int y=currentY;y<=currentY+(1-polarityState)*(getLength(state)-1);y++)
						table[x][y] = state;
				sidePane.repaint();
				if(state<8) {
					ships.add(new int[]{currentX,currentY,getLength(state),polarityState});
					remainingShips[state-1] = 0;
				}
				else if(state ==8) {
					mines.add(new int[]{currentX,currentY});
					remainingMines--;
				}
				else {
					aircrafts.add(new int[]{currentY});
					remainingAirs--;
				}
				state=0;
				
			}
		}
	}
	public void changeAfterMouseMove(int i, int j) {
		if(i+polarityState*getLength(state) > width)
			i = width - 1 - polarityState*(getLength(state)-1);
		if(j+(1-polarityState)*getLength(state) > height)
			j = height - 1 -(1-polarityState)*(getLength(state)-1);
		currentX = i;
		currentY = j;
		hasConflict = false;
		if(state == 9)
			currentX = 0 ;
		if(table[0][currentY] != 0 && currentX == 0)
			hasConflict = true;
	outer:	for(int x=i;x<=currentX+polarityState*(getLength(state)-1);x++) 
			for(int y=j;y<=currentY+(1-polarityState)*(getLength(state)-1);y++)
				if(table[x][y] != 0) {
					hasConflict = true;
					break outer;
				}
		repaint();				
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++)
				g.drawRect(i*540/width, j*540/height, 540/width, 540/height);//base table
		for(int i=0;i<width;i++)
			for(int j=0;j<height;j++) {
				if(getColor(table[i][j]) != null){
				g.setColor(getColor(table[i][j]));
				g.fillRect(i*540/width+1, j*540/height+1, 540/width-1, 540/height-1);//past entries
				}
			}
		g.setColor(getColor(state));
		if(hasConflict)
			g.setColor(Color.red);
		for(int x=currentX;x<=currentX+polarityState*(getLength(state)-1);x++) 
			for(int y=currentY;y<=currentY+(1-polarityState)*(getLength(state)-1);y++)
				g.fillRect(x*540/width+1, y*540/height+1, 540/width-1, 540/height-1);
		g.setColor(Color.black);
		g.drawRect(0, 0, 539, 539);
	}
	public void changePolarity() {
		polarityState = 1 - polarityState;
	}
	public boolean allShipsUsed() {
		boolean temp = true;
		for(int a:remainingShips)
			if(a != 0) {
				temp = false;
				break;
			}
		return temp;
	}
	public boolean allAirsUsed() {
		return remainingAirs==0;
	}
	public boolean allMinesUsed() {
		return remainingMines==0;
	}
	public void setSidePane(SidePanel side) {
		this.sidePane = side;
	}
	static Color getColor(int i) {
		switch(i) {
		case 0:
			return null;
		case 1:
			return Color.gray;
		case 2:
			return Color.cyan;
		case 3:
			return Color.blue;
		case 4:
			return Color.magenta;
		case 5:
			return Color.pink;
		case 6:
			return Color.ORANGE;
		case 7:
			return Color.yellow;
		case 8:
			return Color.black;
		case 9:
			return Color.green;
		}
		return null;
	}
	private static int getLength(int i) {
		switch(i) {
		case 0:
			return 0;
		case 1:
			return 4;
		case 2:
			return 3;
		case 3:
			return 3;
		case 4:
			return 2;
		case 5:
			return 2;
		case 6:
			return 1;
		case 7:
			return 1;
		case 8:
			return 1;
		case 9:
			return 1;
		}
		return 0;
	}
}
