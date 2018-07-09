package com.capitalone.squadron.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.capitalone.squadron.chatbot")
public class SquadronChatBotClient {
    public static void main(String[] args)
    {
        SpringApplication.run(SquadronChatBotClient.class, args);
    }
}