package com.vitor.helpdesck.repositories;

import com.vitor.helpdesck.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
