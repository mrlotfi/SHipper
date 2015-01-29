package Graphics.GameScene;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.DropMode;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
public class HelpFrame extends JFrame {

	public JPanel contentPane;
	public JPanel Wellcome ;
	public JPanel Mouse ;
	public JPanel Keyboard ;
	public JPanel MapBuild ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpFrame frame = new HelpFrame();
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
	public HelpFrame() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setBackground(Color.DARK_GRAY);
		setTitle("Help");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 450, 27);;
		
		
		
		Wellcome = new JPanel();
		Wellcome.setLayout(null);
		JEditorPane Wellcomee = new JEditorPane();
		Wellcomee.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 8));
		Wellcomee.setEditable(false);
		Wellcomee.setBounds(0, 0, 450, 244);
		Wellcome.add(Wellcomee);
		Wellcomee.setBackground(Color.DARK_GRAY);
		Wellcomee.setForeground(Color.WHITE);
		Wellcomee.setText("\t\n\n\t\tHi,Wellcome to battleship!\n\n\tYou can play this game local or network with your friends !!!\n");
		
		
		Mouse = new JPanel();
		Mouse.setBackground(Color.DARK_GRAY);
		Mouse.setForeground(Color.WHITE);
		Mouse.setBounds(32, 60, 388, 160);

		JEditorPane Mousee = new JEditorPane();
		Mousee.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 10));
		Mousee.setBackground(Color.DARK_GRAY);
		Mousee.setForeground(Color.WHITE);
		Mousee.setText("\tThese are the Mousee controls :\nRight Click : Radar instruction\nLeft Click : Attack instruction\t\nMiddle Click : Aircraft instruction\n\n");
		Mousee.setBounds(32, 60, 388, 160);
		Mouse.add(Mousee);
		
		
		Keyboard = new JPanel();
		Keyboard.setBackground(Color.DARK_GRAY);
		Keyboard.setForeground(Color.WHITE);
		Keyboard.setBounds(32, 60, 388, 160);
		JEditorPane Keyboardd = new JEditorPane();
		Keyboardd.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 10));
		Keyboardd.setBackground(Color.DARK_GRAY);
		Keyboardd.setForeground(Color.WHITE);
		Keyboardd.setText("\tThese are the KeyBoard Controlls\nQ = Aircraft instruction\nA = Attack instruction\nR = Radar instruction\nArows = Change the current cell in the table \n");
		Keyboardd.setBounds(32, 60, 388, 160);
		Keyboard.add(Keyboardd);
		
		
		MapBuild = new JPanel();
		MapBuild.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 10));
		MapBuild.setBackground(Color.DARK_GRAY);
		MapBuild.setForeground(Color.WHITE);
		MapBuild.setBounds(32, 60, 388, 160);
		
		JEditorPane MapBuildd = new JEditorPane();
		MapBuildd.setFont(new Font("Copperplate Gothic Bold", Font.ITALIC, 10));
		MapBuildd.setBackground(Color.DARK_GRAY);
		MapBuildd.setForeground(Color.WHITE);
		MapBuildd.setText("In the Map Builder's frame you should build your map :\nYou should build it with mouse !!\n\nPut all of your ship in the map \nPut Mine and Aircraft in map (it's not neccesary to put all of them)\n\n**you can change the polarity of your ships with arrow keys on the keyboard .");
		MapBuildd.setBounds(32, 60, 388, 160);
		MapBuild.add(MapBuildd);
		
		tabbedPane.addTab("Wellcome",Wellcome);
		tabbedPane.addTab("Mouse",Mouse);
		tabbedPane.addTab("Keyboard",Keyboard);
		tabbedPane.addTab("Map Builder",MapBuild);
		
		contentPane.add( tabbedPane, BorderLayout.CENTER );
		
	}
}
