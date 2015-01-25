package Graphics.GameScene;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;























import game.Player;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import Controllers.SingleMachineController;


@SuppressWarnings("serial")
public class SingleMachinceScene extends JFrame {
	private SingleMachineController controller;
	private GameTablePanel panel1,panel2;
	private JTextArea log;
	private JLabel time;
	public SingleMachinceScene(SingleMachineController s) {
		setSize(1400,700);
		setLayout(null);
		this.controller = s;
		final Player player = s.players[0];
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
							1, 0);
					break;
				case MouseEvent.BUTTON2:
					controller.addAircraftStatement(panel1.getActiveY(), 1, 0);
					break;
				case MouseEvent.BUTTON3:
					controller.addRadarStatement(panel1.getActiveX(), panel1.getActiveY(), 
							1, 0);
					break;
					
				}
				
			}
		});
		getContentPane().add(panel1);
		
		
		Player p2 = s.players[1];
		panel2 = new GameTablePanel(p2);
		panel2.setBounds(20+GameTablePanel.SIZE+60,20,GameTablePanel.SIZE, GameTablePanel.SIZE);
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
							0, 1);
					break;
				case KeyEvent.VK_Q:
					controller.addAircraftStatement(panel2.getActiveY(), 0, 1);
					break;
				case KeyEvent.VK_R:
					controller.addRadarStatement(panel2.getActiveX(), panel2.getActiveY(), 
							0, 1);
					break;
				}
				repaintAll();
			}
		});
		
		JLabel messageLogLabel = new JLabel("Message Log");
		messageLogLabel.setFont(messageLogLabel.getFont().deriveFont(14f));
		messageLogLabel.setBounds(20 + 2*GameTablePanel.SIZE+80, 15, 134, 30);
		
		getContentPane().add(messageLogLabel);
		
		JLabel playerOneName = new JLabel(controller.players[0].getName()+" 's MAP");
		playerOneName.setFont(playerOneName.getFont().deriveFont(14f));
		playerOneName.setBounds(20,20+GameTablePanel.SIZE +5,134,30);
		add(playerOneName);
		
		JLabel playerTwoName = new JLabel(controller.players[1].getName()+" 's MAP");
		playerTwoName.setFont(playerTwoName.getFont().deriveFont(14f));
		playerTwoName.setBounds(20 + GameTablePanel.SIZE+60,20+GameTablePanel.SIZE +5,134,30);
		add(playerTwoName);
		
		String guide = "Guide: \nGrey: Unknown     Black: Exploded mine    Yellow: Revealed ship \ns"
				+ "Red: Exploded ship     Blue:Exploded antiaircraft     Green: Reavealed empty cell";
		JTextArea guideLabel = new JTextArea(guide,10,10);
		guideLabel.setBounds(20,20+GameTablePanel.SIZE +40 , 600,80);
		guideLabel.setFocusable(false);
		add(guideLabel);
		
		time = new JLabel("Time: 00:00");
		time.setBounds(700, 550, 200, 80);
		time.setFont(time.getFont().deriveFont(28f));
		getContentPane().add(time);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20+2*GameTablePanel.SIZE+80,50,200,450);
		getContentPane().add(scrollPane);
		
		log = new JTextArea();
		log.setEditable(false);
		log.setFocusable(false);
		DefaultCaret caret = (DefaultCaret)log.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(log);
		
		repaint();
	}
	
	public void appendText(String text) {
		log.append(text);
	}
	
	public void setTime(int t) {
		int sec = t % 60;
		int min = t/60;
		time.setText("Time: "+String.format("%02d",min)+":"+String.format("%02d",sec));
	}
	
	public void repaintAll() {
		panel1.repaint();
		panel2.repaint();
	}
}
