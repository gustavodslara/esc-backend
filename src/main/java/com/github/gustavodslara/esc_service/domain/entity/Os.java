package com.github.gustavodslara.esc_service.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Os extends Curso{


    public Integer quantidadeParticipantes;

    public Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    public Demanda demanda;

    public String coordenadorGeral;

    public String coordenadorApoioInstitucional;

    public String coordenadorAcaoCapacitacao;
    @ElementCollection
    public List<String> coordenacaoApoioAcao;
    @ElementCollection
    public List<String>  coordenacaoApoioOperacional;

    @ElementCollection
    public List<String> equipeTecnica;

    public boolean equipeJuridica;
    public boolean equipeEdu;

    public String observacao;
    public String osElaboradaPor;
    public String local;
    public String publicoAlvo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    public Long id;
    public Long idTr;

    @ElementCollection
    public List<String> datas;
    @ElementCollection
    public List<String> horas;
    @ElementCollection
    public List<String> horasfim;
}
