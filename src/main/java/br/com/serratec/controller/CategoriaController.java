package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.serratec.dto.CategoriaRequestDTO;
import br.com.serratec.dto.CategoriaResponseDTO;
import br.com.serratec.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService servico;
	
	@GetMapping
	@ApiOperation(value="Lista todas as categorias", notes="Listagem de Categorias")
    @ApiResponses(value= {
                        
                @ApiResponse(responseCode="200", description="Retorna todas as categorias"),
                @ApiResponse(responseCode="401", description="Erro de autenticação"),
                @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
                @ApiResponse(responseCode="404", description="Recurso não encontrado"),
                @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
                })
	public ResponseEntity<List<CategoriaResponseDTO>> obterTodos(){
		return ResponseEntity.ok(servico.obterTodos());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna uma categoria", notes="Categoria")
    @ApiResponses(value= {
                @ApiResponse(responseCode="200", description="Retorna uma categoria"),
                @ApiResponse(responseCode="401", description="Erro de autenticação"),
                @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
                @ApiResponse(responseCode="404", description="Recurso não encontrado"),
                @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
                })
	public ResponseEntity<Optional<CategoriaResponseDTO>> oberPorId(@PathVariable Long id){
		return  ResponseEntity.ok(servico.obterPorId(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="Insere os dados de uma categoria", notes="Inserir categoria")
    @ApiResponses(value= {
                @ApiResponse(responseCode="200", description="Categoria adicionada"),
                @ApiResponse(responseCode="401", description="Erro de autenticação"),
                @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
                @ApiResponse(responseCode="404", description="Recurso não encontrado"),
                @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
                })
	public ResponseEntity<CategoriaResponseDTO> cadastrar(@RequestBody CategoriaRequestDTO categoria) {
		CategoriaResponseDTO dto = servico.cadastrar(categoria);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ApiOperation(value="Insere os dados de uma categoria", notes="Atualizar Categoria")
    @ApiResponses(value= {
            @ApiResponse(responseCode="200", description="Categoria Atualizada"),
            @ApiResponse(responseCode="401", description="Erro de autenticação"),
            @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode="404", description="Recurso não encontrado"),
            @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
            })
	public ResponseEntity<CategoriaResponseDTO> atualizar( @PathVariable Long id, @RequestBody CategoriaRequestDTO categoria) {
		CategoriaResponseDTO dto = servico.atualizar(id, categoria);		
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove uma categoria", notes="Remover Categoria")
    @ApiResponses(value= {
    @ApiResponse(responseCode="200", description="Categoria Removida"),
    @ApiResponse(responseCode="401", description="Erro de autenticação"),
    @ApiResponse(responseCode="403", description="Não há permissão para acessar o recurso"),
    @ApiResponse(responseCode="404", description="Recurso não encontrado"),
    @ApiResponse(responseCode="505", description="Exceção interna da aplicação"),
    })
	public ResponseEntity<?> deletar(Long id){
		servico.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}