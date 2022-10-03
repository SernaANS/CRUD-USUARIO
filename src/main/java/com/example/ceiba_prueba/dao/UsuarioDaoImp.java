package com.example.ceiba_prueba.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.ceiba_prueba.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    EntityManager entityManager;  //sirve para hacer la conexion con la base de datos


    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        return entityManager.createQuery("from Usuario").getResultList();
    }

    @Override
    @Transactional
    public Usuario getUsuario(Integer id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    @Transactional
    public Usuario addUsuario(Usuario usuario) {
        //String query= "Insert Into Usuario (nombre,apellido,correo,telefono,password) values ()";
        return entityManager.merge(usuario);
    }

    @Override
    @Transactional
    public void deleteUsuario(Integer id) {
        // TODO Auto-generated method stub

        Usuario usuario= entityManager.find(Usuario.class, id);

        entityManager.remove(usuario);
    }

    @Override
    public Usuario editUsuario(Usuario usuario) {
        
        return entityManager.merge(usuario);
    }

    @Override
    public Usuario verificarCredenciales(Usuario usuario) {

        String query= "FROM Usuario WHERE email= :email AND password= :password";
        Usuario  user=(Usuario) entityManager.createQuery(query)
        .setParameter("email", usuario.getPassword())
        //.setParameter("password", usuario.getPassword())
        .getSingleResult();

        if(user==null) return null;

        Argon2 argon=Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        boolean verifi= argon.verify(user.getPassword(), usuario.getPassword().toCharArray());

        if (!verifi) return null;
    
        return user;

 
    }
    


    
}