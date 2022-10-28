package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.ItemPedidoDTO;
import br.com.serratec.dto.ItemPedidoInserirDTO;
import br.com.serratec.model.ItemPedido;
import br.com.serratec.service.ItemPedidoService;

@RestController
@RequestMapping("/itensPedidos")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService itemPedidoService;
   

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemPedidoDTO> listar() {
        return itemPedidoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemPedidoDTO> inserir(@RequestBody ItemPedidoInserirDTO itemPedido) {

        if (null != itemPedido) {
            ItemPedidoDTO itemPedidos = itemPedidoService.inserir(itemPedido);
            return ResponseEntity.ok(itemPedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
