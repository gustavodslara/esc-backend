package com.github.gustavodslara.esc_service.domain.entity;

import jakarta.persistence.*;

@Entity
public class TermoRecisao  extends Curso{
        public boolean enviaEmail;
    public String esc;
    public String categoria;
    public String anexo;
    public String objeto;
    public String justificativa;
    public String modalidadeLicitacao;
    public String investimento;
    public String especificacoes;
    public String ementa;
    public String obrigacoesContratante;
    public String obrigacoesContratada;
    public String sancoes;
    public String dotacaoOrcamentaria;
    public String condicoesPagamento;
    public String documentosRegularidade;
    public String contrato;
    public String acompanhamento;
    public String nomeAssinante;
    public String cargoAssinante;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long idOs;
}
