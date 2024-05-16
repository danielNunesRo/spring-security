package com.danielnunesro.authapi.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.danielnunesro.authapi.dto.UsuarioDto;
import com.danielnunesro.authapi.models.Usuario;
import com.danielnunesro.authapi.repositories.UsuarioRepository;
import com.danielnunesro.authapi.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UsuarioDto salvar(UsuarioDto usuarioDto) {
		
		Usuario usuarioExisting = usuarioRepository.findByLogin(usuarioDto.login());
		
		if(usuarioExisting != null) {
			throw new RuntimeException("Usuario já existe!");
		}
		
		var passwordHash = passwordEncoder.encode(usuarioDto.senha());
		
		Usuario entity = new Usuario(usuarioDto.nome(), usuarioDto.login(), passwordHash, usuarioDto.roleEnum());
		Usuario novoUsuario = usuarioRepository.save(entity);
		return new UsuarioDto(novoUsuario.getNome(), novoUsuario.getLogin(), novoUsuario.getSenha(), novoUsuario.getRole());
	}
	
	
	
}
