package br.com.alura.microservice.fornecedor.repository;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoFornecedorRepository extends CrudRepository<InfoFornecedor, Long> {

    List<InfoFornecedor> findByEstado(String estado);

}
