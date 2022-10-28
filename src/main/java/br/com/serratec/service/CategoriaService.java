package br.com.serratec.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.CategoriaRequestDTO;
import br.com.serratec.dto.CategoriaResponseDTO;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.model.Categoria;
import br.com.serratec.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repositorio;
	
	public List<CategoriaResponseDTO> obterTodos(){
		
		List<Categoria> categorias = repositorio.findAll();
		ModelMapper mapper = new ModelMapper();
		
		// Aqui eu pega o lista de Categoria (Modelo), e converto um a uma em um CategoriaResponseDTO.
		//No final devolvo a lista convertida.
		return categorias.stream()
				.map(categoria -> mapper.map(categoria, CategoriaResponseDTO.class))
				.collect(Collectors.toList());
		
		
	}
	
	public Optional<CategoriaResponseDTO> obterPorId(Long id){
		
		Optional<Categoria> optCategoria = repositorio.findById(id);
		
		if(optCategoria.isEmpty()) {
			//Aqui lanço um exception
			throw new ResourceNotFoundException("Não foi possível encontrar um cliente com id: " + id);
		}
		
		var categoriaDTO = new ModelMapper().map(optCategoria.get(), CategoriaResponseDTO.class);
		
		return Optional.of(categoriaDTO);

	}

    public CategoriaResponseDTO cadastrar(CategoriaRequestDTO categoriaDto) {
		ModelMapper mapper = new ModelMapper();
		
		var categoriaModel = mapper.map(categoriaDto, Categoria.class);
		
		
		categoriaModel.setIdCategoria(null);
		categoriaModel =  repositorio.save(categoriaModel);
		
		return mapper.map(categoriaModel, CategoriaResponseDTO.class);
		
	}

	public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoriaDTO){
		ModelMapper mapper = new ModelMapper();

		var categoriaModel = mapper.map(categoriaDTO, Categoria.class);

		categoriaModel.setIdCategoria(id);
		categoriaModel =  repositorio.save(categoriaModel);

		return mapper.map(categoriaModel, CategoriaResponseDTO.class);
	}

	public void deletar(Long id){
		var optCategoria = obterPorId(id);

		if(optCategoria.isEmpty()){
			//lancar exception
		}
		repositorio.deleteById(id);
	}

}