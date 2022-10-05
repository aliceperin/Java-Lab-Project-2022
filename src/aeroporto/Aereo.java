package aeroporto;

public class Aereo extends Thread {
	
	public Pista pista;
	public boolean inVolo;
	public int ID;
	
	//costruttore 
	public Aereo (int ID, Pista pista, boolean inVolo) {
		this.ID=ID;
		this.pista=pista;
		this.inVolo=inVolo;
		start(); //quando l'oggetto verrà creato partirà in automatico 
	}
	
	public void run() {
		//creo il ciclo "infinito"
		while (true) {
			
			if(this.inVolo) { //se è in volo dovrà atterrare
					try {
						sleep(5000); //faccio "addormentare" il thread 
					} catch (InterruptedException e) {
						e.printStackTrace();
					} 
					System.out.println("L'aereo " + this.getID() + " vuole atterrare.");
					System.out.println("");
					pista.atterraggio(this);
					System.out.println("--- L'aereo " + this.getID() + " è atterrato --- ");
					System.out.println("");
					this.inVolo=false; //quando ha decollato non è più in volo, quindi torna false
			
			
			} else if (!this.inVolo) { //se non è in volo dovrà decollare
					try {
						sleep(7000); //faccio "addormentare" il thread 
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("L'aereo " + this.getID() + " vuole decollare.");
					System.out.println("");
					pista.decollo(this);
					System.out.println("--- L'aereo " + this.getID() + " ha decollato --- ");
					System.out.println("");
					this.inVolo=true; //Decolla, quindi lo stato passa "in volo"
					
				}
			
			}
		}
	
	public int getID (){
		return this.ID;
	}
}
