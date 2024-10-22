package com.ejercicio.factura.service.impl;

import com.ejercicio.factura.DTO.ProveedorDTO;
import com.ejercicio.factura.entity.Proveedor;
import com.ejercicio.factura.repository.ProveedorRepository;
import com.ejercicio.factura.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public ProveedorDTO crearProveedor(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = new Proveedor();
        proveedor.setRazonSocial(proveedorDTO.getRazonSocial());
        proveedor.setIdentificacion(proveedorDTO.getIdentificacion());

        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        return mapToDTO(nuevoProveedor);
    }

    @Override
    public List<ProveedorDTO> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorDTO obtenerProveedorPorId(int idProveedor) {
        return proveedorRepository.findById(idProveedor)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public ProveedorDTO actualizarProveedor(int idProveedor, ProveedorDTO proveedorDTO) {
        return proveedorRepository.findById(idProveedor)
                .map(proveedor -> {
                    proveedor.setRazonSocial(proveedorDTO.getRazonSocial());
                    proveedor.setIdentificacion(proveedorDTO.getIdentificacion());
                    return mapToDTO(proveedorRepository.save(proveedor));
                })
                .orElse(null);
    }

    @Override
    public void eliminarProveedor(int idProveedor) {
        proveedorRepository.deleteById(idProveedor);
    }

    private ProveedorDTO mapToDTO(Proveedor proveedor) {
        return new ProveedorDTO(proveedor.getIdProveedor(),
                proveedor.getRazonSocial(),
                proveedor.getIdentificacion());
    }
}
