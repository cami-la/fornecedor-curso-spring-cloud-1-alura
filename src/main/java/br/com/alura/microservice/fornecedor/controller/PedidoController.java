package br.com.alura.microservice.fornecedor.controller;

import br.com.alura.microservice.fornecedor.model.Pedido;
import br.com.alura.microservice.fornecedor.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    public Pedido realizaPedido(@RequestBody List<ItemDoPedidoDto> produtos) {
        return pedidoService.realizaPedido(produtos);
    }

}
