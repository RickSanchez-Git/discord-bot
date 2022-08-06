package com.example.discordbot.listeners;

import com.example.discordbot.controllers.ReplyController;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageListener extends ListenerAdapter {
    private ReplyController messageHandler;

    @Autowired
    public MessageListener(ReplyController messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event)
    {
        String msg = event
                .getMessage()
                .getContentRaw();
        switch (msg) {
            case "!meme":
                messageHandler.sendMeme(event.getChannel());
                break;
            case "!pirate":
                try {
                    messageHandler.sendVKMeme(event.getChannel());
                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
