# discord-bot

Simple discord bot using JDA and VKApi

Implements functional to send memes from reddit and posts from VK

## Configuration

To run bot you have to create file **config.properties** along the path **/src/main/resources/appProperties** using this pattern:
```
discord.token            = TOKEN_FROM_JDA (https://discord.com/developers/applications/)
discord.playing.activity = ACTIVITY (is playing...)
vk.community.id          = VK_COMMUNITY_ID
vk.community.count       = POSTS_COUNT
vk.token                 = VKAPI_TOKEN
vk.app.id                = VKAPI_APP_ID
meme.url                 = URL_MEMES (default url for reddit: https://meme-api.herokuapp.com/gimme)
```
