package Graphics.GameScene;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;









import game.Player;

import javax.swing.JFrame;

import Controllers.SingleMachineController;


@SuppressWarnings("serial")
public class SingleMachinceScene extends JFrame {
	private SingleMachineController controller;
	private GameTablePanel panel1,panel2;
	public SingleMachinceScene() {
		setSize(1300,700);
		setVisible(true);
		
		final Player player = new Player(15,10,0);
		// in felan bara teste
		panel1 = new GameTablePanel(player);
		panel1.setBounds(20, 20, GameTablePanel.SIZE, GameTablePanel.SIZE);
		panel1.setVisible(true);
		
		panel1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				int x = arg0.getX() / (GameTablePanel.SIZE/player.getWidth());
				int y= arg0.getY() / (GameTablePanel.SIZE/player.getHeight());
				panel1.setActiveCell(x, y);
				repaintAll();
			}
		});
		panel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch(arg0.getButton()) {
				case MouseEvent.BUTTON1:
					controller.addAttackStatement(panel1.getActiveX(), panel1.getActiveY(),
							0, 1);
					break;
				case MouseEvent.BUTTON2:
					controller.addAircraftStatement(panel1.getActiveY(), 0, 1);
					break;
				case MouseEvent.BUTTON3:
					controller.addRadarStatement(panel1.getActiveX(), panel1.getActiveY(), 
							0, 1);
					break;
					
				}
				
			}
		});
		getContentPane().add(panel1);
		
		
		Player p2 = new Player(10,10,0);
		panel2 = new GameTablePanel(p2);
		panel2.setBounds(20+GameTablePanel.SIZE+100,20,GameTablePanel.SIZE, GameTablePanel.SIZE);
		panel2.setVisible(true);
		getContentPane().add(panel2);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch(arg0.getKeyCode()) {
				case KeyEvent.VK_UP:
					panel2.setActiveCell(panel2.getActiveX(), panel2.getActiveY()-1);
					break;
				case KeyEvent.VK_LEFT:
					panel2.setActiveCell(panel2.getActiveX()-1, panel2.getActiveY());
					break;
				case KeyEvent.VK_RIGHT:
					panel2.setActiveCell(panel2.getActiveX()+1, panel2.getActiveY());
					break;	
				case KeyEvent.VK_DOWN:
					panel2.setActiveCell(panel2.getActiveX(), panel2.getActiveY()+1);
					break;	
				case KeyEvent.VK_A:
					controller.addAttackStatement(panel2.getActiveX(), panel2.getActiveY(),
							1, 0);
					break;
				case KeyEvent.VK_Q:
					controller.addAircraftStatement(panel2.getActiveY(), 1, 0);
					break;
				case KeyEvent.VK_R:
					controller.addRadarStatement(panel2.getActiveX(), panel2.getActiveY(), 
							1, 0);
					break;
				}
				repaintAll();
			}
		});
	}
	
	public void setController(SingleMachineController c) {
		controller = c;
	}
	
	public void repaintAll() {
		panel1.repaint();
		panel2.repaint();
	}
}
