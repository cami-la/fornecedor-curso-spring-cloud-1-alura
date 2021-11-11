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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedidoId")
    private List<PedidoItem> itensDoPedido;

    public Pedido() {
        this.status = PedidoStatus.RECIBIDO;
    }

    public Pedido(List<PedidoItem> itensDoPedido) {
        this.status = PedidoStatus.RECIBIDO;
        this.itensDoPedido = itensDoPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public void setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public List<PedidoItem> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<PedidoItem> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }
}
