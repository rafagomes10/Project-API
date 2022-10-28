package br.com.serratec.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.serratec.model.Endereco;

public class EnderecoInserirDTO {
    @NotBlank(message = " Por favor, preencha o número.")
    private Integer numero;

    @NotNull(message = " Por favor, preencha o complemento.")
    private String complemento;

    @NotBlank(message = " Por favor, digite um CEP válido.")
    @Size(min = 9, max = 9, message = "Por favor, digite 9 números.")
    private String cep;

    public EnderecoInserirDTO() {
    }

    public EnderecoInserirDTO(Endereco endereco) {
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.cep = endereco.getCep();
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
