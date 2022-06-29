package com.esaprojects.bot.speechbot.service;

import org.telegram.telegrambots.meta.api.objects.Voice;

public interface TelegramFileService {

    byte[] voiceToByteArray(Voice voice);
}
