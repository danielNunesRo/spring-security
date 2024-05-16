package com.danielnunesro.authapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielnunesro.authapi.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);

}
