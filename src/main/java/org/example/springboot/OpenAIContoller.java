package org.example.springboot;


import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIContoller {

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    @GetMapping("/hello")
    public String hello(){
        return chatLanguageModel.generate("你好啊");
    }

}
