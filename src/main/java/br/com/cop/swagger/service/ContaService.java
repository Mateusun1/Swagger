package br.com.cop.swagger.service;

import br.com.cop.swagger.dto.response.ContaCorrenteResponse;
import br.com.cop.swagger.dto.response.SaldoResponse;
import br.com.cop.swagger.model.ContaCorrente;
import br.com.cop.swagger.dto.request.ContaCorrenteRequest;
import br.com.cop.swagger.model.Usuario;
import br.com.cop.swagger.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class ContaService {
    private final ContaRepository contaRepository;
    private final UsuarioService usuarioService;
    private final ModelMapper mapper;

    @Autowired
    public ContaService(ContaRepository contaRepository, UsuarioService usuarioService, ModelMapper mapper) {
        this.contaRepository = contaRepository;
        this.usuarioService = usuarioService;
        this.mapper = mapper;
    }

    public ContaCorrenteResponse criarConta(ContaCorrenteRequest conta) {
        int random = new Random().nextInt(0, 99999999);
        Usuario usuarioCadastrado = usuarioService.cadastrar(conta.getUsuario());
        ContaCorrente contaCorrente = ContaCorrente
                .builder()
                .usuario(usuarioCadastrado)
                .agencia("0001")
                .numeroConta(random + "-3")
                .saldo(BigDecimal.TEN)
                .build();
        contaRepository.save(contaCorrente);
        return mapper.map(contaCorrente, ContaCorrenteResponse.class);
    }

    public SaldoResponse buscarSaldo(Long id) {
        ContaCorrente contaCorrente = contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Id não encontrado"));
        return mapper.map(contaCorrente, SaldoResponse.class);
    }
}
