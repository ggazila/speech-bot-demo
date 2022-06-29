package com.esaprojects.bot.speechbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.esaprojects.bot.speechbot.bot.SpeechBot;
import com.esaprojects.bot.speechbot.props.BotProps;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Configuration
@RequiredArgsConstructor
public class BotConfig {
    private final BotProps botProps;

    @Bean
    public SetWebhook setWebhook() {
        return SetWebhook.builder().url(botProps.getWebhook()).build();
    }

    @Bean
    @SneakyThrows
    public SpeechBot speechBot(SetWebhook setWebhook) {
        SpeechBot speechBot = new SpeechBot(setWebhook);
        speechBot.setBotToken(botProps.getToken());
        speechBot.setBotPath("update");
        speechBot.setBotUsername(botProps.getName());
        speechBot.setWebhook(setWebhook);

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(speechBot, setWebhook);

        return speechBot;
    }
}
