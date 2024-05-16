package com.danielnunesro.authapi.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.danielnunesro.authapi.dto.AuthDto;

public interface AutenticacaoService extends UserDetailsService {
	
	public String obterToken(AuthDto authDto);
	
	public String validaTokenJwt(String token);
	
}
