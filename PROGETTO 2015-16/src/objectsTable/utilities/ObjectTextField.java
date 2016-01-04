package objectsTable.utilities;

import javax.swing.JTextField;

public class ObjectTextField<T> extends JTextField {

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
