
--BaseRSA

-- -------------------------------------------------------
--
-- Modifica estos stores para que concuerden con tu login
--
-- -------------------------------------------------------

/*
	Es importante que ubiques tu tabla de usuarios y agregues las columnas necesarias para el funcionamiento de este sistemas,
	el script para agregar las columnas es el siguiente:
	
	Si ya tienes estas columnas omite este paso:
	
	ALTER TABLE TuTabla ADD
	token VARCHAR(8) NULL DEFAULT '',
	privateKey VARCHAR(2048) NULL DEFAULT '',
	ultimaActividad DATETIME NULL,
	ip VARCHAR(30) NOT NULL DEFAULT '',
	numeroPeticion INT NOT NULL DEFAULT 0
	
	Recuerda cambiar la tabla de los stored por tu tabla de Usuarios
	
*/

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_ValidarUsuario]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [GeneSys].[SP_ValidarUsuario] END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [GeneSys].[SP_ValidarUsuario] 
	@usuario varchar(120),
	@clave varchar(120),
	@token varchar(10),
	@idApp int = null,
	@privateKey varchar(2048)
AS
BEGIN
	--Inicializamos una bandera y una variable para el id del usuario logueando
	DECLARE @valido int = 0,
			@idUsuario int

	--Comprobamos que el usuario exista y que no esté bloqueado
	SELECT 
		@valido = 1,
		@idUsuario = Id_Usuario
	FROM
		[GeneSys].Usuario  
    WHERE 
		UPPER(Usuario) = @usuario AND 
        Estatus_Id = 1 AND
		ISNULL(Bloquear,0) < 3
		
	IF @valido = 0
	BEGIN
		SELECT 2 as 'valido'
		RETURN;
	END
	
	--Convertimos en mayusculas el usuario para quitarle la sensibilidad a mayusculas
	SET @usuario = upper(@usuario)

	/*
		Checamos que el login sea correcto de un usuario Estatus_Id y le damos valor a la variable @valido y a @idUsuario,
		utiliza el método de encriptacion o validacion que requieras para comparar la autenticidad del usuario.
	*/
	SET @valido = 0
	
	SELECT 
		@valido = 1
	FROM
		[GeneSys].Usuario  
    WHERE 
		Id_Usuario = @idUsuario  AND 
        CONVERT(VARCHAR(256),HASHBYTES('SHA2_256',@clave),2) = Password AND
        Estatus_Id = 1
		
	
	--Si el login fue incorrecto agrega 1 a los intentos
	IF @valido = 0
			BEGIN
				UPDATE 
					[GeneSys].Usuario
				SET
					Bloquear = ISNULL(Bloquear, 0) + 1
				WHERE 
					Id_Usuario = @idUsuario
					
				DECLARE @bloqueo INT
				
				SELECT 
					@bloqueo = bloquear 
				FROM 
					[GeneSys].Usuario
				WHERE
					Id_Usuario = @idUsuario
					
				IF @bloqueo > 2
				BEGIN
					UPDATE 
					[GeneSys].Usuario
				SET
					Fch_Bloqueo = GETDATE()
				WHERE 
					Id_Usuario = @idUsuario
					
				SELECT 2 as 'valido'
				RETURN;
				END
				
				SELECT 0 as 'valido'
				RETURN;
			END
			
	--Si el login fue exitoso, guarda el token y privateKey en el usuario
	UPDATE 
		[GeneSys].Usuario
	SET
		token = @token,
		privateKey = @privateKey,
		bloquear = 0
	WHERE 
		Id_Usuario = @idUsuario

	/*
	Si el login fue exitoso, devuelve informacion del usuario para ser procesada (guardarla en el token)
	Los campos que son obligatorios son valido, token, tipo de usuario y el id del usuario.
	
	Puedes agregar los valores que necesites guardar en tus variables de sesion, mas adelante los leeremos
	el codigo de c# y los agregaremos al token para poder leerlas al momento de hacer una peticion web.
	*/
	
	SELECT 1 as 'valido', Id_Usuario, TipoUsuario_Id, Token 
	FROM 
		[GeneSys].Usuario
	WHERE 
		Id_Usuario = @idUsuario AND
		Estatus_Id = 1
END
GO


IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_UsuarioValidarToken]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [GeneSys].SP_UsuarioValidarToken END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [GeneSys].[SP_UsuarioValidarToken] 
	@idUsuario int,
	@idApp int = null,
	@token varchar(10),
	@numeroPeticion int
AS
BEGIN
	--Declara las variables
	DECLARE @valido int = 0
	DECLARE @mensaje varchar(100) = 'Token invalido'

	
	--Comprueba que el token pertenezca a algun usuario y checa su numero de peticiones
	SELECT 
		@valido = 1,
		@mensaje = 'Token valido'
	FROM
		[GeneSys].Usuario  
    WHERE 
		Estatus_Id = 1 AND
		Id_Usuario = @idUsuario AND
		token = @token AND
		(@numeroPeticion = 0 OR numeroPeticion + 1 = @numeroPeticion)

	--En caso de estar enviando el numero de peticion, entonces aumentamos en 1
	IF @valido = 1 AND @numeroPeticion > 0
	BEGIN
		UPDATE 
			[GeneSys].Usuario 
		SET 
			numeroPeticion = numeroPeticion + 1
		WHERE 
			Estatus_Id = 1 AND
			id_Usuario = @idUsuario
	END

	--Siempre actualizamos la ultima actividad del usuario
	UPDATE 
		[GeneSys].Usuario 
	SET
		ultimaActividad = GETDATE() 
	WHERE 
		Estatus_Id = 1 AND
		Id_Usuario = @idUsuario

	SELECT @valido as 'valido', @mensaje as 'mensaje'
END
GO

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_ObtenerLlavePrimariaUsuario]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [GeneSys].[SP_ObtenerLlavePrimariaUsuario] END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [GeneSys].[SP_ObtenerLlavePrimariaUsuario] 
	@idUsuario int,
	@idApp int = null
AS
BEGIN
	--Devolvemos la privateKey
	SELECT 
		privateKey
	FROM
		[GeneSys].Usuario  
    WHERE 
		id_Usuario = @idUsuario AND
		Estatus_Id = 1
END
GO


IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_UsuarioALT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [GeneSys].SP_UsuarioALT END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [GeneSys].SP_UsuarioALT
@Perfil_Id int = NULL,
@Nombres varchar(50) = NULL,
@ApellidoP varchar(50) = NULL,
@ApellidoM varchar(50) = NULL,
@RFC varchar(13) = NULL,
@Lada varchar(4) = NULL,
@Telefono varchar(15) = NULL,
@Email varchar(50) = NULL,
@Usuario varchar(30),
@Password varchar(256),
@TipoUsuario_Id int,
@Sucursal_Id int = NULL,
@Estatus_Id int = 1,
@Bloquear int = NULL,
@EnSession int = NULL,
@Fch_Bloqueo smalldatetime = NULL,
@SoloOficina bit = NULL,
@token varchar(8) = NULL,
@privateKey varchar(2048) = NULL,
@ultimaActividad datetime = NULL,
@ip varchar(30) = '0.0.0.0',
@numeroPeticion int = 0,
@CambiarPassword bit = 1,
@FechaCambioPassword datetime = NULL
AS
INSERT INTO [GeneSys].Usuario(Perfil_Id,Nombres,ApellidoP,ApellidoM,RFC,Lada,Telefono,Email,Usuario,Password,TipoUsuario_Id,Sucursal_Id,Estatus_Id,Bloquear,EnSession,Fch_Bloqueo,SoloOficina,token,privateKey,ultimaActividad,ip,numeroPeticion,CambiarPassword,FechaCambioPassword)
VALUES
(@Perfil_Id,@Nombres,@ApellidoP,@ApellidoM,@RFC,@Lada,@Telefono,@Email,@Usuario,CONVERT(VARCHAR(256),HASHBYTES('SHA2_256',@Password),2),@TipoUsuario_Id,@Sucursal_Id,@Estatus_Id,@Bloquear,@EnSession,@Fch_Bloqueo,@SoloOficina,@token,@privateKey,@ultimaActividad,@ip,@numeroPeticion,@CambiarPassword,@FechaCambioPassword)

GO

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[GeneSys].[SP_CambioPassword]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [GeneSys].SP_CambioPassword END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [GeneSys].SP_CambioPassword
@Id_Usuario INT,
@Password VARCHAR(256)
AS
	UPDATE 
		[GeneSys].Usuario
	SET 
		Password = CONVERT(VARCHAR(256),HASHBYTES('SHA2_256',@Password),2),
		FechaCambioPassword = GETDATE(),
		CambiarPassword = 0
	WHERE 
		Id_Usuario = @Id_Usuario
GO



-- -------------------------------------------------------------
--
-- Llenado de catalogos
--
-- -------------------------------------------------------------


INSERT INTO [GeneSys].CatTipoUsuario VALUES('ADMINISTRADOR', 'Administrador de sistema', 1)

EXEC [GeneSys].SP_UsuarioALT @Usuario = 'admin', @Password = 'default', @TipoUsuario_Id = 1


SET IDENTITY_INSERT [GeneSys].[Cat_Control] ON 
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (1, N'TEXTBOX', N'TXT')
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (2, N'COMBO', N'CMB')
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (3, N'FECHA', N'FCH')
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (4, N'ARCHIVO', N'XML')
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (5, N'LISTATEXBOX', N'FIL')
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (6, N'GRID', N'GRI')
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (7, N'TXTBUSCADOR', N'TXB')
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (8, N'TABLA', N'TBL')
GO
INSERT [GeneSys].[Cat_Control] ([Id_Control], [Descripcion], [Clave]) VALUES (9, N'RADIO LISTA', N'RBL')
GO
SET IDENTITY_INSERT [GeneSys].[Cat_Control] OFF
GO
INSERT [GeneSys].[Cat_Estatus] ([Id_Estatus], [Descripcion]) VALUES (1, N'Activo')
GO
INSERT [GeneSys].[Cat_Estatus] ([Id_Estatus], [Descripcion]) VALUES (0, N'Inactivo')
GO
SET IDENTITY_INSERT [GeneSys].[Cat_Pagina] ON 
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (1, N'Reporte a Excel', 1, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (2, N'Reporte en Grid', 1, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (3, N'Reporte en XML', 1, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (4, N'Grafica Linea ,Barras & Spider', 1, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (5, N'Grafica Pay & Dona', 1, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (6, N'Proceso Nocturno', 0, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (7, N'Pagina de Alta', 1, 1)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (8, N'Pagina de Modificacion', 1, 1)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (9, N'Pagina de Mostrar', 1, 1)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (10, N'Reporte Txt', 1, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (11, N'Reporte Grid Por Default', 1, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (12, N'Stream TXT', 1, 0)
GO
INSERT [GeneSys].[Cat_Pagina] ([Id_Pagina], [Descripcion], [Estatus_Id], [Tipo]) VALUES (13, N'Reporte en PDF', 1, 0)
GO
SET IDENTITY_INSERT [GeneSys].[Cat_Pagina] OFF
GO
SET IDENTITY_INSERT [GeneSys].[Cat_Parametros] ON 
GO
INSERT [GeneSys].[Cat_Parametros] ([Id_Parametro], [Descripcion], [Clave], [Val_Int], [Val_Float], [Val_Varchar], [Val_Fecha]) VALUES (1, N'Base Fija', N'PSW', NULL, NULL, N'RELUSALU1', NULL)
GO
INSERT [GeneSys].[Cat_Parametros] ([Id_Parametro], [Descripcion], [Clave], [Val_Int], [Val_Float], [Val_Varchar], [Val_Fecha]) VALUES (2, N'Bloqueos', N'BLO', 6, NULL, NULL, NULL)
GO
INSERT [GeneSys].[Cat_Parametros] ([Id_Parametro], [Descripcion], [Clave], [Val_Int], [Val_Float], [Val_Varchar], [Val_Fecha]) VALUES (3, N'Min Bloqueo', N'MBL', 1, NULL, NULL, NULL)
GO
INSERT [GeneSys].[Cat_Parametros] ([Id_Parametro], [Descripcion], [Clave], [Val_Int], [Val_Float], [Val_Varchar], [Val_Fecha]) VALUES (4, N'Min Session', N'MSS', 10, NULL, NULL, NULL)
GO
SET IDENTITY_INSERT [GeneSys].[Cat_Parametros] OFF
GO
INSERT [GeneSys].[Cat_TipoAcceso] ([Id_TipoAcceso], [Descripcion]) VALUES (1, N'Tipo Usuario o Tipo Perfil')
GO
INSERT [GeneSys].[Cat_TipoAcceso] ([Id_TipoAcceso], [Descripcion]) VALUES (2, N'Usuario')
GO
SET IDENTITY_INSERT [GeneSys].[Cat_Variable] ON 
GO
INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (1, N'STRING', N'STR')
GO
INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (2, N'INT', N'INT')
GO
INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (3, N'SESSION STRING', N'SST')
GO
INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (4, N'SESSION INT', N'SIN')
GO
INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (5, N'NULL', N'NUL')
GO
INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (6, N'Archivo', N'XML')
GO
INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (7, N'ARCHIVO GOOGLE DRIVE', N'AGD')
GO
INSERT [GeneSys].[Cat_Variable] ([Id_Variable], [Descripcion], [Clave]) VALUES (8, N'ARCHIVOS FISICOS', N'ARF')
GO
SET IDENTITY_INSERT [GeneSys].[Cat_Variable] OFF
GO

