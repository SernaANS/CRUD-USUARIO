package com.example.ceiba_prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ceiba_prueba.dao.UsuarioDao;
import com.example.ceiba_prueba.models.Usuario;
import com.example.ceiba_prueba.utils.JWTUtil;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {

        Usuario userLoged = usuarioDao.verificarCredenciales(usuario);
        
        if (userLoged == null)
            return "Fail";

        String token = jwtUtil.create(String.valueOf(userLoged.getId()), userLoged.getCorreo());
        return token;
    }

}
