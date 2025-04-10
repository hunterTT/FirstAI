package org.example.aiserice;

import ch.qos.logback.classic.spi.EventArgUtil;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import org.example.constant.OpenAIUtil;

import java.time.LocalDateTime;

public class UserAISerivce {


    interface Assistant{
        String chat(@MemoryId String userId, @UserMessage String message);
    }

    static class dateUtil{
         String getDate(){
            return LocalDateTime.now().toString();
        }
    }

    public static void main(String[] args) {
        ChatLanguageModel model = OpenAIUtil.getOpenAIChatModel();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(model)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .tools(ToolSpecifications.toolSpecificationsFrom(dateUtil.class))
                .build();


        System.out.println(assistant.chat("123", "你好，我是小齐"));
    }
}
