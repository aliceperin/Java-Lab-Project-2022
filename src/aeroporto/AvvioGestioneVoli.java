package aeroporto;
public class AvvioGestioneVoli {

	public static void main(String[] args) {
		//creazione buffer
		Pista pista = new Pista();
		//creazione addetto
		AddettoAeroporto addetto = new AddettoAeroporto(pista);
		System.out.println("Gli aerei si stanno preparando... attendere.");
		System.out.println("");
		
		//numero random
		int random=(int)(Math.random() * 20);
		
		//crea metà aerei già in volo e metà a terra, per differenziare i comportamenti
		for (int i = 1; i<= random; i++) {
			if(i%2==0) {
				Aereo a = new Aereo (i, pista, true); //crea aereo in volo
				
			} else {
				Aereo a = new Aereo (i, pista,false); // crea oggetto non in volo
			}
		}
		//attivazione thread dell'addetto 
		addetto.start();
	}

}
