USE [sysFriendsClub_v1]
GO

CREATE SCHEMA WS;
GO


/****** Object:  Table [dbo].[TB_CATUsuario]    Script Date: 30/10/2017 11:02:54 a.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [WS].[TB_CatUsuarios](
	[idUsuario] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NULL,
	[apellidoP] [varchar](50) NULL,
	[apellidoM] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[usuario] [varchar](30) NULL,
	[clave] [varbinary](256) NULL,
	[idEstatus] [int] NOT NULL,
CONSTRAINT [PK_TB_CatUsuarios] PRIMARY KEY CLUSTERED 
(
	[IdUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [WS].[TB_CatUsuarios] ADD  CONSTRAINT [DF_TB_CatUsuarios_IdEstatus]  DEFAULT ((1)) FOR [IdEstatus]
GO




/****** Object:  Table [dbo].[TB_CatUsuarioApps]    Script Date: 30/10/2017 01:00:26 p.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [WS].[TB_CatUsuarioApps](
	[idUsuarioApp] [int] IDENTITY(1,1) NOT NULL,
	[idUsuario] [int] NOT NULL,
	[idApp] [int] NOT NULL,
	[token] [varchar](8) NULL DEFAULT '',
	[privateKey] [varchar](2048) NULL DEFAULT '',
	[ultimaActividad] [datetime] NULL,
	[ip] [varchar](30) NOT NULL DEFAULT '',
	[numeroPeticion] [int] NOT NULL DEFAULT 0,
	[idEstatus] [int] NOT NULL DEFAULT 1,
 CONSTRAINT [PK_TB_CatUsuarioApps] PRIMARY KEY CLUSTERED 
(
	[idUsuarioApp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO






/****** Object:  Table [dbo].[TB_CatAppVersiones]    Script Date: 30/10/2017 01:00:05 p.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [WS].[TB_CatAppVersiones](
	[idAppVersion] [int] IDENTITY(1,1) NOT NULL,
	[idApp] [int] NOT NULL,
	[idPlataforma] [int] NOT NULL,
	[version] [float] NOT NULL,
	[fechaLanzamiento] [smalldatetime] NOT NULL,
 CONSTRAINT [PK_TB_AppVersiones] PRIMARY KEY CLUSTERED 
(
	[idAppVersion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'1-iOS
2-Android' , @level0type=N'SCHEMA',@level0name=N'Admin', @level1type=N'TABLE',@level1name=N'TB_CatAppVersiones', @level2type=N'COLUMN',@level2name=N'idPlataforma'
GO






/****** Object:  Table [dbo].[TB_CatApps]    Script Date: 30/10/2017 12:59:25 p.m. ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [WS].[TB_CatApps](
	[idApp] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](60) NOT NULL,
	[tiempoSesion] [int] NOT NULL,
 CONSTRAINT [PK_TB_CatApps] PRIMARY KEY CLUSTERED 
(
	[idApp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [WS].[TB_CatApps] ADD  DEFAULT ((0)) FOR [tiempoSesion]
GO





INSERT INTO [WS].tb_catUsuarios VALUES ('Demo', '', '', 'demo@garsa.com', 'demo', PWDENCRYPT('DEMO'), 1)
INSERT INTO [WS].tb_catUsuarios VALUES ('FriendsClub', '', '', 'arturo@peaceandlovecompany.com', 'FriendsClub', PWDENCRYPT('FRIENDSCLUB'), 1)


INSERT INTO [WS].TB_CatApps (nombre, tiempoSesion) VALUES('Base', 15);
INSERT INTO [WS].TB_CatApps (nombre, tiempoSesion) VALUES('FriendsClub', 1440);
INSERT INTO [WS].TB_CatUsuarioApps (idUsuario, idApp) VALUES(1, 1);
INSERT INTO [WS].TB_CatUsuarioApps VALUES(2, 1, '', '', GETDATE(), '', 0, 1);
INSERT INTO [WS].TB_CatAppVersiones VALUES(1, 1, 1, GETDATE());
INSERT INTO [WS].TB_CatAppVersiones VALUES(2, 1, 1, GETDATE());

select * from [WS].TB_CatApps
select * from [WS].TB_CatUsuarioApps
select * from [WS].TB_CatAppVersiones
select * from [WS].tb_catUsuarios

