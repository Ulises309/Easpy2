package noxcuse.logica;

import java.util.Vector;

public class Elemento {
	
	private Vector<Atributo> atributos;
	private Vector<String> clases;
	private String tipo;
	public String html;
	
	public Elemento(String tipo) {
		this.tipo = tipo;
		this.atributos = new Vector<Atributo>();
		this.clases = new Vector<String>();
		this.html = "";
	}
	
	public Elemento() {
		this.atributos = new Vector<Atributo>();
		this.clases = new Vector<String>();
		this.html = "";
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public void attr(String atributo, String valor) {
		Boolean existe = false;
		for(int i = 0; i < atributos.size(); i++) {
			if( atributos.get(i).getAtributo().equals( atributo ) ) {
				atributos.set(i, new Atributo(atributo, valor));
				existe = true;
				break;
			}
		}
		if(!existe) {
			atributos.add(new Atributo(atributo, valor));
		}
	}
	
	public void attr(String atributo, int valor) {
		Boolean existe = false;
		for(int i = 0; i < atributos.size(); i++) {
			if( atributos.get(i).getAtributo().equals( atributo ) ) {
				atributos.set(i, new Atributo(atributo, String.valueOf(valor)));
				existe = true;
				break;
			}
		}
		if(!existe) {
			atributos.add(new Atributo(atributo, String.valueOf(valor)));
		}
	}
	
	public void attr(String atributo, float valor) {
		Boolean existe = false;
		for(int i = 0; i < atributos.size(); i++) {
			if( atributos.get(i).getAtributo().equals( atributo ) ) {
				atributos.set(i, new Atributo(atributo, String.valueOf(valor)));
				existe = true;
				break;
			}
		}
		if(!existe) {
			atributos.add(new Atributo(atributo, String.valueOf(valor)));
		}
	}
	
	public String attr(String atributo) {
		for(int i = 0; i < atributos.size(); i++) {
			if( atributos.get(i).getAtributo().equals( atributo ) ) {
				return atributos.get(i).getValor();
			}
		}
		return null;
	}
	
	public void addClass(String clase) {
		clases.add(clase);
	}
	
	public void append(Elemento elemento) {
		this.html += "\n" + elemento.toString();
	}
	
	public void append(String elemento) {
		this.html += "\n" + elemento;
	}
	
	public void empty() {
		this.html = "";
	}
	
	public String toString() {
		String codigo;
		codigo = "<" + tipo;
		codigo += " " + generarClases();
		codigo += " " + generarAtributos();
		codigo += ">\n";
		codigo += html + "\n";
		codigo += "</" + tipo + ">";
		return codigo;
	}
	
	private String generarClases() {
		if(clases.size()<=0)
			return "";
		String c = "class=\"";
		for(int i = 0; i < clases.size(); i++) {
			c += clases.get(i) + " ";
		}
		return c.substring(0, c.length()-1) + "\"";
	}
	
	private String generarAtributos() {
		if(atributos.size()<=0)
			return "";
		String c = "";
		for(int i = 0; i < atributos.size(); i++) {
			Atributo a = atributos.get(i);
			c += a.getAtributo() + "=\"" + a.getValor() + "\" ";
		}
		return c.substring(0, c.length()-1);
	}
}
