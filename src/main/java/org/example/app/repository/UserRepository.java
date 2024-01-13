package org.example.app.repository;

import org.example.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    // Ejemplo: Buscar usuarios por nombre de usuario
    List<User> findByUsername(String username);

    // Ejemplo: Buscar usuarios por dirección de correo electrónico
    Optional<User> findByEmail(String email);

    // Ejemplo: Obtener todos los usuarios ordenados por nombre de usuario
    List<User> findAllByOrderByUsername();

    // Ejemplo: Contar la cantidad de usuarios activos
    long countByActiveTrue();

    // Ejemplo: Buscar usuarios por nombre de usuario que contenga la cadena proporcionada (ignore case)
    List<User> findByUsernameContainingIgnoreCase(String keyword);
}
