package org.example.tools;

import dev.langchain4j.agent.tool.*;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.tool.DefaultToolExecutor;
import org.example.constant.OpenAIUtil;

import java.lang.reflect.Method;
import java.util.*;

public class ToolDemo2 {

   static class WeatherUtil {

        @Tool("查询某一个城市的天气")
        public String getWeather(@P("指定的城市") String city) {
            return "今天" + city + "的天气是晴天，气温25度";
        }
    }

    public static void main(String[] args) {

        ChatLanguageModel model = OpenAIUtil.getOpenAIChatModel();

        List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(WeatherUtil.class);

        ArrayList<ChatMessage> chatMessages = new ArrayList<>();

        chatMessages.add(UserMessage.from("广州市的天气怎么样"));

        AiMessage aiMessageResponse = model.generate(chatMessages, toolSpecifications).content();
        System.out.println(aiMessageResponse);

        List<ToolExecutionRequest> toolExecutionRequests = aiMessageResponse.toolExecutionRequests();

        if(aiMessageResponse.hasToolExecutionRequests()){
            for (ToolExecutionRequest toolExecutionRequest : toolExecutionRequests) {
                String methodName = toolExecutionRequest.name();
                String arguments = toolExecutionRequest.arguments();
                System.out.println("调用的方法:" + methodName);
                System.out.println("方法的参数:" + arguments);
            }
        }
        chatMessages.add(aiMessageResponse);
        WeatherUtil weatherUtil = new WeatherUtil();

        toolExecutionRequests.forEach(toolExecutionRequest -> {
            DefaultToolExecutor toolExecutor = new DefaultToolExecutor(weatherUtil,toolExecutionRequest);
            String result = toolExecutor.execute(toolExecutionRequest, UUID.randomUUID());
            System.out.printf("方法的返回值:%s\n", result);
            ToolExecutionResultMessage excuteMessage = ToolExecutionResultMessage.from(toolExecutionRequest, result);
            chatMessages.add(excuteMessage);
        });


        AiMessage respone = model.generate(chatMessages).content();
        System.out.println("最终的返回值:" + respone.text());



    }
}
