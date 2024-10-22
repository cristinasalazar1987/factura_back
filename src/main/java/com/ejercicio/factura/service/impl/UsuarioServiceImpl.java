package com.ejercicio.factura.service.impl;

import com.ejercicio.factura.DTO.UsuarioDTO;
import com.ejercicio.factura.entity.Usuario;
import com.ejercicio.factura.repository.UsuarioRepository;
import com.ejercicio.factura.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.sql.DriverManager.println;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.builder()
                .username(usuarioDTO.getUsername())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .email(usuarioDTO.getEmail())
                .build();
        usuario = usuarioRepository.save(usuario);
        return mapToDTO(usuario);
    }

    @Override
    public UsuarioDTO login(String username, String password) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsername(username);
        if (usuarioOptional.isPresent()) {
            Usuario user = usuarioOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return mapToDTO(user);
            }
        }
        return null;
    }

    private UsuarioDTO mapToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .build();
    }
}
