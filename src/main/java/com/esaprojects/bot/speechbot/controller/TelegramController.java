package com.esaprojects.bot.speechbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.esaprojects.bot.speechbot.bot.SpeechBot;
import com.esaprojects.bot.speechbot.service.MessageCreateService;
import com.esaprojects.bot.speechbot.service.MessageFormateService;
import com.esaprojects.bot.speechbot.service.SpeechService;
import com.esaprojects.bot.speechbot.service.TelegramFileService;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequestMapping("callback")
@RequiredArgsConstructor
public class TelegramController {
    private final SpeechBot speechBot;
    private final TelegramFileService fileService;
    private final SpeechService speechService;
    private final MessageFormateService formateService;
    private final MessageCreateService createService;

    @PostMapping("update")
    @SneakyThrows
    public BotApiMethod<?> onWebhookUpdateReceived(@RequestBody Update update) {
        if (isVoice(update)) {
            byte[] bytes = fileService.voiceToByteArray(update.getMessage().getVoice());
            SpeechRecognitionAlternative alternative = speechService.voiceToText(bytes);
            String text = formateService.formatVoice(alternative);
            SendMessage message = createService.createMessage(String.valueOf(update.getMessage().getFrom().getId()), text, ParseMode.HTML);
            speechBot.execute(message);

        }
        return new SendMessage();
    }

    private boolean isVoice(Update update) {
        return update.hasMessage() && update.getMessage().hasVoice() && update.getMessage().getVoice().getDuration() < 60;
    }
}
