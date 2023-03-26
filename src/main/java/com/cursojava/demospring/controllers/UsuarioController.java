package com.cursojava.demospring.controllers;

import com.cursojava.demospring.dao.UsuarioDAO;
import com.cursojava.demospring.model.Usuarios;
import com.cursojava.demospring.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuarios getUsuario(@PathVariable Long id){

        Usuarios usuarios = new Usuarios();
        usuarios.setId(id);
        usuarios.setNombre("Christian");
        usuarios.setApellidos("Camarena Hernández");
        usuarios.setEmail("chris@gmail.com");
        usuarios.setTelefono("23453453");

        return usuarios;
    }

    private boolean validarToken(String token){
        String idUser = jwtUtil.getKey(token);

        return idUser != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuarios> getUsuarios(@RequestHeader(value ="Auth") String token){
        if(!validarToken(token)) return null;

        return usuarioDAO.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuarios usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);


        usuarioDAO.registrar(usuario);
    }

    @RequestMapping(value = "api/editUser")
    public Usuarios editUsuario(){
        Usuarios usuarios = new Usuarios();

        usuarios.setNombre("Christian");
        usuarios.setApellidos("Camarena Hernández");
        usuarios.setEmail("chris@gmail.com");
        usuarios.setTelefono("23453453");
        return usuarios;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@RequestHeader(value = "Auth") String token, @PathVariable Long id){
        if(!validarToken(token)) return;
        usuarioDAO.deleteUser(id);
    }


}
