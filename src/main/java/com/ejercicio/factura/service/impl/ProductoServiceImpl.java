package com.ejercicio.factura.service.impl;

import com.ejercicio.factura.DTO.ProductoDTO;
import com.ejercicio.factura.entity.Producto;
import com.ejercicio.factura.repository.ProductoRepository;
import com.ejercicio.factura.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecioUnitario(productoDTO.getPrecioUnitario());
        producto.setStock(productoDTO.getStock());

        Producto nuevoProducto = productoRepository.save(producto);
        return mapToDTO(nuevoProducto);
    }

    @Override
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerProductoPorId(int id) {
        return productoRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public ProductoDTO actualizarProducto(int id, ProductoDTO productoDTO) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setDescripcion(productoDTO.getDescripcion());
                    producto.setPrecioUnitario(productoDTO.getPrecioUnitario());
                    producto.setStock(productoDTO.getStock());
                    return mapToDTO(productoRepository.save(producto));
                })
                .orElse(null);
    }

    @Override
    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    private ProductoDTO mapToDTO(Producto producto) {
        return new ProductoDTO(producto.getId(), producto.getDescripcion(),
                producto.getPrecioUnitario(), producto.getStock());
    }
}
