package com.urlEncoder.builder.repository;

import com.urlEncoder.builder.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("ClienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

//    public cliente findByToken(String token);

}
