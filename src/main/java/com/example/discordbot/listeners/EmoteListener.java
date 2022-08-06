package com.example.discordbot.listeners;

import com.example.discordbot.controllers.ReactionController;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmoteListener extends ListenerAdapter {
    private ReactionController reactionController;

    @Autowired
    EmoteListener(ReactionController reactionController) {
        this.reactionController = reactionController;
    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        reactionController.addReaction(event.getChannel(),
                event.getEmoji(),
                event.getMessageId());
    }

    @Override
    public void onMessageReactionRemove(@NotNull MessageReactionRemoveEvent event) {
        reactionController.removeReaction(event.getChannel(),
                event.getEmoji(),
                event.getMessageId());
    }
}
