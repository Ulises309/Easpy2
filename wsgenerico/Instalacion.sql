
-- ----------------------------------------------------
--
-- Reportes
--
-- ----------------------------------------------------

CREATE SCHEMA Reportes

GO
/****** Object:  Table [Reportes].[Accesos]    Script Date: 05/07/2018 10:40:19 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Accesos](
	[Id_Acceso] [int] IDENTITY(1,1) NOT NULL,
	[Reporte_Id] [int] NULL,
	[UsuarioTipo_ID] [int] NULL,
	[TipoAcceso_Id] [int] NULL,
	[TipoAcc] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Campos]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Campos](
	[ID_Campo] [int] IDENTITY(1,1) NOT NULL,
	[Reporte_Id] [int] NOT NULL,
	[Variable_Id] [int] NULL,
	[Control_Id] [int] NULL,
	[Titulo_Pagina] [varchar](30) NULL,
	[Nombre] [varchar](100) NULL,
	[Orden] [int] NULL,
	[Visible] [int] NULL,
	[Valor_Variable] [varchar](60) NULL,
	[DataSet] [varchar](300) NULL,
	[DS] [varchar](1000) NULL,
	[Parametro] [nvarchar](30) NULL,
	[ComboPadre] [varchar](30) NULL,
	[FileJScript] [varchar](100) NULL,
	[FuncionJS] [varchar](50) NULL,
	[Evento] [varchar](50) NULL,
	[CssFile] [varchar](100) NULL,
	[Css] [varchar](50) NULL,
	[Ancho] [int] NULL,
	[Longitud] [int] NULL,
	[Renglon] [int] NULL,
	[Class] [varchar](100) NULL,
	[Parsley] [varchar](max) NULL,
	[Omitir] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_Campo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[CamposLabel]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[CamposLabel](
	[Id_Label] [int] IDENTITY(1,1) NOT NULL,
	[Campo_Id] [int] NULL,
	[Descripcion] [varchar](1000) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Cat_Control]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Cat_Control](
	[Id_Control] [int] IDENTITY(1,1) NOT NULL,
	[Descripcion] [varchar](50) NULL,
	[Clave] [varchar](3) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id_Control] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Cat_Estatus]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Cat_Estatus](
	[Id_Estatus] [bit] NULL,
	[Descripcion] [varchar](9) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Cat_Menu]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Cat_Menu](
	[IdMenu] [int] IDENTITY(1,1) NOT NULL,
	[Menu] [varchar](100) NULL,
	[Descripcion] [varchar](255) NULL,
	[Carpeta] [varchar](50) NULL,
	[URL] [varchar](max) NULL,
	[Padre] [int] NULL,
	[Orden] [int] NULL,
	[IdEstatus] [int] NULL,
	[SistemaId] [int] NULL,
	[class] [varchar](50) NULL,
	[TipoRedirect] [varchar](15) NULL,
 CONSTRAINT [PK_TB_CATMenu] PRIMARY KEY CLUSTERED 
(
	[IdMenu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Cat_Pagina]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Cat_Pagina](
	[Id_Pagina] [int] IDENTITY(1,1) NOT NULL,
	[Descripcion] [varchar](50) NULL,
	[Estatus_Id] [bit] NULL,
	[Tipo] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id_Pagina] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Cat_Parametros]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Cat_Parametros](
	[Id_Parametro] [int] IDENTITY(1,1) NOT NULL,
	[Descripcion] [varchar](30) NULL,
	[Clave] [varchar](3) NULL,
	[Val_Int] [int] NULL,
	[Val_Float] [real] NULL,
	[Val_Varchar] [varchar](50) NULL,
	[Val_Fecha] [smalldatetime] NULL,
 CONSTRAINT [PK_Cat_Parametros] PRIMARY KEY CLUSTERED 
(
	[Id_Parametro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Cat_TipoAcceso]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Cat_TipoAcceso](
	[Id_TipoAcceso] [int] NULL,
	[Descripcion] [varchar](30) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Cat_Usuario]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Cat_Usuario](
	[Id_Usuario] [int] NOT NULL,
	[Perfil_Id] [int] NULL,
	[Compania_Id] [int] NULL,
	[Usuario] [varchar](30) NULL,
	[Clave] [varbinary](max) NULL,
	[Nombre] [varchar](250) NULL,
	[Activo] [bit] NULL,
	[Bloquear] [int] NULL,
	[Fch_Bloqueo] [smalldatetime] NULL,
	[EnSession] [bit] NULL,
	[Fch_Session] [smalldatetime] NULL,
	[Base] [varchar](9) NULL,
	[Puesto_Id] [int] NULL,
	[Correo] [varchar](50) NULL,
	[SoloOficina] [bit] NULL,
 CONSTRAINT [PK_Cat_Usuario] PRIMARY KEY CLUSTERED 
(
	[Id_Usuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Cat_Variable]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Cat_Variable](
	[Id_Variable] [int] IDENTITY(1,1) NOT NULL,
	[Descripcion] [varchar](50) NULL,
	[Clave] [varchar](3) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id_Variable] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Error]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Error](
	[IDError] [int] IDENTITY(1,1) NOT NULL,
	[Fecha] [datetime] NULL,
	[IDUsuario] [int] NULL,
	[URL] [varchar](1000) NULL,
	[Error] [varchar](1000) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[LogTemporal]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[LogTemporal](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Str_Log] [varchar](max) NULL,
 CONSTRAINT [PK_LogTemporal] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[Reportes]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[Reportes](
	[Id_Reporte] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [nvarchar](50) NOT NULL,
	[Sp] [nvarchar](150) NOT NULL,
	[Pagina_Id] [int] NULL,
	[Estatus_Id] [bit] NULL,
	[Descripcion] [nvarchar](250) NULL,
	[Nombre_Archivo] [nchar](10) NULL,
	[Orden] [int] NULL,
	[Campo_Nombre] [nvarchar](50) NULL,
	[Encoding] [int] NULL,
	[Ruta_Default] [nvarchar](100) NULL,
	[Servidor] [nvarchar](30) NULL,
	[DB] [nvarchar](30) NULL,
	[Esquema] [nvarchar](30) NULL,
	[Ejecuciones] [int] NULL,
	[Fecha_Ult_Exec] [smalldatetime] NULL,
	[Fecha_Creacion] [smalldatetime] NULL,
	[RegGrid] [int] NULL,
	[Padre] [int] NULL,
	[class] [varchar](100) NULL,
	[URL] [varchar](max) NULL,
	[SistemaId] [int] NULL,
	[TipoRedirect] [varchar](15) NULL,
	[Menu_Id] [int] NULL,
 CONSTRAINT [PK__Reportes__6ED7B73A4BAD0A80] PRIMARY KEY CLUSTERED 
(
	[Id_Reporte] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[ReportesRestringidos]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[ReportesRestringidos](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ObjRep_Id] [int] NULL,
	[Tipo_Id] [int] NULL,
	[MaxEjecucionesDiarias] [int] NULL,
	[Aviso] [int] NULL,
	[HoraIni] [varchar](5) NULL,
	[HoraFin] [varchar](5) NULL,
	[TipoUsuario_ID] [int] NULL,
	[IDEstatus] [int] NULL,
	[Aviso_ID] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [Reportes].[UsuarioReportesRestringidos]    Script Date: 05/07/2018 10:40:20 a. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Reportes].[UsuarioReportesRestringidos](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Usuario_ID] [int] NULL,
	[Reporte_ID] [int] NULL,
	[MaxEjecucionesDiarias] [int] NULL,
	[Aviso] [int] NULL,
	[HoraIni] [varchar](5) NULL,
	[HoraFin] [varchar](5) NULL,
	[EjecucionesDiariasReal] [int] NULL,
	[Aviso_ID] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [Reportes].[Campos] ADD  DEFAULT ((0)) FOR [Omitir]
GO
ALTER TABLE [Reportes].[Error] ADD  DEFAULT (getdate()) FOR [Fecha]
GO
ALTER TABLE [Reportes].[Reportes] ADD  CONSTRAINT [DF__Reportes__RegGri__4D9552F2]  DEFAULT ((10)) FOR [RegGrid]
GO



-- --------------------------------------------
--
-- Funcionalidad STORED PROCEDURES
--
-- --------------------------------------------
IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[Fn_Split]') AND OBJECTPROPERTY(id, N'IsTableFunction') = 1 )
BEGIN DROP FUNCTION [dbo].[Fn_Split] END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[Fn_Split]
(
    @String NVARCHAR(4000),
    @Delimiter NCHAR(1)
)
RETURNS TABLE
AS
RETURN
(
    WITH Split(stpos,endpos)
    AS(
        SELECT 0 AS stpos, CHARINDEX(@Delimiter,@String) AS endpos
        UNION ALL
        SELECT endpos+1, CHARINDEX(@Delimiter,@String,endpos+1)
            FROM Split
            WHERE endpos > 0
    )
    SELECT 'Id' = ROW_NUMBER() OVER (ORDER BY (SELECT 1)),
        'Data' = SUBSTRING(@String,stpos,COALESCE(NULLIF(endpos,0),LEN(@String)+1)-stpos)
    FROM Split
)

GO

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[Reportes].[AltaReporte]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [Reportes].[AltaReporte] END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [Reportes].[AltaReporte](    
  @Nombre    nvarchar(50),    
  @Sp     nvarchar(50),    
  @Esquema   nvarchar(50),    
  @BaseDatos   nvarchar(50),    
  @Descripcion  nvarchar(250),     
  @Nombre_Archivo  nchar(10),    
  @Campo_Nombre  nvarchar(50),    
  @Pagina_Id   int = null,    
  @Padre    int = null,    
  @SistemaID   int=null,    
  @URL    varchar(150)=null    
)as    
     
    
    
/*Declaracion de Variables*/    
DECLARE @err_message  VARCHAR(250),    
  @SPNuevo   varchar(157),    
  @I     int,    
  @Orden    int,    
  @Reporte_Id   int,    
  @use    varchar(55),    
      
  @Activo    int,    
  @Fecha    smalldatetime,    
  @Ctl    int,    
  @STR    int,    
  @Int    int,    
  @Nul    int,    
  @Encoding   int    
    
if isnull(@baseDatos,'')=''    
 set @BaseDatos=''    
    
      
set @Fecha=convert(smalldatetime,convert(varchar,getdate(),111))    
set @err_message  = null    
set @SPNuevo=upper(case when @BaseDatos<>'' then '['+@BaseDatos+'].' else '' end+'['+ltrim(rtrim(@Esquema))+'].'+ltrim(rtrim(@Sp)))    
set @I=0    
set @Ctl=1    
set @STR=1    
set @Int=2    
set @Nul=5    
set @Encoding=1252 --CodePage 1252 es el Default (iso-8859-1), CodePage 1200 es Unicode, CodePage 65001 es UTF-8    
    
    
    
    
    
if  (select count(*) from reportes.Cat_Pagina where Id_Pagina =@pagina_Id) is null    
 select @Pagina_Id  = min( Id_Pagina) from [Reportes].Cat_Pagina    
    
select @Activo   = 1    
    
    
    
DECLARE @TranCounter INT;    
    SET @TranCounter = @@TRANCOUNT    
    IF @TranCounter > 0    
        SAVE TRANSACTION AltaReporte    
    ELSE    
        BEGIN TRAN AltaReporte    
     
     
BEGIN TRY    
     
    
     
      /*Validacion: Verifica que existan SP*/    
IF isnull(  
(  
SELECT [ID]     
FROM [dbo].[SysObjects]    
where [xtype]='P'    
and category = 0     
and upper(ltrim(rtrim(--case when SCHEMA_NAME(uid)='dbo' then '' else     
'['+SCHEMA_NAME(uid)+'].'--end    
+[name]))) = REPLACE(@SPNuevo,'['+@BaseDatos+'].','')  
     ),0)=0  or      
    (select count(*)     
     from [Reportes].Reportes    
     where Nombre =@nombre )<>0 BEGIN    
             
                    RAISERROR ('Sp no Existe o Nombre de Reporte ya existe', 11,10)    
      END    
      ELSE BEGIN    
                  /*Inicio Codigo de SP:..*/    
    
  if isnull(@Nombre_Archivo,'')='' begin    
    set @Nombre_Archivo='Reporte'    
  end     
    
   SELECT @i=[ID]     
     FROM [dbo].[SysObjects]    
     where [xtype]='P'    
       and category = 0     
       and upper(ltrim(rtrim(--case when SCHEMA_NAME(uid)='dbo' then '' else     
       '['+SCHEMA_NAME(uid)+'].'--end    
       +[name]))) = REPLACE(@SPNuevo,'['+@BaseDatos+'].','')  
      
  select @Orden=count(*) + 1    
   from [Reportes].Reportes     
    
    
  insert into [Reportes].Reportes (Nombre, Sp, Pagina_Id, Estatus_Id, Descripcion, Nombre_Archivo, Orden, Campo_Nombre,     
    DB, Esquema,  Fecha_Creacion,Encoding, Padre, SistemaId, class, URL)    
  select @Nombre,ltrim(rtrim(@Sp)),@Pagina_Id,@Activo,@Descripcion,    
    @Nombre_Archivo,@Orden,@Campo_Nombre,ltrim(rtrim(@BaseDatos)),ltrim(rtrim(@Esquema)),@Fecha,@Encoding,@Padre,@SistemaID,    
    'fa-square', @URL    
       
    
  SET @Reporte_Id = @@IDENTITY    
    
--  select @ReporteId = ReporteId     
--   from Rep_Reportes     
--   where @Nombre=Nombre    
    
    
insert into [reportes].Campos ( Reporte_Id, Variable_Id, Control_Id, Titulo_Pagina, Nombre, Orden, Visible,     
        Valor_Variable, DataSet, DS, Parametro)    
select  ReporteId  = @Reporte_Id,    
  CTipoVariable = case when system_type_id in (35,98,99,167,175,231,239,241,231,58,61,189) then @STR    
        when system_type_id in (34,36,48,52,56,59,60,62,104,106,108,122,127,165,173) then @Int    
        else @Nul end,    
  CTipoControl = @Ctl,    
      
  CTitulo   = ltrim(rtrim(substring([Name],2,Len([Name])-1))),    
  CNombre   = ltrim(rtrim(substring([Name],2,Len([Name])-1))),    
      
  COrden   = [parameter_id],    
  CVisible  = @Activo,    
      
  CValorVariable = '',    
 CCRDataSet  = '',    
  CTipoDS   = '',    
  CParametro  = ltrim(rtrim([Name]))    
  from sys.parameters      
 where  [object_id]=@I    
     
                                
         /*Fin Codifo de SP*/    
  SET @Err_Message  =  'Alta Finalizada Correctamente. Favor de actualizar los campos del Reporte_Id ' +  ltrim(rtrim(convert(char,@Reporte_Id)))     
  select Err_Message = @Err_Message    
  RAISERROR (@Err_Message, 0,0)           
        -----RAISERROR ('Ejecutado Correctamente', 0,0)    
     
                          IF @TranCounter = 0    
                          COMMIT TRAN AltaReporte    
      END    
     
END TRY    
BEGIN CATCH    
     
      SET @err_message=isnull(@err_message,ERROR_MESSAGE())    
     
         
            IF @TranCounter = 0    
            -- Transaction started in procedure.    
            -- Roll back complete transaction.    
            ROLLBACK TRAN AltaReporte    
        ELSE    
                  IF XACT_STATE() <> -1    
                ROLLBACK TRAN AltaReporte        
     
            RAISERROR (@err_message, 11,10)    
               
END CATCH    

GO

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[obtenerSP]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].[obtenerSP] END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerSP]
@sp INT,
@usuario_id INT = NULL,
@tipousuario_id INT = NULL
AS
SELECT DISTINCT Store = r.Esquema + '.' + r.sp, ID_Campo, Variable_Id, Control_Id, Titulo_Pagina, c.Nombre, c.Orden, Visible, Valor_Variable, ComboPadre, FileJScript, FuncionJS, Evento, CssFile, Css, Ancho, Longitud, Renglon, c.Class, Parsley
FROM Reportes.Reportes r
LEFT JOIN Reportes.Campos c ON r.Id_Reporte = c.Reporte_Id
INNER JOIN Reportes.Accesos a ON r.Id_Reporte = a.Reporte_Id
WHERE r.Id_Reporte = @sp AND
(
(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)
OR
(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)
)
GO

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[obtenerMenu]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].[obtenerMenu] END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Procedure obtenerMenu
@usuario_id INT = NULL,
@tipousuario_id INT = NULL
AS
SELECT DISTINCT r.Id_Reporte, r.Nombre
FROM Reportes.Reportes r
INNER JOIN Reportes.Accesos a ON r.Id_Reporte = a.Reporte_Id
WHERE a.TipoAcc = 1 AND
(
(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)
OR
(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)
)
GO

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[obtenerDataSet]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].[obtenerDataSet] END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Procedure obtenerDataSet
@ID_Campo INT,
@usuario_id INT = NULL,
@tipousuario_id INT = NULL
AS
DECLARE @store VARCHAR(100), @ds VARCHAR(30), @opcion VARCHAR(30) = '', @opcion2 VARCHAR(30) = '', @otros VARCHAR(30) = ''

SELECT @store = DataSet, @ds = DS FROM Reportes.Campos c
INNER JOIN Reportes.Reportes r ON r.Id_Reporte = c.Reporte_Id
INNER JOIN Reportes.Accesos a ON r.Id_Reporte = a.Reporte_Id
WHERE ID_Campo = @ID_Campo AND
(
(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)
OR
(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)
)

SELECT * INTO #coma FROM Fn_Split(@ds, ',')
DECLARE @tipo VARCHAR(30), @contador INT, @aux VARCHAR(30) = '', @exec VARCHAR(1000)

SELECT @contador = MIN(id) FROM #coma
WHILE @contador IS NOT NULL
BEGIN
	SELECT @ds = data FROM #coma WHERE Id = @contador
	IF LEN(@ds) < 1
	BEGIN
		SET @contador = NULL
		BREAK
	END
	SELECT TOP 1 @tipo = data FROM Fn_Split(@ds, '§')
	SELECT TOP 1 @aux = data FROM Fn_Split(@ds, '§') order by id desc

	IF @tipo LIKE '%SIN%'
	BEGIN
		IF @aux LIKE '%cve_sucurs%' OR @aux LIKE '%compania%'
			SET @aux = 'IdSucursal'
		IF @aux LIKE '%Rol%'
			SET @aux = 'IdTipoUsuario'
		SET @exec = 'SELECT @aux = '+@aux+' FROM TB_CATUsuario WHERE IDUsuario = '+CAST(@usuario_id AS VARCHAR)
		EXECUTE (@exec)
	END
	IF @contador = 1
		SET @opcion = @aux
	IF @contador = 2
		SET @opcion2 = ',' + @aux
	IF @contador = 3
		SET @otros = ',' + @aux

	SELECT @contador = MIN(id) FROM #coma WHERE id > @contador
END
DROP TABLE #coma
SET @store = 'exec ' + @store + ' ' + @opcion + @opcion2 + @otros
EXECUTE (@store)
GO

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[Reportes].[SP_ReportesCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [Reportes].SP_ReportesCON END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE Reportes.SP_ReportesCON
@Id_Reporte int = NULL,
@Nombre nvarchar = NULL,
@Sp nvarchar = NULL,
@Pagina_Id int = NULL,
@Estatus_Id bit = NULL,
@Descripcion nvarchar = NULL,
@Nombre_Archivo nchar = NULL,
@Orden int = NULL,
@Campo_Nombre nvarchar = NULL,
@Encoding int = NULL,
@Ruta_Default nvarchar = NULL,
@Servidor nvarchar = NULL,
@DB nvarchar = NULL,
@Esquema nvarchar = NULL,
@Ejecuciones int = NULL,
@Fecha_Ult_Exec smalldatetime = NULL,
@Fecha_Creacion smalldatetime = NULL,
@RegGrid int = NULL,
@Padre int = NULL,
@class varchar(100) = '',
@URL varchar(MAX) = '',
@SistemaId int = NULL,
@TipoRedirect varchar(15) = '',
@usuario_id INT = NULL,
@tipousuario_id INT = NULL
AS
SELECT DISTINCT Reportes.Id_Reporte,Reportes.Nombre,Reportes.Sp,Reportes.Pagina_Id,Reportes.Estatus_Id,Reportes.Descripcion,Reportes.Nombre_Archivo,Reportes.Orden,Reportes.Campo_Nombre,Reportes.Encoding,Reportes.Ruta_Default,Reportes.Servidor,Reportes.DB,Reportes.Esquema,Reportes.Ejecuciones,Reportes.Fecha_Ult_Exec,Reportes.Fecha_Creacion,Reportes.RegGrid,Reportes.Padre,Reportes.class,Reportes.URL,Reportes.SistemaId,Reportes.TipoRedirect
FROM Reportes.Reportes
INNER JOIN Reportes.Accesos a ON Reportes.Id_Reporte = a.Reporte_Id
WHERE
(Reportes.Id_Reporte = @Id_Reporte OR @Id_Reporte IS NULL) AND
(Reportes.Nombre = @Nombre OR @Nombre IS NULL) AND
(Reportes.Sp = @Sp OR @Sp IS NULL) AND
(Reportes.Pagina_Id = @Pagina_Id OR @Pagina_Id IS NULL) AND
(Reportes.Estatus_Id = @Estatus_Id OR @Estatus_Id IS NULL) AND
(Reportes.Descripcion = @Descripcion OR @Descripcion IS NULL) AND
(Reportes.Nombre_Archivo = @Nombre_Archivo OR @Nombre_Archivo IS NULL) AND
(Reportes.Orden = @Orden OR @Orden IS NULL) AND
(Reportes.Campo_Nombre = @Campo_Nombre OR @Campo_Nombre IS NULL) AND
(Reportes.Encoding = @Encoding OR @Encoding IS NULL) AND
(Reportes.Ruta_Default = @Ruta_Default OR @Ruta_Default IS NULL) AND
(Reportes.Servidor = @Servidor OR @Servidor IS NULL) AND
(Reportes.DB = @DB OR @DB IS NULL) AND
(Reportes.Esquema = @Esquema OR @Esquema IS NULL) AND
(Reportes.Ejecuciones = @Ejecuciones OR @Ejecuciones IS NULL) AND
(Reportes.Fecha_Ult_Exec = @Fecha_Ult_Exec OR @Fecha_Ult_Exec IS NULL) AND
(Reportes.Fecha_Creacion = @Fecha_Creacion OR @Fecha_Creacion IS NULL) AND
(Reportes.RegGrid = @RegGrid OR @RegGrid IS NULL) AND
(Reportes.Padre = @Padre OR @Padre IS NULL) AND
(Reportes.class LIKE '%'+@class+'%' OR @class = '') AND
(Reportes.URL LIKE '%'+@URL+'%' OR @URL = '') AND
(Reportes.SistemaId = @SistemaId OR @SistemaId IS NULL) AND
(Reportes.TipoRedirect LIKE '%'+@TipoRedirect+'%' OR @TipoRedirect = '') AND 
(
(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)
OR
(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)
)
GO

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[Reportes].[SP_CamposCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [Reportes].SP_CamposCON END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE Reportes.SP_CamposCON
@ID_Campo int = NULL,
@usuario_id INT = NULL,
@tipousuario_id INT = NULL,
@Reporte_Id int = NULL,
@Variable_Id int = NULL,
@Control_Id int = NULL,
@Titulo_Pagina varchar(30) = '',
@Nombre varchar(100) = '',
@Orden int = NULL,
@Visible int = NULL,
@Valor_Variable varchar(60) = '',
@DataSet varchar(300) = '',
@DS varchar(1000) = '',
@Parametro nvarchar = NULL,
@ComboPadre varchar(30) = '',
@FileJScript varchar(100) = '',
@FuncionJS varchar(50) = '',
@Evento varchar(50) = '',
@CssFile varchar(100) = '',
@Css varchar(50) = '',
@Ancho int = NULL,
@Longitud int = NULL,
@Renglon int = NULL,
@Class varchar(100) = '',
@Parsley varchar(MAX) = ''
AS
SELECT c.ID_Campo,c.Reporte_Id,c.Variable_Id,c.Control_Id,c.Titulo_Pagina,c.Nombre,c.Orden,c.Visible,c.Valor_Variable,c.DataSet,c.DS,c.Parametro,c.ComboPadre,c.FileJScript,c.FuncionJS,c.Evento,c.CssFile,c.Css,c.Ancho,c.Longitud,c.Renglon,c.Class,c.Parsley,c.Omitir
FROM Reportes.Campos c
INNER JOIN Reportes.Reportes r ON r.Id_Reporte = c.Reporte_Id
INNER JOIN Reportes.Accesos a ON r.Id_Reporte = a.Reporte_Id
WHERE
(c.ID_Campo = @ID_Campo OR @ID_Campo IS NULL) AND
(c.Reporte_Id = @Reporte_Id OR @Reporte_Id IS NULL) AND
(c.Variable_Id = @Variable_Id OR @Variable_Id IS NULL) AND
(c.Control_Id = @Control_Id OR @Control_Id IS NULL) AND
(c.Titulo_Pagina LIKE '%'+@Titulo_Pagina+'%' OR @Titulo_Pagina = '') AND
(c.Nombre LIKE '%'+@Nombre+'%' OR @Nombre = '') AND
(c.Orden = @Orden OR @Orden IS NULL) AND
(c.Visible = @Visible OR @Visible IS NULL) AND
(c.Valor_Variable LIKE '%'+@Valor_Variable+'%' OR @Valor_Variable = '') AND
(c.DataSet LIKE '%'+@DataSet+'%' OR @DataSet = '') AND
(c.DS LIKE '%'+@DS+'%' OR @DS = '') AND
(c.Parametro = @Parametro OR @Parametro IS NULL) AND
(c.ComboPadre LIKE '%'+@ComboPadre+'%' OR @ComboPadre = '') AND
(c.FileJScript LIKE '%'+@FileJScript+'%' OR @FileJScript = '') AND
(c.FuncionJS LIKE '%'+@FuncionJS+'%' OR @FuncionJS = '') AND
(c.Evento LIKE '%'+@Evento+'%' OR @Evento = '') AND
(c.CssFile LIKE '%'+@CssFile+'%' OR @CssFile = '') AND
(c.Css LIKE '%'+@Css+'%' OR @Css = '') AND
(c.Ancho = @Ancho OR @Ancho IS NULL) AND
(c.Longitud = @Longitud OR @Longitud IS NULL) AND
(c.Renglon = @Renglon OR @Renglon IS NULL) AND
(c.Class LIKE '%'+@Class+'%' OR @Class = '') AND
(c.Parsley LIKE '%'+@Parsley+'%' OR @Parsley = '') AND
(
(a.UsuarioTipo_ID = @usuario_id AND a.TipoAcceso_Id = 2)
OR
(a.UsuarioTipo_ID = @tipousuario_id AND a.TipoAcceso_Id = 1)
)
ORDER BY c.Orden asc
GO
