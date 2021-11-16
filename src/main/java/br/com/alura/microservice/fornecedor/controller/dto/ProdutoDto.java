package br.com.alura.microservice.fornecedor.controller.dto;

import br.com.alura.microservice.fornecedor.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {
    private Long id;

    private String nome;

    private String estado;

    private String descricao;

    private BigDecimal preco;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.estado = produto.getEstado();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static List<ProdutoDto> convert (List<Produto> produtos) {
        return produtos.stream().map(produto -> new ProdutoDto(produto)).collect(Collectors.toList());
    }

}
