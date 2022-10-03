package com.example.ceiba_prueba.dao;

import java.util.List;

import com.example.ceiba_prueba.models.Usuario;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    Usuario getUsuario(Integer id);

    Usuario addUsuario(Usuario usuario);

    void deleteUsuario(Integer id);

    Usuario editUsuario(Usuario usuario);

    Usuario verificarCredenciales(Usuario usuario);
}
