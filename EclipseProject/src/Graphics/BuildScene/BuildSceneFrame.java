package Graphics.BuildScene;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Player;

import javax.swing.JFrame;

import jdk.nashorn.internal.scripts.JO;

public class BuildSceneFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1630011047229464942L;
	public static void main(String args[]) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {}
		BuildSceneFrame a = new BuildSceneFrame(null, 18, 10);
		a.setVisible(true);
	}
	//
	Player owner;
	private BuildScenePanel panel;
	private JButton[] buildButtons;
	private JButton clearButton;
	private JButton doneButton;
	public BuildSceneFrame(Player owner, final int width, final int height) {
		this.owner = owner;
		this.setBounds(0, 0, 1080, 600);
		this.setAlwaysOnTop(true);
		this.setTitle("Map Builder");
		this.setIconImage(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(null);
		this.setFocusable(true);
		//
		panel = new BuildScenePanel(width, height);
		this.add(panel);
		panel.repaint();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				panel.polarityState = 1 - panel.polarityState;
				panel.changeAfterMouseMove(panel.currentX,panel.currentY);
				panel.repaint();
			}
		});
		//
		clearButton = new JButton("Clear");
		clearButton.setBounds(600, 360+80, 200, 50);
		getContentPane().add(clearButton);
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int sure = JOptionPane.showConfirmDialog(BuildSceneFrame.this, "Are you sure?","Ask", 2);
				if(sure == 0) {
					BuildSceneFrame.this.remove(panel);
					panel = new BuildScenePanel(width, height);
					BuildSceneFrame.this.add(panel);
					panel.repaint();
				}
				BuildSceneFrame.this.requestFocus();
			}
		});
		//
		doneButton = new JButton("Done");
		doneButton.setBounds(600, 420+80, 200, 50);
		getContentPane().add(doneButton);
		doneButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				BuildSceneFrame.this.requestFocus();
			}
		});
		//
		buildButtons = new JButton[6];
		initializeButtons();
	}
	private void initializeButtons() {
		initializeShipButtons();
		buildButtons[4] = new JButton("Add Mine");
		buildButtons[4].setBounds(600,4*65+10,200,50);
		buildButtons[4].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent chert) {
					if(panel.remainingMines != 0)
						panel.state = 8;
					else
						JOptionPane.showMessageDialog(BuildSceneFrame.this, "You have no more Mines!");
					BuildSceneFrame.this.requestFocus();
				}
				});
		getContentPane().add(buildButtons[4]);
		buildButtons[5] = new JButton("Add AntiAircraft");
		buildButtons[5].setBounds(600,5*65+10,200,50);
		buildButtons[5].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent chert) {
				if(panel.remainingAirs != 0) {
					panel.state = 9;
				}
				else
					JOptionPane.showMessageDialog(BuildSceneFrame.this, "You have no more AntiAircrafts!");
				BuildSceneFrame.this.requestFocus();
			}
			});
		getContentPane().add(buildButtons[5]);
	}
	private void initializeShipButtons() {
		for(int i=0;i<4;i++) {
			final int k = i;
			buildButtons[i]  = new JButton("Add Ship Type "+(i+1));
			buildButtons[i].setBounds(600,i*65+10,200,50);
			buildButtons[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent chert) {
					if(k==0) {
						if(panel.remainingShips[0] == 0)
							JOptionPane.showMessageDialog(BuildSceneFrame.this, "You have no more of that ship!");
						else
							panel.state = 1;
					}
					else {
						if(panel.remainingShips[2*k-1] != 0)
							panel.state = 2*k;
						else if(panel.remainingShips[2*k] != 0)
							panel.state = 2*k+1;
						else
							JOptionPane.showMessageDialog(BuildSceneFrame.this, "You have no more of that ship!");
					}
					BuildSceneFrame.this.requestFocus();
				}
			});
			getContentPane().add(buildButtons[i]);
		}
	}
}
