package br.com.alura.microservice.fornecedor.controller.dto;

import br.com.alura.microservice.fornecedor.model.Produto;
import br.com.alura.microservice.fornecedor.repository.ProdutoRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Optional;

public class AtualizaProdutoDto {
    private String nome;

    private String estado;

    private String descricao;

    private BigDecimal preco;

    public AtualizaProdutoDto(String nome, String estado, String descricao, BigDecimal preco) {
        this.nome = nome;
        this.estado = estado;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.findById(id).get();

        produto.setNome(this.nome);
        produto.setEstado(this.estado);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);

        return produto;

    }
}
