DROP TABLE IF EXISTS TABLACONVERSION;
DROP TABLE IF EXISTS TIPOMONEDA;
DROP TABLE IF EXISTS REVINFO;
DROP TABLE IF EXISTS TIPOMONEDA_AUD;

CREATE TABLE TIPOMONEDA (
	ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	CODIGO VARCHAR(100) NOT NULL UNIQUE,
	NOMBRE VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE TIPOMONEDA_AUD (
	ID INT NOT NULL,
	CODIGO VARCHAR(100) NOT NULL,
	NOMBRE VARCHAR(100) NOT NULL,
	REV integer not null,
	REVTYPE tinyint
);

CREATE TABLE TABLACONVERSION (
	ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	ORIGEN INT NOT NULL,
	DESTINO INT NOT NULL,
	MONTO DECIMAL(20,2) NOT NULL,
	FECHA DATE NOT NULL,
	FOREIGN KEY (ORIGEN) REFERENCES TIPOMONEDA(ID),
    FOREIGN KEY (DESTINO) REFERENCES TIPOMONEDA(ID)
);

create table REVINFO (
    REV integer generated by default as identity (start with 1),
    REVTSTMP bigint,
    primary key (REV)
);

INSERT INTO TIPOMONEDA (CODIGO, NOMBRE) VALUES ('PEN','Sol');
INSERT INTO TIPOMONEDA (CODIGO, NOMBRE) VALUES ('USD','Dolar');
INSERT INTO TIPOMONEDA (CODIGO, NOMBRE) VALUES ('EUR','Euro');
INSERT INTO TIPOMONEDA (CODIGO, NOMBRE) VALUES ('CNY','Yuan');
INSERT INTO TIPOMONEDA (CODIGO, NOMBRE) VALUES ('JPY','Yen');
INSERT INTO TIPOMONEDA (CODIGO, NOMBRE) VALUES ('KRW','Yeon');

INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (1,2,'2',parsedatetime('25-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (2,2,'3',parsedatetime('25-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (1,3,'4',parsedatetime('25-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (3,1,'5',parsedatetime('25-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (1,3,'6',parsedatetime('25-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (3,1,'7',parsedatetime('25-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (1,2,'2',parsedatetime('26-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (2,2,'3',parsedatetime('26-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (1,3,'4',parsedatetime('26-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (3,1,'5',parsedatetime('26-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (1,3,'6',parsedatetime('26-03-2021', 'dd-MM-yyyy'));
INSERT INTO TABLACONVERSION (ORIGEN, DESTINO, MONTO, FECHA) VALUES (3,1,'7',parsedatetime('26-03-2021', 'dd-MM-yyyy'));