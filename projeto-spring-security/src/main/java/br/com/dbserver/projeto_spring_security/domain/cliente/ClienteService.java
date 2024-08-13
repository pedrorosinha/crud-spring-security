package br.com.dbserver.projeto_spring_security.domain.cliente;

import br.com.dbserver.projeto_spring_security.domain.produto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> findById(Long id) {
        return clienteRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente savedCliente = clienteRepository.save(cliente);
        return convertToDTO(savedCliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        List<ProdutoDTO> produtos = cliente.getProdutos().stream()
                .map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), cliente.getId()))
                .collect(Collectors.toList());

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail(), produtos);
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.id());
        cliente.setNome(clienteDTO.nome());
        cliente.setEmail(clienteDTO.email());
        return cliente;
    }
}