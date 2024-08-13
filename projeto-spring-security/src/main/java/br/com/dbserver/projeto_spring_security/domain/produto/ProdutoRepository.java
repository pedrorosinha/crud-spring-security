package br.com.dbserver.projeto_spring_security.domain.produto;

import br.com.dbserver.projeto_spring_security.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Cliente,Long> {

}
