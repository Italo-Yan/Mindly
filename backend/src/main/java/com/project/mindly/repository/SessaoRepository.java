package com.project.mindly.repository;

import com.project.mindly.model.sessao.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoRepository extends JpaRepository<Sessao, Integer> {
}
