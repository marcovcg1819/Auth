/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empresa.Mb;

import com.empresa.dao.UsuarioDao;
import com.empresa.entidades.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author marco
 */
@ManagedBean
@ViewScoped
public class UsuarioMb implements Serializable{
    
    private List<Usuario> usuarioList = new ArrayList<Usuario>();
    @EJB
    UsuarioDao usuarioDao;
    private Usuario usuario = new Usuario();
    private String respuesta = null;
    
    @PostConstruct
    public void getUsuarios(){
        usuarioList = usuarioDao.getUsuarios();        
    }
    
    public void guardarUsuario(){
        Usuario usuarioFromDao;
        usuarioFromDao = usuarioDao.guardarUsuario(usuario);
        if(null!=usuarioFromDao){
            getUsuarios();
            respuesta = "Usuario agregado con éxito";            
        }else{
            respuesta = "error";
        }
        procesarRespuesta(respuesta);
    }
    
    public void eliminarUsuario(Usuario usuario){
        Usuario usuarioEliminado = new Usuario();
        usuarioEliminado = usuarioDao.eliminarUsuario(usuario);
        if(null!=usuarioEliminado){
            getUsuarios();
            respuesta = "Usuario "+usuarioEliminado.getNombre()+" eliminado con éxito";
         }else{
            respuesta = "error";
        }
        procesarRespuesta(respuesta);
    }
    
    public void seleccionarUsuario(Usuario usuarioDt){
        usuario = usuarioDt;
    }
    
    public void actualizarUsuario(){
        Usuario usuarioFromDao;
        usuarioFromDao = usuarioDao.actualizarUsuario(usuario);
        if(null!=usuarioFromDao){
            getUsuarios();
            respuesta = "Usuario "+usuarioFromDao.getNombre()+" actualizado con éxito";            
        }else{
            respuesta = "error";
        }
        procesarRespuesta(respuesta);
    }
    
    public void limpiar(){
        usuario = new Usuario();
    }
    
    public void procesarRespuesta(String respuesta){
//        if(!respuesta.equals("error")){
            FacesMessage messaje = new FacesMessage(FacesMessage.SEVERITY_INFO, respuesta, null);
            FacesContext.getCurrentInstance().addMessage(null, messaje);
//        }
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
