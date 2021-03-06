package br.com.alg.oficinaspringframework.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.alg.oficinaspringframework.model.TipoVinho;
import br.com.alg.oficinaspringframework.model.Vinho;
import br.com.alg.oficinaspringframework.repository.VinhosRepository;

@Controller
@RequestMapping(value="/vinhos")
public class VinhosController {

	@Autowired
	private VinhosRepository repository;
	
	
	@GetMapping()
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("vinhos/lista-vinhos");
		modelAndView.addObject("vinhos", this.repository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView modelAndView = new ModelAndView("vinhos/cadastro-vinho");
		
		modelAndView.addObject(vinho);
		modelAndView.addObject("tipos", TipoVinho.values());
		
		return modelAndView; 
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result,
			RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(vinho);
		}
		
		this.repository.save(vinho);
		
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso!");
		
		return new ModelAndView("redirect:/vinhos/novo");
	}
	
	@GetMapping(value="/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		return novo(repository.findOne(id));
	}
	
}
