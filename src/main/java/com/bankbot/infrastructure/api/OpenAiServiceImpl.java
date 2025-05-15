package com.bankbot.infrastructure.api;

import com.bankbot.application.port.AiChatClient;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OpenAiServiceImpl implements AiChatClient {

    private final OpenAiService openAiClient; // از کلاس کتابخانه
    private final String model;

    public OpenAiServiceImpl(@Value("${openai.api.key}") String apiKey,
                             @Value("${openai.model}") String model) {
        this.openAiClient = new OpenAiService(apiKey); // کلاس کتابخانه
        this.model = model;
    }

    @Override
    public String generateResponse(String prompt) {
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model(model)
                .messages(Arrays.asList(new ChatMessage("user", prompt)))
                .build();

        return openAiClient.createChatCompletion(request)
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }
}
