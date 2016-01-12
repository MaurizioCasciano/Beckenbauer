package struttura;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import user.Cliente;

public class ElencoPrenotazioni {
	
	public ElencoPrenotazioni(){
		this.prenotazioni = new ArrayList<PrenotazioneV2>();
	}
	
	public void addPrenotazione(PrenotazioneV2 p){
		this.prenotazioni.add(p);
	}
	
	public ArrayList<PrenotazioneV2> prenotazioniPerCliente(Cliente c){
		ArrayList<PrenotazioneV2> risultato = new ArrayList<>();
		
		for(PrenotazioneV2 p: this.prenotazioni){
			if(p.getBigliettoPrenotato().getCliente().equals(c)){
				risultato.add(p);
			}
		}
		
		return risultato;
	}

	public ArrayList<PrenotazioneV2> prenotazioniPerPartita(Partita part){
		ArrayList<PrenotazioneV2> risultato = new ArrayList<>();
		
		for(PrenotazioneV2 p: this.prenotazioni){
			if(p.getBigliettoPrenotato().getPartita().equals(part)){
				risultato.add(p);
			}
		}
		
		return risultato;
		
	}
	
	public ArrayList<PrenotazioneV2> prenotazioniPerStadio(Stadio s){
		ArrayList<PrenotazioneV2> risultato = new ArrayList<>();
		
		for(PrenotazioneV2 p: this.prenotazioni){
			if(p.getBigliettoPrenotato().getPartita().getStadio().equals(s)){
				risultato.add(p);
			}
		}
		
		return risultato;
	}
	
	public void cancellaPrenotazioneCliente(Cliente c, Partita part){
		
		for(int i = 0; i < this.prenotazioni.size(); i++){
			if((this.prenotazioni.get(i).getBigliettoPrenotato().getCliente().equals(c)) &&
				this.prenotazioni.get(i).getBigliettoPrenotato().getPartita().equals(part)){
					
					this.prenotazioni.remove(i);
					i--;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void importaPrenotazioni() throws FileNotFoundException, IOException, ClassNotFoundException{
		if(ElencoPrenotazioni.ARCHIVIO_PRENOTAZIONI.exists()){
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(ElencoPrenotazioni.ARCHIVIO_PRENOTAZIONI));
			this.prenotazioni = (ArrayList<PrenotazioneV2>) in.readObject();
			in.close();
		} 
	}
	
	public void salvaPrenotazioni() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ElencoPrenotazioni.ARCHIVIO_PRENOTAZIONI));
		out.writeObject(this.prenotazioni);
		out.close();
	}
	
	private ArrayList<PrenotazioneV2> prenotazioni;
	private static final File ARCHIVIO_PRENOTAZIONI = new File("Prenotazioni.dat");
}
