SET GLOBAL time_zone = '-6:00';

DROP SCHEMA IF EXISTS rides;
DROP USER IF exists 'rides_user'@'%';

CREATE SCHEMA rides;
create user 'rides_user'@'%' identified by 'Admin123_';
grant all privileges on rides.* to 'rides_user'@'%';
flush privileges;

USE rides;

CREATE TABLE rol (
  id int unsigned NOT NULL AUTO_INCREMENT,
  idRol VARCHAR(15) NOT NULL,
  descripcionRol VARCHAR(30) NOT NULL,
  activo Boolean,
  PRIMARY KEY (id),
  unique key  `idRol` (idRol)
);


CREATE TABLE turista (
  id int unsigned NOT NULL AUTO_INCREMENT,
  idTurista varchar(10) not null,
  pwTurista varchar(255) not null,
  nombreUsuario VARCHAR(30) NOT NULL,
  correoTurista varchar(30) not null,
  activo Boolean,
  idRolTurista VARCHAR(15) NOT NULL,
  
  PRIMARY KEY (id),
  unique key  `idTurista` (idTurista)
 
);

CREATE TABLE viaje (
id int unsigned NOT NULL AUTO_INCREMENT,
idViaje VARCHAR(10) not null,
nombre VARCHAR(30) NOT NULL,
descripcion varchar(255) not null,
telefono varchar(20) not null,
correoElectronico varchar(35) not null,
activo Boolean,
costo double,
PRIMARY KEY (id),
unique key  `idViaje` (idViaje)

);


CREATE TABLE pais (
id int unsigned NOT NULL AUTO_INCREMENT,
idPais VARCHAR(10) NOT NULL, 
nombre VARCHAR(30) NOT NULL,
continente VARCHAR(30) NOT NULL,
descripcion VARCHAR(30) NOT NULL, 
primary key (id), 
unique key `idPais` (idPais)
);

CREATE TABLE reserva (
id int unsigned NOT NULL AUTO_INCREMENT, 
idReserva varchar(10),
costo double, 
fecha date not null,
idTurista VARCHAR(10),
idPais VARCHAR(10),
idViaje VARCHAR(10),
 
primary key (id),
unique key `idReserva` (idReserva),
FOREIGN KEY (idTurista)
REFERENCES turista(idTurista),
FOREIGN KEY (idPais)
REFERENCES pais(idPais),
FOREIGN KEY (idViaje)
REFERENCES viaje(idViaje)

);


insert into rol (idRol,descripcionRol,activo) values ('admin','Admninistrador del sistema', true);

insert into viaje (idViaje, nombre, descripcion, 
 telefono, correoElectronico, activo, costo)  values
("256854",'Paquete Gold','Todo Incluido',
'+34 695 686 817','info@ongvoluntariado.org',true, 230000),

("214409",'Paquete Diamante',' Hospedaje y Alimentacion',
'88152514','info@territoriodezaguates.com',true, 250000);

insert into turista (idTurista, pwTurista, nombreUsuario, correoTurista, activo, idRolTurista) values ("123","123","juan","titi@gmail.com", true, 'admin');
insert into turista (idTurista, pwTurista, nombreUsuario, correoTurista, activo, idRolTurista) values ("116","116","pedro","titi2@gmail.com", true, 'admin');

insert into pais (idPais, nombre, continente, descripcion) values ('CR','Costa Rica','America', ' Posee hermosas playas'); 
insert into pais (idPais, nombre, continente, descripcion) values ('GUA','Guatemala','America', ' Posee hermosos lagos');


 

insert into reserva (idReserva, costo, fecha,  idTurista, idPais, idViaje) values ("RES02", 150000, '2016-12-06', '123', 'GUA','256854');
insert into reserva (idReserva, costo, fecha,  idTurista, idPais, idViaje) values ("RES01", 130000, '2016-11-06',  '116', 'CR', '256854');
