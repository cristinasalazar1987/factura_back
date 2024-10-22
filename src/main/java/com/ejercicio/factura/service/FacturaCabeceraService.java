package com.ejercicio.factura.service;

import com.ejercicio.factura.DTO.FacturaCabeceraDTO;

import java.util.List;

public interface FacturaCabeceraService {
    FacturaCabeceraDTO crearFacturaCabecera(FacturaCabeceraDTO facturaCabeceraDTO);
    List<FacturaCabeceraDTO> obtenerTodasLasFacturas();
    FacturaCabeceraDTO obtenerFacturaPorId(int idFactura);
    FacturaCabeceraDTO actualizarFactura(int idFactura, FacturaCabeceraDTO facturaCabeceraDTO);
    void eliminarFactura(int idFactura);
}
