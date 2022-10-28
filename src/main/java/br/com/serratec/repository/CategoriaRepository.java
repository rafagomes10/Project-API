package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}