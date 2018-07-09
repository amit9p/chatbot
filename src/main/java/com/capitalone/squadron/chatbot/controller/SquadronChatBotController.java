package com.capitalone.squadron.chatbot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.capitalone.squadron.chatbot.service.SquadronChatBotService;

@RestController
public class SquadronChatBotController {

    @Autowired
    private SquadronChatBotService squadronChatBotService;

    @RequestMapping("/")
    public String welcome() {
        return "Welcome to Squadron Chat Bot";
    }

    @RequestMapping(value="/hello/{name}", method={RequestMethod.POST,RequestMethod.GET})
    public String myData(@PathVariable("name") String name) {
        return squadronChatBotService.getMessage(name);
    }


    @RequestMapping(value="/chatbotResponse/{question}", method={RequestMethod.GET})
    public String getResponseFromBot(@PathVariable("question") String question) throws Exception{
        return squadronChatBotService.getResponseFromBot(question);
    }


}