package com.capitalone.squadron.chatbot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.capitalone.squadron.chatbot.service.SquadronChatBotService;


import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SquadronChatBotController {

    @Autowired
    private SquadronChatBotService squadronChatBotService;

    @RequestMapping("/")
    public String welcome() {
        return "Welcome to Squadron Chat Bot";
    }

    @RequestMapping(value = "/hello/{name}", method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public String myData(@PathVariable("name") String name) {
        return squadronChatBotService.getMessage(name);
    }


    @RequestMapping(value = "/chatbotResponse/{question}", method = {RequestMethod.GET})
    public String getResponseFromBot(@PathVariable("question") String question) throws Exception {

        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("bot_reply", squadronChatBotService.getResponseFromBot(question));
        JsonArrayBuilder plnArrBld = Json.createArrayBuilder();
        JsonArray arr = plnArrBld.build();
        JsonObject empObj = jsonBuilder.build();
        StringWriter strWtr = new StringWriter();
        JsonWriter jsonWtr = Json.createWriter(strWtr);
        jsonWtr.writeObject(empObj);
        jsonWtr.close();
        return "["+strWtr.toString()+"]";



    }


}
