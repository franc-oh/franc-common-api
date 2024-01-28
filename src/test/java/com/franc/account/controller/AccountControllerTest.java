package com.franc.account.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.franc.account.controller.dto.AccountSaveDto;
import com.franc.account.service.AccountService;
import com.franc.common.response.CommonVar;
import com.franc.exception.ControllerExceptionHandler;
import com.franc.exception.ExceptionContent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private static final String URL = "/account";

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(ControllerExceptionHandler.class)
                .build();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Test
    @Transactional
    @DisplayName("회원가입실패 - Request 유효성")
    public void save_fail_valid() throws Exception {
        // #1. Given
        AccountSaveDto.Request request = AccountSaveDto.Request.builder()
                .build();


        // #2. When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                            .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                            .accept(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                            .content(objectMapper.writeValueAsString(request))
        ).andDo(print());


        // #3. Then
        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").value(ExceptionContent.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getCode().value()))
                .andExpect(jsonPath("message").value(ExceptionContent.METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getMessage()));

    }


    @Test
    @Transactional
    @DisplayName("회원가입실패 - 이미 가입된 이메일")
    public void save_fail_already_email() throws Exception {
        // #1. Given
        AccountSaveDto.Request request = AccountSaveDto.Request.builder()
                .accountName("max")
                .birth("19880210")
                .email("max@gmail.com")
                .hphone("01012349484")
                .build();


        // #2. When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                        .accept(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());


        // #3. Then
        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").value(ExceptionContent.BIZ_ALREADY_EMAIL.getCode().value()))
                .andExpect(jsonPath("message").value(ExceptionContent.BIZ_ALREADY_EMAIL.getMessage()));

    }

    @Test
    @Transactional
    @DisplayName("회원가입성공")
    public void save_success() throws Exception {
        // #1. Given
        AccountSaveDto.Request request = AccountSaveDto.Request.builder()
                .accountName("new")
                .birth("19880210")
                .email("new@gmail.com")
                .hphone("01012349484")
                .build();


        // #2. When
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .contentType(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                        .accept(new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8))
                        .content(objectMapper.writeValueAsString(request))
        ).andDo(print());


        // #3. Then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("code").value(CommonVar.API_SUCCESS_CODE))
                .andExpect(jsonPath("message").value(CommonVar.API_SUCCESS_MSG));

    }
}
