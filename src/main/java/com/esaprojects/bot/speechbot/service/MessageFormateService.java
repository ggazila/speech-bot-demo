package com.esaprojects.bot.speechbot.service;

import com.google.cloud.speech.v1.SpeechRecognitionAlternative;

public interface MessageFormateService {

    String formatVoice(SpeechRecognitionAlternative alternative);
}
