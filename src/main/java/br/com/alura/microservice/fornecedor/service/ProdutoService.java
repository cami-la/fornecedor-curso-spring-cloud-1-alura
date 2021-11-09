package br.com.alura.microservice.fornecedor.service;

import br.com.alura.microservice.fornecedor.model.Produto;
import br.com.alura.microservice.fornecedor.repository.ProdutoRepository;
import br.com.alura.microservice.fornecedor.controller.dto.ProdutoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDto> getProdutosPorEstado(String estado) {
        List<Produto> byEstado = produtoRepository.findByEstado(estado);
        return ProdutoDto.convert(byEstado);
    }


}
