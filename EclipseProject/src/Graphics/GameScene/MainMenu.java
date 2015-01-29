package Graphics.GameScene;



import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import Controllers.GameStartActions;


@SuppressWarnings("serial")
public class MainMenu extends JFrame{
	
	public static void main(String args[]) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
	    } catch (Exception e) {
	            e.printStackTrace();
	    }
		MainMenu m = new MainMenu();
		m.setVisible(true);
	}
	
	public MainMenu() {
		setBounds(100, 100, 300, 120);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
		});
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton singleButton = new JButton("Play on this computer with your friend");
		getContentPane().add(singleButton);
		singleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SingleComputerEntryPoint entryPoint = new SingleComputerEntryPoint();
				entryPoint.setVisible(true);
			}
		});
		
		JButton hostBut = new JButton("Host a game");
		hostBut.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				GameStartActions.startHosting();
			}
		});
		getContentPane().add(hostBut);
		
		JButton joinBut = new JButton("Join a game");
		joinBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GameStartActions.startJoiningHost();
			}
		});
		getContentPane().add(joinBut);
		
		
	}

}
