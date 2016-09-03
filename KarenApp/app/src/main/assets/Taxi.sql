DROP TABLE IF EXISTS "Registros";
CREATE TABLE "Registros" ("placa" VARCHAR PRIMARY KEY  NOT NULL  UNIQUE , "cooperativa" VARCHAR DEFAULT 50, "nombre" VARCHAR DEFAULT 50, "apellido" VARCHAR DEFAULT 80, "marca" VARCHAR DEFAULT 50, "modelo" VARCHAR DEFAULT 50, "color" VARCHAR DEFAULT 30, "telefono" NUMERIC DEFAULT 60, "direccion" VARCHAR DEFAULT 70);
INSERT INTO "Registros" VALUES('04-13-VCM','2 DE AGOSTO','Pedro Augusto','Moya ','SUZUKI','Alto 1.0 GA','ROJO',22801025,'70');
INSERT INTO "Registros" VALUES('B-08-673','Taxis Expresos','Manuel ','Jarquin','Nissan','T-suru','Verde',223056490,'Villa Flor Sur costado Este');
INSERT INTO "Registros" VALUES('Y42-ACU','Taxis 20 de Agosto','Alexander','Cahavrria','Nissan','Tiida','Amarillo',22870378,'Villa Venezuela cancha Municipal');
INSERT INTO "Registros" VALUES('A-72-422','Taxis El Patriarca','Buanerge','Gomez','Ford','Fiesta','Plomo',22306190,'Villa Austria Entr Ppal 2c Al N O-6');
INSERT INTO "Registros" VALUES('10-02-XCW','Carlos Fonseca','David','Ayerdis','TOYOTA','Land Crusier D-4D GX','Amarillo',22305612,'Rest El Madroño 1000m Este');
