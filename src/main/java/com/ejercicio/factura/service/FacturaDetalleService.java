package com.ejercicio.factura.service;

import com.ejercicio.factura.DTO.FacturaDetalleDTO;

import java.util.List;

public interface FacturaDetalleService {
    FacturaDetalleDTO crearFacturaDetalle(FacturaDetalleDTO facturaDetalleDTO);
    List<FacturaDetalleDTO> obtenerTodosLosDetalles();
    FacturaDetalleDTO obtenerDetallePorId(int idDetalle);
    FacturaDetalleDTO actualizarDetalle(int idDetalle, FacturaDetalleDTO facturaDetalleDTO);
    void eliminarDetalle(int idDetalle);
}
