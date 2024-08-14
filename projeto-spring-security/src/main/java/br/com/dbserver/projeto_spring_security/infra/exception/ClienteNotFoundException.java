package br.com.dbserver.projeto_spring_security.infra.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente com ID " + id + " não foi encontrado.");
    }
}
