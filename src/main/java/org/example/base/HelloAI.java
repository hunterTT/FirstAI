package org.example.base;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;

public class HelloAI {

    public static void main(String[] args) {
//        ChatLanguageModel model = OpenAiChatModel.builder()
//                .apiKey("demo")
//                .modelName("gpt-4o-mini")
//                .build();
//
//        String generate = model.generate("你好，你是谁");
//        System.out.println(generate);
//
//        String answer = model.generate("请重复一次");
//        System.out.println(answer);


        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();
//
//        System.out.println(model.generate("你好，你是谁？"));
//        System.out.println("----");
//        System.out.println(model.generate("请重复"));

        UserMessage userMessage1 = UserMessage.from("你好，我是小齐");
        Response<AiMessage> response = model.generate(userMessage1);

        AiMessage aiMessage = response.content();

        UserMessage userMessage2 = UserMessage.from("我是谁");
        Response<AiMessage> generate = model.generate(userMessage1, aiMessage, userMessage2);
        System.out.println(generate.content().text());
    }
}
