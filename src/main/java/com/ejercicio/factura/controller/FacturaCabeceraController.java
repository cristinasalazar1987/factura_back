package com.ejercicio.factura.controller;

import com.ejercicio.factura.DTO.FacturaCabeceraDTO;
import com.ejercicio.factura.service.FacturaCabeceraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura")
@CrossOrigin(origins = "http://localhost:4200")
public class FacturaCabeceraController {

    @Autowired
    private FacturaCabeceraService facturaCabeceraService;

    @GetMapping
    public List<FacturaCabeceraDTO> obtenerTodas() {
        return facturaCabeceraService.obtenerTodasLasFacturas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaCabeceraDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(facturaCabeceraService.obtenerFacturaPorId(id));
    }

    @PostMapping
    public ResponseEntity<FacturaCabeceraDTO> crearFactura(@RequestBody FacturaCabeceraDTO facturaCabeceraDTO) {
        return ResponseEntity.ok(facturaCabeceraService.crearFacturaCabecera(facturaCabeceraDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaCabeceraDTO> actualizarFactura(@PathVariable Integer id, @RequestBody FacturaCabeceraDTO facturaCabeceraDTO) {
        return ResponseEntity.ok(facturaCabeceraService.actualizarFactura(id, facturaCabeceraDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Integer id) {
        facturaCabeceraService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }
}
