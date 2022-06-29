package com.esaprojects.bot.speechbot.service.impl;

import org.springframework.stereotype.Service;

import com.esaprojects.bot.speechbot.service.MessageFormateService;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;

@Service
public class MessageFormateServiceImpl implements MessageFormateService {
    @Override
    public String formatVoice(SpeechRecognitionAlternative alternative) {
        return """
                З вірогідністью %.2f%% було сказано:
                                
                <i>%s</i>
                """.formatted(alternative.getConfidence() * 100, alternative.getTranscript());
    }
}
