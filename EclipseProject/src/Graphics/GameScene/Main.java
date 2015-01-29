package Graphics.GameScene;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.UIManager;

import java.net.URL;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import Controllers.GameStartActions;
public class Main extends JFrame {

	static URL url = Main.class.getResource("gamestartup.wav");
	static URL url2 = Main.class.getResource("Click.wav");
	final static AudioClip clip =  Applet.newAudioClip(url);
	final static AudioClip clip2 =  Applet.newAudioClip(url2);
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	    
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Main() {
		clip.play();
		clip.loop();
		ImageIcon img = new ImageIcon("/Users/Apple/BattleShip/src/Graphics/GameScene/battleship.jpg");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 380);
		setTitle("Main Menu");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btn = new JButton("Local Game");
		btn.setBackground(Color.BLACK);
		btn.setForeground(Color.WHITE);
		btn.setBounds(29,182,117,30);
		btn.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 8));
		btn.setOpaque(true);
		btn.setBorderPainted(false);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clip2.play();
				SingleComputerEntryPoint entryPoint = new SingleComputerEntryPoint();
				entryPoint.setVisible(true);
			}
		});
		contentPane.add(btn);
		
		JButton btn1 = new JButton("Create Server");
		btn1.setForeground(Color.WHITE);
		btn1.setBackground(Color.BLACK);
		btn1.setBounds(29,224,117,30);
		btn1.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 8));
		btn1.setOpaque(true);
		btn1.setBorderPainted(false);
		btn1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				clip2.play();
				GameStartActions.startHosting();
			}
		});
		contentPane.add(btn1);
		
			
		JButton btn2 = new JButton("Join");
		btn2.setForeground(Color.WHITE);
		btn2.setBackground(Color.black);
		btn2.setBounds(29,266,117,30);
		btn2.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 8));
		btn2.setOpaque(true);
		btn2.setBorderPainted(false);
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clip2.play();
				GameStartActions.startJoiningHost();
			}
		});
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("Quit");
		btn3.setForeground(Color.WHITE);
		btn3.setBackground(Color.BLACK);
		btn3.setBounds(29,308,117,30);
		btn3.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 8));
		btn3.setOpaque(true);
		btn3.setBorderPainted(false);
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clip2.play();
				System.exit(1);
			}
		});
		contentPane.add(btn3);
		
		
		
		final JCheckBox Stop = new JCheckBox("Stop Music");
		Stop.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 8));
		Stop.setBackground(Color.BLACK);
		Stop.setForeground(Color.WHITE);
		Stop.setBounds(438, 6, 83, 23);
		Stop.setOpaque(true);
		Stop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(Stop.getSelectedObjects()!=null)
					clip.stop();
				else{
					clip.play();
					clip.loop();
				}
			}
		});
		
		JButton btn4 = new JButton("Help ");
		btn4.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 8));
		btn4.setForeground(Color.WHITE);
		btn4.setBackground(Color.BLACK);
		btn4.setBounds(438, 28, 86, 29);
		btn4.setOpaque(true);
		btn4.setBorderPainted(false);
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clip2.play();
				HelpFrame helpFrame = new HelpFrame();
				helpFrame.setVisible(true);
			}
		});
		contentPane.add(btn4);
	
		contentPane.add(Stop);
		
		JLabel lbl = new JLabel("Created by Mohammadreza Lotfi and Pooya Mahmoodi . January 2015");
		lbl.setDisplayedMnemonic('1');
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setForeground(Color.WHITE);
		lbl.setBackground(Color.BLACK);
		lbl.setBounds(166, 327, 214, 16);
		lbl.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 5));
		lbl.setOpaque(true);
		contentPane.add(lbl);
		
		JLabel image = new JLabel(img);
		image.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 8));
		image.setBounds(-25, 0,550,371);
		contentPane.add(image);
		
	}
}
