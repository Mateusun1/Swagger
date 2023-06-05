package br.com.cop.swagger.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
public class PessoaRequest {
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;
    @NotNull
    private Integer idade;
    @CPF(message = "CPF invalido")
    private String cpf;
}
