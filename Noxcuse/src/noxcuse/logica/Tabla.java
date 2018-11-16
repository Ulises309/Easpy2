package noxcuse.logica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Tabla {
	private int id;
	private String nombre;
	private String esquema;
	private Vector<Columna> columnas = new Vector<Columna>();
	private String bd;
	private String auditoria;
	private String[] prefijos = 
		{
			"TB_",
			"tbl",
			"tb",
			"tb_"
		};
	
	public Tabla() { }
	
	public String toString() {
    	return getNombreCompleto();
	}
	
	public Tabla(String bd, int id, String nombre, String esquema) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.esquema = esquema;
	}
	
	public Tabla(String bd, int id, String nombre, String esquema, String auditoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.esquema = esquema;
		this.auditoria = auditoria;
	}
	
	public String getNombreCompleto() {
		return this.esquema + "." + this.nombre;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEsquema() {
		return esquema;
	}
	public void setEsquema(String esquema) {
		this.esquema = esquema;
	}
	public Vector<Columna> getColumnas() {
		return columnas;
	}
	public void setColumnas(Connection conn) {
		try {
			this.columnas.clear();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String queryString = "SELECT c.name as nombre, t.name as tipo, c.max_length as longitud, c.is_nullable as nulleable, ISNULL(pk.isPk,c.is_identity) as primario FROM sys.columns c\r\n" + 
					"INNER JOIN sys.types t ON c.user_type_id = t.user_type_id\r\n" + 
					"INNER JOIN sys.objects o ON o.object_id = c.object_id\r\n" + 
					"INNER JOIN sys.schemas s ON o.schema_id = s.schema_id\r\n" + 
					"LEFT JOIN (SELECT *, 1 as 'isPk' FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE OBJECTPROPERTY(OBJECT_ID(CONSTRAINT_SCHEMA + '.' + QUOTENAME(CONSTRAINT_NAME)), 'IsPrimaryKey') = 1 ) pk ON pk.COLUMN_NAME = c.name AND pk.CONSTRAINT_SCHEMA = s.name AND pk.TABLE_NAME = o.name\r\n" + 
					"WHERE c.object_id ="+this.id;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Columna c = new Columna(rs.getString("nombre"), rs.getString("tipo"), rs.getInt("longitud"), rs.getInt("nulleable"), rs.getInt("primario"));
				this.columnas.add(c);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getJoins(Connection conn) {
		String join = "";
		Vector<Tabla> tablas = new Vector<Tabla>();
		Vector<String> joins = new Vector<String>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String queryString = "SELECT\r\n" + 
					"idFK = tab2.object_id, ColPK = col1.name, SchFK = sch.name, TablaFK = tab2.name, ColFK = col2.name FROM sys.foreign_key_columns fkc\r\n" + 
					"INNER JOIN sys.objects obj ON obj.object_id = fkc.constraint_object_id\r\n" + 
					"INNER JOIN sys.tables tab1 ON tab1.object_id = fkc.parent_object_id\r\n" + 
					"INNER JOIN sys.tables tab2 ON tab2.object_id = fkc.referenced_object_id\r\n" + 
					"INNER JOIN sys.columns col1 ON col1.column_id = parent_column_id AND col1.object_id = tab1.object_id\r\n" + 
					"INNER JOIN sys.columns col2 ON col2.column_id = referenced_column_id AND col2.object_id = tab2.object_id \r\n" + 
					"INNER JOIN sys.schemas sch ON tab2.schema_id = sch.schema_id\r\n" + 
					"WHERE tab1.object_id = "+this.id;
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(queryString);
			while (rs.next()) {
				Tabla t = new Tabla();
				t.setId(rs.getInt("idFK"));
				t.setNombre(rs.getString("TablaFK"));
				t.setEsquema(rs.getString("SchFK"));
				boolean encontrada = false;
				for(int i = 0; i < tablas.size(); i++) {
					if(tablas.get(i).esquema.equals(t.esquema) && tablas.get(i).nombre.equals(t.nombre))
					{
						encontrada = true;
						joins.set(i, joins.get(i) + " AND " + this.nombre + "." + rs.getString("ColPK") + " = " + t.nombre + "." + rs.getString("ColFK"));
						break;
					}
				}
				if(!encontrada) {
					tablas.add(t);
					joins.add("LEFT JOIN " + t.getEsquema() + "." + t.getNombre() + " ON " + this.nombre + "." + rs.getString("ColPK") + " = " + t.nombre + "." + rs.getString("ColFK"));
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < joins.size(); i++) {
			join += joins.get(i) + "\r\n";
		}
		return join;
	}
	
	public String getSelectSP(Connection conn) {
		String select = "SELECT ";
		String parametros = "";
		String where = "";
		String joins = getJoins(conn);
		String columnas = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			parametros += this.columnas.get(i).getParametroNull() + ",\r\n";
			where += this.columnas.get(i).getWhere(this) + " AND\r\n";
			columnas += this.nombre + "." + this.columnas.get(i).getNombre() + ",";
		}
		
		parametros = parametros.substring(0, parametros.length() - 3);
		columnas = columnas.substring(0, columnas.length() - 1);
		where = where.substring(0, where.length() - 5);
		select = parametros + "\r\nAS\r\n" + select;
		select += columnas + "\r\n" + "FROM " + this.esquema + "." + this.nombre + "\r\n";
		select += joins;
		select += "WHERE\r\n" + where;
		
		return getSP("CON", select);
	}
	public String getSelectSPAuditoria(Connection conn) {
		String select = "SELECT ";
		String parametros = "";
		String where = "";
		String joins = getJoins(conn);
		String columnas = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			parametros += this.columnas.get(i).getParametroNull() + ",\r\n";
			where += this.columnas.get(i).getWhere(this) + " AND\r\n";
			columnas += this.nombre + "." + this.columnas.get(i).getNombre() + ",";
		}
		
		columnas = columnas.substring(0, columnas.length() - 1);
		where = where.substring(0, where.length() - 5);
		select += columnas + "\r\n" + "FROM " + this.esquema + "." + this.nombre + "\r\n";
		select += joins;
		select += "WHERE\r\n" + where;
		String query = parametros + getParametrosAuditoria() + "\r\n" + getIniVariablesAuditoria() + "\r\n" + select + "\r\n" + getFinAuditoria();
		return getSPAuditoria("CON", query);
	}
	
	public String getUpdateSP() {
		String update = "UPDATE " + getNombreCompleto() + " SET\r\n";
		String parametros = "";
		String set = "";
		String where = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = this.columnas.get(i);
			if(!c.getPrimary()) {
				parametros += c.getParametroNullUpd() + ",\r\n";
				set += c.getSet() + ",\r\n";
			}
			else {
				where += c.getWherePrimaria(this) + " AND\r\n";
				parametros += c.getParametroPrimaria() + ",\r\n";
			}
		}
		
		if(parametros.length()>3)
			parametros = parametros.substring(0, parametros.length() - 3);
		else 
			parametros = "";
		set = set.substring(0, set.length() - 3);
		if(where.length()>5)
		where = where.substring(0, where.length() - 5);
		else {
			where = "";
		}
		update = parametros + "\r\nAS\r\n" + update;
		update += set + "\r\n";
		if(where.length()==0)
			where = "1 = @" + this.columnas.get(0).getNombre();
		update += "WHERE\r\n" + where;
		
		return getSP("ACT", update);
	}
	public String getUpdateSPAuditoria() {
		String update = "UPDATE " + getNombreCompleto() + " SET\r\n";
		String parametros = "";
		String set = "";
		String where = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = this.columnas.get(i);
			if(!c.getPrimary()) {
				parametros += c.getParametroNullUpd() + ",\r\n";
				set += c.getSet() + ",\r\n";
			}
			else {
				where += c.getWherePrimaria(this) + "AND\r\n";
				parametros += c.getParametroPrimaria() + ",\r\n";
			}
		}
		
		set = set.substring(0, set.length() - 3);
		if(where.length()>5)
			where = where.substring(0, where.length() - 5);
		else {
				where = "";
		}
		update += set + "\r\n";
		if(where.length()==0)
			where = "1 = @" + this.columnas.get(0).getNombre();
		update += "WHERE\r\n" + where;
		
		String query = parametros + getPrimerParteTranUPD() + "\r\n" + update + "\r\n" + getSegundaParteTran();
		return getSPAuditoria("ACT", query);
	}
	
	public String getInsertSP() {
		String insert = "INSERT INTO " + getNombreCompleto() + "(";
		String parametros = "";
		String columnas = "";
		String valores = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = this.columnas.get(i);
			if(!c.getPrimary()) {
				parametros += this.columnas.get(i).getParametroInsert() + ",\r\n";
				columnas += c.getNombre() + ",";
				valores += "@" + c.getNombre() + ",";
			}
		}
		
		if(parametros.length()>3)
			parametros = parametros.substring(0, parametros.length() - 3);
		else 
			parametros = "";
		columnas = columnas.substring(0, columnas.length() - 1);
		valores = valores.substring(0, valores.length() - 1);
		insert += columnas + ")\r\nVALUES\r\n(" + valores + ")\r\n";
		insert = parametros + "\r\nAS\r\n" + insert;
		return getSP("ALT", insert);
	}	
	public String getInsertSPAuditoria() {
		String insert = "INSERT INTO " + getNombreCompleto() + "(";
		String parametros = "";
		String columnas = "";
		String parametros2 = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = this.columnas.get(i);
			if(!c.getPrimary()) {
				parametros += this.columnas.get(i).getParametroInsert() + ",\r\n";
				columnas += c.getNombre() + ",";
				parametros2 += "@" + c.getNombre() + ",";
			}
		}
		
		columnas = columnas.substring(0, columnas.length() - 1);
		parametros2 = parametros2.substring(0, parametros2.length() - 1);
		insert += columnas + ")\r\nVALUES\r\n(" + parametros2 + ")\r\n";
		String query = parametros + getPrimerParteTran() + "\r\n" + insert + "\r\n" + getSegundaParteTran();
		return getSPAuditoria("ALT", query);
	}	
	
	public String getDeleteSP() {
		String delete = "DELETE " + getNombreCompleto();
		String parametros = "";
		String where = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = columnas.get(i);
			if(c.getPrimary()) {
				parametros += c.getParametroPrimaria() + ",\r\n";
				where += c.getWherePrimaria(this) + " AND\r\n";
			}
		}
		if(parametros.length()>3)
			parametros = parametros.substring(0, parametros.length() - 3);
		else 
			parametros = "";
		if(where.length()>5)
			where = where.substring(0, where.length() - 5);
		else {
				where = "";
		}
		if(parametros.length()==0)
			parametros += this.columnas.get(0).getParametroPrimaria();
		delete = parametros + "\r\nAS\r\n" + delete + "\r\n";
		if(where.length()==0)
			where = "1 = @" + this.columnas.get(0).getNombre();
		
		delete += "WHERE\r\n" + where;
		
		return getSP("DEL", delete);
	}
	public String getDeleteSPAuditoria() {
		String delete = "DELETE " + getNombreCompleto();
		String parametros = "";
		String where = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = columnas.get(i);
			if(c.getPrimary()) {
				parametros += c.getParametroPrimaria() + ",\r\n";
				where += c.getWherePrimaria(this) + "AND\r\n";
			}
		}
		
		if(where.length()>5)
			where = where.substring(0, where.length() - 5);
		else {
				where = "";
		}
		if(where.length()==0)
			where = "1 = @" + this.columnas.get(0).getNombre();
		delete += "\r\nWHERE\r\n" + where;
		if(parametros.length()==0)
			parametros += this.columnas.get(0).getParametroPrimaria();
		String query = parametros + getPrimerParteTranDEL() + "\r\n" + delete + "\r\n" + getSegundaParteTran();
		return getSPAuditoria("DEL", query);
	}
	
	private String getParametrosAuditoria() {
		return "	/* Parametros Obligatorios */\r\n" + 
				"	@NumeroTransaccion	BigInt        = NULL,	--  Transaccion de Cartera para Identificar su Afectaciones    /*Esto es obligatorio para los Sp que Afecten la Cartera*/\r\n" + 
				"	@Transaccion        BigInt        = NULL,	--  Transaccion de Registro de Actividades\r\n" + 
				"	@Sy_Paso            int           = NUll,	--  Valor del paso que se esta ejecutando\r\n" + 
				"	@UsurarioId         Int           = NULL,	--  Usuraio que Ejecuta SP\r\n" + 
				"	@IP                 Varchar(15)   = NULL,	--  IP de Usuario\r\n" + 
				"	@ObjetoId           Int           = NULL	--  Objeto de Donde se Ejecuta El SP\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"AS\r\n" + 
				"\r\n" + 
				"/*Deshabilita la salida de counts en los SP*/\r\n" + 
				"SET NOCOUNT ON\r\n" + 
				"\r\n" + 
				"/*Declaracion de Variables*/\r\n" + 
				"/*Variables de Registro*/\r\n" + 
				"declare	@Parametros		Varchar(1000),  --  Cadena de parametros del SP\r\n" + 
				"		@Err_Message	Varchar(1000),  --  Mensage de salida del sp se puede recuperar con ERROR_MESSAGE() /*Esto nos permite Mandar Errores Controlados\r\n" + 
				"		@TranCounter	Int,            --  Verificar si Existe Transaccion Abierta\r\n" + 
				"		@Sy_Accion		Int,            --  Si es Inicio (1) o Fin (2) del Registro de Actividad\r\n" + 
				"		@Sy_ObjetoId	Int,            --  ObjetoId al que pertenece el SP\r\n" + 
				"		@Comando		Varchar(100)    --  SP que se Ejecuta con todo y parametros";
	}
	private String getParametrosValores() {
		String param = "";
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = this.columnas.get(i);
			if(!c.getPrimary()) {
				param += "'@"+ c.getNombre() + "=' + ISNULL(CAST(@"+c.getNombre()+" AS varchar),'NULL') +";
			}
			
		}
		param = param.substring(0, param.length() - 1);
		return param;
	}
	private String getParametrosValoresDEL() {
		String param = "";
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = this.columnas.get(i);
			if(c.getPrimary()) {
				param += "'@"+ c.getNombre() + "=' + ISNULL(CAST(@"+c.getNombre()+" AS varchar),'NULL') +";
			}
			
		}
		param = param.substring(0, param.length() - 1);
		return param;
	}
	private String getParametrosValoresUPD() {
		String param = "";
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = this.columnas.get(i);
			param += "'@"+ c.getNombre() + "=' + ISNULL(CAST(@"+c.getNombre()+" AS varchar),'NULL') +";
			
		}
		param = param.substring(0, param.length() - 1);
		return param;
	}
	

	private String getSegundaParteTran() {
		return "/***************************CODIGO***************************************/\r\n" + 
				"\r\n" + 
				"			/***************************CODIGO***************************************/\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"\r\n" + 
				"		/*Fin Codifo de SP*/\r\n" + 
				"\r\n" + 
				"		set @Err_Message  = 'Finalizo Correctamente'\r\n" + 
				"\r\n" + 
				"		raiserror (@Err_Message, 0,0)\r\n" + 
				"\r\n" + 
				"		if @TranCounter = 0 begin\r\n" + 
				"			commit tran SP_EstandarTRA\r\n" + 
				"		end\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		/*Registro de Actividad FIN*/\r\n" + 
				"		set @Sy_Accion            = 2\r\n" + 
				"			\r\n" + 
				"		if @Sy_ObjetoId <> 0 begin\r\n" + 
				"			exec ["+this.auditoria+"].[Auditoria].Log_Registra  @Transaccion,@Sy_Paso,  @UsurarioId, @IP, @ObjetoId,       @Sy_ObjetoId ,  @NumeroTransaccion, @Sy_Accion, @Comando, @Err_Message\r\n" + 
				"		end\r\n" + 
				"\r\n" + 
				"		set nocount off\r\n" + 
				"\r\n" + 
				"	end\r\n" + 
				"\r\n" + 
				"--end \r\n" + 
				"try\r\n" + 
				"\r\n" + 
				"begin catch\r\n" + 
				"\r\n" + 
				"	set @Err_Message  = substring(ltrim(rtrim(convert(varchar,Error_Line()))) + ' - ' + ISNULL( @Err_Message,ERROR_MESSAGE()),1,1000)\r\n" + 
				"	/*Registro de Actividad FIN*/\r\n" + 
				"\r\n" + 
				"	if @TranCounter = 0 begin\r\n" + 
				"		rollback tran SP_EstandarTRA\r\n" + 
				"	end else begin\r\n" + 
				"		if XACT_STATE() <> -1 begin\r\n" + 
				"			rollback tran NombreSP\r\n" + 
				"		end\r\n" + 
				"	end\r\n" + 
				"\r\n" + 
				"	/*Registro de Actividad FIN*/\r\n" + 
				"	set @Sy_Accion = 2\r\n" + 
				"	\r\n" + 
				"	if @Sy_ObjetoId <> 0 begin\r\n" + 
				"		exec ["+this.auditoria+"].[Auditoria].Log_Registra  @Transaccion,@Sy_Paso,  @UsurarioId, @IP, @ObjetoId,@Sy_ObjetoId ,  @NumeroTransaccion, @Sy_Accion, @Comando, @Err_Message\r\n" + 
				"	end\r\n" + 
				"      \r\n" + 
				"	set nocount off\r\n" + 
				"\r\n" + 
				"	raiserror (@Err_Message, 11,10)\r\n" + 
				"\r\n" + 
				"end catch";
	}
	private String getPrimerParteTran() {
		return "	/***** (Desarrollador) *****/\r\n" + 
				"\r\n" + 
				"	/*Parametros Obligatorios*/\r\n" + 
				"	@NumeroTransaccion	BigInt        = NULL,	--  Transaccion de Cartera para Identificar su Afectaciones    /*Esto es obligatorio para los Sp que Afecten la Cartera*/\r\n" + 
				"	@Transaccion        BigInt        = NULL,	--  Transaccion de Registro de Actividades\r\n" + 
				"	@Sy_Paso            int           = NUll,	--  Valor del paso que se esta ejecutando\r\n" + 
				"	@UsurarioId         Int           = NULL,	--  Usuraio que Ejecuta SP\r\n" + 
				"	@IP                 Varchar(15)   = NULL,	--  IP de Usuario\r\n" + 
				"	@ObjetoId           Int           = NULL	--  Objeto de Donde se Ejecuta El SP\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"AS\r\n" + 
				"\r\n" + 
				"/*Deshabilita la salida de counts en los SP*/\r\n" + 
				"SET NOCOUNT ON\r\n" + 
				"\r\n" + 
				"/*Declaracion de Variables*/\r\n" + 
				"/*Variables de Registro*/\r\n" + 
				"declare	@Parametros		Varchar(1000),  --  Cadena de parametros del SP\r\n" + 
				"		@Err_Message	Varchar(1000),  --  Mensage de salida del sp se puede recuperar con ERROR_MESSAGE() /*Esto nos permite Mandar Errores Controlados\r\n" + 
				"		@TranCounter	Int,            --  Verificar si Existe Transaccion Abierta\r\n" + 
				"		@Sy_Accion		Int,            --  Si es Inicio (1) o Fin (2) del Registro de Actividad\r\n" + 
				"		@Sy_ObjetoId	Int,            --  ObjetoId al que pertenece el SP\r\n" + 
				"		@Comando		Varchar(100)    --  SP que se Ejecuta con todo y parametros\r\n" + 
				"\r\n" + 
				"/*Variables de SP*/\r\n" + 
				"\r\n" + 
				"/*\r\n" + 
				"/*Declaracion de  variables tipo tabla*/\r\n" + 
				"declare @Tabla table (\r\n" + 
				"	Campos  int\r\n" + 
				")\r\n" + 
				"*/\r\n" + 
				"\r\n" + 
				"/***************************NOTA***************************************/\r\n" + 
				"/*Declaracion de  Tablas Temporales */\r\n" + 
				"/* Opcional */\r\n" + 
				"/* Si se va a verificar que  una tabla temporal no exista */\r\n" + 
				"--if OBJECT_ID('tempdb..#Tabla') is not null begin\r\n" + 
				"--	drop table #Tabla\r\n" + 
				"--end else begin\r\n" + 
				"--	create Table #Tabla (\r\n" + 
				"--		Campos  varchar(1000)) \r\n" + 
				"--end\r\n" + 
				"\r\n" + 
				"/* Opcional */\r\n" + 
				"/*Si se va a utilizar una tabla temporal de otro Sp debes de crear la tabla en el sp*/\r\n" + 
				"--if object_id('tempdb..#Tabla') is null begin\r\n" + 
				"--	create table #Tabla (\r\n" + 
				"--		Campos  int) \r\n" + 
				"--end\r\n" + 
				"\r\n" + 
				"/*NOTA: No se pueden usar las dos para la misma tabla*/\r\n" + 
				"/******************************************************************/\r\n" + 
				"/* Inicializacion de Variables */\r\n" + 
				"/* Parametros del SP */\r\n" + 
				"/* Variables de Registro */\r\n" + 
				"set	@Parametros		= "+getParametrosValores()+"\r\n" + 
				"set	@Err_Message	= NULL\r\n" + 
				"set	@TranCounter	= @@TRANCOUNT\r\n" + 
				"set	@Sy_Accion		= 1\r\n" + 
				"set	@Sy_Paso		= isnull(@Sy_Paso,1)\r\n" + 
				"set	@Comando		= substring('EXEC "+getNombreCompleto()+"' + '@Parametros  = ' + ltrim(rtrim(convert(varchar,@Parametros))),1,1000)\r\n" + 
				"set @Sy_ObjetoId	= 14-- Asignar el Id del SP\r\n" + 
				"\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"/* Variables SP */\r\n" + 
				"\r\n" + 
				"/* Variables Tipo Tabla */\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"\r\n" + 
				"/* Variables tabla Temporal */\r\n" + 
				"--insert into #Tabla values(@Parametros)\r\n" + 
				"\r\n" + 
				"/*Registro de Actividad Inicio*/\r\n" + 
				"exec ["+this.auditoria+"].[Auditoria].[Log_Registra]  @Transaccion output,@Sy_Paso output,  @UsurarioId output, @IP output, @ObjetoId ,@Sy_ObjetoId ,  @NumeroTransaccion output, @Sy_Accion, @Comando, @Err_Message output\r\n" + 
				"\r\n" + 
				"/*Asigna el objetoId de este SP por sise ejecuta adentro*/\r\n" + 
				"set @ObjetoId   = @Sy_ObjetoId--##\r\n" + 
				"\r\n" + 
				"/*SP Transaccion*/\r\n" + 
				"if @TranCounter > 0 begin\r\n" + 
				"	save transaction SP_EstandarTRA\r\n" + 
				"end else begin\r\n" + 
				"	begin tran SP_EstandarTRA\r\n" + 
				"end\r\n" + 
				"\r\n" + 
				"begin try\r\n" + 
				"--	/*Validacion:*/\r\n" + 
				"--	if (select count(*)\r\n" + 
				"--			from #Tabla) = 0 begin\r\n" + 
				"--\r\n" + 
				"--		/*Marca Error Controlado*/\r\n" + 
				"--		set @Err_Message  = 'Error'\r\n" + 
				"--		raiserror (@Err_Message, 11,10)\r\n" + 
				"--	end \r\n" + 
				"--	else begin\r\n" + 
				"\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"		/*Inicio Codigo de SP:..*/\r\n" + 
				"\r\n" + 
				"			/***************************CODIGO***************************************/\r\n" + 
				"\r\n" + 
				"			/***************************CODIGO***************************************/\r\n" + 
				"";
	}
	private String getPrimerParteTranUPD() {
		return "	/***** (Desarrollador) *****/\r\n" + 
				"\r\n" + 
				"	/*Parametros Obligatorios*/\r\n" + 
				"	@NumeroTransaccion	BigInt        = NULL,	--  Transaccion de Cartera para Identificar su Afectaciones    /*Esto es obligatorio para los Sp que Afecten la Cartera*/\r\n" + 
				"	@Transaccion        BigInt        = NULL,	--  Transaccion de Registro de Actividades\r\n" + 
				"	@Sy_Paso            int           = NUll,	--  Valor del paso que se esta ejecutando\r\n" + 
				"	@UsurarioId         Int           = NULL,	--  Usuraio que Ejecuta SP\r\n" + 
				"	@IP                 Varchar(15)   = NULL,	--  IP de Usuario\r\n" + 
				"	@ObjetoId           Int           = NULL	--  Objeto de Donde se Ejecuta El SP\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"AS\r\n" + 
				"\r\n" + 
				"/*Deshabilita la salida de counts en los SP*/\r\n" + 
				"SET NOCOUNT ON\r\n" + 
				"\r\n" + 
				"/*Declaracion de Variables*/\r\n" + 
				"/*Variables de Registro*/\r\n" + 
				"declare	@Parametros		Varchar(1000),  --  Cadena de parametros del SP\r\n" + 
				"		@Err_Message	Varchar(1000),  --  Mensage de salida del sp se puede recuperar con ERROR_MESSAGE() /*Esto nos permite Mandar Errores Controlados\r\n" + 
				"		@TranCounter	Int,            --  Verificar si Existe Transaccion Abierta\r\n" + 
				"		@Sy_Accion		Int,            --  Si es Inicio (1) o Fin (2) del Registro de Actividad\r\n" + 
				"		@Sy_ObjetoId	Int,            --  ObjetoId al que pertenece el SP\r\n" + 
				"		@Comando		Varchar(100)    --  SP que se Ejecuta con todo y parametros\r\n" + 
				"\r\n" + 
				"/*Variables de SP*/\r\n" + 
				"\r\n" + 
				"/*\r\n" + 
				"/*Declaracion de  variables tipo tabla*/\r\n" + 
				"declare @Tabla table (\r\n" + 
				"	Campos  int\r\n" + 
				")\r\n" + 
				"*/\r\n" + 
				"\r\n" + 
				"/***************************NOTA***************************************/\r\n" + 
				"/*Declaracion de  Tablas Temporales */\r\n" + 
				"/* Opcional */\r\n" + 
				"/* Si se va a verificar que  una tabla temporal no exista */\r\n" + 
				"--if OBJECT_ID('tempdb..#Tabla') is not null begin\r\n" + 
				"--	drop table #Tabla\r\n" + 
				"--end else begin\r\n" + 
				"--	create Table #Tabla (\r\n" + 
				"--		Campos  varchar(1000)) \r\n" + 
				"--end\r\n" + 
				"\r\n" + 
				"/* Opcional */\r\n" + 
				"/*Si se va a utilizar una tabla temporal de otro Sp debes de crear la tabla en el sp*/\r\n" + 
				"--if object_id('tempdb..#Tabla') is null begin\r\n" + 
				"--	create table #Tabla (\r\n" + 
				"--		Campos  int) \r\n" + 
				"--end\r\n" + 
				"\r\n" + 
				"/*NOTA: No se pueden usar las dos para la misma tabla*/\r\n" + 
				"/******************************************************************/\r\n" + 
				"/* Inicializacion de Variables */\r\n" + 
				"/* Parametros del SP */\r\n" + 
				"/* Variables de Registro */\r\n" + 
				"set	@Parametros		= "+getParametrosValoresUPD()+"\r\n" + 
				"set	@Err_Message	= NULL\r\n" + 
				"set	@TranCounter	= @@TRANCOUNT\r\n" + 
				"set	@Sy_Accion		= 1\r\n" + 
				"set	@Sy_Paso		= isnull(@Sy_Paso,1)\r\n" + 
				"set	@Comando		= substring('EXEC "+getNombreCompleto()+"' + '@Parametros  = ' + ltrim(rtrim(convert(varchar,@Parametros))),1,1000)\r\n" + 
				"set @Sy_ObjetoId	= 14-- Asignar el Id del SP\r\n" + 
				"\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"/* Variables SP */\r\n" + 
				"\r\n" + 
				"/* Variables Tipo Tabla */\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"\r\n" + 
				"/* Variables tabla Temporal */\r\n" + 
				"--insert into #Tabla values(@Parametros)\r\n" + 
				"\r\n" + 
				"/*Registro de Actividad Inicio*/\r\n" + 
				"exec ["+this.auditoria+"].[Auditoria].[Log_Registra]  @Transaccion output,@Sy_Paso output,  @UsurarioId output, @IP output, @ObjetoId ,@Sy_ObjetoId ,  @NumeroTransaccion output, @Sy_Accion, @Comando, @Err_Message output\r\n" + 
				"\r\n" + 
				"/*Asigna el objetoId de este SP por sise ejecuta adentro*/\r\n" + 
				"set @ObjetoId   = @Sy_ObjetoId--##\r\n" + 
				"\r\n" + 
				"/*SP Transaccion*/\r\n" + 
				"if @TranCounter > 0 begin\r\n" + 
				"	save transaction SP_EstandarTRA\r\n" + 
				"end else begin\r\n" + 
				"	begin tran SP_EstandarTRA\r\n" + 
				"end\r\n" + 
				"\r\n" + 
				"begin try\r\n" + 
				"--	/*Validacion:*/\r\n" + 
				"--	if (select count(*)\r\n" + 
				"--			from #Tabla) = 0 begin\r\n" + 
				"--\r\n" + 
				"--		/*Marca Error Controlado*/\r\n" + 
				"--		set @Err_Message  = 'Error'\r\n" + 
				"--		raiserror (@Err_Message, 11,10)\r\n" + 
				"--	end \r\n" + 
				"--	else begin\r\n" + 
				"\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"		/*Inicio Codigo de SP:..*/\r\n" + 
				"\r\n" + 
				"			/***************************CODIGO***************************************/\r\n" + 
				"\r\n" + 
				"			/***************************CODIGO***************************************/\r\n" + 
				"";
	}
	

	private String getPrimerParteTranDEL() {
		return "	/***** (Desarrollador) *****/\r\n" + 
				"\r\n" + 
				"	/*Parametros Obligatorios*/\r\n" + 
				"	@NumeroTransaccion	BigInt        = NULL,	--  Transaccion de Cartera para Identificar su Afectaciones    /*Esto es obligatorio para los Sp que Afecten la Cartera*/\r\n" + 
				"	@Transaccion        BigInt        = NULL,	--  Transaccion de Registro de Actividades\r\n" + 
				"	@Sy_Paso            int           = NUll,	--  Valor del paso que se esta ejecutando\r\n" + 
				"	@UsurarioId         Int           = NULL,	--  Usuraio que Ejecuta SP\r\n" + 
				"	@IP                 Varchar(15)   = NULL,	--  IP de Usuario\r\n" + 
				"	@ObjetoId           Int           = NULL	--  Objeto de Donde se Ejecuta El SP\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"AS\r\n" + 
				"\r\n" + 
				"/*Deshabilita la salida de counts en los SP*/\r\n" + 
				"SET NOCOUNT ON\r\n" + 
				"\r\n" + 
				"/*Declaracion de Variables*/\r\n" + 
				"/*Variables de Registro*/\r\n" + 
				"declare	@Parametros		Varchar(1000),  --  Cadena de parametros del SP\r\n" + 
				"		@Err_Message	Varchar(1000),  --  Mensage de salida del sp se puede recuperar con ERROR_MESSAGE() /*Esto nos permite Mandar Errores Controlados\r\n" + 
				"		@TranCounter	Int,            --  Verificar si Existe Transaccion Abierta\r\n" + 
				"		@Sy_Accion		Int,            --  Si es Inicio (1) o Fin (2) del Registro de Actividad\r\n" + 
				"		@Sy_ObjetoId	Int,            --  ObjetoId al que pertenece el SP\r\n" + 
				"		@Comando		Varchar(100)    --  SP que se Ejecuta con todo y parametros\r\n" + 
				"\r\n" + 
				"/*Variables de SP*/\r\n" + 
				"\r\n" + 
				"/*\r\n" + 
				"/*Declaracion de  variables tipo tabla*/\r\n" + 
				"declare @Tabla table (\r\n" + 
				"	Campos  int\r\n" + 
				")\r\n" + 
				"*/\r\n" + 
				"\r\n" + 
				"/***************************NOTA***************************************/\r\n" + 
				"/*Declaracion de  Tablas Temporales */\r\n" + 
				"/* Opcional */\r\n" + 
				"/* Si se va a verificar que  una tabla temporal no exista */\r\n" + 
				"--if OBJECT_ID('tempdb..#Tabla') is not null begin\r\n" + 
				"--	drop table #Tabla\r\n" + 
				"--end else begin\r\n" + 
				"--	create Table #Tabla (\r\n" + 
				"--		Campos  varchar(1000)) \r\n" + 
				"--end\r\n" + 
				"\r\n" + 
				"/* Opcional */\r\n" + 
				"/*Si se va a utilizar una tabla temporal de otro Sp debes de crear la tabla en el sp*/\r\n" + 
				"--if object_id('tempdb..#Tabla') is null begin\r\n" + 
				"--	create table #Tabla (\r\n" + 
				"--		Campos  int) \r\n" + 
				"--end\r\n" + 
				"\r\n" + 
				"/*NOTA: No se pueden usar las dos para la misma tabla*/\r\n" + 
				"/******************************************************************/\r\n" + 
				"/* Inicializacion de Variables */\r\n" + 
				"/* Parametros del SP */\r\n" + 
				"/* Variables de Registro */\r\n" + 
				"set	@Parametros		= "+getParametrosValoresDEL()+"\r\n" + 
				"set	@Err_Message	= NULL\r\n" + 
				"set	@TranCounter	= @@TRANCOUNT\r\n" + 
				"set	@Sy_Accion		= 1\r\n" + 
				"set	@Sy_Paso		= isnull(@Sy_Paso,1)\r\n" + 
				"set	@Comando		= substring('EXEC "+getNombreCompleto()+"' + '@Parametros  = ' + ltrim(rtrim(convert(varchar,@Parametros))),1,1000)\r\n" + 
				"set @Sy_ObjetoId	= 14-- Asignar el Id del SP\r\n" + 
				"\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"/* Variables SP */\r\n" + 
				"\r\n" + 
				"/* Variables Tipo Tabla */\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"\r\n" + 
				"/* Variables tabla Temporal */\r\n" + 
				"--insert into #Tabla values(@Parametros)\r\n" + 
				"\r\n" + 
				"/*Registro de Actividad Inicio*/\r\n" + 
				"exec ["+this.auditoria+"].[Auditoria].[Log_Registra]  @Transaccion output,@Sy_Paso output,  @UsurarioId output, @IP output, @ObjetoId ,@Sy_ObjetoId ,  @NumeroTransaccion output, @Sy_Accion, @Comando, @Err_Message output\r\n" + 
				"\r\n" + 
				"/*Asigna el objetoId de este SP por sise ejecuta adentro*/\r\n" + 
				"set @ObjetoId   = @Sy_ObjetoId--##\r\n" + 
				"\r\n" + 
				"/*SP Transaccion*/\r\n" + 
				"if @TranCounter > 0 begin\r\n" + 
				"	save transaction SP_EstandarTRA\r\n" + 
				"end else begin\r\n" + 
				"	begin tran SP_EstandarTRA\r\n" + 
				"end\r\n" + 
				"\r\n" + 
				"begin try\r\n" + 
				"--	/*Validacion:*/\r\n" + 
				"--	if (select count(*)\r\n" + 
				"--			from #Tabla) = 0 begin\r\n" + 
				"--\r\n" + 
				"--		/*Marca Error Controlado*/\r\n" + 
				"--		set @Err_Message  = 'Error'\r\n" + 
				"--		raiserror (@Err_Message, 11,10)\r\n" + 
				"--	end \r\n" + 
				"--	else begin\r\n" + 
				"\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"		/*Inicio Codigo de SP:..*/\r\n" + 
				"\r\n" + 
				"			/***************************CODIGO***************************************/\r\n" + 
				"\r\n" + 
				"			/***************************CODIGO***************************************/\r\n" + 
				"";
	}
	
	private String getIniVariablesAuditoria() {
		
		return  "/*Variables de SP*/\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"/*declare @AccBackup	int,\r\n" + 
				"		@FecFin		smalldatetime*/\r\n" + 
				"/***** (Desarrollador) *****/" +
				"/* Opcional */\r\n" + 
				"/*Declaracion de  variables tipo tabla*/\r\n" + 
				"/*declare @Tabla table (\r\n" + 
				"	Campos  int\r\n" + 
				")*/\r\n" + 
				"\r\n" + 
				"/***************************NOTA***************************************/\r\n" + 
				"/* Opcional */\r\n" + 
				"/*Declaracion de  Tablas Temporales */\r\n" + 
				"/* Si se va a verificar que  una tabla temporal no exista */\r\n" + 
				"/*if OBJECT_ID('tempdb..#Tabla') is not null begin\r\n" + 
				"	drop table #Tabla\r\n" + 
				"end else begin\r\n" + 
				"	create Table #Tabla (\r\n" + 
				"		Campos  varchar(1000)) \r\n" + 
				"end*/\r\n" + 
				"\r\n" + 
				"/* Opcional */\r\n" + 
				"/*Si se va a utilizar una tabla temporal de otro Sp debes de crear la tabla en el sp*/\r\n" + 
				"/*if object_id('tempdb..#Tabla') is null begin\r\n" + 
				"	create table #Tabla (\r\n" + 
				"		Campos  int) \r\n" + 
				"end*/ \r\n" +
				"/******************************************************************/\r\n" + 
				"/* Inicializacion de Variables */\r\n" + 
				"/*Parametros de Registro*/\r\n" + 
				"set @Sy_ObjetoId  = 10	-- Asignar el Id del SP\r\n" + 
				"\r\n" + 
				"if isnull((select [Registro]\r\n" + 
				"				from ["+this.auditoria+"].[Auditoria].[tb_Objetos]\r\n" + 
				"				where [id_Objeto] = @Sy_ObjetoId),0) = 0 begin /* Verifica si se va registrar o no el objeto que manda llamar */\r\n" + 
				"\r\n" + 
				"	set @Sy_ObjetoId	= 0\r\n" + 
				"\r\n" + 
				"end else begin\r\n" + 
				"\r\n" + 
				"	if isnull((select [Registro]\r\n" + 
				"					from ["+this.auditoria+"].[Auditoria].[tb_Objetos] /*Verifica si se va registrar o este SP*/\r\n" + 
				"					where [id_Objeto]=@ObjetoId),0) = 0 begin\r\n" + 
				"\r\n" + 
				"		set @Sy_ObjetoId	= 0\r\n" + 
				"	end\r\n" + 
				"end\r\n" + 
				"	\r\n" + 
				"/* Parametros del SP */\r\n" + 
				"/* Variables de Registro */\r\n" + 
				"set	@Parametros		= "+getParametrosValores()+"\r\n" + 
				"set	@Err_Message	= NULL\r\n" + 
				"set	@TranCounter	= @@TRANCOUNT\r\n" + 
				"set	@Sy_Accion		= 1\r\n" + 
				"set	@Sy_Paso		= isnull(@Sy_Paso,1)\r\n" + 
				"set	@Comando		= substring('EXEC "+this.esquema+"."+this.nombre+"' + '@Parametros  = ' + ltrim(rtrim(convert(varchar,@Parametros))),1,1000)\r\n" + 
				"\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"/* Variables SP */\r\n" + 
				"--set	@AccBackup	= 1\r\n" + 
				"--set	@FecFin		= dateadd(d, 1, @FecIni)\r\n" + 
				"\r\n" + 
				"/*Variables Tipo Tabla*/\r\n" + 
				"\r\n" + 
				"/***** (Desarrollador) *****/\r\n" + 
				"\r\n" + 
				"/*Variables tabla Temporal*/\r\n" + 
				"--insert into #Tabla values(@Parametros)\r\n" +
				"/*Registro de Actividad Inicio*/\r\n" + 
				"if @Sy_ObjetoId <> 0 begin\r\n" + 
				"	exec ["+this.auditoria+"].[Auditoria].[Log_Registra]  @Transaccion output,@Sy_Paso output,  @UsurarioId output, @IP output, @ObjetoId ,@Sy_ObjetoId ,  @NumeroTransaccion output, @Sy_Accion, @Comando, @Err_Message output\r\n" + 
				"end\r\n" + 
				"\r\n" + 
				"/*Asigna el objetoId de este SP por sise ejecuta adentro*/\r\n" + 
				"set @ObjetoId   = @Sy_ObjetoId--##\r\n" + 
				"\r\n" + 
				"begin try\r\n" + 
				"	/*Validacion:*/\r\n" + 
				"--	if (select count(*)\r\n" + 
				"--			from #Tabla) = 0 begin\r\n" + 
				"\r\n" + 
				"		/*Marca Error Controlado*/\r\n" + 
				"--		set @Err_Message  = 'Error'\r\n" + 
				"--		raiserror (@Err_Message, 11,10)\r\n" + 
				"--	end else \r\n" + 
				"--begin\r\n" + 
				"";
	}
	private String getFinAuditoria() {

		return "set @Err_Message  = 'Finalizo Correctamente'\r\n" + 
				"\r\n" + 
				"		raiserror (@Err_Message, 0,0)\r\n" + 
				"\r\n" + 
				"		/*Registro de Actividad FIN*/\r\n" + 
				"		set @Sy_Accion            = 2\r\n" + 
				"			\r\n" + 
				"		if @Sy_ObjetoId <> 0 begin\r\n" + 
				"			exec ["+this.auditoria+"].[Auditoria].Log_Registra  @Transaccion,@Sy_Paso,  @UsurarioId, @IP, @ObjetoId,       @Sy_ObjetoId ,  @NumeroTransaccion, @Sy_Accion, @Comando, @Err_Message\r\n" + 
				"		end\r\n" + 
				"\r\n" + 
				"		set nocount off\r\n" + 
				"\r\n" + 
				"--	end\r\n" + 
				"\r\n" + 
				"end try\r\n" + 
				"\r\n" + 
				"begin catch\r\n" + 
				"\r\n" + 
				"	set @Err_Message  = substring(ltrim(rtrim(convert(varchar,Error_Line()))) + ' - ' + ISNULL( @Err_Message,ERROR_MESSAGE()),1,1000)\r\n" + 
				"	/*Registro de Actividad FIN*/\r\n" + 
				"\r\n" + 
				"	set @Sy_Accion            = 2\r\n" + 
				"\r\n" + 
				"	if @Sy_ObjetoId <> 0 begin\r\n" + 
				"		exec ["+this.auditoria+"].[Auditoria].Log_Registra  @Transaccion,@Sy_Paso,  @UsurarioId, @IP, @ObjetoId,@Sy_ObjetoId ,  @NumeroTransaccion, @Sy_Accion, @Comando, @Err_Message\r\n" + 
				"	end\r\n" + 
				"      \r\n" + 
				"	set nocount off\r\n" + 
				"\r\n" + 
				"	raiserror (@Err_Message, 11,10)\r\n" + 
				"\r\n" + 
				"end catch";
	}
	public String getController() {
		return "";
	}
	
	public String getInsertWS() {
		String insert = "[HttpPost]\r\npublic string Insert("+ this.getNombre() +" O){\r\ntry\r\n{";
		String parametros = "";
		String columnas = "";
		String parametros2 = "";
		
		for(int i = 0; i < this.columnas.size(); i++) {
			Columna c = this.columnas.get(i);
			if(!c.getPrimary()) {
				parametros += this.columnas.get(i).getParametroInsert() + ",\r\n";
				columnas += c.getNombre() + ",";
				parametros2 += "@" + c.getNombre() + ",";
			}
		}
		
		parametros = parametros.substring(0, parametros.length() - 3);
		columnas = columnas.substring(0, columnas.length() - 1);
		parametros2 = parametros2.substring(0, parametros2.length() - 1);
		insert += columnas + ")\r\nVALUES\r\n(" + parametros2 + ")\r\n";
		insert = parametros + "\r\nAS\r\n" + insert;
		
		return getSP("ALT", insert);
	}
	
	public void generarArchivo(String path, String archivo, String codigo, String ext) {
		String fileName = path + archivo + "." + ext;
		File file  = new File(String.valueOf(fileName));
		File directory = new File(String.valueOf(path));
		try {
			if(!directory.exists()){
				directory.mkdirs();
			    if(!file.exists()){
			    	file.createNewFile();
			    }
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(codigo);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private String getSP(String accion, String script) {
		String sp = "";
		sp += "IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'["+this.esquema+"].[SP_"+ this.nombre + accion + "]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\nBEGIN DROP PROCEDURE ["+this.esquema+"].SP_" + this.nombre + accion + " END\r\nSET ANSI_NULLS ON\r\nGO\r\nSET QUOTED_IDENTIFIER ON\r\nGO\r\nCREATE PROCEDURE "+this.esquema+".SP_" + this.nombre + accion + "\r\n" + script + "\r\nGO\r\n\r\n\r\n";
		return limpiarPrefijos(sp);
	}
	
	private String getSPAuditoria(String accion, String script) {
		String sp = "\r\n" + 
				"/*****************************************************************\r\n" + 
				"** ObjetoId                       : ##\r\n" + 
				"** Nombre del Objeto       : ["+this.esquema+"].[SP_"+ this.nombre + accion + "]\r\n" + 
				"** Descripcion                    : Estructura de los stored procedures a crear en                                           \r\n" + 
				"                                               la base de datos    \r\n" + 
				"\r\n" + 
				"******************************************************************/\r\n" + 
				"\r\n" + 
				"/*\r\n" + 
				"Debes tener registrado el Sistema al que pertenece el Stores en\r\n" + 
				"select * from GarSaAdmin.Auditoria.TB_Sistemas\r\n" + 
				"\r\n" + 
				"Tambien se debe registrar el SP\r\n" + 
				"insert into GarSaAdmin.Auditoria.TB_Objetos values ('"+this.esquema+"."+ this.nombre + accion +"', 'SP "+accion+" de la tabla "+ getNombreCompleto() +"', 10, 0)\r\n" + 
				"select * from GarSaAdmin.Auditoria.TB_Objetos\r\n" + 
				"\r\n" + 
				"Validar que haya insertado info en las tablas de auditoria\r\n" + 
				"select * from GarSaAdmin.Auditoria.tb_Procesos order by FechaSys desc\r\n" + 
				"select * from GarSaAdmin.Auditoria.tb_ProcesosDetalle order by FechaInicio desc\r\n" + 
				"*/\r\n";
		sp += "IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'["+this.esquema+"].[SP_"+ this.nombre + accion + "]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\nBEGIN DROP PROCEDURE ["+this.esquema+"].SP_" + this.nombre + accion + " END\r\nSET ANSI_NULLS ON\r\nGO\r\nSET QUOTED_IDENTIFIER ON\r\nGO\r\nCREATE PROCEDURE "+this.esquema+".SP_" + this.nombre + accion + "\r\n" + script + "\r\nGO\r\n";
		return sp;
	}
	private String limpiarPrefijos(String codigo) {
		for(int i = 0; i < this.prefijos.length; i++) {
			codigo = codigo.replaceAll("SP_"+this.prefijos[i],"SP_");
		}
		return codigo;
	}
}
