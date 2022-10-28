package br.com.serratec.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.exception.EmailException;
import br.com.serratec.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @ApiOperation(value="Lista todas os pedidos", notes="Listagem de Pedidos")
    @ApiResponses(value= {
                        
                @ApiResponse(responseCode="200", description="Retorna todos os pedidos"),
                @ApiResponse(responseCode="401", description="Erro de autenticação"),
                @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
                @ApiResponse(responseCode="404", description="Recurso não encontrado"),
                @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
                })
    public ResponseEntity<List<PedidoResponseDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }
    
     @GetMapping("/{id}")
     @ApiOperation(value="Retorna um pedido", notes="Pedido")
     @ApiResponses(value= {
              @ApiResponse(responseCode="200", description="Retorna um pedido"),
              @ApiResponse(responseCode="401", description="Erro de autenticação"),
              @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
              @ApiResponse(responseCode="404", description="Recurso não encontrado"),
              @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
              })
        public Optional<PedidoResponseDTO> oberPorId(@PathVariable Long id){
            return  pedidoService.obterPorId(id);
       }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="Insere os dados de um pedido", notes="Inserir pedidos")
    @ApiResponses(value= {
             @ApiResponse(responseCode="200", description="Pedido adicionado"),
             @ApiResponse(responseCode="401", description="Erro de autenticação"),
             @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
             @ApiResponse(responseCode="404", description="Recurso não encontrado"),
             @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
             })

    public ResponseEntity<Object> inserir(@Valid @RequestBody PedidoRequestDTO pedidoInserirDTO) {   
        
        
        try {
            PedidoResponseDTO pedidoDTO= pedidoService.inserir(pedidoInserirDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(pedidoDTO.getIdPedido()).toUri();
            return ResponseEntity.created(uri).body(pedidoDTO);

        } catch (EmailException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
        
    }

    

    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza os dados de um pedido", notes="Atualizar Pedido")
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Pedido Atualizado"),
            @ApiResponse(responseCode="401", description="Erro de autenticação"),
            @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode="404", description="Recurso não encontrado"),
            @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
            })

    public ResponseEntity<PedidoResponseDTO> atualizar( @PathVariable Long id, @RequestBody PedidoRequestDTO categoria) {
        PedidoResponseDTO dto = pedidoService.atualizar(id, categoria);        
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Remove um pedido", notes="Remover Pedido")
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Pedido Removido"),
            @ApiResponse(responseCode="401", description="Erro de autenticação"),
            @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode="404", description="Recurso não encontrado"),
            @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
            })
    public ResponseEntity<?> deletar(Long id){
        pedidoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
   
}

