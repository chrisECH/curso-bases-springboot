package com.cursojava.demospring.dao;

import com.cursojava.demospring.model.Usuarios;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Usuarios> getUsuarios() {

        String query = "FROM Usuarios";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        Usuarios usuarios = entityManager.find(Usuarios.class, id);
        entityManager.remove(usuarios);
    }

    @Override
    public void registrar(Usuarios usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuarios obtenerUsuarioCredenciales(Usuarios usuario){
        String query = "FROM Usuarios WHERE email = :email ";
        List<Usuarios> listaUsuarios =  entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(listaUsuarios.isEmpty()) return null;

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if(argon2.verify(listaUsuarios.get(0).getPassword(), usuario.getPassword())){
            return listaUsuarios.get(0);
        }else{
            return null;
        }
    }

}
