package com.github.gustavodslara.esc_service.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String unidadeSolicitante;
    public String tituloEvento;

    public LocalDate dataInicio;
    public LocalTime horaInicio;

    public LocalDate dataFim;
    public LocalTime horaFim;

    @Enumerated(EnumType.STRING)
    public Modalidade modalidade;
}
