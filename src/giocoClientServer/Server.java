package giocoClientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws Exception {
		
		ServerSocket ss = new ServerSocket(8080); //mi metto in ascolto sulla porta 8080
		
		System.out.println("Server >> Benvenuto!");

		try {
			
		while (true) { //ciclo infinito
			
			Socket socket = ss.accept(); 
			
			try {
				
				new ServerThread(socket); //crea un thread per ogni connessione
				
			} catch (IOException e) {socket.close();}
			
		}
		
		} catch ( IOException e) {System.err.println("Errore nel socket");}
		
		ss.close();
		
	}
	
}
