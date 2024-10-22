package com.ejercicio.factura.service;

import com.ejercicio.factura.DTO.ProveedorDTO;

import java.util.List;

public interface ProveedorService {
    ProveedorDTO crearProveedor(ProveedorDTO proveedorDTO);
    List<ProveedorDTO> obtenerTodosLosProveedores();
    ProveedorDTO obtenerProveedorPorId(int idProveedor);
    ProveedorDTO actualizarProveedor(int idProveedor, ProveedorDTO proveedorDTO);
    void eliminarProveedor(int idProveedor);
}
