package com.danielnunesro.authapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielnunesro.authapi.dto.UsuarioDto;
import com.danielnunesro.authapi.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto) {
		return usuarioService.salvar(usuarioDto);
	}
	
}
