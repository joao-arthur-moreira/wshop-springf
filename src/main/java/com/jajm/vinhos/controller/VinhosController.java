package com.jajm.vinhos.controller;

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

import com.jajm.vinhos.model.TipoVinho;
import com.jajm.vinhos.model.Vinho;
import com.jajm.vinhos.repository.Vinhos;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	
	@Autowired
	private Vinhos vinhos;
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		return novo(vinhos.getOne(id));
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView mv = new ModelAndView("vinhos/cadastro-vinho");
		mv.addObject("vinho", vinho);
		mv.addObject("tipos", TipoVinho.values());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(vinho);
		}
		
		vinhos.save(vinho);
		
		attributes.addFlashAttribute("message", "Vinho saldo com sucesso!");
		
		return new ModelAndView("redirect:/vinhos/novo");
	}

}
