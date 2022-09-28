CREATE DATABASE IF NOT EXISTS CINE;
USE CINE;

CREATE TABLE IF NOT EXISTS genero(
id_genero INTEGER PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
imagen VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS personaje (
id_personaje INTEGER PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
edad TINYINT NOT NULL,
peso FLOAT NOT NULL, 
imagen VARCHAR(255),
historia TEXT
);

CREATE TABLE IF NOT EXISTS pelicula_serie(
id_pelicula_serie INTEGER PRIMARY KEY AUTO_INCREMENT,
titulo VARCHAR(50) NOT NULL,
imagen VARCHAR(255),
fecha_creacion DATE NOT NULL,
califiacion TINYINT DEFAULT 0,
personaje_id INTEGER NOT NULL,
id_genero INTEGER NOT NULL,
FOREIGN KEY(id_genero) REFERENCES genero(id_genero)
);

-- ALTER TABLE pelicula_serie ADD INDEX i_pelicula_serie(personaje_id);

CREATE TABLE IF NOT EXISTS pelicula_serie_personajes(
personaje_id INTEGER NOT NULL,
id_personaje INTEGER NOT NULL,
FOREIGN KEY(id_personaje) REFERENCES personaje(id_personaje)
);

CREATE TABLE IF NOT EXISTS usuario(
id_usuario INTEGER PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
`password` VARCHAR(255) NOT NULL
);
