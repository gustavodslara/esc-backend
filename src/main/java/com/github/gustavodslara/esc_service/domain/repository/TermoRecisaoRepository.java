package com.github.gustavodslara.esc_service.domain.repository;

import com.github.gustavodslara.esc_service.domain.entity.TermoRecisao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestResource(path = "tr")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public interface TermoRecisaoRepository extends CrudRepository<TermoRecisao, Long> {
}
