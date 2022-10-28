package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
		
	public Endereco findByCep(String cep);

}
