package org.example.tools;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.example.constant.OpenAIUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ToolDemo {


    @Tool("获取当前时间")
    public static String dateUtil(){
        return LocalDateTime.now().toString();
    }



    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {


        ChatLanguageModel model = OpenAIUtil.getOpenAIChatModel();

        ToolSpecification toolSpecification = ToolSpecifications.toolSpecificationFrom(ToolDemo.class.getMethod("dateUtil"));

        UserMessage userMessage = UserMessage.from("今天是哪一年几月几号");

        AiMessage aiMessageResponse = model.generate(Collections.singletonList(userMessage), toolSpecification).content();

        System.out.println(aiMessageResponse);

        if(aiMessageResponse.hasToolExecutionRequests()){

            List<ToolExecutionRequest> toolExecutionRequests = aiMessageResponse.toolExecutionRequests();

            for (ToolExecutionRequest toolExecutionRequest : toolExecutionRequests) {

                String methodName = toolExecutionRequest.name();
                String arguments = toolExecutionRequest.arguments();
                System.out.println("调用的方法:" + methodName);
                System.out.println("方法的参数:" + arguments);

                Method method = ToolDemo.class.getMethod(methodName);
                String result = (String) method.invoke(arguments);

                System.out.printf("方法的返回值:%s\n", result);

                ToolExecutionResultMessage executionMessage = ToolExecutionResultMessage.from(toolExecutionRequest.id(), toolExecutionRequest.name(), result);

                String text = model.generate(Arrays.asList(userMessage, aiMessageResponse, executionMessage)).content().text();
                System.out.println("最终的返回值:" + text);
            }

        }
    }
}

