package com.example.discordbot.vk.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Posts {
    @Value("${vk.community.id}") private int communityId;
    @Value("${vk.community.count}") private int count;

    private VkApiClient vk;
    private UserActor userActor;

    @Autowired
    Posts(VkApiClient vkApiClient, UserActor userActor) {
        this.vk = vkApiClient;
        this.userActor = userActor;
    }

    public GetResponse getPosts() throws ClientException, ApiException {
        GetResponse getResponse = this.vk.wall().get(this.userActor)
                .ownerId(communityId)
                .count(count)
                .execute();
        return getResponse;
    }
}
