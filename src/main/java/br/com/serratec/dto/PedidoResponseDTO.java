package br.com.serratec.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.serratec.model.Cliente;
import br.com.serratec.model.ItemPedido;
import br.com.serratec.model.Pedido;

public class PedidoResponseDTO {

    private Long idPedido;

    private LocalDate dataPedido;

    private LocalDate dataEntrega;

    private LocalDate dataEnvio;

    private String status;

    private Double valorTotal;

    private Cliente cliente;

    private List<ItemPedido> itensPedidos = new ArrayList<>();

    public PedidoResponseDTO() {

    }

    public PedidoResponseDTO(Pedido pedido) {
        super();
        this.idPedido = pedido.getIdPedido();
        this.dataPedido = pedido.getDataPedido();
        this.dataEntrega = pedido.getDataEntrega();
        this.dataEnvio = pedido.getDataEnvio();
        this.status = pedido.getStatus();
        this.cliente = pedido.getCliente();
        this.valorTotal = pedido.getValorTotal();
        
        for (ItemPedido item : pedido.getItems()) {
            itensPedidos.add(item);
        }
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
