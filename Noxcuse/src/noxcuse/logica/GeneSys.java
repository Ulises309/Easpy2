package noxcuse.logica;

public class GeneSys {
	public static final String [] TABLAS = { 
								"Usuario",
								"Accesos",
								/*"Campos",*/
								"CamposLabel",
								"Cat_Control",
								"Cat_Estatus",
								"Cat_Menu",
								/*"Cat_Pagina",*/
								"Cat_Parametros",
								"Cat_TipoAcceso",
								"Cat_TipoUsuario",
								"Cat_Variable",
								"Error",
								"LogTemporal",
								/*"Reportes",*/
								"ReportesRestringidos",
								"UsuarioReportesRestringidos"
							  }; 
	
	public static final String [] TABLAS_AUDITORIA = { 
			"tb_Objetos",
			"tb_Procesos",
			"tb_Procesos_Hist",
			"tb_ProcesosDetalle",
			"tb_ProcesosDetalle_Hist",
			"Tb_Sistemas",
			"Tb_SistemasEmpresas",
			"tb_TipoSistema"
		  }; 
	
	public static final String [] FUNCIONES = {
								"Fn_Split"
							  };
	
	public static final String [] STOREDPROCEDURES = {
			"AltaReporte",
			"obtenerSP",
			"obtenerMenu",
			"obtenerDataSet",
			"SP_ReportesCON",
			"SP_CamposCON",
			"AltaUsuario",
			"SP_ValidarUsuario",
			"SP_UsuarioValidarToken",
			"SP_ObtenerLlavePrimariaUsuario",
			"SP_UsuarioALT",
		  };
	
	public static final String SCHEMA = "GeneSys";
	
	public static final String [] SCRIPT = 
		{"\r\n" + 
			"-- ----------------------------------------------------\r\n" + 
			"--\r\n" + 
			"-- GeneSys\r\n" + 
			"--\r\n" + 
			"-- ----------------------------------------------------\r\n",
			"CREATE SCHEMA [GeneSys]",
			"CREATE TABLE [GeneSys].[Cat_TipoUsuario](\r\n" + 
			"	[Id_TipoUsuario] [int] NOT NULL IDENTITY,\r\n" + 
			"	[TipoUsuario] [varchar](50) NULL,\r\n" + 
			"	[Descripcion] [varchar](255) NULL,\r\n" + 
			"	[Estatus_Id] [int] NULL,\r\n" + 
			" CONSTRAINT [PK_Cat_TipoUsuario] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_TipoUsuario] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n"  ,
			"CREATE TABLE [GeneSys].[Usuario](\r\n" + 
			"	[Id_Usuario] [int] NOT NULL IDENTITY,\r\n" + 
			"	[Perfil_Id] [int] NULL,\r\n" + 
			"	[Nombres] [varchar](50) NULL,\r\n" + 
			"	[ApellidoP] [varchar](50) NULL,\r\n" + 
			"	[ApellidoM] [varchar](50) NULL,\r\n" + 
			"	[RFC] [varchar](13) NULL,\r\n" + 
			"	[Lada] [varchar](4) NULL,\r\n" + 
			"	[Telefono] [varchar](15) NULL,\r\n" + 
			"	[Email] [varchar](50) NULL,\r\n" + 
			"	[Usuario] [varchar](30) NOT NULL,\r\n" + 
			"	[Password] [varchar](256) NOT NULL,\r\n" + 
			"	[TipoUsuario_Id] [int] NOT NULL,\r\n" + 
			"	[Sucursal_Id] [int] NULL,\r\n" + 
			"	[Estatus_Id] [int] NOT NULL,\r\n" + 
			"	[Bloquear] [int] NULL,\r\n" + 
			"	[EnSession] [int] NULL,\r\n" + 
			"	[Fch_Bloqueo] [smalldatetime] NULL,\r\n" + 
			"	[SoloOficina] [bit] NULL,\r\n" + 
			"	[token] VARCHAR(8) NULL DEFAULT '',\r\n" + 
			"	[privateKey] VARCHAR(2048) NULL DEFAULT '',\r\n" + 
			"	[ultimaActividad] DATETIME NULL,\r\n" + 
			"	[ip] VARCHAR(30) NOT NULL DEFAULT '',\r\n" + 
			"	[numeroPeticion] INT NOT NULL DEFAULT 0,\r\n" + 
			"	[CambiarPassword] [bit] NOT NULL,\r\n" + 
			"	[FechaCambioPassword] [datetime] NULL\r\n" + 
			" CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Usuario] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n"  ,
			"/****** Object:  Table [GeneSys].[Accesos]    Script Date: 05/07/2018 10:40:19 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Accesos](\r\n" + 
			"	[Id_Acceso] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Reporte_Id] [int] NULL,\r\n" + 
			"	[UsuarioTipo_ID] [int] NULL,\r\n" + 
			"	[TipoAcceso_Id] [int] NULL,\r\n" + 
			"	[TipoAcc] [int] NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[Campos]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Campos](\r\n" + 
			"	[ID_Campo] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Reporte_Id] [int] NOT NULL,\r\n" + 
			"	[Variable_Id] [int] NULL,\r\n" + 
			"	[Control_Id] [int] NULL,\r\n" + 
			"	[Titulo_Pagina] [varchar](30) NULL,\r\n" + 
			"	[Nombre] [varchar](100) NULL,\r\n" + 
			"	[Orden] [int] NULL,\r\n" + 
			"	[Visible] [int] NULL,\r\n" + 
			"	[Valor_Variable] [varchar](60) NULL,\r\n" + 
			"	[DataSet] [varchar](300) NULL,\r\n" + 
			"	[DS] [varchar](1000) NULL,\r\n" + 
			"	[Parametro] [nvarchar](30) NULL,\r\n" + 
			"	[ComboPadre] [varchar](30) NULL,\r\n" + 
			"	[FileJScript] [varchar](100) NULL,\r\n" + 
			"	[FuncionJS] [varchar](50) NULL,\r\n" + 
			"	[Evento] [varchar](50) NULL,\r\n" + 
			"	[CssFile] [varchar](100) NULL,\r\n" + 
			"	[Css] [varchar](50) NULL,\r\n" + 
			"	[Ancho] [int] NULL,\r\n" + 
			"	[Longitud] [int] NULL,\r\n" + 
			"	[Renglon] [int] NULL,\r\n" + 
			"	[Class] [varchar](100) NULL,\r\n" + 
			"	[Parsley] [varchar](max) NULL,\r\n" + 
			"	[Omitir] [bit] NOT NULL,\r\n" + 
			"PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[ID_Campo] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[CamposLabel]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[CamposLabel](\r\n" + 
			"	[Id_Label] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Campo_Id] [int] NULL,\r\n" + 
			"	[Descripcion] [varchar](1000) NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[Cat_Control]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Cat_Control](\r\n" + 
			"	[Id_Control] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Descripcion] [varchar](50) NULL,\r\n" + 
			"	[Clave] [varchar](3) NULL,\r\n" + 
			"PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Control] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[Cat_Estatus]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Cat_Estatus](\r\n" + 
			"	[Id_Estatus] [bit] NULL,\r\n" + 
			"	[Descripcion] [varchar](9) NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[Cat_Menu]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Cat_Menu](\r\n" + 
			"	[IdMenu] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Menu] [varchar](100) NULL,\r\n" + 
			"	[Descripcion] [varchar](255) NULL,\r\n" + 
			"	[Carpeta] [varchar](50) NULL,\r\n" + 
			"	[URL] [varchar](max) NULL,\r\n" + 
			"	[Padre] [int] NULL,\r\n" + 
			"	[Orden] [int] NULL,\r\n" + 
			"	[IdEstatus] [int] NULL,\r\n" + 
			"	[SistemaId] [int] NULL,\r\n" + 
			"	[class] [varchar](50) NULL,\r\n" + 
			"	[TipoRedirect] [varchar](15) NULL,\r\n" + 
			" CONSTRAINT [PK_TB_CATMenu] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[IdMenu] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[Cat_Pagina]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Cat_Pagina](\r\n" + 
			"	[Id_Pagina] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Descripcion] [varchar](50) NULL,\r\n" + 
			"	[Estatus_Id] [bit] NULL,\r\n" + 
			"	[Tipo] [int] NULL,\r\n" + 
			"PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Pagina] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[Cat_Parametros]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Cat_Parametros](\r\n" + 
			"	[Id_Parametro] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Descripcion] [varchar](30) NULL,\r\n" + 
			"	[Clave] [varchar](3) NULL,\r\n" + 
			"	[Val_Varchar] [varchar](50) NULL\r\n" + 
			" CONSTRAINT [PK_Cat_Parametros] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Parametro] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" ,
			"/****** Object:  Table [GeneSys].[Cat_TipoAcceso]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Cat_TipoAcceso](\r\n" + 
			"	[Id_TipoAcceso] [int] NULL,\r\n" + 
			"	[Descripcion] [varchar](30) NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"/****** Object:  Table [GeneSys].[Cat_Variable]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Cat_Variable](\r\n" + 
			"	[Id_Variable] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Descripcion] [varchar](50) NULL,\r\n" + 
			"	[Clave] [varchar](3) NULL,\r\n" + 
			"PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Variable] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" ,
			"/****** Object:  Table [GeneSys].[Error]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Error](\r\n" + 
			"	[IDError] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Fecha] [datetime] NULL,\r\n" + 
			"	[IDUsuario] [int] NULL,\r\n" + 
			"	[URL] [varchar](1000) NULL,\r\n" + 
			"	[Error] [varchar](1000) NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" ,
			"/****** Object:  Table [GeneSys].[LogTemporal]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[LogTemporal](\r\n" + 
			"	[id] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Str_Log] [varchar](max) NULL,\r\n" + 
			" CONSTRAINT [PK_LogTemporal] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[id] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]\r\n" + 
			"\r\n" ,
			"/****** Object:  Table [GeneSys].[Reportes]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[Reportes](\r\n" + 
			"	[Id_Reporte] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Nombre] [nvarchar](50) NOT NULL,\r\n" + 
			"	[Sp] [nvarchar](150) NOT NULL,\r\n" + 
			"	[Pagina_Id] [int] NULL,\r\n" + 
			"	[Estatus_Id] [bit] NULL,\r\n" + 
			"	[Descripcion] [nvarchar](250) NULL,\r\n" + 
			"	[Nombre_Archivo] [nchar](10) NULL,\r\n" + 
			"	[Orden] [int] NULL,\r\n" + 
			"	[Campo_Nombre] [nvarchar](50) NULL,\r\n" + 
			"	[Encoding] [int] NULL,\r\n" + 
			"	[Ruta_Default] [nvarchar](100) NULL,\r\n" + 
			"	[Servidor] [nvarchar](30) NULL,\r\n" + 
			"	[DB] [nvarchar](30) NULL,\r\n" + 
			"	[Esquema] [nvarchar](30) NULL,\r\n" + 
			"	[Ejecuciones] [int] NULL,\r\n" + 
			"	[Fecha_Ult_Exec] [smalldatetime] NULL,\r\n" + 
			"	[Fecha_Creacion] [smalldatetime] NULL,\r\n" + 
			"	[RegGrid] [int] NULL,\r\n" + 
			"	[Padre] [int] NULL,\r\n" + 
			"	[class] [varchar](100) NULL,\r\n" + 
			"	[URL] [varchar](max) NULL,\r\n" + 
			"	[SistemaId] [int] NULL,\r\n" + 
			"	[TipoRedirect] [varchar](15) NULL,\r\n" + 
			"	[Menu_Id] [int] NULL,\r\n" + 
			" CONSTRAINT [PK__Reportes__6ED7B73A4BAD0A80] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Reporte] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[ReportesRestringidos]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[ReportesRestringidos](\r\n" + 
			"	[ID] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[ObjRep_Id] [int] NULL,\r\n" + 
			"	[Tipo_Id] [int] NULL,\r\n" + 
			"	[MaxEjecucionesDiarias] [int] NULL,\r\n" + 
			"	[Aviso] [int] NULL,\r\n" + 
			"	[HoraIni] [varchar](5) NULL,\r\n" + 
			"	[HoraFin] [varchar](5) NULL,\r\n" + 
			"	[TipoUsuario_ID] [int] NULL,\r\n" + 
			"	[IDEstatus] [int] NULL,\r\n" + 
			"	[Aviso_ID] [int] NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"/****** Object:  Table [GeneSys].[UsuarioReportesRestringidos]    Script Date: 05/07/2018 10:40:20 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" + 
			"CREATE TABLE [GeneSys].[UsuarioReportesRestringidos](\r\n" + 
			"	[ID] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Usuario_ID] [int] NULL,\r\n" + 
			"	[Reporte_ID] [int] NULL,\r\n" + 
			"	[MaxEjecucionesDiarias] [int] NULL,\r\n" + 
			"	[Aviso] [int] NULL,\r\n" + 
			"	[HoraIni] [varchar](5) NULL,\r\n" + 
			"	[HoraFin] [varchar](5) NULL,\r\n" + 
			"	[EjecucionesDiariasReal] [int] NULL,\r\n" + 
			"	[Aviso_ID] [int] NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" , 
			"ALTER TABLE [GeneSys].[Campos] ADD  DEFAULT ((0)) FOR [Omitir]\r\n" + 
			"\r\n" , 
			"ALTER TABLE [GeneSys].[Error] ADD  DEFAULT (getdate()) FOR [Fecha]\r\n" + 
			"\r\n" , 
			"ALTER TABLE [GeneSys].[Reportes] ADD  CONSTRAINT [DF__Reportes__RegGri__4D9552F2]  DEFAULT ((10)) FOR [RegGrid]\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"-- --------------------------------------------\r\n" + 
			"--\r\n" + 
			"-- Funcionalidad STORED PROCEDURES\r\n" + 
			"--\r\n" + 
			"-- --------------------------------------------\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[Fn_Split]') AND OBJECTPROPERTY(id, N'IsTableFunction') = 1 )\r\n" + 
			"BEGIN DROP FUNCTION [GeneSys].[Fn_Split] END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE FUNCTION [GeneSys].[Fn_Split]\r\n" + 
			"(\r\n" + 
			"    @String NVARCHAR(4000),\r\n" + 
			"    @Delimiter NCHAR(1)\r\n" + 
			")\r\n" + 
			"RETURNS TABLE\r\n" + 
			"AS\r\n" + 
			"RETURN\r\n" + 
			"(\r\n" + 
			"    WITH Split(stpos,endpos)\r\n" + 
			"    AS(\r\n" + 
			"        SELECT 0 AS stpos, CHARINDEX(@Delimiter,@String) AS endpos\r\n" + 
			"        UNION ALL\r\n" + 
			"        SELECT endpos+1, CHARINDEX(@Delimiter,@String,endpos+1)\r\n" + 
			"            FROM Split\r\n" + 
			"            WHERE endpos > 0\r\n" + 
			"    )\r\n" + 
			"    SELECT 'Id' = ROW_NUMBER() OVER (ORDER BY (SELECT 1)),\r\n" + 
			"        'Data' = SUBSTRING(@String,stpos,COALESCE(NULLIF(endpos,0),LEN(@String)+1)-stpos)\r\n" + 
			"    FROM Split\r\n" + 
			")\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"CREATE FUNCTION [GeneSys].[Parametro]\r\n" + 
			"(\r\n" + 
			"@Parametro VARCHAR(3)\r\n" + 
			")\r\n" + 
			"RETURNS VARCHAR\r\n" + 
			"AS\r\n" + 
			"BEGIN\r\n" + 
			"	DECLARE @Valor VARCHAR(50)\r\n" + 
			"	SELECT \r\n" + 
			"		@Valor = Val_Varchar \r\n" + 
			"	FROM \r\n" + 
			"		[GeneSys].[Cat_Parametros] \r\n" + 
			"	WHERE \r\n" + 
			"		Clave = @Parametro\r\n" + 
			"\r\n" + 
			"	RETURN @Valor\r\n" + 
			"END",
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[AltaReporte]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].[AltaReporte] END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" , 
			"CREATE procedure [GeneSys].[AltaReporte](    \r\n" + 
			"  @Nombre    nvarchar(50),    \r\n" + 
			"  @Sp     nvarchar(50),    \r\n" + 
			"  @Esquema   nvarchar(50),    \r\n" + 
			"  @BaseDatos   nvarchar(50),    \r\n" + 
			"  @Descripcion  nvarchar(250),     \r\n" + 
			"  @Nombre_Archivo  nchar(10),    \r\n" + 
			"  @Campo_Nombre  nvarchar(50),    \r\n" + 
			"  @Pagina_Id   int = null,    \r\n" + 
			"  @Padre    int = null,    \r\n" + 
			"  @SistemaID   int=null,    \r\n" + 
			"  @URL    varchar(150)=null    \r\n" + 
			")as    \r\n" + 
			"     \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"/*Declaracion de Variables*/    \r\n" + 
			"DECLARE @err_message  VARCHAR(250),    \r\n" + 
			"  @SPNuevo   varchar(157),    \r\n" + 
			"  @I     int,    \r\n" + 
			"  @Orden    int,    \r\n" + 
			"  @Reporte_Id   int,    \r\n" + 
			"  @use    varchar(55),    \r\n" + 
			"      \r\n" + 
			"  @Activo    int,    \r\n" + 
			"  @Fecha    smalldatetime,    \r\n" + 
			"  @Ctl    int,    \r\n" + 
			"  @STR    int,    \r\n" + 
			"  @Int    int,    \r\n" + 
			"  @Nul    int,    \r\n" + 
			"  @Encoding   int    \r\n" + 
			"    \r\n" + 
			"if isnull(@baseDatos,'')=''    \r\n" + 
			" set @BaseDatos=''    \r\n" + 
			"    \r\n" + 
			"      \r\n" + 
			"set @Fecha=convert(smalldatetime,convert(varchar,getdate(),111))    \r\n" + 
			"set @err_message  = null    \r\n" + 
			"set @SPNuevo=upper(case when @BaseDatos<>'' then '['+@BaseDatos+'].' else '' end+'['+ltrim(rtrim(@Esquema))+'].'+ltrim(rtrim(@Sp)))    \r\n" + 
			"set @I=0    \r\n" + 
			"set @Ctl=1    \r\n" + 
			"set @STR=1    \r\n" + 
			"set @Int=2    \r\n" + 
			"set @Nul=5    \r\n" + 
			"set @Encoding=1252 --CodePage 1252 es el Default (iso-8859-1), CodePage 1200 es Unicode, CodePage 65001 es UTF-8    \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"if  (select count(*) from GeneSys.Cat_Pagina where Id_Pagina =@pagina_Id) is null    \r\n" + 
			" select @Pagina_Id  = min( Id_Pagina) from [GeneSys].Cat_Pagina    \r\n" + 
			"    \r\n" + 
			"select @Activo   = 1    \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"DECLARE @TranCounter INT;    \r\n" + 
			"    SET @TranCounter = @@TRANCOUNT    \r\n" + 
			"    IF @TranCounter > 0    \r\n" + 
			"        SAVE TRANSACTION AltaReporte    \r\n" + 
			"    ELSE    \r\n" + 
			"        BEGIN TRAN AltaReporte    \r\n" + 
			"     \r\n" + 
			"     \r\n" + 
			"BEGIN TRY    \r\n" + 
			"     \r\n" + 
			"    \r\n" + 
			"     \r\n" + 
			"      /*Validacion: Verifica que existan SP*/    \r\n" + 
			"IF isnull(  \r\n" + 
			"(  \r\n" + 
			"SELECT [ID]     \r\n" + 
			"FROM [SysObjects]    \r\n" + 
			"where [xtype]='P'    \r\n" + 
			"and category = 0     \r\n" + 
			"and upper(ltrim(rtrim(--case when SCHEMA_NAME(uid)='GeneSys' then '' else     \r\n" + 
			"'['+SCHEMA_NAME(uid)+'].'--end    \r\n" + 
			"+[name]))) = REPLACE(@SPNuevo,'['+@BaseDatos+'].','')  \r\n" + 
			"     ),0)=0  or      \r\n" + 
			"    (select count(*)     \r\n" + 
			"     from [GeneSys].Reportes    \r\n" + 
			"     where Nombre =@nombre )<>0 BEGIN    \r\n" + 
			"             \r\n" + 
			"                    RAISERROR ('Sp no Existe o Nombre de Reporte ya existe', 11,10)    \r\n" + 
			"      END    \r\n" + 
			"      ELSE BEGIN    \r\n" + 
			"                  /*Inicio Codigo de SP:..*/    \r\n" + 
			"    \r\n" + 
			"  if isnull(@Nombre_Archivo,'')='' begin    \r\n" + 
			"    set @Nombre_Archivo='Reporte'    \r\n" + 
			"  end     \r\n" + 
			"    \r\n" + 
			"   SELECT @i=[ID]     \r\n" + 
			"     FROM [SysObjects]    \r\n" + 
			"     where [xtype]='P'    \r\n" + 
			"       and category = 0     \r\n" + 
			"       and upper(ltrim(rtrim(--case when SCHEMA_NAME(uid)='GeneSys' then '' else     \r\n" + 
			"       '['+SCHEMA_NAME(uid)+'].'--end    \r\n" + 
			"       +[name]))) = REPLACE(@SPNuevo,'['+@BaseDatos+'].','')  \r\n" + 
			"      \r\n" + 
			"  select @Orden=count(*) + 1    \r\n" + 
			"   from [GeneSys].Reportes     \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"  insert into [GeneSys].Reportes (Nombre, Sp, Pagina_Id, Estatus_Id, Descripcion, Nombre_Archivo, Orden, Campo_Nombre,     \r\n" + 
			"    DB, Esquema,  Fecha_Creacion,Encoding, Padre, SistemaId, class, URL)    \r\n" + 
			"  select @Nombre,ltrim(rtrim(@Sp)),@Pagina_Id,@Activo,@Descripcion,    \r\n" + 
			"    @Nombre_Archivo,@Orden,@Campo_Nombre,ltrim(rtrim(@BaseDatos)),ltrim(rtrim(@Esquema)),@Fecha,@Encoding,@Padre,@SistemaID,    \r\n" + 
			"    'fa-square', @URL    \r\n" + 
			"       \r\n" + 
			"    \r\n" + 
			"  SET @Reporte_Id = @@IDENTITY    \r\n" + 
			"    \r\n" + 
			"--  select @ReporteId = ReporteId     \r\n" + 
			"--   from Rep_Reportes     \r\n" + 
			"--   where @Nombre=Nombre    \r\n" + 
			"    \r\n" + 
			"    \r\n" + 
			"insert into [GeneSys].Campos ( Reporte_Id, Variable_Id, Control_Id, Titulo_Pagina, Nombre, Orden, Visible,     \r\n" + 
			"        Valor_Variable, DataSet, DS, Parametro)    \r\n" + 
			"select  ReporteId  = @Reporte_Id,    \r\n" + 
			"  CTipoVariable = case when system_type_id in (35,98,99,167,175,231,239,241,231,58,61,189) then @STR    \r\n" + 
			"        when system_type_id in (34,36,48,52,56,59,60,62,104,106,108,122,127,165,173) then @Int    \r\n" + 
			"        else @Nul end,    \r\n" + 
			"  CTipoControl = @Ctl,    \r\n" + 
			"      \r\n" + 
			"  CTitulo   = ltrim(rtrim(substring([Name],2,Len([Name])-1))),    \r\n" + 
			"  CNombre   = ltrim(rtrim(substring([Name],2,Len([Name])-1))),    \r\n" + 
			"      \r\n" + 
			"  COrden   = [parameter_id],    \r\n" + 
			"  CVisible  = @Activo,    \r\n" + 
			"      \r\n" + 
			"  CValorVariable = '',    \r\n" + 
			" CCRDataSet  = '',    \r\n" + 
			"  CTipoDS   = '',    \r\n" + 
			"  CParametro  = ltrim(rtrim([Name]))    \r\n" + 
			"  from sys.parameters      \r\n" + 
			" where  [object_id]=@I    \r\n" + 
			"     \r\n" + 
			"                                \r\n" + 
			"         /*Fin Codifo de SP*/    \r\n" + 
			"  SET @Err_Message  =  'Alta Finalizada Correctamente. Favor de actualizar los campos del Reporte_Id ' +  ltrim(rtrim(convert(char,@Reporte_Id)))     \r\n" + 
			"  select Err_Message = @Err_Message    \r\n" + 
			"  RAISERROR (@Err_Message, 0,0)           \r\n" + 
			"        -----RAISERROR ('Ejecutado Correctamente', 0,0)    \r\n" + 
			"     \r\n" + 
			"                          IF @TranCounter = 0    \r\n" + 
			"                          COMMIT TRAN AltaReporte    \r\n" + 
			"      END    \r\n" + 
			"     \r\n" + 
			"END TRY    \r\n" + 
			"BEGIN CATCH    \r\n" + 
			"     \r\n" + 
			"      SET @err_message=isnull(@err_message,ERROR_MESSAGE())    \r\n" + 
			"     \r\n" + 
			"         \r\n" + 
			"            IF @TranCounter = 0    \r\n" + 
			"            -- Transaction started in procedure.    \r\n" + 
			"            -- Roll back complete transaction.    \r\n" + 
			"            ROLLBACK TRAN AltaReporte    \r\n" + 
			"        ELSE    \r\n" + 
			"                  IF XACT_STATE() <> -1    \r\n" + 
			"                ROLLBACK TRAN AltaReporte        \r\n" + 
			"     \r\n" + 
			"            RAISERROR (@err_message, 11,10)    \r\n" + 
			"               \r\n" + 
			"END CATCH    \r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[obtenerSP]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].[obtenerSP] END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[obtenerSP]\r\n" + 
			"@sp INT,\r\n" + 
			"@usuario_id INT = NULL,\r\n" + 
			"@tipousuario_id INT = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT DISTINCT Store = r.Esquema + '.' + r.sp, ID_Campo, Variable_Id, Control_Id, Titulo_Pagina, c.Nombre, c.Orden, Visible, Valor_Variable, ComboPadre, FileJScript, FuncionJS, Evento, CssFile, Css, Ancho, Longitud, Renglon, c.Class, Parsley\r\n" + 
			"FROM GeneSys.Reportes r\r\n" + 
			"LEFT JOIN GeneSys.Campos c ON r.Id_Reporte = c.Reporte_Id\r\n" + 
			"INNER JOIN GeneSys.Accesos a ON r.Id_Reporte = a.Reporte_Id\r\n" + 
			"WHERE r.Id_Reporte = @sp AND\r\n" + 
			"(\r\n" + 
			"(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)\r\n" + 
			"OR\r\n" + 
			"(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)\r\n" + 
			")\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[obtenerMenu]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].[obtenerMenu] END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE Procedure [GeneSys].obtenerMenu\r\n" + 
			"@usuario_id INT = NULL,\r\n" + 
			"@tipousuario_id INT = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT DISTINCT IdMenu, Menu, Descripcion, Carpeta, URL, Padre, Orden, IdEstatus, SistemaId, class, TipoRedirect\r\n" + 
			"FROM GeneSys.Cat_Menu m\r\n" + 
			"INNER JOIN GeneSys.Accesos a ON m.IdMenu = a.Reporte_Id\r\n" + 
			"WHERE a.TipoAcc = 1 AND\r\n" + 
			"(\r\n" + 
			"(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)\r\n" + 
			"OR\r\n" + 
			"(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)\r\n" + 
			")\r\n" + 
			"" + 
			"\r\n" + 
			"\r\n" ,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[obtenerDataSet]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].[obtenerDataSet] END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE Procedure [GeneSys].obtenerDataSet\r\n" + 
			"@ID_Campo INT,\r\n" + 
			"@usuario_id INT = NULL,\r\n" + 
			"@tipousuario_id INT = NULL\r\n" + 
			"AS\r\n" + 
			"DECLARE @store VARCHAR(100), @ds VARCHAR(30), @opcion VARCHAR(30) = '', @opcion2 VARCHAR(30) = '', @otros VARCHAR(30) = ''\r\n" + 
			"\r\n" + 
			"SELECT @store = DataSet, @ds = DS FROM GeneSys.Campos c\r\n" + 
			"INNER JOIN GeneSys.Reportes r ON r.Id_Reporte = c.Reporte_Id\r\n" + 
			"INNER JOIN GeneSys.Accesos a ON r.Id_Reporte = a.Reporte_Id\r\n" + 
			"WHERE ID_Campo = @ID_Campo AND\r\n" + 
			"(\r\n" + 
			"(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)\r\n" + 
			"OR\r\n" + 
			"(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)\r\n" + 
			")\r\n" + 
			"\r\n" + 
			"SELECT * INTO #coma FROM [GeneSys].Fn_Split(@ds, ',')\r\n" + 
			"DECLARE @tipo VARCHAR(30), @contador INT, @aux VARCHAR(30) = '', @exec VARCHAR(1000)\r\n" + 
			"\r\n" + 
			"SELECT @contador = MIN(id) FROM #coma\r\n" + 
			"WHILE @contador IS NOT NULL\r\n" + 
			"BEGIN\r\n" + 
			"	SELECT @ds = data FROM #coma WHERE Id = @contador\r\n" + 
			"	IF LEN(@ds) < 1\r\n" + 
			"	BEGIN\r\n" + 
			"		SET @contador = NULL\r\n" + 
			"		BREAK\r\n" + 
			"	END\r\n" + 
			"	SELECT TOP 1 @tipo = data FROM [GeneSys].Fn_Split(@ds, '§')\r\n" + 
			"	SELECT TOP 1 @aux = data FROM [GeneSys].Fn_Split(@ds, '§') order by id desc\r\n" + 
			"\r\n" + 
			"	IF @tipo LIKE '%SIN%'\r\n" + 
			"	BEGIN\r\n" + 
			"		IF @aux LIKE '%cve_sucurs%' OR @aux LIKE '%compania%'\r\n" + 
			"			SET @aux = 'IdSucursal'\r\n" + 
			"		IF @aux LIKE '%Rol%'\r\n" + 
			"			SET @aux = 'IdTipoUsuario'\r\n" + 
			"		SET @exec = 'SELECT @aux = '+@aux+' FROM GeneSys.Usuario WHERE IDUsuario = '+CAST(@usuario_id AS VARCHAR)\r\n" + 
			"		EXECUTE (@exec)\r\n" + 
			"	END\r\n" + 
			"	IF @contador = 1\r\n" + 
			"		SET @opcion = @aux\r\n" + 
			"	IF @contador = 2\r\n" + 
			"		SET @opcion2 = ',' + @aux\r\n" + 
			"	IF @contador = 3\r\n" + 
			"		SET @otros = ',' + @aux\r\n" + 
			"\r\n" + 
			"	SELECT @contador = MIN(id) FROM #coma WHERE id > @contador\r\n" + 
			"END\r\n" + 
			"DROP TABLE #coma\r\n" + 
			"SET @store = 'exec ' + @store + ' ' + @opcion + @opcion2 + @otros\r\n" + 
			"EXECUTE (@store)\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_ReportesCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_ReportesCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_ReportesCON\r\n" + 
			"@Id_Reporte int = NULL,\r\n" + 
			"@Nombre nvarchar = NULL,\r\n" + 
			"@Sp nvarchar = NULL,\r\n" + 
			"@Pagina_Id int = NULL,\r\n" + 
			"@Estatus_Id bit = NULL,\r\n" + 
			"@Descripcion nvarchar = NULL,\r\n" + 
			"@Nombre_Archivo nchar = NULL,\r\n" + 
			"@Orden int = NULL,\r\n" + 
			"@Campo_Nombre nvarchar = NULL,\r\n" + 
			"@Encoding int = NULL,\r\n" + 
			"@Ruta_Default nvarchar = NULL,\r\n" + 
			"@Servidor nvarchar = NULL,\r\n" + 
			"@DB nvarchar = NULL,\r\n" + 
			"@Esquema nvarchar = NULL,\r\n" + 
			"@Ejecuciones int = NULL,\r\n" + 
			"@Fecha_Ult_Exec smalldatetime = NULL,\r\n" + 
			"@Fecha_Creacion smalldatetime = NULL,\r\n" + 
			"@RegGrid int = NULL,\r\n" + 
			"@Padre int = NULL,\r\n" + 
			"@class varchar(100) = '',\r\n" + 
			"@URL varchar(MAX) = '',\r\n" + 
			"@SistemaId int = NULL,\r\n" + 
			"@TipoRedirect varchar(15) = '',\r\n" + 
			"@usuario_id INT = NULL,\r\n" + 
			"@tipousuario_id INT = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT DISTINCT Reportes.Id_Reporte,Reportes.Nombre,Reportes.Sp,Reportes.Pagina_Id,Reportes.Estatus_Id,Reportes.Descripcion,Reportes.Nombre_Archivo,Reportes.Orden,Reportes.Campo_Nombre,Reportes.Encoding,Reportes.Ruta_Default,Reportes.Servidor,Reportes.DB,Reportes.Esquema,Reportes.Ejecuciones,Reportes.Fecha_Ult_Exec,Reportes.Fecha_Creacion,Reportes.RegGrid,Reportes.Padre,Reportes.class,Reportes.URL,Reportes.SistemaId,Reportes.TipoRedirect\r\n" + 
			"FROM GeneSys.Reportes\r\n" + 
			"INNER JOIN GeneSys.Accesos a ON Reportes.Id_Reporte = a.Reporte_Id\r\n" + 
			"WHERE\r\n" + 
			"(Reportes.Id_Reporte = @Id_Reporte OR @Id_Reporte IS NULL) AND\r\n" + 
			"(Reportes.Nombre = @Nombre OR @Nombre IS NULL) AND\r\n" + 
			"(Reportes.Sp = @Sp OR @Sp IS NULL) AND\r\n" + 
			"(Reportes.Pagina_Id = @Pagina_Id OR @Pagina_Id IS NULL) AND\r\n" + 
			"(Reportes.Estatus_Id = @Estatus_Id OR @Estatus_Id IS NULL) AND\r\n" + 
			"(Reportes.Descripcion = @Descripcion OR @Descripcion IS NULL) AND\r\n" + 
			"(Reportes.Nombre_Archivo = @Nombre_Archivo OR @Nombre_Archivo IS NULL) AND\r\n" + 
			"(Reportes.Orden = @Orden OR @Orden IS NULL) AND\r\n" + 
			"(Reportes.Campo_Nombre = @Campo_Nombre OR @Campo_Nombre IS NULL) AND\r\n" + 
			"(Reportes.Encoding = @Encoding OR @Encoding IS NULL) AND\r\n" + 
			"(Reportes.Ruta_Default = @Ruta_Default OR @Ruta_Default IS NULL) AND\r\n" + 
			"(Reportes.Servidor = @Servidor OR @Servidor IS NULL) AND\r\n" + 
			"(Reportes.DB = @DB OR @DB IS NULL) AND\r\n" + 
			"(Reportes.Esquema = @Esquema OR @Esquema IS NULL) AND\r\n" + 
			"(Reportes.Ejecuciones = @Ejecuciones OR @Ejecuciones IS NULL) AND\r\n" + 
			"(Reportes.Fecha_Ult_Exec = @Fecha_Ult_Exec OR @Fecha_Ult_Exec IS NULL) AND\r\n" + 
			"(Reportes.Fecha_Creacion = @Fecha_Creacion OR @Fecha_Creacion IS NULL) AND\r\n" + 
			"(Reportes.RegGrid = @RegGrid OR @RegGrid IS NULL) AND\r\n" + 
			"(Reportes.Padre = @Padre OR @Padre IS NULL) AND\r\n" + 
			"(Reportes.class LIKE '%'+@class+'%' OR @class = '') AND\r\n" + 
			"(Reportes.URL LIKE '%'+@URL+'%' OR @URL = '') AND\r\n" + 
			"(Reportes.SistemaId = @SistemaId OR @SistemaId IS NULL) AND\r\n" + 
			"(Reportes.TipoRedirect LIKE '%'+@TipoRedirect+'%' OR @TipoRedirect = '') AND \r\n" + 
			"(\r\n" + 
			"(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)\r\n" + 
			"OR\r\n" + 
			"(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)\r\n" + 
			")\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_CamposCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_CamposCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_CamposCON\r\n" + 
			"@ID_Campo int = NULL,\r\n" + 
			"@usuario_id INT = NULL,\r\n" + 
			"@tipousuario_id INT = NULL,\r\n" + 
			"@Reporte_Id int = NULL,\r\n" + 
			"@Variable_Id int = NULL,\r\n" + 
			"@Control_Id int = NULL,\r\n" + 
			"@Titulo_Pagina varchar(30) = '',\r\n" + 
			"@Nombre varchar(100) = '',\r\n" + 
			"@Orden int = NULL,\r\n" + 
			"@Visible int = NULL,\r\n" + 
			"@Valor_Variable varchar(60) = '',\r\n" + 
			"@DataSet varchar(300) = '',\r\n" + 
			"@DS varchar(1000) = '',\r\n" + 
			"@Parametro nvarchar = NULL,\r\n" + 
			"@ComboPadre varchar(30) = '',\r\n" + 
			"@FileJScript varchar(100) = '',\r\n" + 
			"@FuncionJS varchar(50) = '',\r\n" + 
			"@Evento varchar(50) = '',\r\n" + 
			"@CssFile varchar(100) = '',\r\n" + 
			"@Css varchar(50) = '',\r\n" + 
			"@Ancho int = NULL,\r\n" + 
			"@Longitud int = NULL,\r\n" + 
			"@Renglon int = NULL,\r\n" + 
			"@Class varchar(100) = '',\r\n" + 
			"@Parsley varchar(MAX) = ''\r\n" + 
			"AS\r\n" + 
			"SELECT DISTINCT c.ID_Campo,c.Reporte_Id,c.Variable_Id,c.Control_Id,c.Titulo_Pagina,c.Nombre,c.Orden,c.Visible,c.Valor_Variable,c.DataSet,c.DS,c.Parametro,c.ComboPadre,c.FileJScript,c.FuncionJS,c.Evento,c.CssFile,c.Css,c.Ancho,c.Longitud,c.Renglon,c.Class,c.Parsley,c.Omitir\r\n" + 
			"FROM GeneSys.Campos c\r\n" + 
			"INNER JOIN GeneSys.Reportes r ON r.Id_Reporte = c.Reporte_Id\r\n" + 
			"INNER JOIN GeneSys.Accesos a ON r.Id_Reporte = a.Reporte_Id\r\n" + 
			"WHERE\r\n" + 
			"(c.ID_Campo = @ID_Campo OR @ID_Campo IS NULL) AND\r\n" + 
			"(c.Reporte_Id = @Reporte_Id OR @Reporte_Id IS NULL) AND\r\n" + 
			"(c.Variable_Id = @Variable_Id OR @Variable_Id IS NULL) AND\r\n" + 
			"(c.Control_Id = @Control_Id OR @Control_Id IS NULL) AND\r\n" + 
			"(c.Titulo_Pagina LIKE '%'+@Titulo_Pagina+'%' OR @Titulo_Pagina = '') AND\r\n" + 
			"(c.Nombre LIKE '%'+@Nombre+'%' OR @Nombre = '') AND\r\n" + 
			"(c.Orden = @Orden OR @Orden IS NULL) AND\r\n" + 
			"(c.Visible = @Visible OR @Visible IS NULL) AND\r\n" + 
			"(c.Valor_Variable LIKE '%'+@Valor_Variable+'%' OR @Valor_Variable = '') AND\r\n" + 
			"(c.DataSet LIKE '%'+@DataSet+'%' OR @DataSet = '') AND\r\n" + 
			"(c.DS LIKE '%'+@DS+'%' OR @DS = '') AND\r\n" + 
			"(c.Parametro = @Parametro OR @Parametro IS NULL) AND\r\n" + 
			"(c.ComboPadre LIKE '%'+@ComboPadre+'%' OR @ComboPadre = '') AND\r\n" + 
			"(c.FileJScript LIKE '%'+@FileJScript+'%' OR @FileJScript = '') AND\r\n" + 
			"(c.FuncionJS LIKE '%'+@FuncionJS+'%' OR @FuncionJS = '') AND\r\n" + 
			"(c.Evento LIKE '%'+@Evento+'%' OR @Evento = '') AND\r\n" + 
			"(c.CssFile LIKE '%'+@CssFile+'%' OR @CssFile = '') AND\r\n" + 
			"(c.Css LIKE '%'+@Css+'%' OR @Css = '') AND\r\n" + 
			"(c.Ancho = @Ancho OR @Ancho IS NULL) AND\r\n" + 
			"(c.Longitud = @Longitud OR @Longitud IS NULL) AND\r\n" + 
			"(c.Renglon = @Renglon OR @Renglon IS NULL) AND\r\n" + 
			"(c.Class LIKE '%'+@Class+'%' OR @Class = '') AND\r\n" + 
			"(c.Parsley LIKE '%'+@Parsley+'%' OR @Parsley = '') AND\r\n" + 
			"(\r\n" + 
			"(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)\r\n" + 
			"OR\r\n" + 
			"(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)\r\n" + 
			")\r\n" + 
			"ORDER BY c.Orden asc\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"--BaseRSA\r\n" + 
			"\r\n" + 
			"-- -------------------------------------------------------\r\n" + 
			"--\r\n" + 
			"-- Modifica estos stores para que concuerden con tu login\r\n" + 
			"--\r\n" + 
			"-- -------------------------------------------------------\r\n" + 
			"\r\n" + 
			"/*\r\n" + 
			"	Es importante que ubiques tu tabla de usuarios y agregues las columnas necesarias para el funcionamiento de este sistemas,\r\n" + 
			"	el script para agregar las columnas es el siguiente:\r\n" + 
			"	\r\n" + 
			"	Si ya tienes estas columnas omite este paso:\r\n" + 
			"	\r\n" + 
			"	ALTER TABLE TuTabla ADD\r\n" + 
			"	token VARCHAR(8) NULL DEFAULT '',\r\n" + 
			"	privateKey VARCHAR(2048) NULL DEFAULT '',\r\n" + 
			"	ultimaActividad DATETIME NULL,\r\n" + 
			"	ip VARCHAR(30) NOT NULL DEFAULT '',\r\n" + 
			"	numeroPeticion INT NOT NULL DEFAULT 0\r\n" + 
			"	\r\n" + 
			"	Recuerda cambiar la tabla de los stored por tu tabla de Usuarios\r\n" + 
			"	\r\n" + 
			"*/\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_ValidarUsuario]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].[SP_ValidarUsuario] END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[SP_ValidarUsuario] \r\n" + 
			"	@usuario varchar(120),\r\n" + 
			"	@clave varchar(120),\r\n" + 
			"	@token varchar(10),\r\n" + 
			"	@idApp int = null,\r\n" + 
			"	@privateKey varchar(2048)\r\n" + 
			"AS\r\n" + 
			"BEGIN\r\n" + 
			"	--Inicializamos una bandera y una variable para el id del usuario logueando\r\n" + 
			"	DECLARE @valido int = 0,\r\n" + 
			"			@idUsuario int\r\n" + 
			"\r\n" + 
			"	--Comprobamos que el usuario exista y que no esté bloqueado\r\n" + 
			"	SELECT \r\n" + 
			"		@valido = 1,\r\n" + 
			"		@idUsuario = Id_Usuario\r\n" + 
			"	FROM\r\n" + 
			"		[GeneSys].Usuario  \r\n" + 
			"    WHERE \r\n" + 
			"		UPPER(Usuario) = @usuario AND \r\n" + 
			"        Estatus_Id = 1 AND\r\n" + 
			"		((ISNULL(Bloquear,0) < [GeneSys].[Parametro]('BLO')) OR (DATEADD(MI, CAST([GeneSys].[Parametro]('MBL') AS INT), Fch_Bloqueo) < GETDATE()))\r\n" + 
			"		\r\n" + 
			"	IF @valido = 0\r\n" + 
			"	BEGIN\r\n" + 
			"		SELECT 2 as 'valido'\r\n" + 
			"		RETURN;\r\n" + 
			"	END\r\n" + 
			"	\r\n" + 
			"	--Convertimos en mayusculas el usuario para quitarle la sensibilidad a mayusculas\r\n" + 
			"	SET @usuario = upper(@usuario)\r\n" + 
			"\r\n" + 
			"	/*\r\n" + 
			"		Checamos que el login sea correcto de un usuario Estatus_Id y le damos valor a la variable @valido y a @idUsuario,\r\n" + 
			"		utiliza el método de encriptacion o validacion que requieras para comparar la autenticidad del usuario.\r\n" + 
			"	*/\r\n" + 
			"	SET @valido = 0\r\n" + 
			"	\r\n" + 
			"	SELECT \r\n" + 
			"		@valido = 1\r\n" + 
			"	FROM\r\n" + 
			"		[GeneSys].Usuario  \r\n" + 
			"    WHERE \r\n" + 
			"		Id_Usuario = @idUsuario  AND \r\n" + 
			"        CONVERT(VARCHAR(256),HASHBYTES('SHA2_256',@clave),2) = Password AND\r\n" + 
			"        Estatus_Id = 1\r\n" + 
			"		\r\n" + 
			"	\r\n" + 
			"	--Si el login fue incorrecto agrega 1 a los intentos\r\n" + 
			"	IF @valido = 0\r\n" + 
			"			BEGIN\r\n" + 
			"				UPDATE \r\n" + 
			"					[GeneSys].Usuario\r\n" + 
			"				SET\r\n" + 
			"					Bloquear = ISNULL(Bloquear, 0) + 1\r\n" + 
			"				WHERE \r\n" + 
			"					Id_Usuario = @idUsuario\r\n" + 
			"					\r\n" + 
			"				DECLARE @bloqueo INT\r\n" + 
			"				\r\n" + 
			"				SELECT \r\n" + 
			"					@bloqueo = bloquear \r\n" + 
			"				FROM \r\n" + 
			"					[GeneSys].Usuario\r\n" + 
			"				WHERE\r\n" + 
			"					Id_Usuario = @idUsuario\r\n" + 
			"					\r\n" + 
			"				IF @bloqueo > [GeneSys].[Parametro]('BLO')\r\n" + 
			"				BEGIN\r\n" + 
			"					UPDATE \r\n" + 
			"					[GeneSys].Usuario\r\n" + 
			"				SET\r\n" + 
			"					Fch_Bloqueo = GETDATE()\r\n" + 
			"				WHERE \r\n" + 
			"					Id_Usuario = @idUsuario\r\n" + 
			"					\r\n" + 
			"				SELECT 2 as 'valido'\r\n" + 
			"				RETURN;\r\n" + 
			"				END\r\n" + 
			"				\r\n" + 
			"				SELECT 0 as 'valido'\r\n" + 
			"				RETURN;\r\n" + 
			"			END\r\n" + 
			"			\r\n" + 
			"	--Si el login fue exitoso, guarda el token y privateKey en el usuario\r\n" + 
			"	UPDATE \r\n" + 
			"		[GeneSys].Usuario\r\n" + 
			"	SET\r\n" + 
			"		token = @token,\r\n" + 
			"		privateKey = @privateKey,\r\n" + 
			"		bloquear = 0\r\n" + 
			"	WHERE \r\n" + 
			"		Id_Usuario = @idUsuario\r\n" + 
			"\r\n" + 
			"	/*\r\n" + 
			"	Si el login fue exitoso, devuelve informacion del usuario para ser procesada (guardarla en el token)\r\n" + 
			"	Los campos que son obligatorios son valido, token, tipo de usuario y el id del usuario.\r\n" + 
			"	\r\n" + 
			"	Puedes agregar los valores que necesites guardar en tus variables de sesion, mas adelante los leeremos\r\n" + 
			"	el codigo de c# y los agregaremos al token para poder leerlas al momento de hacer una peticion web.\r\n" + 
			"	*/\r\n" + 
			"	\r\n" + 
			"	SELECT 1 as 'valido', Id_Usuario, TipoUsuario_Id, Token, CAST(CambiarPassword AS VARCHAR) AS CambiarPassword \r\n" + 
			"	FROM \r\n" + 
			"		[GeneSys].Usuario\r\n" + 
			"	WHERE \r\n" + 
			"		Id_Usuario = @idUsuario AND\r\n" + 
			"		Estatus_Id = 1\r\n" + 
			"END\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_UsuarioValidarToken]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_UsuarioValidarToken END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[SP_UsuarioValidarToken] \r\n" + 
			"	@idUsuario int,\r\n" + 
			"	@idApp int = null,\r\n" + 
			"	@token varchar(10),\r\n" + 
			"	@numeroPeticion int\r\n" + 
			"AS\r\n" + 
			"BEGIN\r\n" + 
			"	--Declara las variables\r\n" + 
			"	DECLARE @valido int = 0\r\n" + 
			"	DECLARE @mensaje varchar(100) = 'Token invalido'\r\n" + 
			"\r\n" + 
			"	\r\n" + 
			"	--Comprueba que el token pertenezca a algun usuario y checa su numero de peticiones\r\n" + 
			"	SELECT \r\n" + 
			"		@valido = 1,\r\n" + 
			"		@mensaje = 'Token valido'\r\n" + 
			"	FROM\r\n" + 
			"		[GeneSys].Usuario  \r\n" + 
			"    WHERE \r\n" + 
			"		Estatus_Id = 1 AND\r\n" + 
			"		Id_Usuario = @idUsuario AND\r\n" + 
			"		token = @token AND\r\n" + 
			"		(@numeroPeticion = 0 OR numeroPeticion + 1 = @numeroPeticion)\r\n" + 
			"\r\n" + 
			"	--En caso de estar enviando el numero de peticion, entonces aumentamos en 1\r\n" + 
			"	IF @valido = 1 AND @numeroPeticion > 0\r\n" + 
			"	BEGIN\r\n" + 
			"		UPDATE \r\n" + 
			"			[GeneSys].Usuario \r\n" + 
			"		SET \r\n" + 
			"			numeroPeticion = numeroPeticion + 1\r\n" + 
			"		WHERE \r\n" + 
			"			Estatus_Id = 1 AND\r\n" + 
			"			id_Usuario = @idUsuario\r\n" + 
			"	END\r\n" + 
			"\r\n" + 
			"	--Siempre actualizamos la ultima actividad del usuario\r\n" + 
			"	UPDATE \r\n" + 
			"		[GeneSys].Usuario \r\n" + 
			"	SET\r\n" + 
			"		ultimaActividad = GETDATE() \r\n" + 
			"	WHERE \r\n" + 
			"		Estatus_Id = 1 AND\r\n" + 
			"		Id_Usuario = @idUsuario\r\n" + 
			"\r\n" + 
			"	SELECT @valido as 'valido', @mensaje as 'mensaje'\r\n" + 
			"END\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_ObtenerLlavePrimariaUsuario]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].[SP_ObtenerLlavePrimariaUsuario] END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[SP_ObtenerLlavePrimariaUsuario] \r\n" + 
			"	@idUsuario int,\r\n" + 
			"	@idApp int = null\r\n" + 
			"AS\r\n" + 
			"BEGIN\r\n" + 
			"	--Devolvemos la privateKey\r\n" + 
			"	SELECT \r\n" + 
			"		privateKey\r\n" + 
			"	FROM\r\n" + 
			"		[GeneSys].Usuario  \r\n" + 
			"    WHERE \r\n" + 
			"		id_Usuario = @idUsuario AND\r\n" + 
			"		Estatus_Id = 1\r\n" + 
			"END\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[LogOut]\r\n" + 
			"@Id_Usuario INT\r\n" + 
			"AS\r\n" + 
			"UPDATE \r\n" + 
			"	[GeneSys].Usuario\r\n" + 
			"SET\r\n" + 
			"	EnSession = 0,\r\n" + 
			"	Bloquear = 0,\r\n" + 
			"	token = '',\r\n" + 
			"	privateKey = ''\r\n" + 
			"WHERE \r\n" + 
			"	Id_Usuario = @Id_Usuario",
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_AccesosALT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_AccesosALT END\r\n" + 
			"SET ANSI_NULLS ON\r\n" 
			,
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_AccesosALT\r\n" + 
			"@Reporte_Id int = NULL,\r\n" + 
			"@UsuarioTipo_ID int = NULL,\r\n" + 
			"@TipoAcceso_Id int = NULL,\r\n" + 
			"@TipoAcc int = NULL\r\n" + 
			"AS\r\n" + 
			"INSERT INTO GeneSys.Accesos(Reporte_Id,UsuarioTipo_ID,TipoAcceso_Id,TipoAcc)\r\n" + 
			"VALUES\r\n" + 
			"(@Reporte_Id,@UsuarioTipo_ID,@TipoAcceso_Id,@TipoAcc)\r\n",
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_AccesosCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_AccesosCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_AccesosCON\r\n" + 
			"@Id_Acceso int = NULL,\r\n" + 
			"@Reporte_Id int = NULL,\r\n" + 
			"@UsuarioTipo_ID int = NULL,\r\n" + 
			"@TipoAcceso_Id int = NULL,\r\n" + 
			"@TipoAcc int = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT Accesos.Id_Acceso,Accesos.Reporte_Id,Accesos.UsuarioTipo_ID,Accesos.TipoAcceso_Id,Accesos.TipoAcc\r\n" + 
			"FROM GeneSys.Accesos\r\n" + 
			"WHERE\r\n" + 
			"(Accesos.Id_Acceso = @Id_Acceso OR @Id_Acceso IS NULL) AND\r\n" + 
			"(Accesos.Reporte_Id = @Reporte_Id OR @Reporte_Id IS NULL) AND\r\n" + 
			"(Accesos.UsuarioTipo_ID = @UsuarioTipo_ID OR @UsuarioTipo_ID IS NULL) AND\r\n" + 
			"(Accesos.TipoAcceso_Id = @TipoAcceso_Id OR @TipoAcceso_Id IS NULL) AND\r\n" + 
			"(Accesos.TipoAcc = @TipoAcc OR @TipoAcc IS NULL) \r\n" ,
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_AccesosACT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_AccesosACT END\r\n" + 
			"SET ANSI_NULLS ON\r\n",
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_AccesosACT\r\n" + 
			"@Id_Acceso int,\r\n" + 
			"@Reporte_Id int = NULL,\r\n" + 
			"@UsuarioTipo_ID int = NULL,\r\n" + 
			"@TipoAcceso_Id int = NULL,\r\n" + 
			"@TipoAcc int = NULL\r\n" + 
			"AS\r\n" + 
			"UPDATE GeneSys.Accesos SET\r\n" + 
			"Reporte_Id = ISNULL(@Reporte_Id, Reporte_Id),\r\n" + 
			"UsuarioTipo_ID = ISNULL(@UsuarioTipo_ID, UsuarioTipo_ID),\r\n" + 
			"TipoAcceso_Id = ISNULL(@TipoAcceso_Id, TipoAcceso_Id),\r\n" + 
			"TipoAcc = ISNULL(@TipoAcc, TipoAcc)\r\n" + 
			"WHERE\r\n" + 
			"Accesos.Id_Acceso = @Id_Acceso\r\n",
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_AccesosDEL]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_AccesosDEL END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n",
			"CREATE PROCEDURE GeneSys.SP_AccesosDEL\r\n" + 
			"@Id_Acceso int\r\n" + 
			"AS\r\n" + 
			"DELETE GeneSys.Accesos\r\n" + 
			"WHERE\r\n" + 
			"Accesos.Id_Acceso = @Id_Acceso\r\n",
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_Cat_ControlCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_Cat_ControlCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n",
			"SET QUOTED_IDENTIFIER ON\r\n",
			"CREATE PROCEDURE GeneSys.SP_Cat_ControlCON\r\n" + 
			"@Id_Control int = NULL,\r\n" + 
			"@Descripcion varchar(50) = '',\r\n" + 
			"@Clave varchar(3) = ''\r\n" + 
			"AS\r\n" + 
			"SELECT Cat_Control.Id_Control,Cat_Control.Descripcion,Cat_Control.Clave\r\n" + 
			"FROM GeneSys.Cat_Control\r\n" + 
			"WHERE\r\n" + 
			"(Cat_Control.Id_Control = @Id_Control OR @Id_Control IS NULL) AND\r\n" + 
			"(Cat_Control.Descripcion LIKE '%'+@Descripcion+'%' OR @Descripcion = '') AND\r\n" + 
			"(Cat_Control.Clave LIKE '%'+@Clave+'%' OR @Clave = '') \r\n" ,
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_Cat_EstatusCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_Cat_EstatusCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_Cat_EstatusCON\r\n" + 
			"@Id_Estatus bit = NULL,\r\n" + 
			"@Descripcion varchar(9) = ''\r\n" + 
			"AS\r\n" + 
			"SELECT Cat_Estatus.Id_Estatus,Cat_Estatus.Descripcion\r\n" + 
			"FROM GeneSys.Cat_Estatus\r\n" + 
			"WHERE\r\n" + 
			"(Cat_Estatus.Id_Estatus = @Id_Estatus OR @Id_Estatus IS NULL) AND\r\n" + 
			"(Cat_Estatus.Descripcion LIKE '%'+@Descripcion+'%' OR @Descripcion = '') \r\n",
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_Cat_MenuALT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_Cat_MenuALT END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n",
			"CREATE PROCEDURE GeneSys.SP_Cat_MenuALT\r\n" + 
			"@Menu varchar(100) = NULL,\r\n" + 
			"@Descripcion varchar(255) = NULL,\r\n" + 
			"@Carpeta varchar(50) = NULL,\r\n" + 
			"@URL varchar(MAX) = NULL,\r\n" + 
			"@Padre int = NULL,\r\n" + 
			"@Orden int = NULL,\r\n" + 
			"@IdEstatus int = NULL,\r\n" + 
			"@SistemaId int = NULL,\r\n" + 
			"@class varchar(50) = NULL,\r\n" + 
			"@TipoRedirect varchar(15) = NULL\r\n" + 
			"AS\r\n" + 
			"INSERT INTO GeneSys.Cat_Menu(Menu,Descripcion,Carpeta,URL,Padre,Orden,IdEstatus,SistemaId,class,TipoRedirect)\r\n" + 
			"VALUES\r\n" + 
			"(@Menu,@Descripcion,@Carpeta,@URL,@Padre,@Orden,@IdEstatus,@SistemaId,@class,@TipoRedirect)\r\n" ,
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_Cat_MenuCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_Cat_MenuCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_Cat_MenuCON\r\n" + 
			"@IdMenu int = NULL,\r\n" + 
			"@Menu varchar(100) = '',\r\n" + 
			"@Descripcion varchar(255) = '',\r\n" + 
			"@Carpeta varchar(50) = '',\r\n" + 
			"@URL varchar(MAX) = '',\r\n" + 
			"@Padre int = NULL,\r\n" + 
			"@Orden int = NULL,\r\n" + 
			"@IdEstatus int = NULL,\r\n" + 
			"@SistemaId int = NULL,\r\n" + 
			"@class varchar(50) = '',\r\n" + 
			"@TipoRedirect varchar(15) = ''\r\n" + 
			"AS\r\n" + 
			"SELECT Cat_Menu.IdMenu,Cat_Menu.Menu,Cat_Menu.Descripcion,Cat_Menu.Carpeta,Cat_Menu.URL,Cat_Menu.Padre,Cat_Menu.Orden,Cat_Menu.IdEstatus,Cat_Menu.SistemaId,Cat_Menu.class,Cat_Menu.TipoRedirect\r\n" + 
			"FROM GeneSys.Cat_Menu\r\n" + 
			"WHERE\r\n" + 
			"(Cat_Menu.IdMenu = @IdMenu OR @IdMenu IS NULL) AND\r\n" + 
			"(Cat_Menu.Menu LIKE '%'+@Menu+'%' OR @Menu = '') AND\r\n" + 
			"(Cat_Menu.Descripcion LIKE '%'+@Descripcion+'%' OR @Descripcion = '') AND\r\n" + 
			"(Cat_Menu.Carpeta LIKE '%'+@Carpeta+'%' OR @Carpeta = '') AND\r\n" + 
			"(Cat_Menu.URL LIKE '%'+@URL+'%' OR @URL = '') AND\r\n" + 
			"(Cat_Menu.Padre = @Padre OR @Padre IS NULL) AND\r\n" + 
			"(Cat_Menu.Orden = @Orden OR @Orden IS NULL) AND\r\n" + 
			"(Cat_Menu.IdEstatus = @IdEstatus OR @IdEstatus IS NULL) AND\r\n" + 
			"(Cat_Menu.SistemaId = @SistemaId OR @SistemaId IS NULL) AND\r\n" + 
			"(Cat_Menu.class LIKE '%'+@class+'%' OR @class = '') AND\r\n" + 
			"(Cat_Menu.TipoRedirect LIKE '%'+@TipoRedirect+'%' OR @TipoRedirect = '') \r\n" ,
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_Cat_MenuACT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_Cat_MenuACT END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_Cat_MenuACT\r\n" + 
			"@IdMenu int,\r\n" + 
			"@Menu varchar(100) = NULL,\r\n" + 
			"@Descripcion varchar(255) = NULL,\r\n" + 
			"@Carpeta varchar(50) = NULL,\r\n" + 
			"@URL varchar(MAX) = NULL,\r\n" + 
			"@Padre int = NULL,\r\n" + 
			"@Orden int = NULL,\r\n" + 
			"@IdEstatus int = NULL,\r\n" + 
			"@SistemaId int = NULL,\r\n" + 
			"@class varchar(50) = NULL,\r\n" + 
			"@TipoRedirect varchar(15) = NULL\r\n" + 
			"AS\r\n" + 
			"UPDATE GeneSys.Cat_Menu SET\r\n" + 
			"Menu = ISNULL(@Menu, Menu),\r\n" + 
			"Descripcion = ISNULL(@Descripcion, Descripcion),\r\n" + 
			"Carpeta = ISNULL(@Carpeta, Carpeta),\r\n" + 
			"URL = ISNULL(@URL, URL),\r\n" + 
			"Padre = ISNULL(@Padre, Padre),\r\n" + 
			"Orden = ISNULL(@Orden, Orden),\r\n" + 
			"IdEstatus = ISNULL(@IdEstatus, IdEstatus),\r\n" + 
			"SistemaId = ISNULL(@SistemaId, SistemaId),\r\n" + 
			"class = ISNULL(@class, class),\r\n" + 
			"TipoRedirect = ISNULL(@TipoRedirect, TipoRedirect)\r\n" + 
			"WHERE\r\n" + 
			"Cat_Menu.IdMenu = @IdMenu\r\n" , 
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_Cat_MenuDEL]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_Cat_MenuDEL END\r\n" + 
			"SET ANSI_NULLS ON\r\n" , 
			"SET QUOTED_IDENTIFIER ON\r\n", 
			"CREATE PROCEDURE GeneSys.SP_Cat_MenuDEL\r\n" + 
			"@IdMenu int\r\n" + 
			"AS\r\n" + 
			"DELETE GeneSys.Cat_Menu\r\n" + 
			"WHERE\r\n" + 
			"Cat_Menu.IdMenu = @IdMenu\r\n" , 
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_Cat_PaginaCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_Cat_PaginaCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n" , 
			"SET QUOTED_IDENTIFIER ON\r\n" , 
			"CREATE PROCEDURE GeneSys.SP_Cat_PaginaCON\r\n" + 
			"@Id_Pagina int = NULL,\r\n" + 
			"@Descripcion varchar(50) = '',\r\n" + 
			"@Estatus_Id bit = NULL,\r\n" + 
			"@Tipo int = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT Cat_Pagina.Id_Pagina,Cat_Pagina.Descripcion,Cat_Pagina.Estatus_Id,Cat_Pagina.Tipo\r\n" + 
			"FROM GeneSys.Cat_Pagina\r\n" + 
			"WHERE\r\n" + 
			"(Cat_Pagina.Id_Pagina = @Id_Pagina OR @Id_Pagina IS NULL) AND\r\n" + 
			"(Cat_Pagina.Descripcion LIKE '%'+@Descripcion+'%' OR @Descripcion = '') AND\r\n" + 
			"(Cat_Pagina.Estatus_Id = @Estatus_Id OR @Estatus_Id IS NULL) AND\r\n" + 
			"(Cat_Pagina.Tipo = @Tipo OR @Tipo IS NULL) \r\n" , 
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_Cat_VariableCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_Cat_VariableCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_Cat_VariableCON\r\n" + 
			"@Id_Variable int = NULL,\r\n" + 
			"@Descripcion varchar(50) = '',\r\n" + 
			"@Clave varchar(3) = ''\r\n" + 
			"AS\r\n" + 
			"SELECT Cat_Variable.Id_Variable,Cat_Variable.Descripcion,Cat_Variable.Clave\r\n" + 
			"FROM GeneSys.Cat_Variable\r\n" + 
			"WHERE\r\n" + 
			"(Cat_Variable.Id_Variable = @Id_Variable OR @Id_Variable IS NULL) AND\r\n" + 
			"(Cat_Variable.Descripcion LIKE '%'+@Descripcion+'%' OR @Descripcion = '') AND\r\n" + 
			"(Cat_Variable.Clave LIKE '%'+@Clave+'%' OR @Clave = '') \r\n" , 
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_UsuarioCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_UsuarioCON END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_UsuarioCON\r\n" + 
			"@Id_Usuario int = NULL,\r\n" + 
			"@Perfil_Id int = NULL,\r\n" + 
			"@Nombres varchar(50) = '',\r\n" + 
			"@ApellidoP varchar(50) = '',\r\n" + 
			"@ApellidoM varchar(50) = '',\r\n" + 
			"@RFC varchar(13) = '',\r\n" + 
			"@Lada varchar(4) = '',\r\n" + 
			"@Telefono varchar(15) = '',\r\n" + 
			"@Email varchar(50) = '',\r\n" + 
			"@Usuario varchar(30) = '',\r\n" + 
			"@Password varchar(256) = '',\r\n" + 
			"@TipoUsuario_Id int = NULL,\r\n" + 
			"@Sucursal_Id int = NULL,\r\n" + 
			"@Estatus_Id int = NULL,\r\n" + 
			"@Bloquear int = NULL,\r\n" + 
			"@EnSession int = NULL,\r\n" + 
			"@Fch_Bloqueo smalldatetime = NULL,\r\n" + 
			"@SoloOficina bit = NULL,\r\n" + 
			"@token varchar(8) = '',\r\n" + 
			"@privateKey varchar(2048) = '',\r\n" + 
			"@ultimaActividad datetime = NULL,\r\n" + 
			"@ip varchar(30) = '',\r\n" + 
			"@numeroPeticion int = NULL,\r\n" + 
			"@CambiarPassword bit = NULL,\r\n" + 
			"@FechaCambioPassword datetime = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT Usuario.Id_Usuario,Usuario.Usuario,Usuario.Perfil_Id,Usuario.Nombres,Usuario.ApellidoP,Usuario.ApellidoM,Usuario.RFC,Usuario.Lada,Usuario.Telefono,Usuario.Email,Usuario.Password,Usuario.TipoUsuario_Id,Usuario.Sucursal_Id,Usuario.Estatus_Id,Usuario.Bloquear,Usuario.EnSession,Usuario.Fch_Bloqueo,Usuario.SoloOficina,Usuario.token,Usuario.privateKey,Usuario.ultimaActividad,Usuario.ip,Usuario.numeroPeticion,Usuario.CambiarPassword,Usuario.FechaCambioPassword\r\n" + 
			"FROM GeneSys.Usuario\r\n" + 
			"WHERE\r\n" + 
			"(Usuario.Id_Usuario = @Id_Usuario OR @Id_Usuario IS NULL) AND\r\n" + 
			"(Usuario.Perfil_Id = @Perfil_Id OR @Perfil_Id IS NULL) AND\r\n" + 
			"(Usuario.Nombres LIKE '%'+@Nombres+'%' OR @Nombres = '') AND\r\n" + 
			"(Usuario.ApellidoP LIKE '%'+@ApellidoP+'%' OR @ApellidoP = '') AND\r\n" + 
			"(Usuario.ApellidoM LIKE '%'+@ApellidoM+'%' OR @ApellidoM = '') AND\r\n" + 
			"(Usuario.RFC LIKE '%'+@RFC+'%' OR @RFC = '') AND\r\n" + 
			"(Usuario.Lada LIKE '%'+@Lada+'%' OR @Lada = '') AND\r\n" + 
			"(Usuario.Telefono LIKE '%'+@Telefono+'%' OR @Telefono = '') AND\r\n" + 
			"(Usuario.Email LIKE '%'+@Email+'%' OR @Email = '') AND\r\n" + 
			"(Usuario.Usuario LIKE '%'+@Usuario+'%' OR @Usuario = '') AND\r\n" + 
			"(Usuario.Password LIKE '%'+@Password+'%' OR @Password = '') AND\r\n" + 
			"(Usuario.TipoUsuario_Id = @TipoUsuario_Id OR @TipoUsuario_Id IS NULL) AND\r\n" + 
			"(Usuario.Sucursal_Id = @Sucursal_Id OR @Sucursal_Id IS NULL) AND\r\n" + 
			"(Usuario.Estatus_Id = @Estatus_Id OR @Estatus_Id IS NULL) AND\r\n" + 
			"(Usuario.Bloquear = @Bloquear OR @Bloquear IS NULL) AND\r\n" + 
			"(Usuario.EnSession = @EnSession OR @EnSession IS NULL) AND\r\n" + 
			"(Usuario.Fch_Bloqueo = @Fch_Bloqueo OR @Fch_Bloqueo IS NULL) AND\r\n" + 
			"(Usuario.SoloOficina = @SoloOficina OR @SoloOficina IS NULL) AND\r\n" + 
			"(Usuario.token LIKE '%'+@token+'%' OR @token = '') AND\r\n" + 
			"(Usuario.privateKey LIKE '%'+@privateKey+'%' OR @privateKey = '') AND\r\n" + 
			"(Usuario.ultimaActividad = @ultimaActividad OR @ultimaActividad IS NULL) AND\r\n" + 
			"(Usuario.ip LIKE '%'+@ip+'%' OR @ip = '') AND\r\n" + 
			"(Usuario.numeroPeticion = @numeroPeticion OR @numeroPeticion IS NULL) AND\r\n" + 
			"(Usuario.CambiarPassword = @CambiarPassword OR @CambiarPassword IS NULL) AND\r\n" + 
			"(Usuario.FechaCambioPassword = @FechaCambioPassword OR @FechaCambioPassword IS NULL) \r\n" ,
			"\r\n" + 
			"\r\n" + 
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_UsuarioACT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_UsuarioACT END\r\n" + 
			"SET ANSI_NULLS ON\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_UsuarioACT\r\n" + 
			"@Id_Usuario int,\r\n" + 
			"@Perfil_Id int = NULL,\r\n" + 
			"@Nombres varchar(50) = NULL,\r\n" + 
			"@ApellidoP varchar(50) = NULL,\r\n" + 
			"@ApellidoM varchar(50) = NULL,\r\n" + 
			"@RFC varchar(13) = NULL,\r\n" + 
			"@Lada varchar(4) = NULL,\r\n" + 
			"@Telefono varchar(15) = NULL,\r\n" + 
			"@Email varchar(50) = NULL,\r\n" + 
			"@Usuario varchar(30) = NULL,\r\n" + 
			"@Password varchar(256) = NULL,\r\n" + 
			"@TipoUsuario_Id int = NULL,\r\n" + 
			"@Sucursal_Id int = NULL,\r\n" + 
			"@Estatus_Id int = NULL,\r\n" + 
			"@Bloquear int = NULL,\r\n" + 
			"@EnSession int = NULL,\r\n" + 
			"@Fch_Bloqueo smalldatetime = NULL,\r\n" + 
			"@SoloOficina bit = NULL,\r\n" + 
			"@token varchar(8) = NULL,\r\n" + 
			"@privateKey varchar(2048) = NULL,\r\n" + 
			"@ultimaActividad datetime = NULL,\r\n" + 
			"@ip varchar(30) = NULL,\r\n" + 
			"@numeroPeticion int = NULL,\r\n" + 
			"@CambiarPassword bit = NULL,\r\n" + 
			"@FechaCambioPassword datetime = NULL\r\n" + 
			"AS\r\n" + 
			"UPDATE GeneSys.Usuario SET\r\n" + 
			"Perfil_Id = ISNULL(@Perfil_Id, Perfil_Id),\r\n" + 
			"Nombres = ISNULL(@Nombres, Nombres),\r\n" + 
			"ApellidoP = ISNULL(@ApellidoP, ApellidoP),\r\n" + 
			"ApellidoM = ISNULL(@ApellidoM, ApellidoM),\r\n" + 
			"RFC = ISNULL(@RFC, RFC),\r\n" + 
			"Lada = ISNULL(@Lada, Lada),\r\n" + 
			"Telefono = ISNULL(@Telefono, Telefono),\r\n" + 
			"Email = ISNULL(@Email, Email),\r\n" + 
			"Usuario = ISNULL(@Usuario, Usuario),\r\n" + 
			"Password = ISNULL(@Password, Password),\r\n" + 
			"TipoUsuario_Id = ISNULL(@TipoUsuario_Id, TipoUsuario_Id),\r\n" + 
			"Sucursal_Id = ISNULL(@Sucursal_Id, Sucursal_Id),\r\n" + 
			"Estatus_Id = ISNULL(@Estatus_Id, Estatus_Id),\r\n" + 
			"Bloquear = ISNULL(@Bloquear, Bloquear),\r\n" + 
			"EnSession = ISNULL(@EnSession, EnSession),\r\n" + 
			"Fch_Bloqueo = ISNULL(@Fch_Bloqueo, Fch_Bloqueo),\r\n" + 
			"SoloOficina = ISNULL(@SoloOficina, SoloOficina),\r\n" + 
			"token = ISNULL(@token, token),\r\n" + 
			"privateKey = ISNULL(@privateKey, privateKey),\r\n" + 
			"ultimaActividad = ISNULL(@ultimaActividad, ultimaActividad),\r\n" + 
			"ip = ISNULL(@ip, ip),\r\n" + 
			"numeroPeticion = ISNULL(@numeroPeticion, numeroPeticion),\r\n" + 
			"CambiarPassword = ISNULL(@CambiarPassword, CambiarPassword),\r\n" + 
			"FechaCambioPassword = ISNULL(@FechaCambioPassword, FechaCambioPassword)\r\n" + 
			"WHERE\r\n" + 
			"Usuario.Id_Usuario = @Id_Usuario\r\n" 
			,
			"\r\n" + 
			"CREATE PROCEDURE [GeneSys].SP_Cat_TipoUsuarioALT\r\n" + 
			"@TipoUsuario VARCHAR(50),\r\n" + 
			"@Descripcion VARCHAR(255)\r\n" + 
			"AS\r\n" + 
			"	INSERT INTO \r\n" + 
			"	[GeneSys].Cat_TipoUsuario\r\n" + 
			"			(TipoUsuario, Descripcion, Estatus_Id)\r\n" + 
			"	VALUES\r\n" + 
			"			(@TipoUsuario, @Descripcion, 1)\r\n"
			,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_UsuarioALT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_UsuarioALT END\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].SP_UsuarioALT\r\n" + 
			"@Perfil_Id int = NULL,\r\n" + 
			"@Nombres varchar(50) = NULL,\r\n" + 
			"@ApellidoP varchar(50) = NULL,\r\n" + 
			"@ApellidoM varchar(50) = NULL,\r\n" + 
			"@RFC varchar(13) = NULL,\r\n" + 
			"@Lada varchar(4) = NULL,\r\n" + 
			"@Telefono varchar(15) = NULL,\r\n" + 
			"@Email varchar(50) = NULL,\r\n" + 
			"@Usuario varchar(30),\r\n" + 
			"@Password varchar(256),\r\n" + 
			"@TipoUsuario_Id int,\r\n" + 
			"@Sucursal_Id int = NULL,\r\n" + 
			"@Estatus_Id int = 1,\r\n" + 
			"@Bloquear int = NULL,\r\n" + 
			"@EnSession int = NULL,\r\n" + 
			"@Fch_Bloqueo smalldatetime = NULL,\r\n" + 
			"@SoloOficina bit = NULL,\r\n" + 
			"@token varchar(8) = NULL,\r\n" + 
			"@privateKey varchar(2048) = NULL,\r\n" + 
			"@ultimaActividad datetime = NULL,\r\n" + 
			"@ip varchar(30) = '0.0.0.0',\r\n" + 
			"@numeroPeticion int = 0,\r\n" + 
			"@CambiarPassword bit = 1,\r\n" + 
			"@FechaCambioPassword datetime = NULL\r\n" + 
			"AS\r\n" + 
			"INSERT INTO [GeneSys].Usuario(Perfil_Id,Nombres,ApellidoP,ApellidoM,RFC,Lada,Telefono,Email,Usuario,Password,TipoUsuario_Id,Sucursal_Id,Estatus_Id,Bloquear,EnSession,Fch_Bloqueo,SoloOficina,token,privateKey,ultimaActividad,ip,numeroPeticion,CambiarPassword,FechaCambioPassword)\r\n" + 
			"VALUES\r\n" + 
			"(@Perfil_Id,@Nombres,@ApellidoP,@ApellidoM,@RFC,@Lada,@Telefono,@Email,@Usuario,CONVERT(VARCHAR(256),HASHBYTES('SHA2_256',@Password),2),@TipoUsuario_Id,@Sucursal_Id,@Estatus_Id,@Bloquear,@EnSession,@Fch_Bloqueo,@SoloOficina,@token,@privateKey,@ultimaActividad,@ip,@numeroPeticion,@CambiarPassword,@FechaCambioPassword)\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_CambiarPassword]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )\r\n" + 
			"BEGIN DROP PROCEDURE [GeneSys].SP_CambiarPassword END\r\n" ,
			"SET ANSI_NULLS ON\r\n" + 
			"\r\n" ,
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[SP_CambiarPassword]\r\n" + 
			"@Id_Usuario INT,\r\n" + 
			"@Password VARCHAR(256)\r\n" + 
			"AS\r\n" + 
			"	UPDATE \r\n" + 
			"		[GeneSys].Usuario\r\n" + 
			"	SET \r\n" + 
			"		Password = CONVERT(VARCHAR(256),HASHBYTES('SHA2_256',@Password),2),\r\n" + 
			"		FechaCambioPassword = GETDATE(),\r\n" + 
			"		CambiarPassword = 0\r\n" + 
			"	WHERE \r\n" + 
			"		Id_Usuario = @Id_Usuario\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_Cat_TipoUsuarioCON\r\n" + 
			"@Id_TipoUsuario INT = NULL,\r\n" + 
			"@TipoUsuario VARCHAR(50) = '',\r\n" + 
			"@Descripcion VARCHAR(255) = ''\r\n" + 
			"AS\r\n" + 
			"SELECT \r\n" + 
			"	Id_TipoUsuario, TipoUsuario, Descripcion, Estatus_Id \r\n" + 
			"FROM \r\n" + 
			"	GeneSys.Cat_TipoUsuario\r\n" + 
			"WHERE\r\n" + 
			"	(Id_TipoUsuario = @Id_TipoUsuario OR @Id_TipoUsuario IS NULL) AND\r\n" + 
			"	(TipoUsuario LIKE '%'+@TipoUsuario+'%' OR @TipoUsuario = '') AND\r\n" + 
			"	(Descripcion LIKE '%'+@Descripcion+'%' OR @Descripcion = '')"
			,
			"CREATE PROCEDURE [GeneSys].[SP_CamposACT]\r\n" + 
			"@ID_Campo int,\r\n" + 
			"@Reporte_Id int = NULL,\r\n" + 
			"@Variable_Id int = NULL,\r\n" + 
			"@Control_Id int = NULL,\r\n" + 
			"@Titulo_Pagina varchar(30) = NULL,\r\n" + 
			"@Nombre varchar(100) = NULL,\r\n" + 
			"@Orden int = NULL,\r\n" + 
			"@Visible int = NULL,\r\n" + 
			"@Valor_Variable varchar(60) = NULL,\r\n" + 
			"@DataSet varchar(300) = NULL,\r\n" + 
			"@DS varchar(1000) = NULL,\r\n" + 
			"@Parametro nvarchar(60) = NULL,\r\n" + 
			"@ComboPadre varchar(30) = NULL,\r\n" + 
			"@FileJScript varchar(100) = NULL,\r\n" + 
			"@FuncionJS varchar(50) = NULL,\r\n" + 
			"@Evento varchar(50) = NULL,\r\n" + 
			"@CssFile varchar(100) = NULL,\r\n" + 
			"@Css varchar(50) = NULL,\r\n" + 
			"@Ancho int = NULL,\r\n" + 
			"@Longitud int = NULL,\r\n" + 
			"@Renglon int = NULL,\r\n" + 
			"@Class varchar(100) = NULL,\r\n" + 
			"@Parsley varchar(MAX) = NULL,\r\n" + 
			"@Omitir bit = NULL\r\n" + 
			"AS\r\n" + 
			"UPDATE GeneSys.Campos SET\r\n" + 
			"Reporte_Id = ISNULL(@Reporte_Id, Reporte_Id),\r\n" + 
			"Variable_Id = ISNULL(@Variable_Id, Variable_Id),\r\n" + 
			"Control_Id = ISNULL(@Control_Id, Control_Id),\r\n" + 
			"Titulo_Pagina = ISNULL(@Titulo_Pagina, Titulo_Pagina),\r\n" + 
			"Nombre = ISNULL(@Nombre, Nombre),\r\n" + 
			"Orden = ISNULL(@Orden, Orden),\r\n" + 
			"Visible = ISNULL(@Visible, Visible),\r\n" + 
			"Valor_Variable = ISNULL(@Valor_Variable, Valor_Variable),\r\n" + 
			"DataSet = ISNULL(@DataSet, DataSet),\r\n" + 
			"DS = ISNULL(@DS, DS),\r\n" + 
			"Parametro = ISNULL(@Parametro, Parametro),\r\n" + 
			"ComboPadre = ISNULL(@ComboPadre, ComboPadre),\r\n" + 
			"FileJScript = ISNULL(@FileJScript, FileJScript),\r\n" + 
			"FuncionJS = ISNULL(@FuncionJS, FuncionJS),\r\n" + 
			"Evento = ISNULL(@Evento, Evento),\r\n" + 
			"CssFile = ISNULL(@CssFile, CssFile),\r\n" + 
			"Css = ISNULL(@Css, Css),\r\n" + 
			"Ancho = ISNULL(@Ancho, Ancho),\r\n" + 
			"Longitud = ISNULL(@Longitud, Longitud),\r\n" + 
			"Renglon = ISNULL(@Renglon, Renglon),\r\n" + 
			"Class = ISNULL(@Class, Class),\r\n" + 
			"Parsley = ISNULL(@Parsley, Parsley),\r\n" + 
			"Omitir = ISNULL(@Omitir, Omitir)\r\n" + 
			"WHERE\r\n" + 
			"Campos.ID_Campo = @ID_Campo \r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[SP_CamposALT]\r\n" + 
			"@Reporte_Id int,\r\n" + 
			"@Variable_Id int = NULL,\r\n" + 
			"@Control_Id int = NULL,\r\n" + 
			"@Titulo_Pagina varchar(30) = NULL,\r\n" + 
			"@Nombre varchar(100) = NULL,\r\n" + 
			"@Orden int = NULL,\r\n" + 
			"@Visible int = NULL,\r\n" + 
			"@Valor_Variable varchar(60) = NULL,\r\n" + 
			"@DataSet varchar(300) = NULL,\r\n" + 
			"@DS varchar(1000) = NULL,\r\n" + 
			"@Parametro nvarchar(60) = NULL,\r\n" + 
			"@ComboPadre varchar(30) = NULL,\r\n" + 
			"@FileJScript varchar(100) = NULL,\r\n" + 
			"@FuncionJS varchar(50) = NULL,\r\n" + 
			"@Evento varchar(50) = NULL,\r\n" + 
			"@CssFile varchar(100) = NULL,\r\n" + 
			"@Css varchar(50) = NULL,\r\n" + 
			"@Ancho int = NULL,\r\n" + 
			"@Longitud int = NULL,\r\n" + 
			"@Renglon int = NULL,\r\n" + 
			"@Class varchar(100) = NULL,\r\n" + 
			"@Parsley varchar(MAX) = NULL,\r\n" + 
			"@Omitir bit\r\n" + 
			"AS\r\n" + 
			"INSERT INTO GeneSys.Campos(Reporte_Id,Variable_Id,Control_Id,Titulo_Pagina,Nombre,Orden,Visible,Valor_Variable,DataSet,DS,Parametro,ComboPadre,FileJScript,FuncionJS,Evento,CssFile,Css,Ancho,Longitud,Renglon,Class,Parsley,Omitir)\r\n" + 
			"VALUES\r\n" + 
			"(@Reporte_Id,@Variable_Id,@Control_Id,@Titulo_Pagina,@Nombre,@Orden,@Visible,@Valor_Variable,@DataSet,@DS,@Parametro,@ComboPadre,@FileJScript,@FuncionJS,@Evento,@CssFile,@Css,@Ancho,@Longitud,@Renglon,@Class,@Parsley,@Omitir)\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[SP_CamposCON2]\r\n" + 
			"@Reporte_Id int = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT c.ID_Campo,c.Titulo_Pagina,c.Nombre,Variable = v.Clave,Control = co.Descripcion,\r\n" + 
			"Editar = '<a href=\"#\" class=\"reporte-modal\" data=\"Reporte=7&ID_Campo='+CAST(ISNULL(c.ID_Campo,0) AS VARCHAR)+'&Reporte_Id='+CAST(ISNULL(c.Reporte_Id,0) AS VARCHAR)+'&Variable_Id='+CAST(ISNULL(c.Variable_Id,0) AS VARCHAR)+'&Control_Id='+CAST(ISNULL(c.Control_Id,0) AS VARCHAR)+'&Titulo_Pagina='+CAST(ISNULL(c.Titulo_Pagina,'') AS VARCHAR)+'&Nombre='+CAST(ISNULL(c.Nombre,'') AS VARCHAR)+'&Orden='+CAST(ISNULL(c.Orden,0) AS VARCHAR)+'&Visible='+CAST(ISNULL(c.Visible,1) AS VARCHAR)+'&Valor_Variable='+CAST(ISNULL(c.Valor_Variable,'') AS VARCHAR)+'&DataSet='+CAST(ISNULL(c.DataSet,'') AS VARCHAR)+'&DS='+CAST(ISNULL(c.DS,'') AS VARCHAR)+'&Parametro='+CAST(ISNULL(c.Parametro,'') AS VARCHAR)+'&ComboPadre='+CAST(ISNULL(c.ComboPadre,'') AS VARCHAR)+'&FileJScript='+CAST(ISNULL(c.FileJScript,'') AS VARCHAR)+'&FuncionJS='+CAST(ISNULL(c.FuncionJS,'') AS VARCHAR)+'&Evento='+CAST(ISNULL(c.Evento,'') AS VARCHAR)+'&CssFile='+CAST(ISNULL(c.CssFile,'') AS VARCHAR)+'&Css='+CAST(ISNULL(c.Css,'') AS VARCHAR)+'&Ancho='+CAST(ISNULL(c.Ancho,0) AS VARCHAR)+'&Longitud='+CAST(ISNULL(c.Longitud,0) AS VARCHAR)+'&Renglon='+CAST(ISNULL(c.Renglon,0) AS VARCHAR)+'&Class='+CAST(ISNULL(c.Class,'') AS VARCHAR)+'&Parsley='+CAST(ISNULL(c.Parsley,'') AS VARCHAR)+'&Omitir='+CAST(ISNULL(c.Omitir,0) AS VARCHAR)+'\">Editar</a>'\r\n" + 
			"FROM GeneSys.Campos c\r\n" + 
			"INNER JOIN GeneSys.Cat_Variable v ON v.Id_Variable = c.Variable_Id\r\n" + 
			"INNER JOIN GeneSys.Cat_Control co ON co.Id_Control = c.Control_Id\r\n" + 
			"WHERE\r\n" + 
			"(c.Reporte_Id = @Reporte_Id OR @Reporte_Id IS NULL)\r\n" + 
			"ORDER BY c.Orden asc\r\n" + 
			"\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE [GeneSys].[SP_ReportesACT]\r\n" + 
			"@Id_Reporte int,\r\n" + 
			"@Nombre nvarchar(100) = NULL,\r\n" + 
			"@Sp nvarchar(300) = NULL,\r\n" + 
			"@Pagina_Id int = NULL,\r\n" + 
			"@Estatus_Id bit = NULL,\r\n" + 
			"@Descripcion nvarchar(500) = NULL,\r\n" + 
			"@Nombre_Archivo nchar = NULL,\r\n" + 
			"@Orden int = NULL,\r\n" + 
			"@Campo_Nombre nvarchar(100) = NULL,\r\n" + 
			"@Encoding int = NULL,\r\n" + 
			"@Ruta_Default nvarchar(200) = NULL,\r\n" + 
			"@Servidor nvarchar(60) = NULL,\r\n" + 
			"@DB nvarchar(60) = NULL,\r\n" + 
			"@Esquema nvarchar(60) = NULL,\r\n" + 
			"@Ejecuciones int = NULL,\r\n" + 
			"@Fecha_Ult_Exec smalldatetime = NULL,\r\n" + 
			"@Fecha_Creacion smalldatetime = NULL,\r\n" + 
			"@RegGrid int = NULL,\r\n" + 
			"@Padre int = NULL,\r\n" + 
			"@class varchar(100) = NULL,\r\n" + 
			"@URL varchar(MAX) = NULL,\r\n" + 
			"@SistemaId int = NULL,\r\n" + 
			"@TipoRedirect varchar(15) = NULL,\r\n" + 
			"@Menu_Id int = NULL\r\n" + 
			"AS\r\n" + 
			"UPDATE GeneSys.Reportes SET\r\n" + 
			"Nombre = ISNULL(@Nombre, Nombre),\r\n" + 
			"Sp = ISNULL(@Sp, Sp),\r\n" + 
			"Pagina_Id = ISNULL(@Pagina_Id, Pagina_Id),\r\n" + 
			"Estatus_Id = ISNULL(@Estatus_Id, Estatus_Id),\r\n" + 
			"Descripcion = ISNULL(@Descripcion, Descripcion),\r\n" + 
			"Nombre_Archivo = ISNULL(@Nombre_Archivo, Nombre_Archivo),\r\n" + 
			"Orden = ISNULL(@Orden, Orden),\r\n" + 
			"Campo_Nombre = ISNULL(@Campo_Nombre, Campo_Nombre),\r\n" + 
			"Encoding = ISNULL(@Encoding, Encoding),\r\n" + 
			"Ruta_Default = ISNULL(@Ruta_Default, Ruta_Default),\r\n" + 
			"Servidor = ISNULL(@Servidor, Servidor),\r\n" + 
			"DB = ISNULL(@DB, DB),\r\n" + 
			"Esquema = ISNULL(@Esquema, Esquema),\r\n" + 
			"Ejecuciones = ISNULL(@Ejecuciones, Ejecuciones),\r\n" + 
			"Fecha_Ult_Exec = ISNULL(@Fecha_Ult_Exec, Fecha_Ult_Exec),\r\n" + 
			"Fecha_Creacion = ISNULL(@Fecha_Creacion, Fecha_Creacion),\r\n" + 
			"RegGrid = ISNULL(@RegGrid, RegGrid),\r\n" + 
			"Padre = ISNULL(@Padre, Padre),\r\n" + 
			"class = ISNULL(@class, class),\r\n" + 
			"URL = ISNULL(@URL, URL),\r\n" + 
			"SistemaId = ISNULL(@SistemaId, SistemaId),\r\n" + 
			"TipoRedirect = ISNULL(@TipoRedirect, TipoRedirect),\r\n" + 
			"Menu_Id = ISNULL(@Menu_Id, Menu_Id)\r\n" + 
			"WHERE\r\n" + 
			"Reportes.Id_Reporte = @Id_Reporte \r\n" + 
			"\r\n" + 
			"\r\n", 
			"CREATE PROCEDURE [GeneSys].[SP_ReportesCON2]\r\n" + 
			"@Id_Reporte int = NULL,\r\n" + 
			"@Nombre nvarchar = NULL,\r\n" + 
			"@Sp nvarchar = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT DISTINCT r.Id_Reporte,r.Nombre,r.Sp,\r\n" + 
			"Editar = '<a href=\"#\" class=\"reporte-modal\" data=\"Reporte=6&Id_Reporte='+CAST(ISNULL(r.Id_Reporte,0) AS VARCHAR)+'&Nombre='+ISNULL(r.Nombre,'')+'&Sp='+ISNULL(r.Sp,'')+'&Pagina_Id='+CAST(ISNULL(r.Pagina_Id,0) AS VARCHAR)+'&Estatus_Id='+CAST(ISNULL(r.Estatus_Id,0) AS VARCHAR)+'&Descripcion='+ISNULL(r.Descripcion,'')+'&Nombre_Archivo='+ISNULL(r.Nombre_Archivo,'')+'&Orden='+CAST(ISNULL(r.Orden,0) AS VARCHAR)+'&Campo_Nombre='+ISNULL(r.Campo_Nombre,'')+'&Encoding='+CAST(ISNULL(r.Encoding,0) AS VARCHAR)+'&Ruta_Default='+ISNULL(r.Ruta_Default,'')+'&Servidor='+ISNULL(r.Servidor,'')+'&DB='+ISNULL(r.DB,'')+'&Esquema='+ISNULL(r.Esquema,'')+'&Padre='+CAST(ISNULL(r.Padre,0) AS VARCHAR)+'&class='+ISNULL(r.class,'')+'&URL='+ISNULL(r.URL,'')+'\">Editar</a>',\r\n" + 
			"Campos = '<a href=\"#\" class=\"reporte-modal\" data=\"AS=1&Reporte=8&Reporte_Id='+CAST(ISNULL(r.Id_Reporte,0) AS VARCHAR)+'\" >Campos</a>'\r\n" + 
			"FROM GeneSys.Reportes r\r\n" + 
			"INNER JOIN GeneSys.Accesos a ON r.Id_Reporte = a.Reporte_Id\r\n" + 
			"INNER JOIN GeneSys.Cat_Pagina p ON r.Pagina_Id = p.Id_Pagina\r\n" + 
			"WHERE\r\n" + 
			"(r.Id_Reporte = @Id_Reporte OR @Id_Reporte IS NULL) AND\r\n" + 
			"(r.Nombre = @Nombre OR @Nombre IS NULL) AND\r\n" + 
			"(r.Sp = @Sp OR @Sp IS NULL)\r\n" + 
			"",
			"\r\n" + 
			"CREATE PROCEDURE GeneSys.SP_UsuarioTipoCON\r\n" + 
			"@TipoAcceso_Id INT\r\n" + 
			"AS\r\n" + 
			"IF @TipoAcceso_Id = 1\r\n" + 
			"BEGIN\r\n" + 
			"SELECT Id_TipoUsuario, TipoUsuario FROM GeneSys.Cat_TipoUsuario\r\n" + 
			"END\r\n" + 
			"ELSE\r\n" + 
			"BEGIN\r\n" + 
			"SELECT Id_Usuario, Usuario FROM GeneSys.Usuario\r\n" + 
			"END\r\n" + 
			"\r\n" ,
			"CREATE PROCEDURE GeneSys.SP_Cat_TipoAccesoCON\r\n" + 
			"@Id_TipoAcceso INT = NULL\r\n" + 
			"AS\r\n" + 
			"SELECT Id_TipoAcceso, Descripcion FROM GeneSys.Cat_TipoAcceso\r\n" + 
			"WHERE\r\n" + 
			"(Id_TipoAcceso = @Id_TipoAcceso OR @Id_TipoAcceso IS NULL)",
			"-- -------------------------------------------------------------\r\n" + 
			"--\r\n" + 
			"-- Llenado de catalogos\r\n" + 
			"--\r\n" + 
			"-- -------------------------------------------------------------\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"INSERT INTO [GeneSys].Cat_TipoUsuario VALUES('ADMINISTRADOR', 'Administrador de sistema', 1)\r\n" + 
			"\r\n" + 
			"EXEC [GeneSys].SP_UsuarioALT @Usuario = 'admin', @Password = 'default', @TipoUsuario_Id = 1\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Cat_Control] ON \r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (1, N'TEXTBOX', N'TXT')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (2, N'COMBO', N'CMB')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (3, N'FECHA', N'FCH')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (4, N'ARCHIVO', N'XML')\r\n" + 
			"\r\n" +
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (5, N'COMBO', N'CMB')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (6, N'LISTATEXBOX', N'FIL')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (7, N'GRID', N'GRI')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (8, N'TXTBUSCADOR', N'TXB')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (9, N'TABLA', N'TBL')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (10, N'RADIO LISTA', N'RBL')\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Cat_Control] OFF\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Estatus] ([Id_Estatus], [Descripcion]) VALUES (1, N'Activo')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Estatus] ([Id_Estatus], [Descripcion]) VALUES (0, N'Inactivo')\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Cat_Pagina] ON \r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (1, N'Reporte a Excel', 1, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (2, N'Reporte en Grid', 1, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (3, N'Reporte en XML', 1, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (4, N'Grafica Linea ,Barras & Spider', 1, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (5, N'Grafica Pay & Dona', 1, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (6, N'Proceso Nocturno', 0, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (7, N'Pagina de Alta', 1, 1)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (8, N'Pagina de Modificacion', 1, 1)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (9, N'Pagina de Mostrar', 1, 1)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (10, N'Reporte Txt', 1, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (11, N'Reporte Grid Por Default', 1, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (12, N'Stream TXT', 1, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (13, N'Reporte en PDF', 1, 0)\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Cat_Pagina] OFF\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Cat_Parametros] ON \r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Parametros] ([Id_Parametro], [Descripcion], [Clave], [Val_Varchar]) VALUES (1, N'Base Fija', N'PSW', N'RELUSALU1')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Parametros] ([Id_Parametro], [Descripcion], [Clave], [Val_Varchar]) VALUES (2, N'Bloqueos', N'BLO', '6')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Parametros] ([Id_Parametro], [Descripcion], [Clave], [Val_Varchar]) VALUES (3, N'Min Bloqueo', N'MBL', '1')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Parametros] ([Id_Parametro], [Descripcion], [Clave], [Val_Varchar]) VALUES (4, N'Min Session', N'MSS', '10')\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Cat_Parametros] OFF\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_TipoAcceso] ([Id_TipoAcceso], [Descripcion]) VALUES (1, N'Tipo Usuario o Tipo Perfil')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_TipoAcceso] ([Id_TipoAcceso], [Descripcion]) VALUES (2, N'Usuario')\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Cat_Variable] ON \r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (1, N'STRING', N'STR')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (2, N'INT', N'INT')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (3, N'SESSION STRING', N'SST')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (4, N'SESSION INT', N'SIN')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (5, N'NULL', N'NUL')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (6, N'Archivo', N'XML')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (7, N'ARCHIVO GOOGLE DRIVE', N'AGD')\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (8, N'ARCHIVOS FISICOS', N'ARF')\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Cat_Variable] OFF\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Reportes] ON \r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Reportes] ([Id_Reporte], [Nombre], [Sp], [Pagina_Id], [Estatus_Id], [Descripcion], [Nombre_Archivo], [Orden], [Campo_Nombre], [Encoding], [Ruta_Default], [Servidor], [DB], [Esquema], [Ejecuciones], [Fecha_Ult_Exec], [Fecha_Creacion], [RegGrid], [Padre], [class], [URL], [SistemaId], [TipoRedirect], [Menu_Id]) VALUES (1, N'Alta de usuarios', N'SP_UsuarioALT', 11, 1, N'', N'Reporte   ', -1, N'', 1252, NULL, NULL, N'', N'GeneSys', NULL, NULL, CAST(N'2018-11-07T00:00:00' AS SmallDateTime), 10, NULL, N'fa-square', NULL, NULL, NULL, NULL)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Reportes] ([Id_Reporte], [Nombre], [Sp], [Pagina_Id], [Estatus_Id], [Descripcion], [Nombre_Archivo], [Orden], [Campo_Nombre], [Encoding], [Ruta_Default], [Servidor], [DB], [Esquema], [Ejecuciones], [Fecha_Ult_Exec], [Fecha_Creacion], [RegGrid], [Padre], [class], [URL], [SistemaId], [TipoRedirect], [Menu_Id]) VALUES (2, N'Alta de permisos', N'SP_AccesosALT', 11, 1, N'', N'Reporte   ', -1, N'', 1252, NULL, NULL, N'', N'GeneSys', NULL, NULL, CAST(N'2018-11-07T00:00:00' AS SmallDateTime), 10, NULL, N'fa-square', NULL, NULL, NULL, NULL)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Reportes] ([Id_Reporte], [Nombre], [Sp], [Pagina_Id], [Estatus_Id], [Descripcion], [Nombre_Archivo], [Orden], [Campo_Nombre], [Encoding], [Ruta_Default], [Servidor], [DB], [Esquema], [Ejecuciones], [Fecha_Ult_Exec], [Fecha_Creacion], [RegGrid], [Padre], [class], [URL], [SistemaId], [TipoRedirect], [Menu_Id]) VALUES (3, N'Alta de reportes', N'AltaReporte', 11, 1, N'', N'Reporte   ', -1, N'', 1252, NULL, NULL, N'', N'GeneSys', NULL, NULL, CAST(N'2018-11-07T00:00:00' AS SmallDateTime), 10, NULL, N'fa-square', NULL, NULL, NULL, NULL)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Reportes] ([Id_Reporte], [Nombre], [Sp], [Pagina_Id], [Estatus_Id], [Descripcion], [Nombre_Archivo], [Orden], [Campo_Nombre], [Encoding], [Ruta_Default], [Servidor], [DB], [Esquema], [Ejecuciones], [Fecha_Ult_Exec], [Fecha_Creacion], [RegGrid], [Padre], [class], [URL], [SistemaId], [TipoRedirect], [Menu_Id]) VALUES (4, N'Alta de tipo de usuario', N'SP_Cat_TipoUsuarioALT', 11, 1, N'', N'Reporte   ', -1, N'', 1252, NULL, NULL, N'', N'GeneSys', NULL, NULL, CAST(N'2018-11-07T00:00:00' AS SmallDateTime), 10, NULL, N'fa-square', NULL, NULL, NULL, NULL)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Reportes] ([Id_Reporte], [Nombre], [Sp], [Pagina_Id], [Estatus_Id], [Descripcion], [Nombre_Archivo], [Orden], [Campo_Nombre], [Encoding], [Ruta_Default], [Servidor], [DB], [Esquema], [Ejecuciones], [Fecha_Ult_Exec], [Fecha_Creacion], [RegGrid], [Padre], [class], [URL], [SistemaId], [TipoRedirect], [Menu_Id]) VALUES (5, N'Reportes', N'SP_ReportesCON2', 11, 1, N'', N'Reporte   ', -1, N'', 1252, NULL, NULL, N'', N'GeneSys', NULL, NULL, CAST(N'2018-11-15T00:00:00' AS SmallDateTime), 10, NULL, N'fa-square', NULL, NULL, NULL, NULL)\r\n" + 
			"\r\n" + 			
			"INSERT [GeneSys].[Reportes] ([Id_Reporte], [Nombre], [Sp], [Pagina_Id], [Estatus_Id], [Descripcion], [Nombre_Archivo], [Orden], [Campo_Nombre], [Encoding], [Ruta_Default], [Servidor], [DB], [Esquema], [Ejecuciones], [Fecha_Ult_Exec], [Fecha_Creacion], [RegGrid], [Padre], [class], [URL], [SistemaId], [TipoRedirect], [Menu_Id]) VALUES (6, N'Editar reporte', N'SP_ReportesACT', 11, 1, N'', N'Reporte   ', -1, N'', 1252, NULL, NULL, N'', N'GeneSys', NULL, NULL, CAST(N'2018-11-15T00:00:00' AS SmallDateTime), 10, NULL, N'fa-square', NULL, NULL, NULL, NULL)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Reportes] ([Id_Reporte], [Nombre], [Sp], [Pagina_Id], [Estatus_Id], [Descripcion], [Nombre_Archivo], [Orden], [Campo_Nombre], [Encoding], [Ruta_Default], [Servidor], [DB], [Esquema], [Ejecuciones], [Fecha_Ult_Exec], [Fecha_Creacion], [RegGrid], [Padre], [class], [URL], [SistemaId], [TipoRedirect], [Menu_Id]) VALUES (7, N'Editar Campo', N'SP_CamposACT', 11, 1, N'', N'Reporte   ', -1, N'', 1252, NULL, NULL, N'', N'GeneSys', NULL, NULL, CAST(N'2018-11-15T00:00:00' AS SmallDateTime), 10, NULL, N'fa-square', NULL, NULL, NULL, NULL)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Reportes] ([Id_Reporte], [Nombre], [Sp], [Pagina_Id], [Estatus_Id], [Descripcion], [Nombre_Archivo], [Orden], [Campo_Nombre], [Encoding], [Ruta_Default], [Servidor], [DB], [Esquema], [Ejecuciones], [Fecha_Ult_Exec], [Fecha_Creacion], [RegGrid], [Padre], [class], [URL], [SistemaId], [TipoRedirect], [Menu_Id]) VALUES (8, N'Campos', N'SP_CamposCON2', 11, 1, N'', N'Reporte   ', -1, N'', 1252, NULL, NULL, N'', N'GeneSys', NULL, NULL, CAST(N'2018-11-15T00:00:00' AS SmallDateTime), 10, NULL, N'fa-square', NULL, NULL, NULL, NULL)\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Reportes] OFF\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Campos] ON \r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (1, 1, 2, 1, N'Perfil_Id', N'Perfil', 1, 1, N'', N'', N'', N'@Perfil_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (2, 1, 1, 1, N'Nombres', N'Nombres', 2, 1, N'', N'', N'', N'@Nombres', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (3, 1, 1, 1, N'ApellidoP', N'Apellido Paterno', 3, 1, N'', N'', N'', N'@ApellidoP', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (4, 1, 1, 1, N'ApellidoM', N'Apellido Materno', 4, 1, N'', N'', N'', N'@ApellidoM', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (5, 1, 1, 1, N'RFC', N'RFC', 5, 1, N'', N'', N'', N'@RFC', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (6, 1, 1, 1, N'Lada', N'Lada', 6, 1, N'', N'', N'', N'@Lada', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (7, 1, 1, 1, N'Telefono', N'Telefono', 7, 1, N'', N'', N'', N'@Telefono', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (8, 1, 1, 1, N'Email', N'Email', 8, 1, N'', N'', N'', N'@Email', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (9, 1, 1, 1, N'Usuario', N'Usuario', 9, 1, N'', N'', N'', N'@Usuario', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (10, 1, 1, 1, N'Password', N'Password', 10, 1, N'', N'', N'', N'@Password', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (11, 1, 2, 2, N'TipoUsuario_Id', N'Tipo de usuario', 11, 1, N'', N'GeneSys.SP_Cat_TipoUsuarioCON', N'', N'@TipoUsuario_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (12, 1, 2, 1, N'Sucursal_Id', N'Sucursal', 12, 1, N'', N'', N'', N'@Sucursal_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (13, 1, 2, 2, N'Estatus_Id', N'Estatus', 13, 1, N'', N'GeneSys.SP_Cat_EstatusCON', N'', N'@Estatus_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (17, 1, 2, 1, N'SoloOficina', N'SoloOficina', 17, 1, N'', N'', N'', N'@SoloOficina', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (25, 2, 2, 2, N'Reporte_Id', N'Reporte', 1, 1, N'', N'GeneSys.SP_ReportesCON2', N'', N'@Reporte_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (26, 2, 2, 2, N'UsuarioTipo_ID', N'Usuario o tipo', 3, 1, N'', N'GeneSys.SP_UsuarioTipoCON', N'', N'@UsuarioTipo_ID', N'TipoAcceso_Id', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (27, 2, 2, 5, N'TipoAcceso_Id', N'Tipo de acceso', 2, 1, N'', N'GeneSys.SP_Cat_TipoAccesoCON', N'', N'@TipoAcceso_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (28, 2, 2, 1, N'TipoAcc', N'Menu', 4, 1, N'0', N'', N'', N'@TipoAcc', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (29, 3, 1, 1, N'Nombre', N'Nombre', 1, 1, N'', N'', N'', N'@Nombre', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (30, 3, 1, 1, N'Sp', N'Sp', 2, 1, N'', N'', N'', N'@Sp', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (31, 3, 1, 1, N'Esquema', N'Esquema', 3, 1, N'', N'', N'', N'@Esquema', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (32, 3, 1, 1, N'BaseDatos', N'BaseDatos', 4, 1, N'', N'', N'', N'@BaseDatos', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (33, 3, 1, 1, N'Descripcion', N'Descripcion', 5, 1, N'', N'', N'', N'@Descripcion', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (34, 3, 1, 1, N'Nombre_Archivo', N'Nombre_Archivo', 6, 1, N'', N'', N'', N'@Nombre_Archivo', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (35, 3, 1, 1, N'Campo_Nombre', N'Campo_Nombre', 7, 1, N'', N'', N'', N'@Campo_Nombre', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (36, 3, 2, 2, N'Pagina_Id', N'Pagina_Id', 8, 1, N'', N'GeneSys.SP_Cat_PaginaCON', N'', N'@Pagina_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (37, 3, 2, 1, N'Padre', N'Padre', 9, 1, N'', N'', N'', N'@Padre', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (38, 3, 2, 1, N'SistemaID', N'SistemaID', 10, 1, N'', N'', N'', N'@SistemaID', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (39, 3, 1, 1, N'URL', N'URL', 11, 1, N'', N'', N'', N'@URL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (40, 4, 1, 1, N'TipoUsuario', N'TipoUsuario', 1, 1, N'', N'', N'', N'@TipoUsuario', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (41, 4, 1, 1, N'Descripcion', N'Descripcion', 2, 1, N'', N'', N'', N'@Descripcion', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (42, 5, 2, 1, N'Id_Reporte', N'Id_Reporte', 1, 1, N'', N'', N'', N'@Id_Reporte', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (43, 5, 1, 1, N'Nombre', N'Nombre', 2, 1, N'', N'', N'', N'@Nombre', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (44, 5, 1, 1, N'Sp', N'Sp', 3, 1, N'', N'', N'', N'@Sp', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (45, 6, 2, 1, N'Id_Reporte', N'Id_Reporte', 1, 0, N'', N'', N'', N'@Id_Reporte', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (46, 6, 1, 1, N'Nombre', N'Nombre', 2, 1, N'', N'', N'', N'@Nombre', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (47, 6, 1, 1, N'Sp', N'Sp', 3, 1, N'', N'', N'', N'@Sp', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (48, 6, 2, 2, N'Pagina_Id', N'Pagina', 4, 1, N'', N'GeneSys.SP_Cat_PaginaCON', N'', N'@Pagina_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (49, 6, 2, 2, N'Estatus_Id', N'Estatus', 5, 1, N'', N'GeneSys.SP_Cat_EstatusCON', N'', N'@Estatus_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (50, 6, 1, 1, N'Descripcion', N'Descripcion', 6, 1, N'', N'', N'', N'@Descripcion', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (51, 6, 1, 1, N'Nombre_Archivo', N'Nombre_Archivo', 7, 1, N'', N'', N'', N'@Nombre_Archivo', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (52, 6, 2, 1, N'Orden', N'Orden', 8, 1, N'', N'', N'', N'@Orden', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (53, 6, 1, 1, N'Campo_Nombre', N'Campo_Nombre', 9, 1, N'', N'', N'', N'@Campo_Nombre', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (54, 6, 2, 1, N'Encoding', N'Encoding', 10, 1, N'', N'', N'', N'@Encoding', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (55, 6, 1, 1, N'Ruta_Default', N'Ruta_Default', 11, 1, N'', N'', N'', N'@Ruta_Default', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (56, 6, 1, 1, N'Servidor', N'Servidor', 12, 1, N'', N'', N'', N'@Servidor', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (57, 6, 1, 1, N'DB', N'DB', 13, 1, N'', N'', N'', N'@DB', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (58, 6, 1, 1, N'Esquema', N'Esquema', 14, 1, N'', N'', N'', N'@Esquema', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (63, 6, 2, 1, N'Padre', N'Padre', 19, 1, N'', N'', N'', N'@Padre', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (64, 6, 1, 1, N'class', N'class', 20, 1, N'', N'', N'', N'@class', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (65, 6, 1, 1, N'URL', N'URL', 21, 1, N'', N'', N'', N'@URL', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" +
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (70, 7, 2, 1, N'ID_Campo', N'ID_Campo', 1, 0, N'', N'', N'', N'@ID_Campo', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n"+
			"\r\n" + 		
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (71, 7, 2, 2, N'Variable_Id', N'Variable_Id', 3, 1, N'', N'GeneSys.SP_Cat_VariableCON', N'', N'@Variable_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (72, 7, 2, 2, N'Control_Id', N'Control_Id', 4, 1, N'', N'GeneSys.SP_Cat_ControlCON', N'', N'@Control_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (73, 7, 1, 1, N'Titulo_Pagina', N'Titulo_Pagina', 5, 1, N'', N'', N'', N'@Titulo_Pagina', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (74, 7, 1, 1, N'Nombre', N'Nombre', 6, 1, N'', N'', N'', N'@Nombre', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (75, 7, 2, 1, N'Orden', N'Orden', 7, 1, N'', N'', N'', N'@Orden', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (76, 7, 2, 1, N'Visible', N'Visible', 8, 1, N'', N'', N'', N'@Visible', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (77, 7, 1, 1, N'Valor_Variable', N'Valor_Variable', 9, 1, N'', N'', N'', N'@Valor_Variable', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (78, 7, 1, 1, N'DataSet', N'DataSet', 10, 1, N'', N'', N'', N'@DataSet', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (79, 7, 1, 1, N'DS', N'DS', 11, 1, N'', N'', N'', N'@DS', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (80, 7, 1, 1, N'Parametro', N'Parametro', 12, 1, N'', N'', N'', N'@Parametro', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (81, 7, 1, 1, N'ComboPadre', N'ComboPadre', 13, 1, N'', N'', N'', N'@ComboPadre', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (82, 7, 1, 1, N'FileJScript', N'FileJScript', 14, 1, N'', N'', N'', N'@FileJScript', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (83, 7, 1, 1, N'FuncionJS', N'FuncionJS', 15, 1, N'', N'', N'', N'@FuncionJS', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (84, 7, 1, 1, N'Evento', N'Evento', 16, 1, N'', N'', N'', N'@Evento', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (85, 7, 1, 1, N'CssFile', N'CssFile', 17, 1, N'', N'', N'', N'@CssFile', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (86, 7, 1, 1, N'Css', N'Css', 18, 1, N'', N'', N'', N'@Css', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (87, 7, 2, 1, N'Ancho', N'Ancho', 19, 1, N'', N'', N'', N'@Ancho', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (88, 7, 2, 1, N'Longitud', N'Longitud', 20, 1, N'', N'', N'', N'@Longitud', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (89, 7, 2, 1, N'Renglon', N'Renglon', 21, 1, N'', N'', N'', N'@Renglon', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (90, 7, 1, 1, N'Class', N'Class', 22, 1, N'', N'', N'', N'@Class', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (91, 7, 1, 1, N'Parsley', N'Parsley', 23, 1, N'', N'', N'', N'@Parsley', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (92, 7, 2, 1, N'Omitir', N'Omitir', 24, 1, N'', N'', N'', N'@Omitir', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"INSERT [GeneSys].[Campos] ([ID_Campo], [Reporte_Id], [Variable_Id], [Control_Id], [Titulo_Pagina], [Nombre], [Orden], [Visible], [Valor_Variable], [DataSet], [DS], [Parametro], [ComboPadre], [FileJScript], [FuncionJS], [Evento], [CssFile], [Css], [Ancho], [Longitud], [Renglon], [Class], [Parsley], [Omitir]) VALUES (93, 8, 2, 1, N'Reporte_Id', N'Reporte_Id', 1, 0, N'', N'', N'', N'@Reporte_Id', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, NULL, NULL, 0)\r\n" + 
			"\r\n" + 
			"SET IDENTITY_INSERT [GeneSys].[Campos] OFF\r\n" + 
			"INSERT INTO GeneSys.CAT_Menu\r\n" + 
			"VALUES\r\n" + 
			"('Administrador', 'Opciones de administrador', 'Administrador', '#', 0, 1, 1, 1, null, null),\r\n" + 
			"('Alta de usuarios','Alta de usuarios del sistema','Administrador','?Reporte=1', 1, 1, 1, 1, null, null),\r\n" + 
			"('Alta de permisos','Alta de permisos para usuarios','Administrador','?Reporte=2', 1, 1, 1, 1, null, null),\r\n" + 
			"('Alta de reportes','Alta de reportes','Administrador','?Reporte=3', 1, 1, 1, 1, null, null),\r\n" + 
			"('Alta de tipo de usuario','Alta de tipos de usuarios','Administrador','?Reporte=4', 1, 1, 1, 1, null, null)," +
			"('Reportes','Administracion de reportes','Administrador','?Reporte=5', 1, 1, 1, 1, null, null)" +
			"\r\n" + 
			"INSERT INTO GeneSys.Accesos (Reporte_Id, UsuarioTipo_ID, TipoAcceso_Id, TipoAcc)\r\n" + 
			"SELECT IdMenu, 1, 1, 1 FROM GeneSys.Cat_Menu "
			+
			"INSERT INTO GeneSys.Accesos (Reporte_Id, UsuarioTipo_ID, TipoAcceso_Id, TipoAcc)\r\n" + 
			"SELECT Id_Reporte, 1, 1, 0 FROM GeneSys.Reportes "};

	public static final String AUDITORIA = 
			"CREATE SCHEMA Auditoria\r\n" + 
			"GO\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING ON\r\n" + 
			"GO\r\n" + 
			"CREATE TABLE [Auditoria].[tb_Objetos](\r\n" + 
			"	[id_Objeto] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Objeto] [varchar](100) NULL,\r\n" + 
			"	[Descripcion] [varchar](1000) NULL,\r\n" + 
			"	[Sistema_Id] [int] NULL,\r\n" + 
			"	[Registro] [bit] NULL,\r\n" + 
			" CONSTRAINT [PK_tb_Objetos] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[id_Objeto] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING OFF\r\n" + 
			"GO\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING ON\r\n" + 
			"GO\r\n" + 
			"CREATE TABLE [Auditoria].[tb_Procesos](\r\n" + 
			"	[Id_Transaccion] [bigint] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Usuario_Id] [int] NULL,\r\n" + 
			"	[Ip] [varchar](15) NULL,\r\n" + 
			"	[Objeto_Id] [int] NULL,\r\n" + 
			"	[FechaSys] [datetime] NULL,\r\n" + 
			" CONSTRAINT [PK_tb_Procesos] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Transaccion] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING OFF\r\n" + 
			"GO\r\n" + 
			"/****** Object:  Table [Auditoria].[tb_Procesos_Hist]    Script Date: 02/11/2018 11:37:23 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING ON\r\n" + 
			"GO\r\n" + 
			"CREATE TABLE [Auditoria].[tb_Procesos_Hist](\r\n" + 
			"	[Id_Transaccion] [bigint] NOT NULL,\r\n" + 
			"	[Usuario_Id] [int] NULL,\r\n" + 
			"	[Ip] [varchar](15) NULL,\r\n" + 
			"	[Objeto_Id] [int] NULL,\r\n" + 
			"	[FechaSys] [datetime] NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING OFF\r\n" + 
			"GO\r\n" + 
			"\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING ON\r\n" + 
			"GO\r\n" + 
			"CREATE TABLE [Auditoria].[tb_ProcesosDetalle](\r\n" + 
			"	[Transaccion_Id] [bigint] NULL,\r\n" + 
			"	[Id_Paso] [bigint] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Objeto_Id_Llama] [int] NULL,\r\n" + 
			"	[Objeto_Id_Ejecuta] [int] NULL,\r\n" + 
			"	[FechaInicio] [datetime] NULL,\r\n" + 
			"	[FechaFin] [datetime] NULL,\r\n" + 
			"	[Comando] [varchar](1000) NULL,\r\n" + 
			"	[Resultado] [varchar](1000) NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING OFF\r\n" + 
			"GO\r\n" + 
			"\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING ON\r\n" + 
			"GO\r\n" + 
			"CREATE TABLE [Auditoria].[tb_ProcesosDetalle_Hist](\r\n" + 
			"	[Transaccion_Id] [bigint] NULL,\r\n" + 
			"	[Id_Paso] [bigint] NULL,\r\n" + 
			"	[Objeto_Id_Llama] [int] NULL,\r\n" + 
			"	[Objeto_Id_Ejecuta] [int] NULL,\r\n" + 
			"	[FechaInicio] [datetime] NULL,\r\n" + 
			"	[FechaFin] [datetime] NULL,\r\n" + 
			"	[Comando] [varchar](1000) NULL,\r\n" + 
			"	[Resultado] [varchar](1000) NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING OFF\r\n" + 
			"GO\r\n" + 
			"\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING ON\r\n" + 
			"GO\r\n" + 
			"CREATE TABLE [Auditoria].[Tb_Sistemas](\r\n" + 
			"	[Id_Sistema] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[Sistema] [varchar](50) NULL,\r\n" + 
			"	[Tipo_Id] [int] NULL,\r\n" + 
			"	[Visible] [bit] NULL,\r\n" + 
			" CONSTRAINT [PK_Tb_Sistemas] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Sistema] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING OFF\r\n" + 
			"GO\r\n" + 
			"\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"CREATE TABLE [Auditoria].[Tb_SistemasEmpresas](\r\n" + 
			"	[Sistema_Id] [int] NULL,\r\n" + 
			"	[Empresa_Id] [int] NULL,\r\n" + 
			"	[Estatus_Id] [int] NULL\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING ON\r\n" + 
			"GO\r\n" + 
			"CREATE TABLE [Auditoria].[tb_TipoSistema](\r\n" + 
			"	[Id_Tipo] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
			"	[TipoSistema] [varchar](30) NULL,\r\n" + 
			" CONSTRAINT [PK_tb_TipoSistema] PRIMARY KEY CLUSTERED \r\n" + 
			"(\r\n" + 
			"	[Id_Tipo] ASC\r\n" + 
			")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"SET ANSI_PADDING OFF\r\n" + 
			"GO\r\n" + 
			"ALTER TABLE [Auditoria].[tb_Objetos]  WITH CHECK ADD  CONSTRAINT [FK_tb_Objetos_Tb_Sistemas] FOREIGN KEY([Sistema_Id])\r\n" + 
			"REFERENCES [Auditoria].[Tb_Sistemas] ([Id_Sistema])\r\n" + 
			"GO\r\n" + 
			"ALTER TABLE [Auditoria].[tb_Objetos] CHECK CONSTRAINT [FK_tb_Objetos_Tb_Sistemas]\r\n" + 
			"GO\r\n" + 
			"ALTER TABLE [Auditoria].[Tb_Sistemas]  WITH CHECK ADD  CONSTRAINT [FK_Tb_Sistemas_tb_TipoSistema] FOREIGN KEY([Tipo_Id])\r\n" + 
			"REFERENCES [Auditoria].[tb_TipoSistema] ([Id_Tipo])\r\n" + 
			"GO\r\n" + 
			"ALTER TABLE [Auditoria].[Tb_Sistemas] CHECK CONSTRAINT [FK_Tb_Sistemas_tb_TipoSistema]\r\n" + 
			"GO\r\n" + 
			"\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"CREATE procedure [Auditoria].[Log_Registra] \r\n" + 
			"			(	@Transaccion		bigint  = null output,			--  Transaccion de Registro de Actividades\r\n" + 
			"				@Sy_Paso			int			= null output,		--  Valor del paso que se esta ejecutando\r\n" + 
			"				@UsurarioId			int			output,			--  Usuraio que Ejecuta SP \r\n" + 
			"				@IP					varchar(15) output,			--  IP de Usuario\r\n" + 
			"				@ObjetoId			int,					--  Objeto de Donde se Ejecuta El SP\r\n" + 
			"				@Sy_ObjetoId		int,					--  Objeto que se esta ejecutando\r\n" + 
			"				@NumeroTransaccion	bigint = null output,			--  Transaccion de Cartera para Identificar su Afectaciones \r\n" + 
			"				@Accion				int,					--	Si es Inicio (1) o Fin (2) del Registro de Actividad,\r\n" + 
			"				@Comando			varchar(1000),			--	SP que se Ejecuta con todo y parametros\r\n" + 
			"				@Err_Message		varchar(1000) = null output	--	Mensage de salida del sp se puede recuperar con ERROR_MESSAGE() \r\n" + 
			"			)\r\n" + 
			"	As\r\n" + 
			"	\r\n" + 
			"	/*Declararion de Variables*/\r\n" + 
			"	\r\n" + 
			"\r\n" + 
			"	/*Asignacion de Variables*/\r\n" + 
			"\r\n" + 
			"	if @Accion = 1  begin-- Inicio del Registro\r\n" + 
			"		\r\n" + 
			"		if isnull(@Transaccion,0) =0 begin\r\n" + 
			"\r\n" + 
			"		insert into [Auditoria].[tb_Procesos] ( Usuario_Id, Ip, Objeto_Id, FechaSys)\r\n" + 
			"			values (@UsurarioId,@ip,@ObjetoId,getdate())\r\n" + 
			"\r\n" + 
			"		set @Transaccion= @@IDENTITY\r\n" + 
			"\r\n" + 
			"		insert into [Auditoria].[tb_ProcesosDetalle](Transaccion_Id,  Objeto_Id_Llama, Objeto_Id_Ejecuta, FechaInicio,  Comando)\r\n" + 
			"			values (@Transaccion,@ObjetoId,@Sy_ObjetoId,getdate(),@Comando)\r\n" + 
			"\r\n" + 
			"		set @Sy_Paso= @@IDENTITY\r\n" + 
			"\r\n" + 
			"		end\r\n" + 
			"		else begin\r\n" + 
			"\r\n" + 
			"		insert into [Auditoria].[tb_ProcesosDetalle](Transaccion_Id,  Objeto_Id_Llama, Objeto_Id_Ejecuta, FechaInicio,  Comando)\r\n" + 
			"			values (@Transaccion,@ObjetoId,@Sy_ObjetoId,getdate(),@Comando)\r\n" + 
			"\r\n" + 
			"			set @Sy_Paso= @@IDENTITY\r\n" + 
			"		end\r\n" + 
			"\r\n" + 
			"		\r\n" + 
			"	end\r\n" + 
			"	else begin\r\n" + 
			"		if @Accion=2 begin \r\n" + 
			"			if @Transaccion is not null and @Sy_Paso is not null begin\r\n" + 
			"				update	[Auditoria].[tb_ProcesosDetalle] \r\n" + 
			"							set	[FechaFin]	= getdate(),\r\n" + 
			"								[Resultado]	= @Err_Message\r\n" + 
			"					where Transaccion_Id	= @Transaccion \r\n" + 
			"					  and Id_Paso			= @Sy_Paso\r\n" + 
			"\r\n" + 
			"					\r\n" + 
			"			end\r\n" + 
			"	\r\n" + 
			"		end\r\n" + 
			"		\r\n" + 
			"			\r\n" + 
			"	end\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"	\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"GO\r\n" + 
			"/****** Object:  StoredProcedure [Auditoria].[MueveLog]    Script Date: 02/11/2018 11:37:23 a. m. ******/\r\n" + 
			"SET ANSI_NULLS ON\r\n" + 
			"GO\r\n" + 
			"SET QUOTED_IDENTIFIER ON\r\n" + 
			"GO\r\n" + 
			"CREATE PROCEDURE [Auditoria].[MueveLog]\r\n" + 
			"  (\r\n" + 
			"	/*Parametros*/\r\n" + 
			"      @Fecha			  datetime		= null,		 --  Parametros\r\n" + 
			"    /*Parametros Obligatorios*/\r\n" + 
			"	  @NumeroTransaccion  BigInt        = NULL,  --  Transaccion de Cartera para Identificar su Afectaciones    /*Esto es obligatorio para los Sp que Afecten la Cartera*/\r\n" + 
			"	  @Transaccion        BigInt        = NULL, --  Transaccion de Registro de Actividades\r\n" + 
			"	  @Sy_Paso			  int			= NUll,	--  Valor del paso que se esta ejecutando\r\n" + 
			"      @UsurarioId         Int           = -1, --  Usuraio que Ejecuta SP\r\n" + 
			"      @IP                 Varchar(15)   = 'Job Sistema', --  IP de Usuario\r\n" + 
			"      @ObjetoId           Int           = 1 --  Objeto de Donde se Ejecuta El SP\r\n" + 
			"      \r\n" + 
			"	)\r\n" + 
			"AS\r\n" + 
			" /*Deshabilita la salida de counts en los SP*/\r\n" + 
			" SET NOCOUNT ON \r\n" + 
			"/*Declaracion de Variables*/\r\n" + 
			"  /*Variables de Registro*/\r\n" + 
			"    DECLARE   @Err_Message            Varchar(1000),	--  Mensage de salida del sp se puede recuperar con ERROR_MESSAGE() /*Esto nos permite Mandar Errores Controlados*/\r\n" + 
			"              @TranCounter            Int,				--  Verificar si Existe Transaccion Abierta  ,         \r\n" + 
			"              @Sy_Accion              Int,				--  Si es Inicio (1) o Fin (2) del Registro de Actividad,\r\n" + 
			"			  @Sy_ObjetoId			  Int,				--	ObjetoId al que pertenece el SP.  \r\n" + 
			"              @Comando				  Varchar(100)		--  SP que se Ejecuta con todo y parametros\r\n" + 
			"\r\n" + 
			"  /*Variables de SP*/\r\n" + 
			"	Declare @TransaAMover bigint   -- Uso de La variable\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"  /*Declaracion de  variables tipo tabla*/\r\n" + 
			"    DECLARE @Tabla TABLE \r\n" + 
			"            (\r\n" + 
			"              Campos  int\r\n" + 
			"            )\r\n" + 
			"\r\n" + 
			"/***************************NOTA***************************************/\r\n" + 
			"\r\n" + 
			"  /*Declaracion de  Tablas Temporales */\r\n" + 
			"  /*Si se va a verificar que  una tabla temporal no exista*/\r\n" + 
			"    IF OBJECT_ID('tempdb..#tmp_Procesos') IS NOT NULL\r\n" + 
			"      DROP TABLE  #tmp_Procesos\r\n" + 
			"    ELSE\r\n" + 
			"      CREATE Table #tmp_Procesos\r\n" + 
			"       (\r\n" + 
			"          Id_Transaccion	bigint, \r\n" + 
			"		  Usuario_Id		int, \r\n" + 
			"		  Ip				varchar(15), \r\n" + 
			"		  Objeto_Id			int, \r\n" + 
			"		  FechaSys			datetime\r\n" + 
			"        )  \r\n" + 
			"\r\n" + 
			"    IF OBJECT_ID('tempdb..#tmp_ProcesosDetalles') IS NOT NULL\r\n" + 
			"      DROP TABLE  #tmp_ProcesosDetalles\r\n" + 
			"    ELSE\r\n" + 
			"      CREATE Table #tmp_ProcesosDetalles\r\n" + 
			"       (\r\n" + 
			"          Transaccion_Id	bigint, \r\n" + 
			"		  Id_Paso			bigint, \r\n" + 
			"		  Objeto_Id_Llama	int, \r\n" + 
			"		  Objeto_Id_Ejecuta	int, \r\n" + 
			"		  FechaInicio		datetime, \r\n" + 
			"		  FechaFin			datetime, \r\n" + 
			"		  Comando			varchar(1000), \r\n" + 
			"		  Resultado			varchar(1000)\r\n" + 
			"        )  \r\n" + 
			"\r\n" + 
			"	/*Si se va a utilizar una tabla temporal de otro Sp debes de crear la tabla en el sp*/\r\n" + 
			"	/*NOTA: No se pueden usar las dos para la misma tabla*/\r\n" + 
			"/******************************************************************/\r\n" + 
			"/*Inicializacion de Variables*/\r\n" + 
			"\r\n" + 
			"/*Parametros de Registro*/\r\n" + 
			"set @Sy_ObjetoId  = 2-- Asignar el Id del SP\r\n" + 
			"\r\n" + 
			"if isnull((select [Registro] \r\n" + 
			"				from [GarSaAdmin].[Auditoria].[tb_Objetos]\r\n" + 
			"				where [id_Objeto]=@Sy_ObjetoId),0)=0 begin /*Verifica si se va registrar o no el objeto que manda llamar*/\r\n" + 
			"	set @Sy_ObjetoId=0\r\n" + 
			"\r\n" + 
			"end else begin\r\n" + 
			"	if isnull((select [Registro] \r\n" + 
			"				from [GarSaAdmin].[Auditoria].[tb_Objetos] /*Verifica si se va registrar o este SP*/\r\n" + 
			"				where [id_Objeto]=@ObjetoId),0)=0\r\n" + 
			"		set @Sy_ObjetoId=0 \r\n" + 
			"		\r\n" + 
			"	end\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"/*Parametors del SP*/\r\n" + 
			"		set @Fecha=convert(smalldatetime,convert(varchar,isnull(@Fecha,dateadd(dd,-1,getdate())),111))\r\n" + 
			"\r\n" + 
			"  /*Variables de Registro*/\r\n" + 
			"    SET @Err_Message  = NULL\r\n" + 
			"    SET @TranCounter  = @@TRANCOUNT\r\n" + 
			"    SET @Sy_Accion       = 1\r\n" + 
			"	set @Sy_Paso		 = isnull(@Sy_Paso,1)\r\n" + 
			"    SET @Comando     = substring('EXEC  [Auditoria].MueveLog ' +  '@Fecha  = ' + ltrim(rtrim(convert(varchar,@Fecha,111))),1,1000)\r\n" + 
			"\r\n" + 
			"\r\n" + 
			" /*Variables SP*/\r\n" + 
			"\r\n" + 
			"\r\n" + 
			" select @TransaAMover=max([Id_Transaccion] ) \r\n" + 
			"	from [Auditoria].[tb_Procesos]\r\n" + 
			"	where convert(smalldatetime,convert(varchar,fechaSys,111))<=@Fecha\r\n" + 
			"\r\n" + 
			" /*Variables tabla Trmporal*/\r\n" + 
			" insert into #tmp_Procesos(Id_Transaccion, Usuario_Id, Ip, Objeto_Id, FechaSys)\r\n" + 
			"	select Id_Transaccion, Usuario_Id, Ip, Objeto_Id, FechaSys\r\n" + 
			"		from [Auditoria].[tb_Procesos]\r\n" + 
			"		where [Id_Transaccion]<=@TransaAMover\r\n" + 
			"\r\n" + 
			" insert into #tmp_ProcesosDetalles(Transaccion_Id, Id_Paso, Objeto_Id_Llama, Objeto_Id_Ejecuta, FechaInicio, FechaFin, Comando, Resultado)\r\n" + 
			"	select Transaccion_Id, Id_Paso, Objeto_Id_Llama, Objeto_Id_Ejecuta, FechaInicio, FechaFin, Comando, Resultado\r\n" + 
			"		from [Auditoria].[tb_ProcesosDetalle]\r\n" + 
			"		where [Transaccion_Id]<=@TransaAMover\r\n" + 
			"\r\n" + 
			"/*Registro de Actividad Inicio*/ \r\n" + 
			"if @Sy_ObjetoId<>0\r\n" + 
			"  exec [Auditoria].[Log_Registra]  @Transaccion output,@Sy_Paso output,  @UsurarioId output, @IP output, @ObjetoId ,@Sy_ObjetoId ,  @NumeroTransaccion output, @Sy_Accion, @Comando, @Err_Message output\r\n" + 
			"\r\n" + 
			"/*Asigna el objetoId de este SP por sise ejecuta adentro*/\r\n" + 
			"  SET @ObjetoId   = @Sy_ObjetoId--##\r\n" + 
			"\r\n" + 
			"/*SP Transaccion*/\r\n" + 
			"  IF @TranCounter > 0\r\n" + 
			"    SAVE TRANSACTION MueveLog\r\n" + 
			"  ELSE\r\n" + 
			"    BEGIN TRAN MueveLog\r\n" + 
			"  \r\n" + 
			"BEGIN TRY\r\n" + 
			"  \r\n" + 
			"  /*Validacion:*/\r\n" + 
			"    IF (  SELECT  count(*) \r\n" + 
			"            FROM #tmp_Procesos   \r\n" + 
			"         )  = 0 BEGIN\r\n" + 
			"         \r\n" + 
			"        /*Marca Error Controlado*/\r\n" + 
			"        SET @Err_Message  = 'No hay Procesos a mover'\r\n" + 
			"        RAISERROR (@Err_Message, 11,10)\r\n" + 
			"    END\r\n" + 
			"	IF (  SELECT  count(*) \r\n" + 
			"            FROM #tmp_ProcesosDetalles   \r\n" + 
			"         )  = 0 BEGIN\r\n" + 
			"         \r\n" + 
			"        /*Marca Error Controlado*/\r\n" + 
			"        SET @Err_Message  = 'No hay Procesos a mover'\r\n" + 
			"        RAISERROR (@Err_Message, 11,10)\r\n" + 
			"    END\r\n" + 
			"    ELSE BEGIN\r\n" + 
			"\r\n" + 
			"			insert into [Auditoria].[tb_Procesos_Hist]	(Id_Transaccion, Usuario_Id, Ip, Objeto_Id, FechaSys)\r\n" + 
			"				select Id_Transaccion, Usuario_Id, Ip, Objeto_Id, FechaSys \r\n" + 
			"					from  #tmp_Procesos\r\n" + 
			"					where [Id_Transaccion]	<= @TransaAMover\r\n" + 
			"\r\n" + 
			"			insert into [Auditoria].[tb_ProcesosDetalle_Hist](Transaccion_Id, Id_Paso, Objeto_Id_Llama, Objeto_Id_Ejecuta, FechaInicio, FechaFin, Comando, Resultado)\r\n" + 
			"				select Transaccion_Id, Id_Paso, Objeto_Id_Llama, Objeto_Id_Ejecuta, FechaInicio, FechaFin, Comando, Resultado\r\n" + 
			"					from #tmp_ProcesosDetalles\r\n" + 
			"					where [Transaccion_Id]	<= @TransaAMover\r\n" + 
			"\r\n" + 
			"			Delete from [Auditoria].[tb_Procesos]\r\n" + 
			"				where [Id_Transaccion]<=@TransaAMover\r\n" + 
			"\r\n" + 
			"			Delete [Auditoria].[tb_ProcesosDetalle]\r\n" + 
			"				where [Transaccion_Id]<=@TransaAMover\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"			\r\n" + 
			"      /*Fin Codifo de SP*/\r\n" + 
			"      \r\n" + 
			"      SET @Err_Message  = 'Finalizo Correctamente'\r\n" + 
			"      \r\n" + 
			" \r\n" + 
			"      RAISERROR (@Err_Message, 0,0)\r\n" + 
			"      IF @TranCounter = 0\r\n" + 
			"        COMMIT TRAN MueveLog\r\n" + 
			"\r\n" + 
			"      /*Registro de Actividad FIN*/ \r\n" + 
			"        SET @Sy_Accion            = 2\r\n" + 
			"		if @Sy_ObjetoId<>0\r\n" + 
			"        exec [Auditoria].Log_Registra  @Transaccion,@Sy_Paso,  @UsurarioId, @IP, @ObjetoId,	@Sy_ObjetoId ,  @NumeroTransaccion, @Sy_Accion, @Comando, @Err_Message\r\n" + 
			"         SET NOCOUNT OFF\r\n" + 
			"\r\n" + 
			"    END\r\n" + 
			" \r\n" + 
			"END TRY\r\n" + 
			"BEGIN CATCH\r\n" + 
			" \r\n" + 
			"  SET @Err_Message  = substring(ltrim(rtrim(convert(varchar,Error_Line()))) + ' - ' + ISNULL( @Err_Message,ERROR_MESSAGE()),1,1000) \r\n" + 
			"    \r\n" + 
			"  IF @TranCounter = 0\r\n" + 
			"    ROLLBACK TRAN MueveLog\r\n" + 
			"  ELSE\r\n" + 
			"    IF XACT_STATE() <> -1\r\n" + 
			"      ROLLBACK TRAN MueveLog \r\n" + 
			"\r\n" + 
			"  /*Registro de Actividad FIN*/ \r\n" + 
			"    SET @Sy_Accion            = 2\r\n" + 
			"	if @Sy_ObjetoId<>0\r\n" + 
			"    exec [Auditoria].Log_Registra  @Transaccion,@Sy_Paso,  @UsurarioId, @IP, @ObjetoId,@Sy_ObjetoId ,  @NumeroTransaccion, @Sy_Accion, @Comando, @Err_Message\r\n" + 
			"	\r\n" + 
			"    SET NOCOUNT OFF\r\n" + 
			"\r\n" + 
			"  RAISERROR (@Err_Message, 11,10)\r\n" + 
			"   \r\n" + 
			"           \r\n" + 
			"END CATCH\r\n" + 
			"";
}
