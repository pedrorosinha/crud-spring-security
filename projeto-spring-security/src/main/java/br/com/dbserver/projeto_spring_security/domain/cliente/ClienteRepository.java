package br.com.dbserver.projeto_spring_security.domain.cliente;

import br.com.dbserver.projeto_spring_security.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Produto, Long> {
}
