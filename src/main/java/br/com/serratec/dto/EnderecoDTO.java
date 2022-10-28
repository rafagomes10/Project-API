package br.com.serratec.dto;

import br.com.serratec.model.Endereco;
import io.swagger.annotations.ApiModelProperty;

public class EnderecoDTO {

    private Long idEndereco;

    @ApiModelProperty(value = "Identificador de uma rua ")
    private String cep;

    @ApiModelProperty(value = "Identifica uma rua")
    private String logradouro;

    private String bairro;

    @ApiModelProperty(value = "Identifica a cidade")
    private String localidade;

    @ApiModelProperty(value = "Identifica o estado")
    private String uf;

    private String complemento;

    private Integer numero;

    public EnderecoDTO() {

    }

    public EnderecoDTO(Endereco endereco) {

        this.idEndereco = endereco.getIdEndereco();
        this.cep = endereco.getCep();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.numero = endereco.getNumero();
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
