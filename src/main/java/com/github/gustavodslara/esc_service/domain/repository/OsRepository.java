package com.github.gustavodslara.esc_service.domain.repository;

import com.github.gustavodslara.esc_service.domain.entity.Os;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestResource(path = "os")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public interface OsRepository extends CrudRepository<Os, Long> {
}
