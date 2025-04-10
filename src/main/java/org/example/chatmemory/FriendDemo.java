package org.example.chatmemory;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;


public class FriendDemo {

    public static void main(String[] args) {

        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();


        MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        memory.add(UserMessage.from("你好，我是小齐,你是我最好的朋友，你叫阿强"));
        AiMessage answer1 = model.generate(memory.messages()).content();
        System.out.println(answer1.text());
        memory.add(answer1);

        memory.add(UserMessage.from("小齐最好的朋友是谁？"));
        AiMessage answer2 = model.generate(memory.messages()).content();
        System.out.println(answer2.text());
        memory.add(answer2);

    }
}
