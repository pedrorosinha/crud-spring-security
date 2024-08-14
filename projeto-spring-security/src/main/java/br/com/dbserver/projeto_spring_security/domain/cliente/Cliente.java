package br.com.dbserver.projeto_spring_security.domain.cliente;

import br.com.dbserver.projeto_spring_security.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Cliente")
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "produtos")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;
}
