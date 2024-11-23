package com.github.gustavodslara.esc_service.adapter.inbound;

import com.github.gustavodslara.esc_service.application.port.GerarPdfUseCase;
import com.github.gustavodslara.esc_service.domain.entity.Os;
import com.github.gustavodslara.esc_service.domain.entity.TermoRecisao;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerarPdf2")
@CrossOrigin(origins ="*")
public class GerarPdfResource2 {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body,
                                byte[] pdfBytes,
                                String pdfFileName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
        true);

        helper.setFrom("gustavodslara@gmail.com");
        // Replace with your email
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);

        helper.addAttachment(pdfFileName, new ByteArrayResource(pdfBytes));

        mailSender.send(message);
        System.out.println("Mail with PDF attachment sent...");

    }

    @Autowired
    GerarPdfUseCase gerarPdfUseCase;

    @PostMapping("/os2")
    public ResponseEntity<byte[]> generateOsPdf(@RequestBody TermoRecisao os) throws MessagingException {
        byte[] pdfContent = gerarPdfUseCase.gerarPdfTr(os);
        return createPdfResponse(pdfContent);
    }

    @PostMapping("/os2/email")
    public ResponseEntity<byte[]> generateOsPdfEmail(@RequestBody TermoRecisao os) throws MessagingException {
        byte[] pdfContent = gerarPdfUseCase.gerarPdfTr(os);
        sendSimpleEmail("yurishimizufrutuoso@gmail.com","Relatório Os", "Relatório PDF em anexo", pdfContent,"relatorio_os");
        return createPdfResponse(pdfContent);
    }

    private ResponseEntity<byte[]> createPdfResponse(byte[] pdfContent) {
        HttpHeaders headers = new HttpHeaders();
        String headerValue = "attachment; filename=documento_tr.pdf";
        headers.add("Content-Disposition", headerValue);
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }

}
