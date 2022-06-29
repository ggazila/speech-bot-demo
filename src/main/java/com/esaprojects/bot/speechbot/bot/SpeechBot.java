package com.esaprojects.bot.speechbot.bot;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechBot extends SpringWebhookBot {
    private String botUsername;
    private String botToken;
    private String botPath;

    public SpeechBot(SetWebhook setWebhook) {
        super(setWebhook);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return new SendMessage();
    }

}
