package com.danielnunesro.authapi.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.danielnunesro.authapi.models.Usuario;
import com.danielnunesro.authapi.repositories.UsuarioRepository;
import com.danielnunesro.authapi.services.AutenticacaoService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = extraiTokenHeader(request);
		
		if(token != null) {
			String login = autenticacaoService.validaTokenJwt(token);
			Usuario usuario = usuarioRepository.findByLogin(login);
			
			var autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(autentication);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	public String extraiTokenHeader(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		
		if(authHeader == null) {
			return null;
		}
		
		if (!authHeader.split(" ")[0].equals("Bearer")) {
			return null;
		}
		
		return authHeader.split(" ")[1];
		
	}
	
	
	
}
