USE Curso;

IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[SP_TipoUsuarioALT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].SP_TipoUsuarioALT END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE dbo.SP_TipoUsuarioALT
@Nombre varchar(25)
AS
INSERT INTO dbo.TipoUsuario(Nombre)
VALUES
(@Nombre)

GO


IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[SP_TipoUsuarioCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].SP_TipoUsuarioCON END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE dbo.SP_TipoUsuarioCON
@Id_TipoUsuario int = NULL,
@Nombre varchar(25) = ''
AS
SELECT TipoUsuario.Id_TipoUsuario,TipoUsuario.Nombre
FROM dbo.TipoUsuario
WHERE
(TipoUsuario.Id_TipoUsuario = @Id_TipoUsuario OR @Id_TipoUsuario IS NULL) AND
(TipoUsuario.Nombre LIKE '%'+@Nombre+'%' OR @Nombre = '') 
GO


IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[SP_TipoUsuarioACT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].SP_TipoUsuarioACT END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE dbo.SP_TipoUsuarioACT
@Id_TipoUsuario int,
@Nombre varchar(25) = NULL
AS
UPDATE dbo.TipoUsuario SET
Nombre = ISNULL(@Nombre, Nombre)
WHERE
TipoUsuario.Id_TipoUsuario = @Id_TipoUsuario
GO


IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[SP_TipoUsuarioDEL]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].SP_TipoUsuarioDEL END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE dbo.SP_TipoUsuarioDEL
@Id_TipoUsuario int
AS
DELETE dbo.TipoUsuario
WHERE
TipoUsuario.Id_TipoUsuario = @Id_TipoUsuario
GO


IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[SP_UsuarioALT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].SP_UsuarioALT END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE dbo.SP_UsuarioALT
@TipoUsuario_Id int,
@Nombre varchar(30),
@ApelllidoP varchar(30),
@ApellidoM varchar(30) = NULL,
@Usuario varchar(30),
@Password varchar(256)
AS
INSERT INTO dbo.Usuario(TipoUsuario_Id,Nombre,ApelllidoP,ApellidoM,Usuario,Password,Activo)
VALUES
(@TipoUsuario_Id,@Nombre,@ApelllidoP,@ApellidoM,@Usuario,CONVERT(VARCHAR(256),HASHBYTES('SHA2_256',@Password),2),1)

GO


IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[SP_UsuarioCON]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].SP_UsuarioCON END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE dbo.SP_UsuarioCON
@Id_Usuario int = NULL,
@TipoUsuario_Id int = NULL,
@Nombre varchar(30) = '',
@ApelllidoP varchar(30) = '',
@ApellidoM varchar(30) = '',
@Usuario varchar(30) = ''
AS
SELECT Usuario.Id_Usuario,Usuario.TipoUsuario_Id,TipoUsuario.Nombre,Usuario.Nombre,Usuario.ApelllidoP,Usuario.ApellidoM,Usuario.Usuario
FROM dbo.Usuario
LEFT JOIN dbo.TipoUsuario ON Usuario.TipoUsuario_Id = TipoUsuario.Id_TipoUsuario
WHERE
(Usuario.Id_Usuario = @Id_Usuario OR @Id_Usuario IS NULL) AND
(Usuario.TipoUsuario_Id = @TipoUsuario_Id OR @TipoUsuario_Id IS NULL) AND
(Usuario.Nombre LIKE '%'+@Nombre+'%' OR @Nombre = '') AND
(Usuario.ApelllidoP LIKE '%'+@ApelllidoP+'%' OR @ApelllidoP = '') AND
(Usuario.ApellidoM LIKE '%'+@ApellidoM+'%' OR @ApellidoM = '') AND
(Usuario.Usuario LIKE '%'+@Usuario+'%' OR @Usuario = '') AND
(Usuario.Activo = 1) 
GO


IF EXISTS ( SELECT * FROM sysobjects WHERE  id = object_id(N'[dbo].[SP_UsuarioACT]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1 )
BEGIN DROP PROCEDURE [dbo].SP_UsuarioACT END
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE dbo.SP_UsuarioACT
@Id_Usuario int,
@TipoUsuario_Id int = NULL,
@Nombre varchar(30) = NULL,
@ApelllidoP varchar(30) = NULL,
@ApellidoM varchar(30) = NULL,
@Usuario varchar(30) = NULL,
@Activo bit = NULL
AS
UPDATE dbo.Usuario SET
TipoUsuario_Id = ISNULL(@TipoUsuario_Id, TipoUsuario_Id),
Nombre = ISNULL(@Nombre, Nombre),
ApelllidoP = ISNULL(@ApelllidoP, ApelllidoP),
ApellidoM = ISNULL(@ApellidoM, ApellidoM),
Usuario = ISNULL(@Usuario, Usuario),
Activo = ISNULL(@Activo, Activo)
WHERE
Usuario.Id_Usuario = @Id_Usuario
GO
