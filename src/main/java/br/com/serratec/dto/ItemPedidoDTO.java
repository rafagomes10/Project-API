package br.com.serratec.dto;
import br.com.serratec.model.ItemPedido;
import br.com.serratec.model.Pedido;
import br.com.serratec.model.Produto;

public class ItemPedidoDTO {

    private Long idItemPedido;

    private Integer quantidade;

    private Integer precoVenda;

    private Produto produto;

    private Pedido pedido;

    public ItemPedidoDTO(ItemPedido itemPedido) {
        this.idItemPedido = itemPedido.getIdItemPedido();
        this.quantidade = itemPedido.getQuantidade();
        this.produto = itemPedido.getProduto();
        this.pedido = itemPedido.getPedido();
        this.precoVenda = itemPedido.getPrecoVenda();
    }

    public ItemPedidoDTO() {
    }

    public Long getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(Long idItemPedido) {
        this.idItemPedido = idItemPedido;
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

   
}