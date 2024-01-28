package com.franc.account.controller;

import com.franc.account.controller.dto.AccountGetDto;
import com.franc.account.controller.dto.AccountGetListDto;
import com.franc.account.controller.dto.AccountSaveDto;
import com.franc.account.controller.mapper.AccountDtoMapper;
import com.franc.account.service.AccountService;
import com.franc.account.vo.AccountVo;
import com.franc.common.response.BizResponse;
import com.franc.common.response.DefaultResponse;
import com.franc.config.annotation.SwaggerApiResponseFail;
import com.franc.config.annotation.SwaggerApiResponseSuccess;
import com.franc.exception.BizException;
import com.franc.exception.ExceptionContent;
import io.swagger.v3.oas.annotations.Parameter;
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
    @SwaggerApiResponseSuccess(summary = "회원가입", description = "save",
            implementation = DefaultResponse.class)
    @SwaggerApiResponseFail
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
    @Parameter(name = "accountId", description = "회원번호", example = "1")
    @SwaggerApiResponseSuccess(summary = "회원조회", description = "getAccountId",
            implementation = AccountGetDto.Response.class)
    @SwaggerApiResponseFail
    public ResponseEntity<BizResponse<AccountGetDto.Response>> findById(@PathVariable Long accountId) throws Exception {
        logger.info("회원조회_Request => accountId : " + accountId);

        // #1. 회원 조회
        AccountVo accountVo = accountService.findById(accountId);
        if(accountVo == null) {
            throw new BizException(ExceptionContent.BIZ_ACCOUNT_NOT_FOUND);
        }

        // #2. 응답처리
        AccountGetDto.Response responseDto = accountDtoMapper.accountVoToAccountGetResponse(accountVo);
        BizResponse response = new BizResponse(responseDto);

        logger.info("회원조회_Response => {}", response.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping
    @SwaggerApiResponseSuccess(summary = "회원목록조회", description = "selectAccountList",
            implementation = AccountGetListDto.Response.class)
    @SwaggerApiResponseFail
    public ResponseEntity<BizResponse<AccountGetListDto.Response>> findAll() throws Exception {
        logger.info("회원목록조회_Request");

        // #1. 회원 조회
        List<AccountVo> accountVoList = accountService.findAll();

        int accountListCount = accountVoList != null ? accountVoList.size() : 0;

        // #2. 응답처리
        AccountGetListDto.Response responseDto = new AccountGetListDto.Response();
        responseDto.setCount(accountListCount);
        responseDto.setList(accountDtoMapper.accountVoListToAccountGetListResponseList(accountVoList));


        BizResponse response = new BizResponse(responseDto);
        logger.info("회원목록조회_Response => {}", response.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
