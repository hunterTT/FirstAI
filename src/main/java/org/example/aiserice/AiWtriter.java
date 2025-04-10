package org.example.aiserice;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.example.constant.OpenAIUtil;

public class AiWtriter {

    interface AiWriter {
        @SystemMessage("你是一个优秀的写作助手，写一篇散文，不超过200个字")
        String write(String prompt);
    }


    interface AiWriter2 {
        @SystemMessage("你是一个优秀的写作助手，写一篇散文，主题是{{theme}}，不超过{{count}}个字")
        String write(@UserMessage String prompt , @V("theme") String theme, @V("count") String count);
    }

    public static void main(String[] args) {
        ChatLanguageModel model = OpenAIUtil.getOpenAIChatModel();

//        AiWriter aiWriter = AiServices.create(AiWriter.class, model);
//        String content = aiWriter.write("最爱喝的茶");
//        System.out.println(content);

        String content = AiServices.create(AiWriter2.class, model)
                .write("我需要一个作文", "歌曲", "200");
        System.out.println(content);

    }
}
