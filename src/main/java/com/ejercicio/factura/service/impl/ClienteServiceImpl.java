package com.ejercicio.factura.service.impl;

import com.ejercicio.factura.DTO.ClienteDTO;
import com.ejercicio.factura.entity.Cliente;
import com.ejercicio.factura.repository.ClienteRepository;
import com.ejercicio.factura.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombres(clienteDTO.getNombres());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setCorreoElectronico(clienteDTO.getCorreoElectronico());
        cliente.setIdentificacion(clienteDTO.getIdentificacion());

        Cliente nuevoCliente = clienteRepository.save(cliente);
        return mapToDTO(nuevoCliente);
    }

    @Override
    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO obtenerClientePorId(int idCliente) {
        return clienteRepository.findById(idCliente)
                .map(this::mapToDTO)
                .orElse(null);
    }

    @Override
    public ClienteDTO actualizarCliente(int idCliente, ClienteDTO clienteDTO) {
        return clienteRepository.findById(idCliente)
                .map(cliente -> {
                    cliente.setNombres(clienteDTO.getNombres());
                    cliente.setApellidos(clienteDTO.getApellidos());
                    cliente.setDireccion(clienteDTO.getDireccion());
                    cliente.setTelefono(clienteDTO.getTelefono());
                    cliente.setCorreoElectronico(clienteDTO.getCorreoElectronico());
                    cliente.setIdentificacion(clienteDTO.getIdentificacion());
                    return mapToDTO(clienteRepository.save(cliente));
                })
                .orElse(null);
    }

    @Override
    public void eliminarCliente(int idCliente) {
        clienteRepository.deleteById(idCliente);
    }


    private ClienteDTO mapToDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getIdCliente(), cliente.getNombres(),
                cliente.getApellidos(), cliente.getDireccion(),
                cliente.getTelefono(), cliente.getCorreoElectronico(),
                cliente.getIdentificacion());
    }
}
