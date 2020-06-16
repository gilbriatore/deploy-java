package br.edu.up.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.up.domain.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
}
