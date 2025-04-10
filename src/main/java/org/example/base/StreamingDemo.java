package org.example.base;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import org.example.constant.OpenAIUtil;

import java.util.concurrent.TimeUnit;

public class StreamingDemo {



    public static void main(String[] args) {

        StreamingChatLanguageModel model = OpenAiStreamingChatModel.builder()
                .baseUrl(OpenAIUtil.BASE_URI_OPENAI)
                .apiKey(OpenAIUtil.OPENAI_KEY2)
                .build();


        model.generate("你好,你是谁", new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }
        });

    }
}
