package com.example.discordbot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;



@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource("classpath:appProperties/config.properties")
public class DiscordBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscordBotApplication.class, args);
    }

    @Bean
    public VkApiClient vkApiClient() {
        return new VkApiClient(new HttpTransportClient());
    }

    @Bean
    public UserActor userActor(@Value("${vk.token}") String vkToken,
                               @Value("${vk.app.id}") int vkAppId) {
        return new UserActor(vkAppId,
                    vkToken);
    }
}
