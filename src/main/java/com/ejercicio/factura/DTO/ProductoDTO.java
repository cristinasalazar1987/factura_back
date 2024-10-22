package com.ejercicio.factura.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private int id;
    private String descripcion;
    private Double precioUnitario;
    private Double stock;

}
