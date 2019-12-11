package com.eventapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventapp.models.Convidado;
import com.eventapp.models.Evento;
import com.eventapp.repository.ConvidadoRepository;
import com.eventapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;
	
	@Autowired
        private ConvidadoRepository cr;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String formulario() {

		return "evento/formEvento";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String formulario(Evento evento) {

		er.saveAndFlush(evento);
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
	    ModelAndView mv = new ModelAndView("index");
	    Iterable<Evento> eventos = er.findAll();
	    mv.addObject("eventos", eventos);
	    return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
	    Evento evento = er.findByCodigo(codigo);
	    ModelAndView mv = new ModelAndView("evento/detalhesEvento");
	    mv.addObject("evento", evento);
	    Iterable<Convidado> convidados = cr.findByEvento(evento);
	    mv.addObject("convidados", convidados);
	    return mv;
	    
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
        public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado) {
            Evento evento = er.findByCodigo(codigo); 
            convidado.setEvento(evento);
            cr.save(convidado);
            
            return "redirect:/{codigo}";
	}
	
	
}
