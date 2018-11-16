package noxcuse.logica;

public class Columna {
	private String nombre;
	private String tipo;
	private int tamano;
	private Boolean nulleable;
	private Boolean primary;

	public Columna() {}
	
	public Columna(String nombre, String tipo, int tamano, int nulleable, int primary) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.tamano = tamano;
		if(nulleable!=1)
			this.nulleable = false;
		else
			this.nulleable = true;
		if(primary!=1)
			this.primary = false;
		else
			this.primary = true;
		
	}
	
	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public Boolean getNulleable() {
		return nulleable;
	}

	public void setNulleable(Boolean nulleable) {
		this.nulleable = nulleable;
	}

	public Columna(String nombre, String tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getTamano() {
		return tamano;
	}
	public void setTamano(int tamano) {
		this.tamano = tamano;
	}
	public String getWhere(Tabla t) {
		if(this.tipo.equals("varchar") || this.tipo.equals("nvarchar")) {
			return "(" + t.getNombre() + "." + this.nombre + " LIKE '%'+@" + this.nombre + "+'%' OR @" + this.nombre + " = '')";
		}
		else {
			return "(" + t.getNombre() + "." + this.nombre + " = @" + this.nombre + " OR @" + this.nombre + " IS NULL)";
		}
	}
	public String getWherePrimaria(Tabla t) {
		if(this.primary) 
			return t.getNombre() + "." + this.nombre + " = @" + this.nombre; 
		return "";
	}
	public String getParametroInsert() {
		if(!this.primary) {
			String respuesta = "@" + this.nombre + " " + this.tipo;
			if(this.tipo.equals("varchar") || this.tipo.equals("nvarchar"))
				if(this.tamano==-1)
					respuesta += "(MAX)";
				else
					respuesta += "(" + this.tamano + ")";
			if(this.nulleable)
				respuesta += " = NULL";
			return respuesta;
		}
		else
			return "";
	}
	public String getParametroNull() {
		String respuesta = "@" + this.nombre + " " + this.tipo;
		if(this.tipo.equals("varchar") || this.tipo.equals("nvarchar")) {
			if(this.tamano==-1)
				respuesta += "(MAX)";
			else
				respuesta += "(" + this.tamano + ")";
			respuesta += " = ''";
		}
		else {
			respuesta += " = NULL";
		}
		
		return respuesta;
	}
	
	public String getParametroNullUpd() {
		String respuesta = "@" + this.nombre + " " + this.tipo;
		if(this.tipo.equals("varchar") || this.tipo.equals("nvarchar")) {
			if(this.tamano==-1)
				respuesta += "(MAX)";
			else
				respuesta += "(" + this.tamano + ")";
			
		}
		respuesta += " = NULL";
		
		return respuesta;
	}
	public String getParametroPrimaria() {
		String respuesta = "@" + this.nombre + " " + this.tipo;
		if(this.tipo.equals("varchar") || this.tipo.equals("nvarchar"))
			if(this.tamano==-1)
				respuesta += "(MAX)";
			else
				respuesta += "(" + this.tamano + ")";
		if(!this.primary)
			respuesta += " = NULL";
		return respuesta;
	}
	public String getSet() {
		if(this.primary)
			return "";
		return this.nombre + " = ISNULL(@" + this.nombre + ", " + this.nombre + ")";
	}
	public String getCreateParameter() {
		String respuesta = "";
		if(this.tipo.equals("int")) {
			if (this.nulleable)
				respuesta = "if(C."+ this.nombre + "!= 0)";
			respuesta += "CONN.CreateParameter(\"@" + this.nombre + "\", C." + this.nombre + ");\r\n";
		}
		if(this.tipo.equals("varchar") || this.tipo.equals("nvarchar")) {
			if (this.nulleable)
				respuesta = "if(C."+ this.nombre + "!= null)";
			respuesta += "CONN.CreateParameter(\"@" + this.nombre + "\", C." + this.nombre + ");\r\n";
		}
		if(this.tipo.equals("bit")) {
			if (this.nulleable)
				respuesta = "if(C."+ this.nombre + "!= null)";
			respuesta += "CONN.CreateParameter(\"@" + this.nombre + "\", C." + this.nombre + ");\r\n";
		}
		
		return respuesta;
	}
	
	
}
