package com.esaprojects.bot.speechbot.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Voice;

import com.esaprojects.bot.speechbot.bot.SpeechBot;
import com.esaprojects.bot.speechbot.service.TelegramFileService;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class TelegramFileServiceImpl implements TelegramFileService {
    private final SpeechBot speechBot;

    @Override
    @SneakyThrows
    public byte[] voiceToByteArray(Voice voice) {
        GetFile getFile = GetFile.builder().fileId(voice.getFileId()).build();
        File file = speechBot.execute(getFile);
        return Files.toByteArray(speechBot.downloadFile(file));
    }
}
