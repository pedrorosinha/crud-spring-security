package br.com.dbserver.projeto_spring_security.domain.cliente;

import br.com.dbserver.projeto_spring_security.domain.produto.ProdutoDTO;

import java.util.List;

public record ClienteDTO(Long id, String nome, String email, List<ProdutoDTO> produtos) {
}
