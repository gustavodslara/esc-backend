package com.github.gustavodslara.esc_service.adapter.inbound;

import com.github.gustavodslara.esc_service.application.port.GerarPdfUseCase;
import com.github.gustavodslara.esc_service.domain.entity.MatrizResponsabilidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerarPdf3")
@CrossOrigin(origins ="*")
public class GerarPdfResource3 {

    @Autowired
    GerarPdfUseCase gerarPdfUseCase;

    @PostMapping("/os3")
    public ResponseEntity<byte[]> generateOsPdf(@RequestBody MatrizResponsabilidade mr) {
        byte[] pdfContent = gerarPdfUseCase.gerarPdfMr(mr);
        return createPdfResponse(pdfContent);
    }

    private ResponseEntity<byte[]> createPdfResponse(byte[] pdfContent) {
        HttpHeaders headers = new HttpHeaders();
        String headerValue = "attachment; filename=documento_mr.pdf";
        headers.add("Content-Disposition", headerValue);
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }

}
