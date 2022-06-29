package com.esaprojects.bot.speechbot.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.esaprojects.bot.speechbot.service.MessageCreateService;

@Service
public class MessageCreateServiceImpl implements MessageCreateService {
    @Override
    public SendMessage createMessage(String chatId, String text, String parseMode) {
        return SendMessage.builder()
                .parseMode(parseMode)
                .text(text)
                .chatId(chatId)
                .build();
    }
}
