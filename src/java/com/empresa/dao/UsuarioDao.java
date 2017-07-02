/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.dao;

import com.empresa.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marco
 */
@Stateless
public class UsuarioDao {
    
    private String respuesta = null;
    private List<Usuario> usuarioList = new ArrayList<Usuario>();
    @PersistenceContext
    private EntityManager em;
    
    public List<Usuario> getUsuarios(){
        try {
           usuarioList = em.createNamedQuery("Usuario.findAll").getResultList();
           respuesta = "";
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = "error";
        }
        return usuarioList;
    }
    
    public Usuario guardarUsuario(Usuario usuario){
        try {
            em.persist(usuario);
            usuario = em.merge(usuario);            
        } catch (Exception e) {
            e.printStackTrace();
            usuario = null;            
        }
        return usuario;
    }
    
    public Usuario eliminarUsuario(Usuario usuario) {
        try {
            Usuario usuarioEliminar;
            usuarioEliminar = em.merge(usuario);
            em.remove(usuarioEliminar);
            return usuarioEliminar;
        } catch (Exception e) {
            e.printStackTrace();
            usuario = null;
            return usuario;
        }        
    }
    
        public Usuario actualizarUsuario(Usuario usuario) {
        try {
            Usuario usuarioActualizar;
            usuarioActualizar = em.merge(usuario);            
            return usuarioActualizar;
        } catch (Exception e) {
            e.printStackTrace();
            usuario = null;
            return usuario;
        }        
    }
}
