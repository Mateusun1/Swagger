package br.com.cop.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {
    @Id
    @GeneratedValue
    @Schema(description = "ID da pessoa", example = "1")
    private Long id;
    @Schema(description = "Nome da pessoa", example = "João")
    private String nome;
    @Schema(description = "Idade da pessoa", example = "25")
    private Integer idade;
    @Schema(description = "CPF da pessoa", example = "292.976.910-64")
    private String cpf;
}
