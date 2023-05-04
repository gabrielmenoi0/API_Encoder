package com.urlEncoder.builder.service;

import com.urlEncoder.builder.domain.Cliente;
import com.urlEncoder.builder.domain.ClienteBuilder;
import com.urlEncoder.builder.dto.ReciveClienteDTO;
import com.urlEncoder.builder.repository.ClienteRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public String save(ReciveClienteDTO clienteRecive) {
        ClienteBuilder clienteBuilder = new ClienteBuilder()
                .setNome(clienteRecive.getNome())
                .setSenha(clienteRecive.getSenha())
                .setEmail(clienteRecive.getEmail())
                .setDateRegister(dateSave())
                .setToken(generateToken(clienteRecive.getSenha()));
        Cliente cliente = new Cliente();
        cliente.setDateRegister(clienteBuilder.build().getDateRegister());
        cliente.setEmail(clienteBuilder.build().getEmail());
        cliente.setNome(clienteBuilder.build().getNome());
        cliente.setToken(clienteBuilder.build().getToken());
        cliente.setSenha(clienteBuilder.build().getSenha());
        Cliente result = repository.save(cliente);
        return result.getToken();
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public String dateSave() {
        LocalDate dataNascimento = LocalDate.now();
        return dataNascimento.toString();
    }

    public Optional<Cliente> findById(UUID id) {
        return repository.findById(id);
    }

    public Cliente alterCliente(Cliente cliente) {
        repository.deleteById(cliente.getId());
        ClienteBuilder clienteBuilder = new ClienteBuilder()
                .setNome(cliente.getNome())
                .setSenha(cliente.getSenha())
                .setEmail(cliente.getEmail())
                .setDateRegister(dateSave())
                .setToken(generateToken(cliente.getSenha()));
        Cliente newCliente = new Cliente();
        newCliente.setDateRegister(clienteBuilder.build().getDateRegister());
        newCliente.setEmail(clienteBuilder.build().getEmail());
        newCliente.setNome(clienteBuilder.build().getNome());
        newCliente.setToken(clienteBuilder.build().getToken());
        newCliente.setSenha(clienteBuilder.build().getSenha());
        return repository.save(newCliente);
    }

    public Optional<ClienteBuilder> findByToken(String token) {
        List<Cliente> list = repository.findAll();
        ClienteBuilder user = new ClienteBuilder().build();
        list.forEach(element -> {
            if (element.getToken().equals(token)) {
                user.setToken(element.getToken());
                user.setId(element.getId());
                user.setNome(element.getNome());
                user.setEmail(element.getSenha());
                user.setDateRegister(element.getDateRegister());
                user.setSenha(element.getSenha());
            }
        });
        return Optional.of(user);
    }

    public Optional<ClienteBuilder> findRegister(String email) {
        List<Cliente> list = repository.findAll();
        ClienteBuilder user = new ClienteBuilder().build();
        list.forEach(element -> {
            if (element.getEmail().equals(email)) {
                user.setToken(element.getToken());
                user.setId(element.getId());
                user.setNome(element.getNome());
                user.setEmail(element.getSenha());
                user.setDateRegister(element.getDateRegister());
                user.setSenha(element.getSenha());
            }
        });
        return Optional.of(user);
    }

    public void delete(Cliente cliente) {
        repository.delete(cliente);
    }

    public void deletebyid(UUID id) {
        repository.deleteById(id);
    }

    public String generateToken(String password) {
        String secret = "JsonWebToken";
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(password)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    }
