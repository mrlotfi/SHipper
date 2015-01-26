package Network;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Graphics.GameScene.SingleMachinceScene;

public class SimpleTest {
	NetworkTransmitter trs;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		final SimpleTest test = new SimpleTest();
		
		final JTextField field = new JTextField(50);
		field.setSize(200, 40);
		frame.getContentPane().add(field);
		JButton  connect = new JButton("connect");
		connect.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				test.trs = new NetworkTransmitter(false);
				String s = JOptionPane.showInputDialog("enter ip");
				test.trs.tryToConnect(s);
				test.trs.setOPIP(s);
			}
		});
		connect.setSize(100, 50);
		frame.getContentPane().add(connect);
		
		JButton  wait = new JButton("wait");
		wait.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				test.trs = new NetworkTransmitter(true);
				Thread t = new Thread() {
					public void run() {
						test.trs.waitForConnection();
					}
				};
				t.start();
				
			}
		});
		wait.setSize(100, 50);
		frame.getContentPane().add(wait);
		
		JButton send = new JButton("send");
		send.setSize(100,50);
		send.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				test.trs.sendString(field.getText());
				
			}
		});
		frame.getContentPane().add(send);
		frame.repaint();
		
		JButton ready = new JButton("ready");
		ready.setSize(100,50);
		ready.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				test.trs.startListening();
			}
		});
		frame.add(ready);
	}

}
