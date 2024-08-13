package br.com.dbserver.projeto_spring_security.domain.produto;

import br.com.dbserver.projeto_spring_security.domain.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "cliente")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
