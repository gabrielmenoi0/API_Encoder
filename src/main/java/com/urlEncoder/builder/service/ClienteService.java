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

    private static ClienteService instance = null;
    private Cliente cliente;

    private ClienteService() {}

    public static ClienteService getInstance() {
        if (instance == null) {
            instance = new ClienteService();
        }
        return instance;
    }

    public void setCliente(ReciveClienteDTO clienteRecive) {
        cliente = new Cliente();
        cliente.setNome(clienteRecive.getNome());
        cliente.setSenha(clienteRecive.getSenha());
        cliente.setEmail(clienteRecive.getEmail());
        cliente.setDateRegister(dateSave());
        cliente.setToken(generateToken(clienteRecive.getSenha()));
    }

    /// Método save cadastra no banco um Cliente, usando uma função disponibilizada pela ORM e usando o Pattern Singleton
    public String save(ReciveClienteDTO clienteRecive) {
        setCliente(clienteRecive);
        Cliente result = repository.save(cliente);
        return result.getToken();
    }

    /// Método findAll recupera o uma lista Clientes cadastradas, buscando com a função disponibilizada pela ORM
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public String dateSave() {
        LocalDate dataNascimento = LocalDate.now();
        return dataNascimento.toString();
    }

    /// Método findById recupera um Cliente cadastrado, buscando com a função disponibilizada pela ORM
    public Optional<Cliente> findById(UUID id) {
        return repository.findById(id);
    }

    /// Método alterCliente altera o cliente informado, com as novas informações usando o patter Builder
    public Cliente alterCliente(Cliente cliente) {
        repository.deleteById(cliente.getId());
        ClienteBuilder clienteBuilder = new ClienteBuilder()
                .setNome(cliente.getNome())
                .setSenha(cliente.getSenha())
                .setEmail(cliente.getEmail())
                .setDateRegister(dateSave())
                .setToken(generateToken(cliente.getSenha())).build();
        Cliente newCliente = new Cliente();
        newCliente.setDateRegister(clienteBuilder.build().getDateRegister());
        newCliente.setEmail(clienteBuilder.build().getEmail());
        newCliente.setNome(clienteBuilder.build().getNome());
        newCliente.setToken(clienteBuilder.build().getToken());
        newCliente.setSenha(clienteBuilder.build().getSenha());
        return repository.save(newCliente);
    }

    /// Método findByToken realiza uma busca e retorna o cliente atravez do token informado usando o patter Builder
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
        return Optional.of(user.build());
    }

    /// Método findRegister realiza uma busca e retorna o cliente atravez do email informado usando o patter Builder
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
        return Optional.of(user.build());
    }
    /// Método delete apaga um Cliente, utilizando com a função disponibilizada pela ORM
    public void delete(Cliente cliente) {
        repository.delete(cliente);
    }

    /// Método deletebyid apaga uma Cliente pelo ID, utilizando com a função disponibilizada pela ORM
    public void deletebyid(UUID id) {
        repository.deleteById(id);
    }

    /// Método generateToken retorna uma String com o Token JWT utilizando "Jwts" usando o patter Builder
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
