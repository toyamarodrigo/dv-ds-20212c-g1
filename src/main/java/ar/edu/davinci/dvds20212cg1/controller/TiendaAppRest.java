package ar.edu.davinci.dvds20212cg1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/tienda/api")
public abstract class TiendaAppRest extends TiendaApp {
	
}
