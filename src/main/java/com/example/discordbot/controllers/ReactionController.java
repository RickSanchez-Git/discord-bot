package com.example.discordbot.controllers;


import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ReactionController {
    public void addReaction(@NotNull MessageChannel messageChannel,
                            @NotNull  Emoji emoji,
                            @NotNull  String messageId) {
        messageChannel.addReactionById(messageId, emoji)
                .queue();
    }

    public void removeReaction(@NotNull MessageChannel messageChannel,
                               @NotNull  Emoji emoji,
                               @NotNull  String messageId) {
        messageChannel.removeReactionById(messageId, emoji).queue();
    }
}
