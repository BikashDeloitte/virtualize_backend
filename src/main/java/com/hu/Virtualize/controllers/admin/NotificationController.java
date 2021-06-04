package com.hu.Virtualize.controllers.admin;

import com.hu.Virtualize.commands.admin.Greeting;
import com.hu.Virtualize.commands.admin.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Slf4j
@RestController
public class NotificationController {

    @MessageMapping("/notification")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        log.info(message.toString());

        Thread.sleep(1000); // simulated delay
        return new Greeting("Add new Product: " +  message.getName());
    }
}
