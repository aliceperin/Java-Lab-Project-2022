package giocoClientServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException { 
		Scanner input = new Scanner (System.in);
		BufferedReader in;
		PrintWriter out;
		
		try {
			Socket clientSocket = new Socket ("127.0.0.1", 8080); //indirizzo IP e porta che coincidono con quelle del server
			System.out.println("Connessione in corso...");
			System.out.println("");
			System.out.println("Connessione client <---> server riuscita");
			
			
			//creazione stream di input da socket
			InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
			in = new BufferedReader(isr);
			
			//creazione stream di output su socket
			OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
			BufferedWriter bw = new BufferedWriter(osw);
			out= new PrintWriter(bw, true);
			
			int tentativo = 1;
			String rispServer;
			int numClient;
			boolean indovinato=false;
			
			System.out.println("Il gioco può iniziare");
			System.out.println(" -> Il client deve inserire un numero da 1 a 100 \n e indovinare il numero segreto del server.");
			System.out.println("");
			
			while (!indovinato) {
				System.out.println("- Tentativo client n." + tentativo + " -");
				System.out.println("Inserisci numero da tastiera");
				numClient= input.nextInt(); 
			
				System.out.println("Il client tenta la fortuna con il numero " + numClient);
					
				out.println(numClient); //inoltra il numero scelto dal client al server
				rispServer=in.readLine(); //ricevo la risposta del server
				System.out.println("Server>> " +rispServer); //stampo la risposta del server

				
				if (rispServer.startsWith("OK: ")) { 
					indovinato=true; // se indovina esce dal ciclo e termina il gioco
					
					} else {
						System.out.println(""); 
						tentativo++; // se non indovina, il ciclo continua e incrementa il numero dei tentativi
					}
					
				}
			System.out.println("");
			System.out.println("Fine della partita");
			out.close();
			in.close();
			clientSocket.close();
			
		} catch (IOException e) {}
		
	}

}
