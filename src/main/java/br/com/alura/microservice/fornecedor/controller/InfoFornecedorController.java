package br.com.alura.microservice.fornecedor.controller;

import br.com.alura.microservice.fornecedor.controller.dto.InfoFornecedorDto;
import br.com.alura.microservice.fornecedor.service.InfoFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InfoFornecedorController {
    @Autowired
    private InfoFornecedorService infoService;

    @GetMapping("/{estado}")
    public List<InfoFornecedorDto> getInfoPorEstado(@PathVariable String estado) {
        return infoService.getInfoPorEstado(estado);
    }
}
