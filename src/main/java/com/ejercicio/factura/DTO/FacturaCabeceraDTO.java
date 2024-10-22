package com.ejercicio.factura.DTO;

import com.ejercicio.factura.entity.Cliente;
import com.ejercicio.factura.entity.Proveedor;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FacturaCabeceraDTO {
    private int idFactura;
    private int idProveedor;
    private int idCliente;
    private Proveedor proveedor;
    private Cliente cliente;
    private List<FacturaDetalleDTO> detalles;

}
