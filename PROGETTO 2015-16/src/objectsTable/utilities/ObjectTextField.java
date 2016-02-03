package objectsTable.utilities;

import java.io.Serializable;

import javax.swing.JTextField;

/**
 * Classe che estende {@link JTextField}, in aggiunta permette di contenere un
 * oggetto generico e di restituirlo quando necessario.
 * 
 * @param <T>
 *            il tipo dell'oggetto da contenere.
 * @author Maurizio
 */
public class ObjectTextField<T> extends JTextField implements Serializable {

	public ObjectTextField() {
		super();
		this.object = null;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public T getObject() {
		return this.object;
	}

	private static final long serialVersionUID = 6204525108117322039L;
	private T object;
}
