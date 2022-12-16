USE [algamoneyapi]
GO

/****** Object:  Table [dbo].[categoria]    Script Date: 16/12/2022 18:27:22 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[categoria](
    [codigo] [bigint] IDENTITY(1,1) NOT NULL,
    [nome] [varchar](50) NULL
    ) ON [PRIMARY]
    GO

)

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Supermercado');
INSERT INTO categoria (nome) VALUES ('Farmácia');
INSERT INTO categoria (nome) VALUES ('Outros');