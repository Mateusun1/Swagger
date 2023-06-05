package br.com.cop.swagger.controller;

import br.com.cop.swagger.dto.PessoaRequest;
import br.com.cop.swagger.model.Pessoa;
import br.com.cop.swagger.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Retorna a pessoa cadastrada"),
            @ApiResponse(responseCode = "400", description = "Lança um erro quando a pessoa não é cadastrada de forma correta",
                    content = @Content(schema = @Schema(example = "Erro ao cadastrar uma pessoa, verifique se o nome,idade e cpf estão corretos"))),
    })
    @Operation(
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = "{\"id\": 1, \"nome\": \"João\", \"idade\": 30, \"cpf\": \"755.299.540-81\"}"
                            )
                    )
            )

    )
    public ResponseEntity<Pessoa> cadastrar(@org.springframework.web.bind.annotation.RequestBody @Valid PessoaRequest pessoaRequest) {
        return new ResponseEntity<>(pessoaService.cadastrar(pessoaRequest), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de pessoas"),
            @ApiResponse(responseCode = "400", description = "Lança um erro informando que o Id é invalido")
    })
    public ResponseEntity<List<Pessoa>> buscarTudo() {
        return new ResponseEntity<>(pessoaService.buscarTodos(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleta a pessoa",
                    content = @Content(schema = @Schema(example = "Deletado com sucesso!"))),
            @ApiResponse(responseCode = "400", description = "Lança um erro informando que o Id é invalido",
                    content = @Content(schema = @Schema(example = "ID inválido")))
    })
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return new ResponseEntity(pessoaService.deletar(id), HttpStatus.OK);
    }

}
