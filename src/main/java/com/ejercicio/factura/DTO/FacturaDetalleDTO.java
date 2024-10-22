package com.ejercicio.factura.DTO;

import com.ejercicio.factura.entity.FacturaCabecera;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaDetalleDTO {
    private int idDetalle;
    private FacturaCabeceraDTO facturaCabeceraDTO;
    private ProductoDTO productoDTO;
    private int cantidad;
    private Double subtotal;
    private Double iva;
    private Double total;

    // Getters y setters...

}
