package br.com.serratec.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.dto.ProdutoResponseDTO;
import br.com.serratec.exception.EmailException;
import br.com.serratec.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;   

    @GetMapping
    @ApiOperation(value="Lista todas os produtos", notes="Listagem de Produtos")
    @ApiResponses(value= {
                        
                @ApiResponse(responseCode="200", description="Retorna todos os produtos"),
                @ApiResponse(responseCode="401", description="Erro de autenticação"),
                @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
                @ApiResponse(responseCode="404", description="Recurso não encontrado"),
                @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
                })
    public ResponseEntity<List<ProdutoResponseDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    
    @GetMapping("/{id}")
    @ApiOperation(value="Retorna um produto", notes="Produto")
    @ApiResponses(value= {
             @ApiResponse(responseCode="200", description="Retorna um produto"),
             @ApiResponse(responseCode="401", description="Erro de autenticação"),
             @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
             @ApiResponse(responseCode="404", description="Recurso não encontrado"),
             @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
             })

    public Optional<ProdutoResponseDTO> oberPorId(@PathVariable Long id){
        return  produtoService.obterPorId(id);
   }   
        

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="Insere os dados de um produto", notes="Inserir produtos")
    @ApiResponses(value= {
             @ApiResponse(responseCode="200", description="Produto adicionado"),
             @ApiResponse(responseCode="401", description="Erro de autenticação"),
             @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
             @ApiResponse(responseCode="404", description="Recurso não encontrado"),
             @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
             })
    public ResponseEntity<Object> inserir(@RequestBody ProdutoRequestDTO produtoInserirDTO) {
        try {
            ProdutoResponseDTO produtoDTO = produtoService.inserir(produtoInserirDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(produtoDTO.getIdProduto())
                    .toUri();
            return ResponseEntity.created(uri).body(produtoDTO);

        } catch (EmailException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza os dados de um produto", notes="Atualizar Produto")
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Produto Atualizado"),
            @ApiResponse(responseCode="401", description="Erro de autenticação"),
            @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode="404", description="Recurso não encontrado"),
            @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
            })
    public ResponseEntity<ProdutoResponseDTO> atualizar( @PathVariable Long id, @RequestBody ProdutoRequestDTO categoria) {
        ProdutoResponseDTO dto = produtoService.atualizar(id, categoria);        
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Remove um produto", notes="Remover Produto")
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Produto Removido"),
            @ApiResponse(responseCode="401", description="Erro de autenticação"),
            @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode="404", description="Recurso não encontrado"),
            @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
            })
    public ResponseEntity<?> deletar(Long id){
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }   
    
}