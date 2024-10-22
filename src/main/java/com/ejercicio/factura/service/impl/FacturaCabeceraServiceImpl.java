package com.ejercicio.factura.service.impl;

import com.ejercicio.factura.DTO.FacturaCabeceraDTO;
import com.ejercicio.factura.DTO.ProveedorDTO;
import com.ejercicio.factura.DTO.ClienteDTO;
import com.ejercicio.factura.entity.FacturaCabecera;
import com.ejercicio.factura.entity.Proveedor;
import com.ejercicio.factura.entity.Cliente;
import com.ejercicio.factura.repository.FacturaCabeceraRepository;
import com.ejercicio.factura.repository.ProveedorRepository;
import com.ejercicio.factura.repository.ClienteRepository;
import com.ejercicio.factura.service.FacturaCabeceraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaCabeceraServiceImpl implements FacturaCabeceraService {

    @Autowired
    private FacturaCabeceraRepository facturaCabeceraRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public FacturaCabeceraDTO crearFacturaCabecera(FacturaCabeceraDTO facturaCabeceraDTO) {
        FacturaCabecera facturaCabecera = new FacturaCabecera();

        if (facturaCabeceraDTO.getIdProveedor() != 0) {
            Proveedor proveedor = proveedorRepository.findById(facturaCabeceraDTO.getIdProveedor())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            facturaCabecera.setProveedor(proveedor);
        }

        if (facturaCabeceraDTO.getIdCliente() != 0) {
            Cliente cliente = clienteRepository.findById(facturaCabeceraDTO.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            facturaCabecera.setCliente(cliente);
        }

        FacturaCabecera nuevaFactura = facturaCabeceraRepository.save(facturaCabecera);
        return mapToDTO(nuevaFactura);
    }

    @Override
    public List<FacturaCabeceraDTO> obtenerTodasLasFacturas() {
        return facturaCabeceraRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FacturaCabeceraDTO obtenerFacturaPorId(int idFactura) {
        return facturaCabeceraRepository.findById(idFactura)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public FacturaCabeceraDTO actualizarFactura(int idFactura, FacturaCabeceraDTO facturaCabeceraDTO) {
        return facturaCabeceraRepository.findById(idFactura)
                .map(factura -> {

                    if (facturaCabeceraDTO.getIdProveedor() != 0) {
                        Proveedor proveedor = proveedorRepository.findById(facturaCabeceraDTO.getIdProveedor())
                                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
                        factura.setProveedor(proveedor);
                    }


                    if (facturaCabeceraDTO.getIdCliente() != 0) {
                        Cliente cliente = clienteRepository.findById(facturaCabeceraDTO.getIdCliente())
                                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
                        factura.setCliente(cliente);
                    }

                    return mapToDTO(facturaCabeceraRepository.save(factura));
                })
                .orElse(null);
    }

    @Override
    public void eliminarFactura(int idFactura) {
        facturaCabeceraRepository.deleteById(idFactura);
    }

    private FacturaCabeceraDTO mapToDTO(FacturaCabecera facturaCabecera) {
        Proveedor proveedor = facturaCabecera.getProveedor();
        Cliente cliente = facturaCabecera.getCliente();

        return FacturaCabeceraDTO.builder()
                .idFactura(facturaCabecera.getIdFactura())
                .idProveedor(proveedor != null ? proveedor.getIdProveedor() : null)
                .idCliente(cliente != null ? cliente.getIdCliente() : null)
                .build();
    }
}

