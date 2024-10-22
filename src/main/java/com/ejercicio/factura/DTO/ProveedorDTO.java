
package com.ejercicio.factura.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDTO {
    private int idProveedor;
    private String razonSocial;
    private String identificacion;

}
