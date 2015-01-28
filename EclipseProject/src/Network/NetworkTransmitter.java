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
import java.rmi.server.SocketSecurityException;

import javax.swing.JOptionPane;

import Controllers.NetworkController;



public class NetworkTransmitter {
	public static void main(String args[]) {
		NetworkTransmitter t1 = new NetworkTransmitter(true);
	}
	
	private ServerSocket ss;
	Thread listener;
	private NetworkController c;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			outputStream.writeUTF(text);
			outputStream.close();
			d.close();
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void setOPIP(String s) {
		opAddress = s;
	}
	
	
	/** after calling this method you shouldn't call send or receive object
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
