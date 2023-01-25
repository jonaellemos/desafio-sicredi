package com.br.desafio.repository;

import com.br.desafio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("select u from USUARIO u where u.cpf = :usuarioCpf")
    Usuario findByUsuarioCpf(@Param("usuarioCpf") String cpfUsuario);
}
