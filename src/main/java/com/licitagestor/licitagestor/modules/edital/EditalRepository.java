package com.licitagestor.licitagestor.modules.edital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditalRepository extends JpaRepository<Edital, Long> {
    // Apenas com essa declaração, o Spring Boot já implementa os comandos de banco de dados (CRUD) por baixo dos panos.
}