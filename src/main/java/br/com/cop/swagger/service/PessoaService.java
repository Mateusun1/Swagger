package br.com.cop.swagger.service;

import br.com.cop.swagger.dto.PessoaRequest;
import br.com.cop.swagger.model.Pessoa;
import br.com.cop.swagger.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    private final ModelMapper modelMapper;
    private final PessoaRepository pessoaRepository;
    @Autowired
    public PessoaService(ModelMapper modelMapper, PessoaRepository pessoaRepository) {
        this.modelMapper = modelMapper;
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa cadastrar(PessoaRequest pessoaRequest) {
        Pessoa pessoaMap = modelMapper.map(pessoaRequest, Pessoa.class);
        pessoaRepository.save(pessoaMap);
        return pessoaMap;
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }
}
