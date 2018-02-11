package com.example.standaloneactivemq.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping("/rest/publish")
public class Producer {

    private Queue queue;
    private JmsTemplate jmsTemplate;

    @Autowired
    public Producer(Queue queue, JmsTemplate jmsTemplate) {
        this.queue = queue;
        this.jmsTemplate = jmsTemplate;
    }


    @GetMapping("/{message}")
    public String publish(@PathVariable("message") final String message) {

        jmsTemplate.convertAndSend(queue, message);
        return "Published Successfully";

    }
}
