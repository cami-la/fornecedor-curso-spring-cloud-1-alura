package br.com.alura.microservice.fornecedor.controller;

import br.com.alura.microservice.fornecedor.controller.dto.AtualizaProdutoDto;
import br.com.alura.microservice.fornecedor.model.Produto;
import br.com.alura.microservice.fornecedor.service.ProdutoService;
import br.com.alura.microservice.fornecedor.controller.dto.ProdutoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getProdutosAll(){
        return produtoService.getProdutosAll();
    }

    @GetMapping("/{estado}")
    public List<ProdutoDto> getProdutosPorEstados(@PathVariable String estado) {
        return produtoService.getProdutosPorEstado(estado);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> saveProduto(@RequestBody ProdutoDto produtoDto){
        return produtoService.saveProduto(produtoDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody AtualizaProdutoDto atualizaProdutoDto) {
        return produtoService.updateProduto(id, atualizaProdutoDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
        return produtoService.deleteProduto(id);
    }


}
