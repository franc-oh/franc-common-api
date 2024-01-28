package com.franc.account.controller;

import com.franc.account.controller.dto.AccountGetDto;
import com.franc.account.controller.dto.AccountSaveDto;
import com.franc.account.controller.mapper.AccountDtoMapper;
import com.franc.account.service.AccountService;
import com.franc.account.vo.AccountVo;
import com.franc.common.response.BizResponse;
import com.franc.common.response.DefaultResponse;
import com.franc.exception.BizException;
import com.franc.exception.ExceptionContent;
import com.franc.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
           ,@ApiResponse(responseCode = "400", description = "실패"
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

        logger.info("회원가입_Response => {}", response.toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    @Operation(summary = "회원조회", description = "save"
            , parameters = @Parameter(name = "accountId", description = "회원번호", example = "1")
            , responses = {
            @ApiResponse(responseCode = "200", description = "성공", useReturnTypeSchema = true)
            ,@ApiResponse(responseCode = "400", description = "실패"
            , content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    }
    )
    public ResponseEntity<BizResponse<AccountGetDto.Response>> findById(@PathVariable Long accountId) throws Exception {
        logger.info("회원조회_Request => accountId : ", accountId);

        // #1. 회원 조회
        AccountVo accountVo = accountService.findById(accountId);
        if(accountVo == null) {
            throw new BizException(ExceptionContent.BIZ_ACCOUNT_NOT_FOUND);
        }

        // #2. 응답처리
        AccountGetDto.Response responseDto = accountDtoMapper.accountVoToAccountGetResponse(accountVo);
        BizResponse response = new BizResponse(responseDto);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
