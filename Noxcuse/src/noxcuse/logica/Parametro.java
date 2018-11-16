package noxcuse.logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class Parametro {
	private int id_Campo;
	private int reporte_Id;
	private int variable_Id;
	private int control_Id;
	private String titulo_pagina;
	private String nombre;
	private int orden;
	private int visible;
	private String valor_variable;
	private String dataset;
	private String ds;
	private String parametro;
	private String combopadre;
	private String filejscript;
	private String funcionjs;
	private String evento;
	private String cssfile;
	private String css;
	private int ancho;
	private int longitud;
	private int renglon;
	private String clase;
	private String parsley;
	private boolean omitir;
	private BD bd;
	
	public Parametro(BD bd) {
		this.bd = bd;
	}
	
	public Parametro(int reporte_Id, int orden, BD bd) {
		this.reporte_Id = reporte_Id;
		this.variable_Id = 1;
		this.control_Id = 1;
		this.titulo_pagina = "Nuevo";
		this.nombre = "Nuevo";
		this.orden = orden;
		this.visible = 1;
		this.valor_variable = "";
		this.dataset = "";
		this.ds = "";
		this.omitir = true;
		this.bd = bd;
	}
	public Parametro(int id_Campo, int reporte_Id, int variable_Id, int control_Id, String titulo_pagina, String nombre,
			int orden, int visible, String valor_variable, String dataset, String ds, String parametro,
			String combopadre, String filejscript, String funcionjs, String evento, String cssfile, String css,
			int ancho, int longitud, int renglon, String clase, String parsley, boolean omitir, BD bd) {
		super();
		this.id_Campo = id_Campo;
		this.reporte_Id = reporte_Id;
		this.variable_Id = variable_Id;
		this.control_Id = control_Id;
		this.titulo_pagina = titulo_pagina;
		this.nombre = nombre;
		this.orden = orden;
		this.visible = visible;
		this.valor_variable = valor_variable;
		this.dataset = dataset;
		this.ds = ds;
		this.parametro = parametro;
		this.combopadre = combopadre;
		this.filejscript = filejscript;
		this.funcionjs = funcionjs;
		this.evento = evento;
		this.cssfile = cssfile;
		this.css = css;
		this.ancho = ancho;
		this.longitud = longitud;
		this.renglon = renglon;
		this.clase = clase;
		this.parsley = parsley;
		this.omitir = omitir;
		this.bd = bd;
	}
	
	public int getVisible() {
		return visible;
	}
	public int getId_Campo() {
		return id_Campo;
	}
	public void setId_Campo(int id_Campo) {
		this.id_Campo = id_Campo;
	}
	public int getReporte_Id() {
		return reporte_Id;
	}
	public void setReporte_Id(int reporte_Id) {
		this.reporte_Id = reporte_Id;
	}
	public int getVariable_Id() {
		return variable_Id;
	}
	public void setVariable_Id(int variable_Id) {
		this.variable_Id = variable_Id;
	}
	public int getControl_Id() {
		return control_Id;
	}
	public void setControl_Id(int control_Id) {
		this.control_Id = control_Id;
	}
	public String getTitulo_pagina() {
		return titulo_pagina;
	}
	public void setTitulo_pagina(String titulo_pagina) {
		this.titulo_pagina = titulo_pagina;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public int isVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getValor_variable() {
		return valor_variable;
	}
	public void setValor_variable(String valor_variable) {
		this.valor_variable = valor_variable;
	}
	public String getDataset() {
		return dataset;
	}
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	public String getDs() {
		return ds;
	}
	public void setDs(String ds) {
		this.ds = ds;
	}
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getCombopadre() {
		return combopadre;
	}
	public void setCombropadre(String combopadre) {
		this.combopadre = combopadre;
	}
	public String getFilejscript() {
		return filejscript;
	}
	public void setFilejscript(String filejscript) {
		this.filejscript = filejscript;
	}
	public String getFuncionjs() {
		return funcionjs;
	}
	public void setFuncionjs(String funcionjs) {
		this.funcionjs = funcionjs;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public String getCssfile() {
		return cssfile;
	}
	public void setCssfile(String cssfile) {
		this.cssfile = cssfile;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public int getRenglon() {
		return renglon;
	}
	public void setRenglon(int renglon) {
		this.renglon = renglon;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getParsley() {
		return parsley;
	}
	public void setParsley(String parsley) {
		this.parsley = parsley;
	}
	public boolean isOmitir() {
		return omitir;
	}
	public void setOmitir(boolean omitir) {
		this.omitir = omitir;
	}
	
	public void actualizar(Connection conn) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		PreparedStatement statement = conn.prepareStatement("UPDATE "+bd.getGeneSys()+".Campos SET "+
				"Variable_Id = ? ," +
				"Control_Id = ? ," +
				"Titulo_Pagina = ? ," +
				"Nombre = ? ," +
				"Orden = ? ," +
				"Visible = ? ," +
				"Valor_Variable = ? ," +
				"DataSet = ? ," +
				"DS = ? ," +
				"Parametro = ? ," +
				"ComboPadre = ? ," +
				"FileJScript = ? ," +
				"FuncionJS = ? ," +
				"Evento = ? ," +
				"CssFile = ? ," +
				"Css = ? ," +
				"Ancho = ? ," +
				"Longitud = ? ," +
				"Renglon = ? ," +
				"Class = ? ," +
				"Parsley = ? ," +
				"Omitir = ? " +
				"WHERE ID_Campo = ? ;");
		BD.setIntOrNull(statement, 1, this.variable_Id);
		BD.setIntOrNull(statement, 2, this.control_Id);
		statement.setString(3, this.titulo_pagina);
		statement.setString(4, this.nombre);
		statement.setObject(5, this.orden, java.sql.Types.INTEGER);
		statement.setObject(6, this.visible, java.sql.Types.INTEGER);
		statement.setString(7, this.valor_variable);
		statement.setString(8, this.dataset);
		statement.setString(9, this.ds);
		statement.setString(10, this.parametro);
		statement.setString(11, this.combopadre);
		statement.setString(12, this.filejscript);
		statement.setString(13, this.funcionjs);
		statement.setString(14, this.evento);
		statement.setString(15, this.cssfile);
		statement.setString(16, this.css);
		statement.setObject(17, this.ancho, java.sql.Types.INTEGER);
		statement.setObject(18, this.longitud, java.sql.Types.INTEGER);
		statement.setObject(19, this.renglon, java.sql.Types.INTEGER);
		statement.setString(20, this.clase);
		statement.setString(21, this.parsley);
		statement.setInt(22, this.omitir ? 1 : 0);
		statement.setInt(23, this.id_Campo);
		statement.executeUpdate();
	}
	
	public void alta(Connection conn) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		PreparedStatement statement = conn.prepareStatement("INSERT INTO "+bd.getGeneSys()+".Campos VALUES("+ this.reporte_Id + "," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? ," +
				"? " +
				");",
                Statement.RETURN_GENERATED_KEYS);
		BD.setIntOrNull(statement, 1, this.variable_Id);
		BD.setIntOrNull(statement, 2, this.control_Id);
		statement.setString(3, this.titulo_pagina);
		statement.setString(4, this.nombre);
		BD.setIntOrNull(statement, 5, this.orden);
		statement.setObject(6, this.visible, java.sql.Types.INTEGER);
		statement.setString(7, this.valor_variable);
		statement.setString(8, this.dataset);
		statement.setString(9, this.ds);
		statement.setString(10, this.parametro);
		statement.setString(11, this.combopadre);
		statement.setString(12, this.filejscript);
		statement.setString(13, this.funcionjs);
		statement.setString(14, this.evento);
		statement.setString(15, this.cssfile);
		statement.setString(16, this.css);
		BD.setIntOrNull(statement, 17, this.ancho);
		BD.setIntOrNull(statement, 18, this.longitud);
		BD.setIntOrNull(statement, 19, this.renglon);
		statement.setString(20, this.clase);
		statement.setString(21, this.parsley);
		statement.setInt(22, this.omitir ? 1 : 0);
		statement.executeUpdate();
		ResultSet generatedKeys = statement.getGeneratedKeys();
		if (generatedKeys.next()) {
            this.id_Campo=generatedKeys.getInt(1);
        }
	}
	
	public void eliminar(Connection conn) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		PreparedStatement statement = conn.prepareStatement("DELETE "+bd.getGeneSys()+".Campos WHERE ID_Campo = ?;");
		statement.setInt(1, id_Campo);
		int result = statement.executeUpdate();
		if(result<=0)
			throw new Exception("No se pudo eliminar", null);
	}
	
	public String getUpdate() {
		String update = "UPDATE "+bd.getGeneSys()+".Campos SET "+
				"Variable_Id = "+this.variable_Id+" ," +
				"Control_Id = "+this.control_Id+" ," +
				"Titulo_Pagina = "+Datos.toSQL(this.titulo_pagina)+" ," +
				"Nombre = "+Datos.toSQL(this.nombre)+" ," +
				"Orden = "+this.orden+" ," +
				"Visible = "+this.visible+" ," +
				"Valor_Variable = "+Datos.toSQL(this.valor_variable)+" ," +
				"DataSet = "+Datos.toSQL(this.dataset)+" ," +
				"DS = "+Datos.toSQL(this.ds)+" ," +
				"Parametro = "+Datos.toSQL(this.parametro)+" ," +
				"ComboPadre = "+Datos.toSQL(this.combopadre)+" ," +
				"FileJScript = "+Datos.toSQL(this.filejscript)+" ," +
				"FuncionJS = "+Datos.toSQL(this.funcionjs)+" ," +
				"Evento = "+Datos.toSQL(this.evento)+" ," +
				"CssFile = "+Datos.toSQL(this.cssfile)+" ," +
				"Css = "+Datos.toSQL(this.css)+" ," +
				"Ancho = "+Datos.setIntOrNull(this.ancho)+" ," +
				"Longitud = "+Datos.setIntOrNull(this.longitud)+" ," +
				"Renglon = "+Datos.setIntOrNull(this.renglon)+" ," +
				"Class = "+Datos.toSQL(this.clase)+" ," +
				"Parsley = "+Datos.toSQL(this.parsley)+" ," +
				"Omitir = "+(this.omitir ? 1 : 0)+ " ";
		return update;
	}
	
	public String getInsert() {
		String insert = "INSERT INTO "+bd.getGeneSys()+".Campos VALUES( @Reporte,"
				+this.variable_Id+" ," +
				+this.control_Id+" ," +
				Datos.toSQL(this.titulo_pagina) + " ," +
				Datos.toSQL(this.nombre)+" ," +
				this.orden+" ," +
				this.visible+" ," +
				Datos.toSQL(this.valor_variable)+" ," +
				Datos.toSQL(this.dataset)+" ," +
				Datos.toSQL(this.ds)+" ," +
				Datos.toSQL(this.parametro)+" ," +
				Datos.toSQL(this.combopadre)+" ," +
				Datos.toSQL(this.filejscript)+" ," +
				Datos.toSQL(this.funcionjs)+" ," +
				Datos.toSQL(this.evento)+" ," +
				Datos.toSQL(this.cssfile)+" ," +
				Datos.toSQL(this.css)+" ," +
				Datos.setIntOrNull(this.ancho)+" ," +
				Datos.setIntOrNull(this.longitud)+" ," +
				Datos.setIntOrNull(this.renglon)+" ," +
				Datos.toSQL(this.clase)+" ," +
				Datos.toSQL(this.parsley)+" ," +
				(this.omitir ? 1 : 0)+")\n";
		return insert;
	}
	
	public Elemento getElemento() {
		Elemento grupo, label, control;
		
		grupo = new Elemento("div");
		grupo.addClass("form-group");
		
		label = new Elemento("label");
		label.attr("for", this.titulo_pagina);
		label.addClass("control-label");
		label.setHtml(this.nombre);
		
		control = new Elemento();
		
		switch(this.control_Id) {
			case 1:
				control.setTipo("input");
				control.attr("type", "text");
				break;
			case 2:
				control.setTipo("select");
				if(this.combopadre!=null) {
					control.attr("combopadre", this.combopadre);
				}
				control.attr("campo", this.id_Campo);
				break;
			case 3:
				control.setTipo("input");
				control.attr("type", "date");
				break;
			case 4:
				control.setTipo("input");
				control.attr("type","file");
				break;
			case 5:
				control.setTipo("select");
				control.attr("parametro", this.parametro.replaceAll("@", ""));
				control.attr("campo", this.id_Campo);
				break;
			case 6:
				control.setTipo("input");
				control.attr("type","file");
				break;
			default:
				control.setTipo("input");
				control.attr("type", "text");
				break;
		}
		
		if(this.visible == 0) {
			grupo.attr("style","display:none;");
		}
		
		if(this.valor_variable != null) {
			control.attr("value", this.valor_variable);
		}
		
		switch(this.variable_Id) {
		case 1:
			break;
		case 2:
			control.attr("type", "number");
			break;
		case 3:
			control.attr("value", "$" + control.attr("value"));
			break;
		case 4:
			control.attr("value", "$" + this.valor_variable);
			break;
		case 5:
			break;
		case 6:
			control.attr("tipo", "XML");
			break;
		case 7:
			control.attr("tipo", "AGD");
			break;
		case 8:
			control.attr("tipo", "ARC");
			break;
		case 9:
			control.attr("tipo", "FTX");
			break;
		case 10:
			control.attr("tipo", "ARF");
			break;
		}
		
		control.addClass("form-control");
		
		if(!this.omitir) {
			control.addClass("parametro");
		}
		
		control.attr("id", this.titulo_pagina);
		grupo.append(label);
		grupo.append(control);
		
		return grupo;
	}
	
	public String generar() {
		Elemento grupo, label, control;
		
		grupo = new Elemento("div");
		grupo.addClass("form-group");
		grupo.addClass("row");
		
		label = new Elemento("label");
		label.attr("for", this.titulo_pagina);
		label.addClass("control-label");
		label.setHtml(this.nombre);
		
		control = new Elemento();
		
		switch(this.control_Id) {
			case 1:
				control.setTipo("input");
				control.attr("type", "text");
				break;
			case 2:
				control.setTipo("select");
				if(this.combopadre!=null) {
					String padre = this.combopadre;
				}
				break;
			case 3:
				control.setTipo("input");
				control.attr("type", "date");
				break;
			case 4:
				control.setTipo("input");
				control.attr("type","file");
				break;
			case 5:
				control.setTipo("select");
				break;
			case 6:
				control.setTipo("textarea");
				control.attr("rows", "6");
				break;
			default:
				control.setTipo("input");
				control.attr("type", "text");
				break;
		}
		
		if(this.visible == 0) {
			control.attr("style","display:none;");
		}
		
		if(this.valor_variable != null) {
			control.attr("value", this.valor_variable);
		}
		
		switch(this.variable_Id) {
		case 1:
			break;
		case 2:
			control.attr("type", "number");
			break;
		case 3:
			control.attr("value", "$" + control.attr("value"));
			break;
		case 4:
			control.attr("value", "$" + this.valor_variable);
			break;
		case 5:
			break;
		case 6:
			break;
		}
		
		control.addClass("form-control");
		
		if(!this.omitir) {
			control.addClass("parametro");
		}
		
		control.attr("id", this.titulo_pagina);
		grupo.append(label);
		grupo.append(control);
		
		return grupo.toString();
	}
	
	
}
