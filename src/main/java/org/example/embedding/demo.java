package org.example.embedding;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.redis.RedisEmbeddingStore;
import org.example.constant.OpenAIUtil;

public class demo {

    public static void main(String[] args) {


        EmbeddingModel embeddingModel = OpenAIUtil.getEmbeddingModel();


        Response<Embedding> embeddingResponse = embeddingModel.embed("我的名字是小齐");

        System.out.println(  embeddingResponse.content().toString());
        System.out.println(  embeddingResponse.content().vector().length);

        RedisEmbeddingStore redisStore = OpenAIUtil.getRedisEmbeddingStore();
        redisStore.add(embeddingResponse.content());

    }
}
