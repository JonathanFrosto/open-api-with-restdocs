package com.jonathan.restdocs;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.jonathan.restdocs.controllers.HelloController;
import com.jonathan.restdocs.dtos.MessageDTO;
import com.jonathan.restdocs.services.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @Test
    void getHelloMessage() throws Exception {

        MessageDTO responseBody = new MessageDTO().message("First message");
        when(helloService.getMessage(any())).thenReturn(responseBody);

        mockMvc.perform(get("/hello/{messageId}", 1))
                .andDo(document("Hello message", resource(
                        ResourceSnippetParameters.builder()
                                .summary("Return greeting message to client")
                                .pathParameters(
                                        parameterWithName("messageId").description("Output message id")
                                )
                                .responseFields(
                                        fieldWithPath("message").description("Output message")
                                )
                                .responseSchema(
                                        Schema.schema("MessageWrapper")
                                )
                                .build()
                )))
                .andExpect(status().isOk());
    }

    @Test
    void notFoundMessage() throws Exception {

        mockMvc.perform(get("/hello/{messageId}", 1))
                .andDo(document("Not found hello message", resource(
                        ResourceSnippetParameters.builder()
                                .pathParameters(
                                        parameterWithName("messageId").description("Output message id")
                                )
                                .build()
                )))
                .andExpect(status().isNotFound());
    }
}