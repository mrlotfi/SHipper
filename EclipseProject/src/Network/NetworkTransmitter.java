package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;




import javax.swing.JOptionPane;

import Controllers.NetworkController;



public class NetworkTransmitter {
	private ServerSocket ss;
	Thread listener;
	private NetworkController c;
	private int myPort;
	private int hisPort;
	private String myAddress;
	private String opAddress;
	private String recentRecievedString;
	public NetworkTransmitter(boolean isHost) {
		myPort = 66;
		hisPort = 666;
		if(isHost) {
			myPort = 666;
			hisPort = 66;
		}
		try {
			myAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {JOptionPane.showMessageDialog(null,
				"Turn off your firewall and try again");}
	}
	
	public void setNetworkController(NetworkController c) {
		this.c = c;
	}
	
	public boolean sendObject(Object o) {
		Socket s;
		try {
			s = new Socket(opAddress, hisPort);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(o);
			out.close();
			s.close();
			return true;
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
	
	public Object recieveObject() throws ClassNotFoundException {
		ServerSocket ss;
		try {
			ss = new ServerSocket(myPort);
			Socket s = ss.accept();
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			Object o  =  in.readObject();
			in.close();
			s.close();
			ss.close();
			return o;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Network problem. sorry. try later.");
			return null;
		}
	}
	
	public boolean waitForConnection() {
		try {
			ServerSocket tempSS = new ServerSocket(myPort);
			Socket tempS = tempSS.accept();
			DataInputStream inputStream = new DataInputStream(tempS.getInputStream());
			opAddress = inputStream.readUTF();
			inputStream.close();
			tempS.close();
			tempSS.close();
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Check your network access and firewall. "
					+ "Or maybe someone is trying to connect to this game unwantedly. Or maybe you are alreaady hosting a game");
			return false;
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
			return false;
		} catch (IOException e) {
			return false;
		}
	}
	
	public boolean sendString(String text) {
		try {
			Socket d = new Socket(opAddress,hisPort);
			DataOutputStream outputStream = new DataOutputStream(d.getOutputStream());
			outputStream.writeUTF(text);
			outputStream.close();
			d.close();
			return true;
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		
	}
	
	public void setOPIP(String s) {
		opAddress = s;
	}
	
	/** after calling this method you won't call send or receive object or we will get exception
	 * 
	 */
	public void startListening() {
		listener = new Thread() {
			public void run() {
				while(true) {
					try {
						ss = new ServerSocket(myPort);
						Socket s = ss.accept();
						DataInputStream input = new DataInputStream(s.getInputStream());
						recentRecievedString = input.readUTF();
						input.close();
						s.close();
						ss.close();
						c.parseInput(recentRecievedString);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
		listener.start();
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		try {
			listener.stop();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
