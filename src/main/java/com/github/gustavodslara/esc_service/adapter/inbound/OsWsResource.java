package com.github.gustavodslara.esc_service.adapter.inbound;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class OsWsResource {

    @MessageMapping("/nova-os")
    @SendTo("/topic/os-updates")
    public String novaOsAdicionada(String message) {


        return
                "Nova OS Adicionada:" + message;
    }
}
