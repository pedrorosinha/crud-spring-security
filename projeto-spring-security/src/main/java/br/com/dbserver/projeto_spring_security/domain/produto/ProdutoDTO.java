package br.com.dbserver.projeto_spring_security.domain.produto;

public record ProdutoDTO(Long id, String nome, Double preco, Long clienteId) {
}
