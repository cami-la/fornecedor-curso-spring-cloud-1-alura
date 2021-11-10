package br.com.alura.microservice.fornecedor.service;

import br.com.alura.microservice.fornecedor.controller.dto.InfoFornecedorDto;
import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.repository.InfoFornecedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InfoFornecedorService {

    private InfoFornecedorRepository infoRepository;

    public List<InfoFornecedorDto> getInfoPorEstado(String estado) {
        List<InfoFornecedor> byEstado = infoRepository.findByEstado(estado);
        return InfoFornecedorDto.convert(byEstado);


    }
}
