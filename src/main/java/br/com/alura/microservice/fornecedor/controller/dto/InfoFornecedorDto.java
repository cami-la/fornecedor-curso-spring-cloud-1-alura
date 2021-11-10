package br.com.alura.microservice.fornecedor.controller.dto;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;

import java.util.List;
import java.util.stream.Collectors;

public class InfoFornecedorDto {
    private Long id;

    private String nome;

    private String estado;

    private String endereco;

    public InfoFornecedorDto(InfoFornecedor infoFornecedor) {
        this.id = infoFornecedor.getId();
        this.nome = infoFornecedor.getNome();
        this.estado = infoFornecedor.getEstado();
        this.endereco = infoFornecedor.getEndereco();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public static List<InfoFornecedorDto> convert(List<InfoFornecedor> infoFornecedores) {
        return infoFornecedores.stream().map(infoFornecedor -> new InfoFornecedorDto(infoFornecedor)).collect(Collectors.toList());
    }
}
