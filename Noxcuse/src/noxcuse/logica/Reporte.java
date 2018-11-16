package noxcuse.logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class Reporte {
	private int id_reporte;
	private String nombre;
	private String sp;
	private String esquema;
	private String basdedatos;
	private String descripcion;
	private String nombre_archivo;
	private String campo_nombre;
	private int pagina_id;
	private Vector<Parametro> parametros;
	private String store_codigo;
	private Vector<String> parametros_sp;
	private BD bd;
	
	
	public Reporte(BD bd) {
		parametros = new Vector<Parametro>();
		parametros_sp = new Vector<String>();
		this.bd = bd;
	}


	public Reporte(int id_reporte, String nombre, String sp, String esquema, String basdedatos, String descripcion, String nombre_archivo,
			String campo_nombre, int pagina_id, BD bd) {
		super();
		this.id_reporte = id_reporte;
		this.parametros = new Vector<Parametro>();
		parametros_sp = new Vector<String>();
		this.nombre = nombre;
		this.sp = sp;
		this.esquema = esquema;
		this.basdedatos = basdedatos;
		this.descripcion = descripcion;
		this.nombre_archivo = nombre_archivo;
		this.campo_nombre = campo_nombre;
		this.pagina_id = pagina_id;
		this.bd = bd;
	}
	
	public String toString() {
		return this.nombre;
	}

	public int getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(int id_reporte) {
		this.id_reporte = id_reporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getEsquema() {
		return esquema;
	}

	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}

	public String getBasdedatos() {
		return basdedatos;
	}

	public void setBasdedatos(String basdedatos) {
		this.basdedatos = basdedatos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre_archivo() {
		return nombre_archivo;
	}

	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}

	public String getCampo_nombre() {
		return campo_nombre;
	}

	public void setCampo_nombre(String campo_nombre) {
		this.campo_nombre = campo_nombre;
	}

	public int getPagina_id() {
		return pagina_id;
	}

	public void setPagina_id(int pagina_id) {
		this.pagina_id = pagina_id;
	}

	public Vector<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(Vector<Parametro> parametros) {
		this.parametros = parametros;
	}
	
	public void alta(Connection conn) throws Exception {
		this.parametros.clear();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		PreparedStatement statement = conn.prepareStatement("EXEC "+bd.getGeneSys()+".AltaReporte "+
				"@Nombre = ? , "+
				"@Sp = ? ," +
				"@Esquema = ? , " +
				"@BaseDatos = ? , " +
				"@Descripcion = ? ," +
				"@Nombre_Archivo = ? , " +
				"@Campo_Nombre = ? , " +
				"@Pagina_Id = ? ;");
		statement.setString(1, this.nombre);
		statement.setString(2, this.sp);
		statement.setString(3, this.esquema);
		statement.setString(4, this.basdedatos);
		statement.setString(5, this.descripcion);
		statement.setString(6, this.nombre_archivo);
		statement.setString(7, this.campo_nombre);
		statement.setInt(8, this.pagina_id);
		int result = statement.executeUpdate();
		if(result<=0)
			throw new Exception("No se pudo crear el reporte", null);
	}
	
	public Elemento generar() {
		Elemento container = new Elemento("div");
		container.addClass("container");
		container.addClass("formulario");
		container.attr("reporte", this.id_reporte);
		for(int i = 0; i < this.parametros.size(); i++) {
			container.append(this.parametros.get(i).getElemento());
		}
		
		return container;
	}
	
	public void obtenerParametros(Connection conn) throws Exception {
		this.parametros.clear();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String queryString = "SELECT * FROM "+bd.getGeneSys()+".Campos WHERE Reporte_Id = " + this.id_reporte + "ORDER BY Orden asc";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		while(rs.next()) {
			Parametro p = new Parametro(
					rs.getInt("ID_Campo"),
					rs.getInt("Reporte_Id"),
					rs.getInt("Variable_Id"),
					rs.getInt("Control_Id"),
					rs.getString("Titulo_Pagina"),
					rs.getString("Nombre"),
					rs.getInt("Orden"),
					rs.getInt("Visible"),
					rs.getString("Valor_Variable"),
					rs.getString("DataSet"),
					rs.getString("DS"),
					rs.getString("Parametro"),
					rs.getString("ComboPadre"),
					rs.getString("FileJScript"),
					rs.getString("FuncionJS"),
					rs.getString("Evento"),
					rs.getString("CssFile"),
					rs.getString("Css"),
					rs.getInt("Ancho"),
					rs.getInt("Longitud"),
					rs.getInt("Renglon"),
					rs.getString("Class"),
					rs.getString("Parsley"),
					rs.getBoolean("Omitir"),
					this.bd
					);
			this.parametros.add(p);
		}
	}
	
	public String getScriptProduccion() {
		String script = this.store_codigo + "GO\n\n" +
		"EXEC "+bd.getGeneSys()+"AltaReporte \n"+
		"@Nombre = "+Datos.toSQL(this.nombre)+" , \n"+
		"@Sp = "+Datos.toSQL(this.sp)+" , \n" +
		"@Esquema = "+Datos.toSQL(this.esquema)+" , \n" +
		"@BaseDatos = "+Datos.toSQL(this.basdedatos)+" , \n" +
		"@Descripcion = "+Datos.toSQL(this.descripcion)+" , \n" +
		"@Nombre_Archivo = "+Datos.toSQL(this.nombre_archivo)+" , \n" +
		"@Campo_Nombre = "+Datos.toSQL(this.campo_nombre)+" , \n" +
		"@Pagina_Id = "+this.pagina_id+" ;\n\n";
		
		script += "DECLARE @Reporte INT;\nSELECT @Reporte=@@IDENTITY\n\n";
		
		for(int i = 0; i < this.parametros.size(); i++) {
			boolean existe = false;
			for(int j = 0; j < this.parametros_sp.size(); j++) {
				if(this.parametros.get(i).getTitulo_pagina().equals(this.parametros_sp.get(j).replaceAll("@", ""))) {
					existe = true;
					break;
				}
			}
				if(existe) {
					script += this.parametros.get(i).getUpdate();
					script += "WHERE Reporte_Id = @Reporte AND Titulo_Pagina LIKE '"+this.parametros.get(i).getTitulo_pagina()+"';\n";
				}
				else {
					script += this.parametros.get(i).getInsert();
				}
		}
		for(int i = 0; i < this.parametros_sp.size(); i++) {
			boolean existe = false;
			for(int j = 0; j < this.parametros.size(); j++) {
				String campo = this.parametros.get(j).getTitulo_pagina();
				String parametro = this.parametros_sp.get(i).replaceAll("@", "");
				if(campo.equals(parametro)) {
					existe = true;
				}
			}
			if(!existe)
				script += "DELETE "+bd.getGeneSys()+".Campos WHERE Reporte_Id = @Reporte AND Titulo_Pagina = '" + this.parametros_sp.get(i).replaceAll("@", "")+ "'\n";
		}
		return script;
		
	}
	
	public void obtenerStore(Connection conn) throws Exception{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String queryString = "sp_helptext'" + this.esquema + "." + this.sp + "'";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		String store = "";
		while(rs.next()) {
			store+=rs.getString("Text");
		}
		this.store_codigo = store;
	}
	
	public void obtenerParametrosSP(Connection conn) throws Exception{
		parametros_sp.clear();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String queryString = "select 'Parameter_name' = name from sys.parameters where object_id = object_id('"+this.esquema + "." + this.sp +"')";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		while(rs.next()) {
			parametros_sp.add(rs.getString("Parameter_name"));
		}
	}
	
	public void agregarPermiso(BD bd, int id, int tipoacceso, int menu) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String queryString = "INSERT INTO "+bd.getGeneSys()+".Accesos VALUES("+this.id_reporte+", "+id+", "+tipoacceso+", "+menu+")";
		Statement statement = bd.getConn().createStatement();
		statement.execute(queryString);
	}
	
	public Vector<Item> obtenerUsuarios(BD bd) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String queryString = "SELECT Id_Acceso, Usuario FROM "+bd.getGeneSys()+".Accesos INNER JOIN "+bd.getGeneSys()+".Usuario ON Id_Usuario = UsuarioTipo_ID WHERE Reporte_Id ="+this.id_reporte+ " AND TipoAcceso_Id = 2";
		Statement statement = bd.getConn().createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		Vector<Item> resultado = new Vector<Item>();
		while(rs.next()) {
			Item i = new Item(rs.getInt("Id_Acceso"), rs.getString("Usuario"));
			resultado.add(i);
		}
		return resultado;
	}
	public Vector<Item> obtenerTipoUsuario(BD bd) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String queryString = "SELECT Id_Acceso, TipoUsuario FROM "+bd.getGeneSys()+".Accesos INNER JOIN "+bd.getGeneSys()+".Cat_TipoUsuario ON Id_TipoUsuario = UsuarioTipo_ID WHERE Reporte_Id ="+this.id_reporte+ " AND TipoAcceso_Id = 1";
		Statement statement = bd.getConn().createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		Vector<Item> resultado = new Vector<Item>();
		while(rs.next()) {
			Item i = new Item(rs.getInt("Id_Acceso"), rs.getString("TipoUsuario"));
			resultado.add(i);
		}
		return resultado;
	}
	
}
