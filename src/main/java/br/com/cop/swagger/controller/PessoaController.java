package br.com.cop.swagger.controller;

import br.com.cop.swagger.dto.PessoaRequest;
import br.com.cop.swagger.model.Pessoa;
import br.com.cop.swagger.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Pessoa> cadastrar(@RequestBody @Valid PessoaRequest pessoaRequest){
        return new ResponseEntity<>(pessoaService.cadastrar(pessoaRequest), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarTudo(){
        return new ResponseEntity<>(pessoaService.buscarTodos(), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        return new ResponseEntity(pessoaService.deletar(id),HttpStatus.OK);
    }

}
