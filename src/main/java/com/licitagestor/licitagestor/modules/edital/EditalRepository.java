package com.licitagestor.licitagestor.modules.edital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditalRepository extends JpaRepository<Edital, Long> {
    // Só com isso, o Java já sabe fazer CRUD completo da tabela 'editais'
}