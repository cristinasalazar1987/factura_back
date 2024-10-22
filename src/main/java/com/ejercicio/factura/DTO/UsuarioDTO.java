package com.ejercicio.factura.DTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private Integer idUsuario;
    private String username;
    private String password;
    private String email;


}
