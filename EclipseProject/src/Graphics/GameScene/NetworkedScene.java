package Graphics.GameScene;

import game.Player;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import Controllers.NetworkController;
import Controllers.SingleMachineController;

@SuppressWarnings("serial")
public class NetworkedScene extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NetworkController c = new NetworkController(10, 10);
		NetworkedScene s = new NetworkedScene(c, 0);
		c.setScene(s);
		s.setVisible(true);
	}
	
	
	private GameTablePanel panelEnemy;
	private RevealedGameTablePanel myPanel;
	private NetworkController controller;
	private int owner;
	private JTextArea log;
	private JLabel time;
	public NetworkedScene(final NetworkController controller, int owner) {
		setSize(1400,700);
		setLayout(null);
		this.controller = controller;
		this.owner = owner;
		final Player player = controller.players[1-owner];
		panelEnemy = new GameTablePanel(player);
		panelEnemy.setBounds(20, 20, GameTablePanel.SIZE, GameTablePanel.SIZE);
		panelEnemy.setVisible(true);
		
		panelEnemy.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				int x = arg0.getX() / (GameTablePanel.SIZE/player.getWidth());
				int y= arg0.getY() / (GameTablePanel.SIZE/player.getHeight());
				panelEnemy.setActiveCell(x, y);
				repaintAll();
			}

		});
		panelEnemy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch(arg0.getButton()) {
				case MouseEvent.BUTTON1:
					//TODO
					break;
				case MouseEvent.BUTTON2:
					//TODO
					break;
				case MouseEvent.BUTTON3:
					//TODO
					break;
					
				}
				
			}
		});
		getContentPane().add(panelEnemy);
		
		
		myPanel = new RevealedGameTablePanel(controller.players[owner]);
		myPanel.setBounds(20+RevealedGameTablePanel.SIZE+60,20,RevealedGameTablePanel.SIZE, RevealedGameTablePanel.SIZE);
		getContentPane().add(myPanel);
		
		
		JLabel messageLogLabel = new JLabel("Message Log");
		messageLogLabel.setFont(messageLogLabel.getFont().deriveFont(14f));
		messageLogLabel.setBounds(20 + 2*GameTablePanel.SIZE+80, 15, 134, 30);
		
		getContentPane().add(messageLogLabel);
		
		JLabel playerOneName = new JLabel(controller.players[1-owner].getName()+" 's MAP");
		playerOneName.setFont(playerOneName.getFont().deriveFont(14f));
		playerOneName.setBounds(20,20+GameTablePanel.SIZE +5,134,30);
		add(playerOneName);
		
		JLabel playerTwoName = new JLabel(controller.players[owner].getName()+" 's MAP");
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
		
		JButton chatBtn = new JButton("Chat");
		chatBtn.setBounds(20+2*GameTablePanel.SIZE+80, 20+GameTablePanel.SIZE+60,200,60);
		add(chatBtn);
		
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
	
	private void repaintAll() {
		// TODO Auto-generated method stub
		myPanel.repaint();
		panelEnemy.repaint();
	}
	
}
