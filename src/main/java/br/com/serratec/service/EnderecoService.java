package br.com.serratec.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import br.com.serratec.dto.EnderecoDTO;
import br.com.serratec.model.Endereco;

import br.com.serratec.repository.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO buscar(String cep, String complemento, Integer numero) {
        Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
        if (endereco.isPresent()) {
            return new EnderecoDTO(endereco.get());
        } else {
            RestTemplate rs = new RestTemplate();
            String uri = "http://viacep.com.br/ws/" + cep + "/json";
            Optional<Endereco> enderecoViaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
            if (enderecoViaCep.get().getCep() != null) {
                
               
                String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
                enderecoViaCep.get().setCep(cepSemTraco);             
                enderecoViaCep.get().setComplemento(complemento);
                enderecoViaCep.get().setNumero(numero);
                return new EnderecoDTO(enderecoViaCep.get());
                
            } else {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        }
    }

    public Endereco inserir(String cep, String complemento, Integer numero) {
        EnderecoDTO ent = buscar(cep, complemento, numero);        
        Endereco endereco = new Endereco();
        endereco.setBairro(ent.getBairro());
        endereco.setCep(ent.getCep());
        endereco.setIdEndereco(ent.getIdEndereco());
        endereco.setLogradouro(ent.getLogradouro());
        endereco.setLocalidade(ent.getLocalidade());
        endereco.setUf(ent.getUf()); 
        endereco.setIdEndereco(ent.getIdEndereco());
        endereco.setComplemento(ent.getComplemento());
        endereco.setNumero(ent.getNumero());

        endereco = enderecoRepository.save(endereco);

        return endereco;
    }
    



        
	

}