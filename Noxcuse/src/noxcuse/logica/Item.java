package noxcuse.logica;

public class Item {
	public int id;
	public String texto;
	
	public Item(int id, String texto) {
		super();
		this.id = id;
		this.texto = texto;
	}
	public String toString() {
		return texto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
}
