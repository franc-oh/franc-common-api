package com.franc.account.service;

import com.franc.account.dao.AccountDao;
import com.franc.account.vo.AccountVo;
import com.franc.exception.BizException;
import com.franc.exception.ExceptionContent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Spy
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountDao accountDao;


    @Test
    @DisplayName("회원가입실패 - 이메일중복")
    public void save_fail_exist_email() throws Exception {
        // #1. Given
        String email = "max@gmail.com";

        AccountVo givenVo = AccountVo.builder()
                .email("email")
                .build();

        when(accountService.findByEmail(anyString()))
                .thenReturn(givenVo);

        // #2. When
        BizException exception
                = assertThrows(BizException.class, () -> accountService.save(givenVo));

        // #3. Then
        assertThat(exception.getClass()).isEqualTo(BizException.class);
        assertThat(exception.getResult()).isEqualTo(ExceptionContent.BIZ_ALREADY_EMAIL);
        verify(accountService, times(1)).findByEmail(anyString());

    }

    @Test
    @DisplayName("회원가입실패 - 휴대폰중복")
    public void save_fail_exist_hphone() throws Exception {
        // #1. Given
        String email = "max@gmail.com";
        String hphone = "01048279018";

        AccountVo givenVo = AccountVo.builder()
                .email(email)
                .hphone(hphone)
                .build();

        when(accountService.findByEmail(anyString()))
                .thenReturn(null);

        when(accountService.findByHphone(anyString()))
                .thenReturn(givenVo);

        // #2. When
        BizException exception
                = assertThrows(BizException.class, () -> accountService.save(givenVo));

        // #3. Then
        assertThat(exception.getClass()).isEqualTo(BizException.class);
        assertThat(exception.getResult()).isEqualTo(ExceptionContent.BIZ_ALREADY_HPHONE);
        verify(accountService, times(1)).findByEmail(anyString());
        verify(accountService, times(1)).findByHphone(anyString());

    }


    @Test
    @DisplayName("회원가입성공")
    public void save_success() throws Exception {
        // #1. Given
        String accountName = "NEW";
        String birth = "20001120";
        String hphone = "01059489291";
        String email = "new@gmail.com";

        AccountVo givenVo = AccountVo.builder()
                .accountName(accountName)
                .email(email)
                .hphone(hphone)
                .birth(birth)
                .build();

        when(accountService.findByEmail(anyString()))
                .thenReturn(null);

        when(accountService.findByHphone(anyString()))
                .thenReturn(null);

        doNothing()
                .when(accountDao)
                .save(any(AccountVo.class));

        // #2. When
        accountService.save(givenVo);

        // #3. Then
        verify(accountService, times(1)).findByEmail(anyString());
        verify(accountService, times(1)).findByHphone(anyString());
        verify(accountDao, times(1)).save(any(AccountVo.class));

    }

}