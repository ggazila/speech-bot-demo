package com.esaprojects.bot.speechbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface MessageCreateService {

    SendMessage createMessage(String chatId, String text, String parseMode);
}
