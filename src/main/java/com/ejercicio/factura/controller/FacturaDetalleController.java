package com.ejercicio.factura.controller;

import com.ejercicio.factura.DTO.FacturaDetalleDTO;
import com.ejercicio.factura.service.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura-detalle")
@CrossOrigin(origins = "http://localhost:4200")
public class FacturaDetalleController {

    @Autowired
    private FacturaDetalleService facturaDetalleService;

    @GetMapping
    public List<FacturaDetalleDTO> obtenerTodos() {
        return facturaDetalleService.obtenerTodosLosDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDetalleDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(facturaDetalleService.obtenerDetallePorId(id));
    }

    @PostMapping
    public ResponseEntity<FacturaDetalleDTO> crearDetalle(@RequestBody FacturaDetalleDTO facturaDetalleDTO) {
        return ResponseEntity.ok(facturaDetalleService.crearFacturaDetalle(facturaDetalleDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDetalleDTO> actualizarDetalle(@PathVariable Integer id, @RequestBody FacturaDetalleDTO facturaDetalleDTO) {
        return ResponseEntity.ok(facturaDetalleService.actualizarDetalle(id, facturaDetalleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Integer id) {
        facturaDetalleService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
