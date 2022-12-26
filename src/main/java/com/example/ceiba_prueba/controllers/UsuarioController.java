package com.example.ceiba_prueba.controllers;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ceiba_prueba.dao.UsuarioDao;
import com.example.ceiba_prueba.models.Usuario;
import com.example.ceiba_prueba.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "usuario/{id}")
    public Usuario getUsuario(@PathVariable Integer id) {
        return usuarioDao.getUsuario(id);
    }

    @RequestMapping(value = "usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    @RequestMapping(value = "usuario/register", method = RequestMethod.POST)
    public ResponseEntity<Object> registrar(@RequestBody Usuario usuario) {

        if(usuario.getCorreo().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo no debe estar vacio");
        }

        // Contraseña codificada
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon.hash(1, 1024, 1, usuario.getPassword().toCharArray());
        usuario.setPassword(hash);
        Usuario usuariotemp= usuarioDao.addUsuario(usuario);

        return new ResponseEntity<>( usuariotemp ,HttpStatus.OK);
    }

    @RequestMapping(value = "usuario/edit/1", method = RequestMethod.POST)
    public Usuario editUsuario(@RequestBody Usuario usuario) {
        return usuarioDao.editUsuario(usuario);
    }

    @RequestMapping(value = "usuario/delete/{id}")
    public void deleteUsuario(@PathVariable Integer id) {
        usuarioDao.deleteUsuario(id);
    }

    /**
     * Este tiene el auteticador de parte del usuario si lo quisieramos por
     * seguridad
     * 
     * @return
     */
    // @RequestMapping(value = "usuarios/token", method = RequestMethod.GET)
    // public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {
    //     String usuarioId = jwtUtil.getKey(token);
    //     if (usuarioId == null)
    //         return new ArrayList<>();
    //     return usuarioDao.getUsuarios();
    // }

    // @RequestMapping(value ="usuarios")
    // public List<Usuario> getUsuarios() {
    // return usuarioDao.getUsuarios();
    // // Usuario u1, u2,u3;
    // // u1=new Usuario(131L,"santiago","serna","correo@","322","lindo");
    // //u2=new Usuario(132L,"sara","serna","correo@","322","lindo");
    // // u3=new Usuario(133L,"laura","serna","correo@","322","lindo");
    // // return Arrays.asList(u1, u2, u3);
    // }

}
