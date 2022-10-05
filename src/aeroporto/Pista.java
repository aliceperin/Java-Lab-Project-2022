package aeroporto;

public class Pista {
	
	public boolean disponibilePerDecollo;
	public boolean disponibilePerAtterraggio;
	
	public Aereo aereoAtterra;
	public Aereo aereoDecollo;
	
	//costruttore 
	public Pista () {
		disponibilePerDecollo = true;
		disponibilePerAtterraggio = true;
	}
	
	
	public synchronized void atterraggio(Aereo aereo) {
		
		if (aereo.inVolo) { //se è non è in volo vuol dire che deve atterrare
			while (!disponibilePerAtterraggio) {
				System.out.println("");
				//se la pista non è disponibile per l'atterraggio, metto in attesa l'aereo
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//System.out.println("Addetto >> Pista libera per l'atterraggio.");
			System.out.println("");
			aereoAtterra = aereo;
			//la disponibilità per l'atterraggio torna falsa per bloccare l'accesso ad altri aerei
			this.disponibilePerAtterraggio = false;
			System.out.println("La pista per l'atterraggio sarà occupata dall'aereo " + aereo.ID);
			notifyAll();  // sveglia gli altri aerei 
		}
	}
	
	public synchronized void decollo (Aereo aereo) {
		
		if (!aereo.inVolo) {
			while(!disponibilePerDecollo) {
				System.out.println("");
				
				try {
					wait(); //se la pista non è disponibile per il decollo, metto in attesa l'aereo
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			System.out.println("");
			aereoDecollo = aereo;
			//la disponibilità per l'atterraggio torna falsa per bloccare l'accesso ad altri aerei
			this.disponibilePerDecollo = false;
			System.out.println("La pista per il decollo sarà occupata dall'aereo " + aereo.ID);

			notifyAll();
			}
		}
	
	public synchronized void liberaPista() {
		//la pista viene liberata quando un aereo decolla e uno atterra, quindi i due lati della pista tornano liberi
		disponibilePerDecollo = true;
		disponibilePerAtterraggio = true;
		System.out.println("Addetto >> la pista è stata liberata!");
		System.out.println("");
		notifyAll();
	}
	
	public synchronized boolean controlloPista() {
		/*metodo di controllo usato dall'addetto. Ritorna true solo se i due lati della pista 
		 * sono disponibili sia per l'atterraggio sia per il decollo
		 */
		while (disponibilePerDecollo || disponibilePerAtterraggio) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		return true;
		
	}
			
}

