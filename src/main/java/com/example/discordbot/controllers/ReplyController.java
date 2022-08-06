package com.example.discordbot.controllers;

import com.example.discordbot.HTTPUrlConnectionHandler;
import com.example.discordbot.vk.services.Posts;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ReplyController {
    @Value("${meme.url}") private String urlName;

    private Posts posts;
    private Random random;
    private Pattern pattern;

    @Autowired
    ReplyController(Posts posts) {
       this.posts = posts;
       this.random = new Random();
       this.pattern = Pattern.compile("size=[5-6][0-9]{2}");
    }

    public void sendMeme(@NotNull MessageChannel messageChannel) {
        try {
            String urlPost = HTTPUrlConnectionHandler
                    .sendGet(new URL(urlName));

            messageChannel.sendMessage(urlPost)
                    .queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendVKMeme(@NotNull MessageChannel messageChannel)
            throws ClientException, ApiException, IOException {

        GetResponse getResponse = this.posts.getPosts();

        Map<String,Object> result =
                new ObjectMapper().readValue(getResponse.toString(), HashMap.class);

        JSONArray urlList = JsonPath.read(new JSONObject(result), "$..url");

        Matcher matcher;

        for (Iterator<Object> iterator = urlList.iterator(); iterator.hasNext();) {
            Object obj = iterator.next();
            matcher = this.pattern.matcher(obj.toString());
            if (!matcher.find()){
                iterator.remove();
            }
        }
        messageChannel.sendMessage(
                (CharSequence) urlList.get(this.random.nextInt(urlList.size())))
                .queue();
    }
}
