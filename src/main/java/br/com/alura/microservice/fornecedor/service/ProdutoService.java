package br.com.alura.microservice.fornecedor.service;

import br.com.alura.microservice.fornecedor.controller.dto.AtualizaProdutoDto;
import br.com.alura.microservice.fornecedor.model.Produto;
import br.com.alura.microservice.fornecedor.repository.ProdutoRepository;
import br.com.alura.microservice.fornecedor.controller.dto.ProdutoDto;
import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDto> getProdutosPorEstado(String estado) {
        List<Produto> byEstado = produtoRepository.findByEstado(estado);
        return ProdutoDto.convert(byEstado);
    }

    public ResponseEntity<List<ProdutoDto>> getProdutosAll() {
        List<Produto> allProdutos = produtoRepository.findAll();
        return ResponseEntity.ok(ProdutoDto.convert(allProdutos));
    }

    public ResponseEntity<ProdutoDto> saveProduto(ProdutoDto produtoDto) {
        Produto produto = new Produto(produtoDto);
        produtoRepository.save(produto);

        return ResponseEntity.ok(produtoDto);
    }

    public ResponseEntity<Produto> updateProduto(Long id, AtualizaProdutoDto atualizaProdutoDto) {
        Optional<Produto> byIdProduto = produtoRepository.findById(id);

        if (byIdProduto.isPresent()) {
            Produto updateProduto = atualizaProdutoDto.atualizar(id, produtoRepository);
            produtoRepository.save(updateProduto);
            return ResponseEntity.ok(updateProduto);
        }

        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<?> deleteProduto(Long id) {
        Optional<Produto> byIdProduto = produtoRepository.findById(id);

        if (byIdProduto.isPresent()) {
            produtoRepository.delete(byIdProduto.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
