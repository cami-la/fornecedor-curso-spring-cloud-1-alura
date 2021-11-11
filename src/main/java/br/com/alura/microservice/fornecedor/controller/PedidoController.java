package br.com.alura.microservice.fornecedor.controller;

import br.com.alura.microservice.fornecedor.controller.dto.ItemDoPedidoDto;
import br.com.alura.microservice.fornecedor.model.Pedido;
import br.com.alura.microservice.fornecedor.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<Pedido> realizaPedido(@RequestBody List<ItemDoPedidoDto> itensEQuantidadeEscolhidoPeloCliente) {
        return pedidoService.realizaPedido(itensEQuantidadeEscolhidoPeloCliente);
    }

    /*@GetMapping(value = "/{idDoPedido}")
    public Pedido getPedidoPorId(@PathVariable Long idDoPedido) {
        return pedidoService.getPedidoPorId(idDoPedido);
    }*/

}
