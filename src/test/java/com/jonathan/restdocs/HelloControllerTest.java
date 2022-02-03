package com.jonathan.restdocs;

import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import com.jonathan.restdocs.controllers.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ola() throws Exception {
        mockMvc.perform(get("/ola"))
                .andDo(document("Hello",
                        responseFields(fieldWithPath("message").description("Mensagem para o usuário"))))
                .andExpect(status().isOk());
    }

    @Test
    void ola2() throws Exception {
        mockMvc.perform(get("/ola"))
                .andDo(document("Hello", ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                                        .responseFields(
                                                fieldWithPath("message").description("Mensagem para o usuário")
                                        )
                        .build()
                )))
                .andExpect(status().isOk());
    }
}