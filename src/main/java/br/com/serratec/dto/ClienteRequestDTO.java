package br.com.serratec.dto;

import java.time.LocalDate;
import javax.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import br.com.serratec.model.Cliente;

public class ClienteRequestDTO {

    @Email(message = "Preencha o email corretamente!")
    private String email;

    private String nomeUsuario;

    private String nomeCompleto;

    private Long idCliente;

    private String senha;

    @CPF(message = "Preencha o CPF corretamente...")
    private String cpf;

    private String telefone;

    private EnderecoInserirDTO endereco;

    private LocalDate dataNascimento;

    public ClienteRequestDTO() {

    }

    public ClienteRequestDTO(Cliente cliente) {
        this.email = cliente.getEmail();
        this.nomeUsuario = cliente.getNomeUsuario();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.dataNascimento = cliente.getDataNascimento();
        this.endereco = new EnderecoInserirDTO(cliente.getEndereco());
        this.senha = cliente.getSenha();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnderecoInserirDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInserirDTO endereco) {
        this.endereco = endereco;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
