package com.github.gustavodslara.esc_service.services;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestService {

    public void someMethod(Date fireTime, String name)
    {
        System.out.println("MyService.someMethod called at " + fireTime + " with name: " + name);
    }
}
