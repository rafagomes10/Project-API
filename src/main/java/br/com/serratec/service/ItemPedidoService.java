package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.ItemPedidoDTO;
import br.com.serratec.dto.ItemPedidoInserirDTO;
import br.com.serratec.model.ItemPedido;
import br.com.serratec.model.Pedido;
import br.com.serratec.model.Produto;
import br.com.serratec.repository.ItemPedidoRepository;
import br.com.serratec.repository.PedidoRepository;
import br.com.serratec.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    public ItemPedidoDTO inserir(ItemPedidoInserirDTO ips) {
        Optional<Produto> produto = produtoRepository.findById(ips.getProduto().getIdProduto());
        
        Double subTotal = 0.0;
        subTotal += produto.get().getValorUnitario() * ips.getQuantidade(); 
        int precoVenda = subTotal.intValue();
        Optional<Pedido> pedido = pedidoRepository.findById(ips.getPedido().getIdPedido());

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPrecoVenda(precoVenda);
        itemPedido.setQuantidade(ips.getQuantidade());
        itemPedido.setProduto(produto.get());
        itemPedido.setPedido(pedido.get());
        itemPedido = itemPedidoRepository.save(itemPedido);

                return new ItemPedidoDTO(itemPedido);

        
    }

    public List<ItemPedidoDTO> listar() {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<>();

        for (ItemPedido itemPedido : itemPedidos) {
            itemPedidoDTOs.add(new ItemPedidoDTO(itemPedido));
        }
        return itemPedidoDTOs;
    }
}
