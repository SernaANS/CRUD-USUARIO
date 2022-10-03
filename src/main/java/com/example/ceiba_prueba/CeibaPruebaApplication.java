package com.example.ceiba_prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CeibaPruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeibaPruebaApplication.class, args);
	}

	
	// Create table libros(
	// 	id varchar(10) not null PRIMARY KEY,
	// 	nombre varchar not null,
	// 	autor varchar,
	// );
	
	// Create table tipo_usuarios(
	//   id INTEGER IDENTITY ,
	//   nombre varchar
	// );
	
	// Create table usuario(
	// 	id varchar(10) not null PRIMARY KEY,
	// 	cedula varchar,
	// 	nombre varchar ,
	// 	apellido varchar,
	// 	password varchar not null,
	// 	edad DATE,
	// 	  tipo_usuario INTEGER CONSTRAINT fk_tipo_usuario FOREIGN key REFERENCES tipo_usuario(ID)
	// );
	
	// CREATE table prestamos(
	//   id numeric not null primary key,
	//   id_usuario varchar FOREIGN key REFERENCES usuario(ID),
	//   id_libro  varchar FOREIGN key REFERENCES libros(ID),
	//   fecha_prestamo DATe,
	//   fecha_maxima_devolucion DATE
	// );

}
