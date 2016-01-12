package testing;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import struttura.StrutturaSportiva;
import user.Cliente;

public class SerialTest {

	
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("test.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		StrutturaSportiva ss = new StrutturaSportiva("Fuck");
		//ss.addGestore(new Gestore("Gestore", "Gestore", "Gestore", "P@ssw0rd"));
		ss.addUtente(new Cliente("Gestore", "Gestore", "Gestore", "P@ssw0rd"));
		
		oos.writeObject(ss);
		
		oos.close();
	}
	
}
