package br.com.dbserver.projeto_spring_security.domain.produto;

import br.com.dbserver.projeto_spring_security.domain.cliente.Cliente;
import br.com.dbserver.projeto_spring_security.domain.cliente.ClienteRepository;
import br.com.dbserver.projeto_spring_security.infra.exception.ProdutoNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ClienteRepository clienteRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<ProdutoDTO> findAll() {
        return produtoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> findById(Long id) {
        return produtoRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Cliente cliente = clienteRepository.findById(produtoDTO.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setPreco(produtoDTO.preco());
        produto.setCliente(cliente);

        Produto savedProduto = produtoRepository.save(produto);
        return convertToDTO(savedProduto);
    }

        public void deleteById(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ProdutoNotFoundException("Produto com ID " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO convertToDTO(Produto produto) {
        Long clienteId = (produto.getCliente() != null) ? produto.getCliente().getId() : null;
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), clienteId);
    }

}
