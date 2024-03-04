import java.net.*;
import java.io.*;

public class TCPCliente {
	public static void main(String args[]) {
		// arguments supply message and hostname of destination
		Socket s = null;
		
		try {
			int serverPort = 5320;
			s = new Socket(args[1], serverPort);

			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			out.writeUTF(args[0]); // UTF is a string encoding see Sn 4.3
			
			String data = in.readUTF();
			
			System.out.println("Result: " + data);
		} catch (UnknownHostException e) {
			System.out.println("Failed to identify host:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
			}
		}
	}
}
