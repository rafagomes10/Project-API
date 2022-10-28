package br.com.serratec.controller;

import java.io.IOException;
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
import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService servico;

    @GetMapping
    @ApiOperation(value = "Lista todas os clientes", notes = "Listagem de Clientes")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Retorna todos os clientes"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação"),
    })
    public ResponseEntity<List<ClienteResponseDTO>> obterTodos() {
        return ResponseEntity.ok(servico.obterTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna uma cliente", notes = "Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna um cliente"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação"),
    })
    public ResponseEntity<Optional<ClienteResponseDTO>> oberPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servico.obterPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere os dados de um cliente", notes = "Inserir clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente adicionado"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação"),
    })
    public ResponseEntity<ClienteResponseDTO> inserir(@RequestBody ClienteRequestDTO clienteInserirDTO)
            throws IOException {
        ClienteResponseDTO clienteDTO = servico.inserir(clienteInserirDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza os dados de um cliente", notes = "Atualizar Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente Atualizado"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação"),
    })
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO cliente) {
        ClienteResponseDTO dto = servico.atualizar(id, cliente);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Boolean response = servico.delete(id);
        if (response == true) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.notFound().build();

    }
}
