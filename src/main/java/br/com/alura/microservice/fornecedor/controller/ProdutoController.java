package br.com.alura.microservice.fornecedor.controller;

import br.com.alura.microservice.fornecedor.service.ProdutoService;
import br.com.alura.microservice.fornecedor.controller.dto.ProdutoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{estado}")
    public List<ProdutoDto> getProdutosPorEstados(@PathVariable String estado) {
        return produtoService.getProdutosPorEstado(estado);
    }
}
