package com.github.gustavodslara.esc_service.domain.repository;
import com.github.gustavodslara.esc_service.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "users") // Expose as REST endpoint
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}