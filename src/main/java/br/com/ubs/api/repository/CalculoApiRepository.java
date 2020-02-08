package br.com.ubs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ubs.api.model.Estoque;

@Repository
public interface CalculoApiRepository extends JpaRepository<Estoque, Integer>{


}
