
package com.example.ceiba_prueba.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity//una entidad que esta en la base de datos
@Table(name ="USUARIO")//A que tabla tiene que apuntar
@ToString //Este implementa el toString
@EqualsAndHashCode
@NoArgsConstructor//Constructor con ningun Argumento
@AllArgsConstructor//Constructor con todos los argumentos
@Builder //Construir un objeto con cierto parametrops
public class Usuario implements Serializable {


    @Getter @Setter @Column(name="id") @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Getter @Setter @Column(name="nombre")
    private String nombre;

    @Getter @Setter @Column(name="apellido")
    private String apellido;

    @Getter @Setter @Column(name="correo")
    private String correo;

    @Getter @Setter @Column(name="telefono")
    private String  telefono;

    @Getter @Setter @Column(name="password")
    private String password;

}
