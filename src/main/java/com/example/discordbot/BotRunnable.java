package com.example.discordbot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;
import java.util.List;

@Component
public class BotRunnable implements Runnable {
    @Value("${discord.token}") private String token;
    @Value("${discord.playing.activity}") private String activity;

    private List<ListenerAdapter> listeners;
    @Autowired
    public BotRunnable(List<ListenerAdapter> listeners) {
        this.listeners = listeners;
    }

    @Override
    @PostConstruct
    public void run() {
        try {
            JDABuilder jda = JDABuilder.createDefault(this.token)
                    .setActivity(Activity.playing(this.activity))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT);

            this.listeners.stream().forEach((listener) -> {
                jda.addEventListeners(listener);
            });

            jda.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
