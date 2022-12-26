CREATE TABLE usuario (
[codigo] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
data_atual_senha date NOT NULL,
login character varying(255) NOT NULL,
senha character varying(255) NOT NULL,
[codigo_pessoa] bigint NOT NULL,
CONSTRAINT fk_usuarioPessoa FOREIGN KEY (codigo_pessoa) REFERENCES pessoa (codigo));
GO
INSERT INTO usuario (data_atual_senha,login,senha,codigo_pessoa)
VALUES('2022-12-25','admin','admin','1')
