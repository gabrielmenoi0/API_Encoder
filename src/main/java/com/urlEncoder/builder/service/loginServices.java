package com.urlEncoder.builder.service;

import com.urlEncoder.builder.domain.Cliente;
import com.urlEncoder.builder.domain.ClienteBuilder;
import com.urlEncoder.builder.domain.login;
import com.urlEncoder.builder.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class loginServices {

    @Autowired
    private ClienteRepository repository;

    /// Método login verifica e retorna o Objeto Cliente baseado no resultado das suas credenciais usando o Patter Builder
    public Optional<ClienteBuilder> login(login info) {
        List<Cliente> list = repository.findAll();
        ClienteBuilder clienteBuilder = new ClienteBuilder();
        list.forEach(element -> {
            if (element.getEmail().equals(info.getEmail()) && element.getSenha().equals(info.getPassword())) {
                clienteBuilder.setToken(element.getToken())
                        .setId(element.getId())
                        .setNome(element.getNome())
                        .setEmail(element.getSenha())
                        .setDateRegister(element.getDateRegister())
                        .setSenha(element.getSenha());
            }
        });
        return Optional.of(clienteBuilder.build());
    }

}
