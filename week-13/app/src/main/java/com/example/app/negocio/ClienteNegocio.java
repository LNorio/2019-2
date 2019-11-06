package com.example.app.negocio;

import com.example.app.negocio.dominio.ClienteDTO;
import com.example.app.negocio.excecao.ObjetoJaExisteException;
import java.util.HashSet;
import java.util.Set;

public class ClienteNegocio implements INegocio<ClienteDTO>{

    private Set<ClienteDTO> lista;

    private final CienteDAO clienteDAO;

    @Override
    public void incluir(ClienteDTO cliente) throws ObjetoJaExisteException {
        if (!lista.add(cliente))
            throw new ObjetoJaExisteException();

        clienteDAO.save(ClienteDTO.EntityFromDTO(cliente));
    }

    @Override
    public Set<ClienteDTO> listar() {
        return PaisDTO.DTOsFromEntities(clienteDAO.findAll());
    }

}
