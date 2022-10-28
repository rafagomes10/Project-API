package br.com.serratec.dto;

import br.com.serratec.model.Categoria;

public class CategoriaResponseDTO {

    private Long idCategoria;

    private String nome;

    private String descricao;

    public CategoriaResponseDTO() {
    }

    public CategoriaResponseDTO(Categoria categoria) {
        super();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
