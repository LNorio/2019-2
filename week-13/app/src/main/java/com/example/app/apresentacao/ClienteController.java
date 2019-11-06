package com.example.app.apresentacao;

import com.example.app.negocio.ClienteNegocio;
import com.example.app.negocio.dominio.ClienteDTO;
import com.example.app.negocio.excecao.ObjetoJaExisteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ClienteController{

    private final ClienteNegocio clienteNegocio;

    @GetMapping("/cliente")
    public String listar(Model memoria){

        memoria.addAttribute("listaClientes",
                ClientesDTO.ModelsFromDTOs(clienteNegocio.listar()));

        return "cliente-view"; //ainda nao existe esse ftl
    }

    @PostMapping("/cliente/criar")
    public String criar(ClienteModel cliente){

        try{
            clienteNegocio.incluir(ClienteDTO.DTOFromModel(cliente));
        }
        catch (ObjetoJaExisteException e){
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, e);
        }

        return "redirect:/cliente";
    }

}
