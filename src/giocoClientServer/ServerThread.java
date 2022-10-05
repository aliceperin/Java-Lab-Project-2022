package giocoClientServer;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerThread extends Thread{
	
		public Socket socket;
		
		
		//costruttore
		public ServerThread (Socket s) throws IOException {
			socket =s;
			start(); //può partire appena viene creato
		
		}
		
		//override metodo run
		public void run() {
			String inputClient;
			int numeroClient;
			
			//crea un numero random tra 0 e 100 
			int numeroServer  = (int) (Math.random() * 100);
			
			try {
				
				BufferedReader in = new BufferedReader(new InputStreamReader (socket.getInputStream()));
				PrintWriter	out = new PrintWriter (new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				boolean indovinato=false;
				
				
				while(!indovinato) { //il loop si interrompe quando il client ha indovinato il numero del server
					sleep(1000);
					//legge il numero intero inserito dal client
					inputClient = in.readLine();
					numeroClient=Integer.parseInt(inputClient);
					
					//confronto il numero client con il numero del server
					
					sleep(1000); //aspetta un secondo prima di dare la risposta
					if (numeroClient < numeroServer) {out.println("A: Il numero segreto è più alto."); }
					else if (numeroClient > numeroServer) {out.println("B: Il numero segreto è più basso."); }
					if (numeroClient==numeroServer) {out.println("OK: Bravo hai indovinato, il numero era " + numeroServer); indovinato=true;}
					
				}	
			
			out.close();			
			in.close();
			socket.close();
			
			
				} catch (IOException e) {
					System.out.println("Chiudo la connessione...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (NumberFormatException e) {System.out.println("Nessun numero letto");}
			}
			
				
				
		}

		



