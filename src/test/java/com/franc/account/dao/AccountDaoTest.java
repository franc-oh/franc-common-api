package com.franc.account.dao;

import com.franc.account.vo.AccountVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountDaoTest {

    @Autowired
    private AccountDao accountDao;


    @Test
    @DisplayName("ID로 회원조회")
    @Transactional
    public void findById() throws Exception {
        // #1. Given
        long accountId = 1L;
        AccountVo givenVo = AccountVo.builder()
                .accountId(accountId)
                .accountName("JOHN")
                .birth("19880101")
                .build();

        // #2. When
        AccountVo resultVo = accountDao.findById(accountId);

        // #3. Then
        assertThat(resultVo).isNotNull();
        assertThat(resultVo.getAccountName()).isEqualTo(givenVo.getAccountName());
        assertThat(resultVo.getBirth()).isEqualTo(givenVo.getBirth());
    }


}