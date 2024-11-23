package com.github.gustavodslara.esc_service.application.port;

import com.github.gustavodslara.esc_service.domain.entity.MatrizResponsabilidade;
import com.github.gustavodslara.esc_service.domain.entity.Os;
import com.github.gustavodslara.esc_service.domain.entity.TermoRecisao;

public interface GerarPdfUseCase {
    public byte[] gerarPdfOs(Os os);
    public byte[] gerarPdfTr(TermoRecisao tr);
    public byte[] gerarPdfMr(MatrizResponsabilidade mr);
}
