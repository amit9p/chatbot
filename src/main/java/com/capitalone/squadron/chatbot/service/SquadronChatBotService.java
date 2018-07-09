package com.capitalone.squadron.chatbot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

@Service
public class SquadronChatBotService {

    private static final boolean TRACE_MODE = false;
    static String botName = "super";

    public String getMessage(String name) {
        return "Hello " + name;
    }

    public String getResponseFromBot(String question) throws Exception {
        String response = "";
        String resourcesPath = getResourcesPath();
        System.out.println(resourcesPath);
        MagicBooleans.trace_mode = TRACE_MODE;
        Bot bot = new Bot("super", resourcesPath);
        Chat chatSession = new Chat(bot);
        bot.brain.nodeStats();
        String textLine = question;

        if ((textLine == null) || (textLine.length() < 1))
            textLine = MagicStrings.null_input;
        if (textLine.equals("q")) {
            System.exit(0);
        } else if (textLine.equals("wq")) {
            bot.writeQuit();
            System.exit(0);
        } else {
            String request = textLine;
            if (MagicBooleans.trace_mode)
                System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.thatHistory.get(0)).get(0) + ":TOPIC=" + chatSession.predicates.get("topic"));
            response = chatSession.multisentenceRespond(request);
            while (response.contains("&lt;")) {
                response = response.replace("&lt;", "<");
            }
            while (response.contains("&gt;")) {
                response = response.replace("&gt;", ">");
            }
            // System.out.println("Robot : " + response);


        }
        return response;
    }

        public static String getResourcesPath() {
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            path = path.substring(0, path.length() - 2);
            System.out.println(path);
            String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
            return resourcesPath;
        }

    }
