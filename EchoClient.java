// Simple echo client.

import java.io.*;
import java.net.*;
import java.net.UnknownHostException;

public class EchoClient {
	static DatagramSocket clientSocket;

	public static void main(String[] args) throws Exception {
		try {
			// Open a UDP datagram socket
			clientSocket = new DatagramSocket();
			InetAddress destination = null;


			// Prepare for transmission
			// Determine server IP address	

			// Approach 1: from the host name
			// Our example is "localhost"
			/*
			String hostname = "localhost";
			try {		
				destination = InetAddress.getByName(hostname);
			} catch (UnknownHostException e) {
				System.out.println("Unable to determine the host by name!");
				System.exit(-1);
			}
			*/		

			// Approach 2: from the IP address
			//*
			byte [] b = new byte[] {(byte)192,(byte)168,(byte)1,(byte)6};
			try {
	        		destination = InetAddress.getByAddress(b);
			} catch (UnknownHostException e) {
				System.out.println("Unable to determine the host by address!");
				System.exit(-1);
			}
			 //*/

			// Determine server port number
			int serverPortNumber = 56789;

			// Message and its length		
			String message = "hi";
			int lengthOfMessage = message.length(); 
			byte[] data = new byte[lengthOfMessage];
			data = message.getBytes();

			// Create a datagram
			DatagramPacket datagram = 
					new DatagramPacket(data, lengthOfMessage, destination, serverPortNumber);

			// Send a datagram carrying the message
			clientSocket.send(datagram);

			// Print out the message sent
			System.out.println("Message sent is:   [" + message + "]");

			// Prepare for receiving
			// Create a buffer for receiving
			byte[] receivedData = new byte[2048];

			// Create a datagram
			DatagramPacket receivedDatagram = 
					new DatagramPacket(receivedData, receivedData.length);

			// Receive a datagram
			clientSocket.receive(receivedDatagram);

			// Display the message in the datagram
			String echoMessage = new String(receivedData, 0, receivedDatagram.getLength());
			System.out.println("Message echoed is: [" + echoMessage + "]");	
		} 
		catch (IOException ioEx) {
			ioEx.printStackTrace();
		} 
		finally {
			// Close the socket 
			clientSocket.close();
		}
	}

}
