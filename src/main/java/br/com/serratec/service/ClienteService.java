package br.com.serratec.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.serratec.config.EmailConfig;
import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.dto.EnderecoInserirDTO;
import br.com.serratec.exception.CpfException;
import br.com.serratec.exception.EmailException;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.model.Cliente;
import br.com.serratec.model.Endereco;
import br.com.serratec.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<ClienteResponseDTO> obterTodos() {

        List<Cliente> clientes = clienteRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return clientes.stream()
                .map(cliente -> mapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());

    }

    public Optional<ClienteResponseDTO> obterPorId(Long id) {

        Optional<Cliente> optCliente = clienteRepository.findById(id);

        if (optCliente.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar um cliente com id: " + id);
        }

        var clienteDTO = new ModelMapper().map(optCliente.get(), ClienteResponseDTO.class);

        return Optional.of(clienteDTO);

    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteDTO) {
        ModelMapper mapper = new ModelMapper();

        var clienteModel = mapper.map(clienteDTO, Cliente.class);

        clienteModel.setIdCliente(id);
        clienteModel = clienteRepository.save(clienteModel);

        return mapper.map(clienteModel, ClienteResponseDTO.class);
    }

    public ClienteResponseDTO inserir(ClienteRequestDTO clienteInserirDTO) {
        if (clienteRepository.findByEmail(clienteInserirDTO.getEmail()) != null) {
            throw new EmailException("Email já existe na base");
        }

        if (clienteRepository.findByCpf(clienteInserirDTO.getCpf()) != null) {
            throw new CpfException("CPF já existe na base");
        }

        EnderecoInserirDTO endereco = clienteInserirDTO.getEndereco();
        Endereco enderecoViaCep = enderecoService.inserir(endereco.getCep(), endereco.getComplemento(),
                endereco.getNumero());
        Cliente cliente = new Cliente();
        cliente.setNomeUsuario(clienteInserirDTO.getNomeUsuario());
        cliente.setEmail(clienteInserirDTO.getEmail());
        cliente.setDataNascimento(clienteInserirDTO.getDataNascimento());
        cliente.setEndereco(enderecoViaCep);
        cliente.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
        cliente.setCpf(clienteInserirDTO.getCpf());
        cliente.setTelefone(clienteInserirDTO.getTelefone());
        cliente.setEmail(clienteInserirDTO.getEmail());
        cliente.setIdCliente(clienteInserirDTO.getIdCliente());
        cliente.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
        cliente = clienteRepository.save(cliente);
        emailConfig.sendEmail(cliente.getEmail(), "Cadastro de Usuário", cliente.toString());
        return new ClienteResponseDTO(cliente);
    }

    public Boolean delete(Long id) {
        Optional<Cliente> clientes = clienteRepository.findById(id);
        if (clientes.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}