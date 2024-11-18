package com.springapitemplate.docs.health;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

import com.springapitemplate.api.controller.health.HealthCheckController;
import com.springapitemplate.docs.RestDocsSupport;

class HealthCheckControllerDocsTest extends RestDocsSupport {

    private final Environment environment = mock(Environment.class);

    @DisplayName("서버 상태 체크 API")
    @Test
    void healthCheck() throws Exception {
        given(environment.getActiveProfiles()).willReturn(new String[]{"local"});

        mockMvc.perform(get("/api/v1/health")
                        .contentType("application/json")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("API Health Check"))
                .andExpect(jsonPath("$.data.health").value("OK"))
                .andExpect(jsonPath("$.data.activeProfiles").isArray())
                .andDo(document("health-check",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("code").type(NUMBER).description("응답 코드"),
                                fieldWithPath("status").type(STRING).description("응답 상태"),
                                fieldWithPath("message").type(STRING).description("응답 메시지"),
                                fieldWithPath("data.health").type(STRING).description("서버 상태"),
                                fieldWithPath("data.activeProfiles").type(ARRAY).description("현재 활성화된 프로파일 목록")
                        )
                ));
    }

    @Override
    protected Object initController() {
        return new HealthCheckController(environment);
    }

}
