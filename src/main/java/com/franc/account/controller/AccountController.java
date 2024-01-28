package com.franc.account.controller;

import com.franc.account.controller.dto.AccountSaveDto;
import com.franc.account.controller.mapper.AccountDtoMapper;
import com.franc.account.service.AccountService;
import com.franc.account.vo.AccountVo;
import com.franc.common.response.BizResponse;
import com.franc.common.response.DefaultResponse;
import com.franc.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AccountController", description = "회원 API")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    private final AccountDtoMapper accountDtoMapper;


    /**
     * 회원가입
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping
    @Operation(summary = "회원가입", description = "save"
        , responses = {
            @ApiResponse(responseCode = "200", description = "가입성공", useReturnTypeSchema = true)
           ,@ApiResponse(responseCode = "400", description = "가입실패"
                , content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
        }
    )

    public ResponseEntity<DefaultResponse> save(@RequestBody @Valid AccountSaveDto.Request request) throws Exception {
        logger.info("회원가입_Request => {}", request.toString());

        // #1. Request 처리
        AccountVo saveAccountVo = accountDtoMapper.accountSaveRequestToAccountVo(request);

        // #2. 회원가입
        accountService.save(saveAccountVo);

        // #3. 응답처리
        DefaultResponse response = new DefaultResponse();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
