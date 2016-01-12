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

public class ElencoAcquisti {
	
	public ElencoAcquisti(){
		this.acquisti = new ArrayList<>();
	}
	
	public ArrayList<Acquisto> acquistiPerCliente(Cliente c){
		ArrayList<Acquisto> risultato = new ArrayList<>();
		
		for (Acquisto a: this.acquisti){
			if(a.getBiglietto().getCliente().equals(c)){
				risultato.add(a);
			}
		}
		
		return risultato;
	}
	
	public ArrayList<Acquisto> acquistiPerPartita(Partita part){
		ArrayList<Acquisto> risultato = new ArrayList<>();
		
		for (Acquisto a: this.acquisti){
			if(a.getBiglietto().getPartita().equals(part)){
				risultato.add(a);
			}
		}
		
		return risultato;
	}
	
	public ArrayList<Acquisto> acquistiPerStadio(Stadio s){
		ArrayList<Acquisto> risultato = new ArrayList<>();
		
		for (Acquisto a: this.acquisti){
			if(a.getBiglietto().getPartita().getStadio().equals(s)){
				risultato.add(a);
			}
		}
		
		return risultato;
	}
	
	
	@SuppressWarnings("unchecked")
	public void importaAcquisti() throws FileNotFoundException, IOException, ClassNotFoundException{
		if(ElencoAcquisti.ARCHIVIO_ACQUISTI.exists()){
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(ElencoAcquisti.ARCHIVIO_ACQUISTI));
			this.acquisti = (ArrayList<Acquisto>) in.readObject();
			in.close();
		} 
	}
	
	public void salvaAcquisti() throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ElencoAcquisti.ARCHIVIO_ACQUISTI));
		out.writeObject(this.acquisti);
		out.close();
	}
	
	private ArrayList<Acquisto> acquisti;
	private static final File ARCHIVIO_ACQUISTI = new File("Acquisti.dat");

}
