CREATE TABLE usuarios_acesso
(
    codigo_usuario bigint NOT NULL,
    codigo_acesso bigint NOT NULL,
    CONSTRAINT acesso_fk FOREIGN KEY (codigo_acesso)REFERENCES acesso (codigo),
    CONSTRAINT usuario_fk FOREIGN KEY (codigo_usuario) REFERENCES usuario (codigo),
    CONSTRAINT uniqueacessouser UNIQUE (codigo_usuario, codigo_acesso))
GO
INSERT INTO usuarios_acesso (codigo_usuario,codigo_acesso) VALUES ('1','1');