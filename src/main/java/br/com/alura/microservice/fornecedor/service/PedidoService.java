package br.com.alura.microservice.fornecedor.service;

import br.com.alura.microservice.fornecedor.controller.dto.ItemDoPedidoDto;
import br.com.alura.microservice.fornecedor.controller.dto.PedidoDto;
import br.com.alura.microservice.fornecedor.model.Pedido;
import br.com.alura.microservice.fornecedor.model.PedidoItem;
import br.com.alura.microservice.fornecedor.model.Produto;
import br.com.alura.microservice.fornecedor.repository.PedidoRepository;
import br.com.alura.microservice.fornecedor.repository.ProdutoRepository;
import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Para efetivar um pedido:
 * 1 - Do lado do cliente, ele só passa um List<DTO> contendo: cada ID DO PRODUTO (item) + QUANTIDADE (do item escolhido)
 * 2 - Temos que pegar as informações citadas acima e transformar em um PedidoItem de fato.
 * 3 - Primeiro, criamos um método que faz essa conversão (DTO -> PedidoItem):
 * 3.1 - Criando uma List<Long> idsProdutos; com todos os Ids que o cliente passou no DTO:
 * Usaremos um map extraindo apenas os Ids (item -> item.getId()) e coletando para a lista +
 * 3.2 Criando um repository chamando o método findByIdIn passando a lista de Ids que coletamos no tópico 3.1
 * com isso teremos uma lista de produtos referente aos ids passado List<Produto> produtosDoPedido.
 * 4. Com os produtos em mãos (Lembrando que o cliente passou apenas o ID do produto, por isso, fizemos o tópido 3),
 * agora vamos de fato montar uma lista de PedidosItem, para posteriormente montar o Pedido:
 * 4.1 Pega a List<ItemDoPedidoDto> itens (passado como argumento) e faz um map pegando cada item e fazendo uma comparação:
 * 4.1.1 Neste map, pega a lista dos produtosDoPedido que coletamos no item 3.2, fazemos um filtro comparando o Id de cada produto
 * dessa lista de Produtos com o Id de cada item do map (Id que o cliente passou pelo DTO), pegamos o primeiro item encontrado findFirst() e retorna get()
 * 4.1.2 Agora tendo em mãos cada produto, vamos criar cada PedidoItem e no final, coletar para uma List<PedidoItem>
 * 4.1.2.1 Iniciamos um PedidoItem e passamos por set o produto e a quantidade que o cliente informou no DTO e retornamos o pedidoItem
 * 4.1.2.2 Retornando o pedidoItem e ao final, coletamos para a lista.
 * 5. Finalmente, retornamos pedidosItens que é list<PedidoItem> montado com a quantidade de itens (que foi passado pelo cliente) + o produto em si.
 * ----------------
 * 6. Para realizar o pedido, precisamos da List<PedidosItem> no construtor.
 * 6.1 Fazemos a verificação, se a List<ItemDoPedidoDto> é null,
 * 6.2 Fazemos a conversão através do método que criamos acima: recebemos a List<ItemPedidoDto> e transformamos em List<PedidoItem> para assim, montar o pedido:
 * 6.3 Agora podemos montar o Pedido, passando a List<PedidoItem> no construtor, set tempoDePreparo (que no exemplo, vamos dizer que é o tamanho da lista ItemDoPedidoDto)
 * 6.4 E de fato, vamos colocar o pedido no banco de dados atravez do método save.
 * 6.5 Criaremos o repository (PedidoRepository) e usaremos o método save para inserir no banco de dados esse pedido.
 * */
@Service
public class PedidoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public ResponseEntity<Pedido> realizaPedido(List<ItemDoPedidoDto> itensEQuantidadeEscolhidoPeloCliente) {
        if (itensEQuantidadeEscolhidoPeloCliente == null) return ResponseEntity.notFound().build();

        Pedido pedido = new Pedido();
        pedido.setTempoDePreparo(itensEQuantidadeEscolhidoPeloCliente.size());
        pedido.setItensDoPedido(toDtoPedidoItem(itensEQuantidadeEscolhidoPeloCliente));

        return ResponseEntity.ok(pedidoRepository.save(pedido));

    }

    private List<PedidoItem> toDtoPedidoItem(List<ItemDoPedidoDto> itensEQuantidadeEscolhidoPeloCliente) {
        //pegando os ids dos produtos que o cliente escolheu e colocando em uma lista
        List<Long> idsDosProdutosEscolhidosPeloCliente = itensEQuantidadeEscolhidoPeloCliente.stream()
                .map(id -> id.getId())
                .collect(Collectors.toList());

        //pegando os produtos escolhidos pelo cliente a partir dos ids
        List<Produto> listaDosProdutosEscolhidosPeloCliente = produtoRepository.findByIdIn(idsDosProdutosEscolhidosPeloCliente);

        //pegamos cada itensEQuantidadeEscolhidoPeloCliente e transformaremos em uma lista de PedidoItem
        List<PedidoItem> listaComOPedidoDeCadaItemMontado = itensEQuantidadeEscolhidoPeloCliente.stream()
                .map(itemEscolhidoPeloCliente -> {
                    Produto produto = listaDosProdutosEscolhidosPeloCliente.stream()
                            .filter(p -> p.getId() == itemEscolhidoPeloCliente.getId())
                            .findFirst().get();

                    PedidoItem pedidoItem = new PedidoItem();
                    pedidoItem.setQuantidade(itemEscolhidoPeloCliente.getQuantidade());
                    pedidoItem.setProduto(produto);
                    return pedidoItem;
                }).collect(Collectors.toList());
        return listaComOPedidoDeCadaItemMontado;
    }

    public ResponseEntity<Pedido> getPedidoPorId(Long idDoPedido) {
        return ResponseEntity.ok(pedidoRepository.findById(idDoPedido).get());
    }


}
