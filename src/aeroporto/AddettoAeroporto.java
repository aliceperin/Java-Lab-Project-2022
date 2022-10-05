package aeroporto;

public class AddettoAeroporto extends Thread {
	
	private Pista pista; // richiamo l'oggetto in comune
	
	//costruttore
	public AddettoAeroporto (Pista pista) {
		this.pista = pista;
	}
	
	//Override metodo run()
	public void run() {
		while (true) {
			//se la pista è pronta (quindi occupata da un aereo che vuole decollare e uno che vuole atterrare, la libera)
			if(pista.controlloPista()) {
				pista.liberaPista();
			}
		}
	}

}
