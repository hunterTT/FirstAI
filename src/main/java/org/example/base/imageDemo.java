package org.example.base;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;
import org.example.constant.OpenAIUtil;

public class imageDemo {

    public static void main(String[] args) {
       ImageModel model = OpenAiImageModel.builder()
                .apiKey(OpenAIUtil.OPENAI_KEY2)
                .baseUrl(OpenAIUtil.BASE_URI_OPENAI)
                .build();

        Response<Image> image = model.generate("一个可爱的猫咪在草地上玩耍");

        System.out.println(image.content().url());

    }
}
