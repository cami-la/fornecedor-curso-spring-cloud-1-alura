package br.com.alura.microservice.fornecedor.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tempoDePreparo;

    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    @OneToMany @JoinColumn(name = "pedidoId")
    private List<PedidoItem> itensDoPedido;

    public Pedido() {
    }

    public Pedido(List<PedidoItem> itensDoPedido) {
        this.status = PedidoStatus.RECIBIDO;
        this.itensDoPedido = itensDoPedido;
    }
}
