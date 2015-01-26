package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;



public class NetworkTransmitter {
	public static void main(String args[]) {
		NetworkTransmitter t1 = new NetworkTransmitter(true);
	}
	
	private boolean isHost;
	int myPort;
	int hisPort;
	private String myAddress;
	private String opAddress;
	private String recentRecievedString;
	public NetworkTransmitter(boolean isHost) {
		this.isHost = isHost;
		if(isHost) {
			myPort = 666;
			hisPort = 66;
		}
		else {
			myPort = 66;
			hisPort = 666;
		}
			
		try {
			myAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void waitForConnection() {
		try {
			ServerSocket tempSS = new ServerSocket(myPort);
			Socket tempS = tempSS.accept();
			DataInputStream inputStream = new DataInputStream(tempS.getInputStream());
			opAddress = inputStream.readUTF();
			inputStream.close();
			tempS.close();
			tempSS.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean tryToConnect(String hisIP) {
		try {
			Socket s = new Socket(hisIP,hisPort);
			opAddress = hisIP;
			DataOutputStream outStream = new DataOutputStream(s.getOutputStream());
			outStream.writeUTF(myAddress);
			outStream.flush();
			outStream.close();
			s.close();
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendString(String text) {
		try {
			Socket d = new Socket(opAddress,hisPort);
			DataOutputStream outputStream = new DataOutputStream(d.getOutputStream());
			outputStream.writeUTF(myPort+" says "+text);
			outputStream.close();
			d.close();
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void setOPIP(String s) {
		opAddress = s;
	}
	
	public void startListening() {
		Thread t = new Thread() {
			public void run() {
				ServerSocket ss;
				while(true) {
					try {
						ss = new ServerSocket(myPort);
						Socket s = ss.accept();
						DataInputStream input = new DataInputStream(s.getInputStream());
						recentRecievedString = input.readUTF();
						input.close();
						s.close();
						ss.close();
						JOptionPane.showMessageDialog(null, recentRecievedString);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		t.start();
	}
}
