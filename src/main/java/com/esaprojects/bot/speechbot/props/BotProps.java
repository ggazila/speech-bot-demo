package com.esaprojects.bot.speechbot.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class BotProps {
    @Value("${bot.name}")
    private String name;
    @Value("${bot.token}")
    private String token;
    @Value("${bot.webhook}")
    private String webhook;
}
