package com.ejercicio.factura.service.impl;

import com.ejercicio.factura.DTO.FacturaCabeceraDTO;
import com.ejercicio.factura.DTO.FacturaDetalleDTO;
import com.ejercicio.factura.DTO.ProductoDTO;
import com.ejercicio.factura.entity.FacturaCabecera;
import com.ejercicio.factura.entity.FacturaDetalle;
import com.ejercicio.factura.entity.Producto;
import com.ejercicio.factura.repository.FacturaCabeceraRepository;
import com.ejercicio.factura.repository.FacturaDetalleRepository;
import com.ejercicio.factura.repository.ProductoRepository;
import com.ejercicio.factura.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {

    private final FacturaDetalleRepository facturaDetalleRepository;
    private final FacturaCabeceraRepository facturaCabeceraRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public FacturaDetalleServiceImpl(FacturaDetalleRepository facturaDetalleRepository,
                                     FacturaCabeceraRepository facturaCabeceraRepository,
                                        ProductoRepository productoRepository) {
        this.facturaDetalleRepository = facturaDetalleRepository;
        this.facturaCabeceraRepository = facturaCabeceraRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public FacturaDetalleDTO crearFacturaDetalle(FacturaDetalleDTO facturaDetalleDTO) {

        FacturaDetalle facturaDetalle = new FacturaDetalle();
        facturaDetalle.setCantidad(facturaDetalleDTO.getCantidad());
        facturaDetalle.setSubtotal(facturaDetalleDTO.getSubtotal());
        facturaDetalle.setIva(facturaDetalleDTO.getIva());
        facturaDetalle.setTotal(facturaDetalleDTO.getTotal());

        if (facturaDetalleDTO.getFacturaCabeceraDTO() != null) {
            FacturaCabeceraDTO cabeceraDTO = facturaDetalleDTO.getFacturaCabeceraDTO();
            FacturaCabecera facturaCabecera = facturaCabeceraRepository.findById(cabeceraDTO.getIdFactura())
                    .orElseThrow(() -> new RuntimeException("FacturaCabecera no encontrada"));
            facturaDetalle.setFacturaCabecera(facturaCabecera);
        }

        if (facturaDetalleDTO.getProductoDTO() != null) {
            ProductoDTO productoDTO = facturaDetalleDTO.getProductoDTO();
            Producto producto = productoRepository.findById(productoDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrada"));
            facturaDetalle.setProducto(producto);
        }

        FacturaDetalle savedFacturaDetalle = facturaDetalleRepository.save(facturaDetalle);

        return convertirAControlador(savedFacturaDetalle);
    }

    @Override
    public List<FacturaDetalleDTO> obtenerTodosLosDetalles() {
        List<FacturaDetalle> detalles = facturaDetalleRepository.findAll();
        return detalles.stream()
                .map(this::convertirAControlador)
                .collect(Collectors.toList());
    }

    @Override
    public FacturaDetalleDTO obtenerDetallePorId(int idDetalle) {
        Optional<FacturaDetalle> detalleOptional = facturaDetalleRepository.findById(idDetalle);
        return detalleOptional.map(this::convertirAControlador).orElse(null);
    }

    @Override
    public FacturaDetalleDTO actualizarDetalle(int idDetalle, FacturaDetalleDTO facturaDetalleDTO) {
        Optional<FacturaDetalle> detalleOptional = facturaDetalleRepository.findById(idDetalle);
        if (detalleOptional.isPresent()) {
            FacturaDetalle facturaDetalle = detalleOptional.get();
            facturaDetalle.setCantidad(facturaDetalleDTO.getCantidad());
            facturaDetalle.setSubtotal(facturaDetalleDTO.getSubtotal());
            facturaDetalle.setIva(facturaDetalleDTO.getIva());
            facturaDetalle.setTotal(facturaDetalleDTO.getTotal());

            if (facturaDetalleDTO.getFacturaCabeceraDTO() != null) {
                FacturaCabeceraDTO cabeceraDTO = facturaDetalleDTO.getFacturaCabeceraDTO();
                FacturaCabecera facturaCabecera = facturaCabeceraRepository.findById(cabeceraDTO.getIdFactura())
                        .orElseThrow(() -> new RuntimeException("FacturaCabecera no encontrada"));
                facturaDetalle.setFacturaCabecera(facturaCabecera);
            }

            if (facturaDetalleDTO.getProductoDTO() != null) {
                ProductoDTO productoDTO = facturaDetalleDTO.getProductoDTO();
                Producto producto = productoRepository.findById(productoDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrada"));
                facturaDetalle.setProducto(producto);
            }

            FacturaDetalle updatedFacturaDetalle = facturaDetalleRepository.save(facturaDetalle);
            return convertirAControlador(updatedFacturaDetalle);
        }
        return null;
    }

    @Override
    public void eliminarDetalle(int idDetalle) {
        facturaDetalleRepository.deleteById(idDetalle);
    }


    private FacturaDetalleDTO convertirAControlador(FacturaDetalle facturaDetalle) {
        FacturaDetalleDTO dto = new FacturaDetalleDTO();
        dto.setIdDetalle(facturaDetalle.getIdDetalle());
        dto.setCantidad(facturaDetalle.getCantidad());
        dto.setSubtotal(facturaDetalle.getSubtotal());
        dto.setIva(facturaDetalle.getIva());
        dto.setTotal(facturaDetalle.getTotal());

        if (facturaDetalle.getFacturaCabecera() != null) {
            FacturaCabecera facturaCabecera = facturaDetalle.getFacturaCabecera();
            FacturaCabeceraDTO cabeceraDTO = new FacturaCabeceraDTO();
            cabeceraDTO.setIdFactura(facturaCabecera.getIdFactura());

            dto.setFacturaCabeceraDTO(cabeceraDTO);
        }

        return dto;
    }
}
