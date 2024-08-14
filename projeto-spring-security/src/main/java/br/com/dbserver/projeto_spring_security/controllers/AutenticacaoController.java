package br.com.dbserver.projeto_spring_security.controllers;

import br.com.dbserver.projeto_spring_security.domain.usuario.AutenticacaoDTO;
import br.com.dbserver.projeto_spring_security.domain.usuario.Usuario;
import br.com.dbserver.projeto_spring_security.infra.security.TokenJWTDTO;
import br.com.dbserver.projeto_spring_security.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTDTO> efetuarLogin(@RequestBody AutenticacaoDTO autenticacaoDTO) {
        var authenticationToken = new    UsernamePasswordAuthenticationToken(autenticacaoDTO.login(), autenticacaoDTO.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }
}
