package com.cursojava.demospring.controllers;

import com.cursojava.demospring.dao.UsuarioDAO;
import com.cursojava.demospring.model.Usuarios;
import com.cursojava.demospring.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private JWTUtil jwtutil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuarios usuario){

        Usuarios userLogged = usuarioDAO.obtenerUsuarioCredenciales(usuario);


        if(userLogged!= null){
            String tokenJWT = jwtutil.create(String.valueOf(userLogged.getId()), userLogged.getEmail());

            return tokenJWT;
        }
        return "Fail!";
    }
}
