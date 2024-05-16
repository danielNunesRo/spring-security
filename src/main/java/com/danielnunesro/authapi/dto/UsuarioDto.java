package com.danielnunesro.authapi.dto;

import com.danielnunesro.authapi.enums.RoleEnum;

public record UsuarioDto(
		String nome,
		String login,
		String senha,
		RoleEnum roleEnum) {

}
