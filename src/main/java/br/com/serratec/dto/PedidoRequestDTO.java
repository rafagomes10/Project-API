package br.com.serratec.dto;

import java.time.LocalDate;

import br.com.serratec.model.Cliente;
import br.com.serratec.model.Pedido;

public class PedidoRequestDTO {

    private LocalDate dataPedido;

    private LocalDate dataEntrega;

    private LocalDate dataEnvio;

    private String status;

    private Cliente cliente;

    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(Pedido pedido) {
        super();
        this.dataPedido = pedido.getDataPedido();
        this.dataEntrega = pedido.getDataEntrega();
        this.dataEnvio = pedido.getDataEnvio();
        this.status = pedido.getStatus();
        this.cliente = pedido.getCliente();
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
