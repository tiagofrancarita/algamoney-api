USE [algamoneyapi]
GO

/****** Object:  Table [dbo].[categoria]    Script Date: 16/12/2022 18:27:22 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[acesso](
[codigo] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
[descricao] [varchar](50) NULL
)
GO

INSERT INTO acesso (descricao) VALUES ('ROLE_ADMIN');
INSERT INTO acesso (descricao) VALUES ('ROLE_USUARIO');
INSERT INTO acesso (descricao) VALUES ('ROLE_GERENTE');