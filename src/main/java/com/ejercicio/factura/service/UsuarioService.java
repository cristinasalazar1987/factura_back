package com.ejercicio.factura.service;

import com.ejercicio.factura.DTO.UsuarioDTO;

public interface UsuarioService {

    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO login(String username, String password);
}
