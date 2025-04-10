package org.example.constant;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.redis.RedisEmbeddingStore;

public class OpenAIUtil {
//    public static final String key = "sk-Qxt1e040220a75c18e3a2193f6b5cb0d8fb718b7ff3uxxI1";
//
//    public static final String url = "https://api.gptsapi.net/v1";



    public static ChatLanguageModel getOpenAIChatModel() {
        return OpenAiChatModel.builder()
                .apiKey(OPENAI_KEY2)
                .baseUrl(BASE_URI_OPENAI)
                .build();
    }


    public static EmbeddingModel getEmbeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(OpenAIUtil.OPENAI_KEY2)
                .baseUrl(OpenAIUtil.BASE_URI_OPENAI)
                .modelName("text-embedding-3-small")
                .build();
    }

    public static RedisEmbeddingStore getRedisEmbeddingStore() {
        return RedisEmbeddingStore.builder()
                .host("117.72.123.98")
                .port(6380)
                .dimension(1536)
                .build();
    }





}
