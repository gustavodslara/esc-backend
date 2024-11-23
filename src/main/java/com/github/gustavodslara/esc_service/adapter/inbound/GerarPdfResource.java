package com.github.gustavodslara.esc_service.adapter.inbound;

import com.github.gustavodslara.esc_service.application.port.GerarPdfUseCase;
import com.github.gustavodslara.esc_service.domain.entity.Curso;
import com.github.gustavodslara.esc_service.domain.entity.Os;
import com.github.gustavodslara.esc_service.domain.entity.TermoRecisao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/gerarPdf")
@CrossOrigin("*")
public class GerarPdfResource {

    @Autowired
    GerarPdfUseCase gerarPdfUseCase;

    @PostMapping("/os")
    public ResponseEntity<byte[]> generateOsPdf(@RequestBody Os os) {
        byte[] pdfContent = gerarPdfUseCase.gerarPdfOs(os);
        return createPdfResponse(pdfContent);
    }

    @PostMapping("/tr")
    public ResponseEntity<byte[]> generateTrPdf(@RequestBody TermoRecisao tr) {
        byte[] pdfContent = gerarPdfUseCase.gerarPdfTr(tr);
        return createPdfResponse(pdfContent);
    }

    private ResponseEntity<byte[]> createPdfResponse(byte[] pdfContent) {
        HttpHeaders headers = new HttpHeaders();
        String headerValue = "attachment; filename=documento_os.pdf";
        headers.add("Content-Disposition", headerValue);
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }

}
