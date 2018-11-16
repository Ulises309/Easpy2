USE [sysRutav3]
GO


SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [WS].[SP_ValidarUsuario] 
	-- Add the parameters for the stored procedure here
	@usuario varchar(120),
	@clave varchar(120),
	@token varchar(10),
	@idApp int = null,
	@privateKey varchar(2048)
AS
--EXEC dbo.SP_ValidarUsuario @usuario = 'demo', @clave = 'demo', @token = '123', @privateKey = '123', @idApp = 1
BEGIN

	DECLARE @valido int = 0,
			@idUsuario int
	
	SET @usuario = upper(@usuario)

	SELECT 
		@valido = 1,
		@idUsuario = IdUsuario
	FROM
		TB_CatUsuarios  
    WHERE 
		upper(usuario) = @usuario  AND 
        PWDCOMPARE (UPPER(@clave), clave) = 1 AND
        idEstatus = 1

	IF @valido = 1
	BEGIN
		IF @idApp IS NOT NULL
		BEGIN
			SET @valido = 0

			SELECT 
				@valido = 1 
			FROM 
				TB_CatUsuarioApps 
			WHERE 
				idApp = @idApp AND 
				idUsuario = @idUsuario

			IF @valido = 1
			BEGIN
				UPDATE 
					TB_CatUsuarioApps
				SET
					token = @token,
					privateKey = @privateKey
				WHERE 
					idApp = @idApp AND 
					idUsuario = @idUsuario
			END
		END
	END

	IF @valido = 1
	BEGIN
		IF @idApp IS NOT NULL
		BEGIN
			SELECT 1 as 'valido', u.idUsuario, u.nombre, u.apellidoP, u.apellidoM, u.email, u.usuario, ua.token 
			FROM 
				TB_CatUsuarios u 
				INNER JOIN TB_CatUsuarioApps ua ON u.idUsuario = ua.idUsuario AND ua.idApp = @idApp
			WHERE 
				ua.idApp = @idApp AND
				ua.idUsuario = @idUsuario AND
				ua.idEstatus = 1

			UPDATE TB_CatUsuarioApps SET numeroPeticion = 0 WHERE idUsuario = @idUsuario AND idApp = @idApp AND idEstatus = 1
		END
		ELSE
		BEGIN
			SELECT 
				1 as 'valido', u.idUsuario, u.nombre, u.apellidoP, u.apellidoM, u.email, u.usuario
			FROM 
				TB_CatUsuarios u 
			WHERE 
				u.usuario = @usuario
		END
	END
	ELSE
	BEGIN
		SELECT 0 as 'valido'
	END
END
GO


SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [WS].[SP_UsuarioValidarToken] 
	-- Add the parameters for the stored procedure here
	@idUsuario int,
	@idApp int,
	@token varchar(10),
	@numeroPeticion int
AS
--EXEC dbo.SP_UsuarioValidarToken 1, 1, 'tylp', 2
BEGIN
	DECLARE @valido int = 0
	DECLARE @mensaje varchar(100) = 'Token invalido'

	SELECT 
		@valido = 1,
		@mensaje = 'Token valido'
	FROM
		TB_CatUsuarioApps  
    WHERE 
		idEstatus = 1 AND
		idUsuario = @idUsuario AND
		idApp = @idApp AND
		token = @token AND
		(@numeroPeticion = 0 OR numeroPeticion + 1 = @numeroPeticion)

	--En caso de estar enviando el numero de peticion, entonces aumentamos en 1
	IF @valido = 1 AND @numeroPeticion > 0
	BEGIN
		UPDATE 
			TB_CatUsuarioApps 
		SET 
			numeroPeticion = numeroPeticion + 1
		WHERE 
			idEstatus = 1 AND
			idUsuario = @idUsuario AND
			idApp = @idApp
	END

	--Siempre actualizamos la ultima actividad del usuario
	UPDATE 
		TB_CatUsuarioApps 
	SET
		ultimaActividad = GETDATE() 
	WHERE 
		idEstatus = 1 AND
		idUsuario = @idUsuario AND
		idApp = @idApp

	SELECT @valido as 'valido', @mensaje as 'mensaje'
END
GO


SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [WS].[SP_ObtenerLlavePrimariaUsuario] 
	-- Add the parameters for the stored procedure here
	@idUsuario int,
	@idApp int
AS
--EXEC dbo.SP_ObtenerLlavePrimariaUsuario @idUsuario = 1, @idApp = 1
BEGIN

	SELECT 
		privateKey
	FROM
		TB_CatUsuarioApps  
    WHERE 
		idUsuario = @idUsuario AND
		idApp = @idApp AND
		idEstatus = 1
END
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [WS].[SP_AppVersionCON] 
	-- Add the parameters for the stored procedure here
	@idApp int,
	@idPlataforma int -- 1-iOS/2-Android
AS
BEGIN
	SELECT 
		top 1 app.idApp, nombre, idPlataforma, version, fechaLanzamiento 
	FROM 
		TB_CatAppVersiones v 
		INNER JOIN TB_CatApps app ON v.idApp = app.idApp 
	WHERE 
		app.idApp = @idApp AND idPlataforma = @idPlataforma ORDER BY version DESC;
END
GO













