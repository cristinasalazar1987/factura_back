package com.ejercicio.factura.service;

import com.ejercicio.factura.DTO.ProductoDTO;

import java.util.List;

public interface ProductoService {
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    List<ProductoDTO> obtenerTodosLosProductos();
    ProductoDTO obtenerProductoPorId(int id);
    ProductoDTO actualizarProducto(int id, ProductoDTO productoDTO);
    void eliminarProducto(int id);
}
