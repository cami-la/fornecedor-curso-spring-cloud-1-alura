package br.com.alura.microservice.fornecedor.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

public enum PedidoStatus {
    RECIBIDO, PRONTO, ENVIADO;
}
