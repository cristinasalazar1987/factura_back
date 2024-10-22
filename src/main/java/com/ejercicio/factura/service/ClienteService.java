package com.ejercicio.factura.service;

import com.ejercicio.factura.DTO.ClienteDTO;

import java.util.List;

public interface ClienteService {
    ClienteDTO crearCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> obtenerTodosLosClientes();
    ClienteDTO obtenerClientePorId(int idCliente);
    ClienteDTO actualizarCliente(int idCliente, ClienteDTO clienteDTO);
    void eliminarCliente(int idCliente);
}
