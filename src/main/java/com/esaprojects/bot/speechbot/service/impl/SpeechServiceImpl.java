package com.esaprojects.bot.speechbot.service.impl;

import org.springframework.stereotype.Service;

import com.esaprojects.bot.speechbot.service.SpeechService;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.protobuf.ByteString;
import lombok.SneakyThrows;

@Service
public class SpeechServiceImpl implements SpeechService {
    @Override
    @SneakyThrows
    public SpeechRecognitionAlternative voiceToText(byte[] bytes) {

        try (SpeechClient client = SpeechClient.create()) {

            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setLanguageCode("uk-UA")
                    .setSampleRateHertz(16000)
                    .setEncoding(RecognitionConfig.AudioEncoding.WEBM_OPUS)
                    .build();

            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(bytes)).build();


            RecognizeResponse recognize = client.recognize(config, audio);

            return recognize.getResults(0).getAlternatives(0);
        }
    }
}
