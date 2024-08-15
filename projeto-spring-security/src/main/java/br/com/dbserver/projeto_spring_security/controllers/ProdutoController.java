package br.com.dbserver.projeto_spring_security.controllers;

import br.com.dbserver.projeto_spring_security.domain.produto.ProdutoDTO;
import br.com.dbserver.projeto_spring_security.domain.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO savedProduto = produtoService.save(produtoDTO);
        return ResponseEntity.ok(savedProduto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar() {
        List<ProdutoDTO> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> detalhar(@PathVariable Long id) {
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.findById(id)
                .map(existingProduto -> {
                    ProdutoDTO updatedProdutoDTO = new ProdutoDTO(id, produtoDTO.nome(), produtoDTO.preco(), produtoDTO.clienteId());
                    ProdutoDTO updatedProduto = produtoService.save(updatedProdutoDTO);
                    return ResponseEntity.ok(updatedProduto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
