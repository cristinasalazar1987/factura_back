package com.ejercicio.factura.repository;

import com.ejercicio.factura.entity.FacturaCabecera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaCabeceraRepository extends JpaRepository<FacturaCabecera, Integer> {

}
