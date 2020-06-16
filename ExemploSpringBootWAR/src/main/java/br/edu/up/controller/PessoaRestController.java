package br.edu.up.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.up.domain.Pessoa;
import br.edu.up.repository.PessoaRepository;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaRestController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public Iterable<Pessoa> index() {
		return pessoaRepository.findAll();
	}
	
	@PostMapping("/incluir")
	public Pessoa incluir(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@GetMapping("/buscar/{id}")
	public Optional<Pessoa> buscar(@PathVariable Long id) {
		return pessoaRepository.findById(id);
	}

	@DeleteMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
		return "Registro " + id + " excluído com sucesso!";
	}
}