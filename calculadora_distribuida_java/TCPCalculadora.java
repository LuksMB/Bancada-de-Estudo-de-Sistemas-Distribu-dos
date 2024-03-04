import java.net.*;
import java.io.*;

public class TCPCalculadora {

	public static int somar(int x, int y) {
		return x + y;
	}

	public static int subtrair(int x, int y) {
		return x - y;
	}

	public static double dividir(double x, double y) {
		return x / y;
	}

	public static int multiplicar(int x, int y) {
		return x * y;
	}

	public static void main(String args[]) {
		try {
			int serverPort = 5320;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Erro de escuta:" + e.getMessage());
		}
	}

}

class Connection extends Thread {

	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Erro de conexão:" + e.getMessage());
		}
	}

	public void run() {
		try {
			String data = in.readUTF();
			String[] formatedData = data.split(",");
			int number1 = Integer.parseInt(formatedData[1]);
			int number2 = Integer.parseInt(formatedData[2]);
			switch (formatedData[0]) {
				case "add":
					out.writeUTF(String.valueOf(TCPCalculadora.somar(number1, number2)));
				case "sub":
					out.writeUTF(String.valueOf(TCPCalculadora.subtrair(number1, number2)));
				case "mult":
					out.writeUTF(String.valueOf(TCPCalculadora.multiplicar(number1, number2)));
				case "div":
					double number3 = Double.parseDouble(formatedData[1]);
					double number4 = Double.parseDouble(formatedData[2]);
					if (number4 == 0) {
						out.writeUTF("Não é possível dividir por denominador 0.");
					} else {
						out.writeUTF(String.valueOf(TCPCalculadora.dividir(number3, number4)));
					}
			}
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				System.out.println("Erro de fechamento: " + e.getMessage());
			}
		}
	}
}