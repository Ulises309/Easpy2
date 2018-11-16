package noxcuse.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class BD {
	private String host;
	private String bd;
	private String user;
	private String pwd;
	private Connection conn;
	private Vector<Tabla> tablas = new Vector<Tabla>();
	private Vector<Reporte> reportes = new Vector<Reporte>();
	private String pathBD;
	private String pathWS;
	private String pathHtml = "C:/";
	private String pathLayout;
	private String auditoria;
	private String geneSys = GeneSys.SCHEMA;
	
	public Configuracion config = new Configuracion();

	public String getPathBD() {
		return pathBD;
	}
	
	public String getGeneSys() {
		return geneSys;
	}
	
	public void setPathBD(String pathBD) {
		this.pathBD = pathBD;
	}

	public String getPathWS() {
		return pathWS;
	}

	public void setPathWS(String pathWS) {
		this.pathWS = pathWS;
	}

	public String getPathHtml() {
		return pathHtml;
	}

	public void setPathHtml(String pathHtml) {
		this.pathHtml = pathHtml;
	}

	public String getPathLayout() {
		return pathLayout;
	}

	public void setPathLayout(String pathLayout) {
		this.pathLayout = pathLayout;
	}

	public String getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(String auditoria) {
		this.auditoria = auditoria;
	}

	public BD() {}

	public BD(String host, String bd, String user, String pwd) {
		super();
		this.host = host;
		this.bd = bd;
		this.user = user;
		this.pwd = pwd;
		this.auditoria = "GarSaAdmin";
		this.pathBD = "C:/BDs";
		this.pathWS = "C:/WSs";
		config.setHost(host);
		config.setBd(bd);
		config.setUser(user);
		config.setPwd(pwd);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://" + host + ";databaseName=" + bd + ";";
			this.conn = DriverManager.getConnection(url, user, pwd);
			String queryString = "SELECT o.id as tableid, o.name as tablename, s.name as schemaname FROM sysobjects o \r\n"
					+ "INNER JOIN sys.schemas s ON o.uid = s.schema_id WHERE o.xtype = 'u'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Tabla t = new Tabla(this.bd, rs.getInt("tableid"), rs.getString("tablename"),
						rs.getString("schemaname"), this.auditoria);
				t.setColumnas(conn);
				this.tablas.add(t);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public BD(Perfil p) throws Exception {
		super();
		this.host = p.getHost();
		this.bd = p.getBd();
		this.user = p.getUser();
		this.pwd = p.getPwd();
		this.pathBD = p.getPathBD();
		this.pathWS = p.getPathWS();
		this.pathHtml = p.getPathHTML();
		this.pathLayout = p.getPathLayout();
		this.auditoria = p.getAuditoria();
		this.geneSys = p.getGeneSys();
		obtenerTablas();

	}

	public void connect(Perfil p) throws Exception {
		this.host = p.getHost();
		this.bd = p.getBd();
		this.user = p.getUser();
		this.pwd = p.getPwd();
		this.pathBD = p.getPathBD();
		this.pathWS = p.getPathWS();
		this.pathHtml = p.getPathHTML();
		this.pathLayout = p.getPathLayout();
		this.auditoria = p.getAuditoria();
		obtenerTablas();
	}
	
	public void connect() throws Exception {
		config.setHost(this.host);
		config.setBd(this.bd);
		config.setUser(this.user);
		config.setPwd(this.pwd);
		obtenerTablas();
	}
	
	public void obtenerTablas() throws Exception {
		this.tablas.clear();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://" + this.host + ";databaseName=" + this.bd + ";";
		this.conn = DriverManager.getConnection(url, user, pwd);
		String queryString = "SELECT o.id as tableid, o.name as tablename, s.name as schemaname FROM sysobjects o \r\n"
				+ "INNER JOIN sys.schemas s ON o.uid = s.schema_id WHERE o.xtype = 'u' ORDER BY s.name asc, o.name asc";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {
			Tabla t = new Tabla(this.bd, rs.getInt("tableid"), rs.getString("tablename"),
					rs.getString("schemaname"), this.auditoria);
			boolean esGeneSys = false;
			for(int j = 0; j < GeneSys.TABLAS.length; j++ ) {
				if(GeneSys.TABLAS[j].toUpperCase().equals( t.getNombre().toUpperCase() )) {
					esGeneSys = true;
				}
			}
			if(esGeneSys)
				continue;
			
			t.setColumnas(conn);
			this.tablas.add(t);
		}
	}
	
	public boolean comprobarGeneSys() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://" + this.host + ";databaseName=" + this.bd + ";";
		this.conn = DriverManager.getConnection(url, user, pwd);
		String queryString = "SELECT o.id as tableid, o.name as tablename, s.name as schemaname FROM sysobjects o \r\n"
				+ "INNER JOIN sys.schemas s ON o.uid = s.schema_id WHERE o.xtype = 'u' ORDER BY s.name asc, o.name asc";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		Vector<Tabla> genesys = new Vector<Tabla>();
		while (rs.next()) {
			Tabla t = new Tabla(this.bd, rs.getInt("tableid"), rs.getString("tablename"),
					rs.getString("schemaname"), this.auditoria);
			t.setColumnas(conn);
			genesys.add(t);
		}
		
		for(int i = 0; i < GeneSys.TABLAS.length; i++) {
			boolean existe = false;
			for(int j = 0; j < genesys.size(); j++ ) {
				if(GeneSys.TABLAS[i].toUpperCase().equals( genesys.get(j).getNombre().toUpperCase() )) {
					existe = true;
					break;
				}
			}
			if(!existe)
				return false;
		}
		
		return true;
	}
	
	public boolean comprobarAuditoria() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://" + this.host + ";databaseName=" + this.bd + ";";
		this.conn = DriverManager.getConnection(url, user, pwd);
		String queryString = "SELECT o.id as tableid, o.name as tablename, s.name as schemaname FROM sysobjects o \r\n"
				+ "INNER JOIN sys.schemas s ON o.uid = s.schema_id WHERE o.xtype = 'u' ORDER BY s.name asc, o.name asc";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		Vector<Tabla> auditoria = new Vector<Tabla>();
		while (rs.next()) {
			Tabla t = new Tabla(this.bd, rs.getInt("tableid"), rs.getString("tablename"),
					rs.getString("schemaname"), this.auditoria);
			t.setColumnas(conn);
			auditoria.add(t);
		}
		
		for(int i = 0; i < GeneSys.TABLAS_AUDITORIA.length; i++) {
			boolean existe = false;
			for(int j = 0; j < auditoria.size(); j++ ) {
				if(GeneSys.TABLAS_AUDITORIA[i].toUpperCase().equals( auditoria.get(j).getNombre().toUpperCase() )) {
					existe = true;
					break;
				}
			}
			if(!existe)
				return false;
		}
		
		return true;
	}
	
	public void altaGeneSys() throws Exception {
		String [] querys = GeneSys.SCRIPT;
		for(int i = 0; i < GeneSys.SCRIPT.length; i++) {
			String queryString = querys[i];
			if(!this.geneSys.equals(GeneSys.SCHEMA))
				queryString = queryString.replace("GeneSys", this.geneSys);
			querys[i] = queryString;
		}
		executeMultiple(querys);
	}
	
	public void altaAuditoria() throws Exception {
		String [] querys = GeneSys.AUDITORIA.split("GO");
		executeMultiple(querys);
		
	}
	
	public void executeMultiple(String [] querys) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://" + this.host + ";databaseName=" + this.bd + ";";
		this.conn = DriverManager.getConnection(url, user, pwd);
		Statement statement = conn.createStatement();
		try {
			conn.setAutoCommit(false);
			for(int i = 0; i < querys.length; i++) {
				String queryString = querys[i];
				statement.execute(queryString);
			}
			conn.commit();
		} catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback();
	                conn.setAutoCommit(true);
	                throw new Exception();
	            } catch(SQLException excep) {
	            }
	        }
		}
		conn.setAutoCommit(true);
	}
	
	public void obtenerReportes() throws Exception{
		this.reportes.clear();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://" + this.host + ";databaseName=" + this.bd + ";";
		this.conn = DriverManager.getConnection(url, user, pwd);
		String queryString = "SELECT Id_Reporte, Nombre, Sp, Pagina_Id, Estatus_Id, Descripcion, Nombre_Archivo, Orden, Campo_Nombre, Encoding, Ruta_Default, Servidor, DB, Esquema, Ejecuciones, Fecha_Ult_Exec, Fecha_Creacion, RegGrid, Padre, class, URL, SistemaId, TipoRedirect FROM "+getGeneSys()+".Reportes";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		while (rs.next()) {
			Reporte r = new Reporte(rs.getInt("Id_Reporte"), rs.getString("Nombre"), rs.getString("Sp"), rs.getString("Esquema"), rs.getString("DB"), rs.getString("Descripcion"), rs.getString("Nombre_Archivo"), rs.getString("Campo_Nombre"), rs.getInt("Pagina_Id"), this);
			reportes.add(r);
		}
	}

	public boolean test() {
		try {
			this.tablas.clear();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://" + this.host + ";databaseName=" + this.bd + ";";
			this.conn = DriverManager.getConnection(url, user, pwd);
			if (conn == null)
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Vector<Item> obtenerUsuario(String busqueda) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://" + this.host + ";databaseName=" + this.bd + ";";
		this.conn = DriverManager.getConnection(url, user, pwd);
		String queryString = "SELECT Id_Usuario, Usuario FROM "+getGeneSys()+".Usuario WHERE Usuario LIKE '%"+busqueda+"%'";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		Vector<Item> usuarios = new Vector<Item>();
		while (rs.next()) {
			Item i = new Item(rs.getInt("Id_Usuario"), rs.getString("Usuario"));
			usuarios.add(i);
		}
		return usuarios;
	}
	
	public Vector<Item> obtenerTipoUsuario(String busqueda) throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String url = "jdbc:sqlserver://" + this.host + ";databaseName=" + this.bd + ";";
		this.conn = DriverManager.getConnection(url, user, pwd);
		String queryString = "SELECT Id_TipoUsuario, TipoUsuario FROM "+getGeneSys()+".Cat_TipoUsuario WHERE TipoUsuario LIKE '%"+busqueda+"%'";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		Vector<Item> usuarios = new Vector<Item>();
		while (rs.next()) {
			Item i = new Item(rs.getInt("Id_TipoUsuario"), rs.getString("TipoUsuario"));
			usuarios.add(i);
		}
		return usuarios;
	}
	

	public Vector<Tabla> getTablas() {
		return this.tablas;
	}
	
	public Vector<Reporte> getReportes(){
		return this.reportes;
	}

	public void close() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public static void setIntOrNull(PreparedStatement pstmt, int column, int value) throws SQLException
	{
	    if (value != 0) {
	        pstmt.setInt(column, value);
	    } else {
	        pstmt.setNull(column, java.sql.Types.INTEGER);
	    }
	}

}
