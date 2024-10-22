package com.ejercicio.factura.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private int idCliente;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String identificacion;

}
