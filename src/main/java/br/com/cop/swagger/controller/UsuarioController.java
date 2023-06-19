package br.com.cop.swagger.controller;

import br.com.cop.swagger.dto.UsuarioForm;
import br.com.cop.swagger.model.Usuario;
import br.com.cop.swagger.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid UsuarioForm form){
        return new ResponseEntity<>(usuarioService.cadastrar(form), HttpStatus.CREATED);
    }
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<Usuario>> puxar(){
        return new ResponseEntity<>(usuarioService.buscarTudo(), HttpStatus.CREATED);
    }
}
