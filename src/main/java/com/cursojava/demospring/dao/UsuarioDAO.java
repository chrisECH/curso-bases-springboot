package com.cursojava.demospring.dao;

import com.cursojava.demospring.model.Usuarios;

import java.util.List;

public interface UsuarioDAO {

    List<Usuarios> getUsuarios();

    void deleteUser(Long id);

    void registrar(Usuarios usuario);

    Usuarios obtenerUsuarioCredenciales(Usuarios usuarios);

}
