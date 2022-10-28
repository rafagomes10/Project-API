package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.dto.ProdutoResponseDTO;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.model.Produto;
import br.com.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    private ModelMapper mapper = new ModelMapper();

    public List<ProdutoResponseDTO> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> produtosDTO = new ArrayList<>();

        for (Produto produto : produtos) {
            produtosDTO.add(new ProdutoResponseDTO(produto));

        }
        return produtosDTO;
    }

    public Optional<ProdutoResponseDTO> obterPorId(Long id) {

        Optional<Produto> optProduto = produtoRepository.findById(id);

        if (optProduto.isEmpty()) {          
            throw new ResourceNotFoundException("Não foi possível encontrar um cliente com id: " + id);
        }

        var produtoDTO = new ModelMapper().map(optProduto.get(), ProdutoResponseDTO.class);

        return Optional.of(produtoDTO);

    }

    public ProdutoResponseDTO inserir(ProdutoRequestDTO produtoInserirDTO) {

        Produto produto = new Produto();
        produto.setCategoria(produtoInserirDTO.getCategoria());
        produto.setDataCadastro(produtoInserirDTO.getDataCadastro());
        produto.setIdProduto(produtoInserirDTO.getIdProduto());
        produto.setNome(produtoInserirDTO.getNome());
        produto.setQtdEstoque(produtoInserirDTO.getQtdEstoque());
        produto.setValorUnitario(produtoInserirDTO.getValorUnitario());
        produto.setDescricao(produtoInserirDTO.getDescricao());        
        produto = produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto);

    }
    
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoDto) {
        
        var produtoModel = mapper.map(produtoDto, Produto.class);
        
        produtoModel.setIdProduto(id);
        produtoModel =  produtoRepository.save(produtoModel);
        
        return mapper.map(produtoModel, ProdutoResponseDTO.class);
    }
    
    
    public void deletar(Long id) {
        var optProduto = obterPorId(id);
        
        if(optProduto.isEmpty()) {         
            throw new ResourceNotFoundException("Não foi possível encontrar um cliente com id: " + id);
        }
        
        produtoRepository.deleteById(id);
    }
}
