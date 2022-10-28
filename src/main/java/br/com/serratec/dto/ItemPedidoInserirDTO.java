package br.com.serratec.dto;

import br.com.serratec.model.ItemPedido;
import br.com.serratec.model.Pedido;
import br.com.serratec.model.Produto;

public class ItemPedidoInserirDTO {

    private Produto produto;

    private Pedido pedido;

    private Integer quantidade;

    private Integer precoVenda;

    public ItemPedidoInserirDTO(ItemPedido itemPedido) {
        this.produto = itemPedido.getProduto();
        this.pedido = itemPedido.getPedido();
        this.quantidade = itemPedido.getQuantidade();
        this.precoVenda = itemPedido.getPrecoVenda();
    }

    public ItemPedidoInserirDTO() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Integer precoVenda) {
        this.precoVenda = precoVenda;
    }
}