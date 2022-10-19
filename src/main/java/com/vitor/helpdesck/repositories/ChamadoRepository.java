package com.vitor.helpdesck.repositories;

import com.vitor.helpdesck.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
