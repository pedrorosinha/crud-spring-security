package br.com.dbserver.projeto_spring_security.domain.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

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
        Produto produto = convertToEntity(produtoDTO);
        Produto savedProduto = produtoRepository.save(produto);
        return convertToDTO(savedProduto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO convertToDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCliente() != null ? produto.getCliente().getId() : null
        );
    }

    private Produto convertToEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setId(produtoDTO.id());
        produto.setNome(produtoDTO.nome());
        produto.setPreco(produtoDTO.preco());
        return produto;
    }
}
