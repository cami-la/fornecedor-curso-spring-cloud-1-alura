package br.com.alura.microservice.fornecedor.controller.dto;

public class ItemDoPedidoDto {
    private Long id;

    private int quantidade;

    public ItemDoPedidoDto() {
    }

    public ItemDoPedidoDto(Long id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
