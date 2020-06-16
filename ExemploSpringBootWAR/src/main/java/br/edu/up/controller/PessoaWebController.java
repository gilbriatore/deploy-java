package br.edu.up.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.up.domain.Pessoa;
import br.edu.up.repository.PessoaRepository;

@Controller
public class PessoaWebController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping
	public String home() {
		return "index";
	}

	@GetMapping("/contato")
	public String contato() {
		return "contato";
	}

	@GetMapping("/api")
	public String api() {
		return "api";
	}
	
	@GetMapping("/sobre")
	public String sobre() {
		return "sobre";
	}

	@GetMapping("/incluir")
	public String incluir(Model model) {
		model.addAttribute("pessoa", new Pessoa());
		return "cadastrar";
	}
	
	@GetMapping("/editar")
	public String editar(@RequestParam Long id, Model model) {
		Pessoa pessoa = pessoaRepository.findById(id).get();
		model.addAttribute("pessoa", pessoa);
		return "editar";
	}
	
	@GetMapping("/cadastro")
	public String cadastrar(Model model) {
		model.addAttribute("pessoas", pessoaRepository.findAll());
		return "listar";
	}

	@GetMapping("/buscar")
	public String buscar(@RequestParam Long id, Model model) {
		model.addAttribute("pessoa", pessoaRepository.findById(id));
		return "listar";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Pessoa pessoa, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "cadastrar";
		}

		pessoaRepository.save(pessoa);
		model.addAttribute("pessoas", pessoaRepository.findAll());
		return "listar";
	}

	@PostMapping("/atualizar")
	public String atualizar(@RequestParam Long id, @Valid Pessoa pessoa, BindingResult result, Model model) {

		if (result.hasErrors()) {
			pessoa.setId(id);
			return "editar";
		}
		pessoa.setId(id);
		pessoaRepository.save(pessoa);
		model.addAttribute("pessoas", pessoaRepository.findAll());
		return "listar";
	}

	@GetMapping("/excluir")
	public String excluir(@RequestParam Long id, Model model) {
		Pessoa pessoa = pessoaRepository.findById(id).get();
		if (pessoa != null) {
			pessoaRepository.delete(pessoa);
		}
		model.addAttribute("pessoas", pessoaRepository.findAll());
		return "listar";
	}
}
