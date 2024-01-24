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

import java.util.List;

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
                .vipYn(false)
                .build();

        // #2. When
        AccountVo resultVo = accountDao.findById(accountId);

        // #3. Then
        assertThat(resultVo).isNotNull();
        assertThat(resultVo.getAccountName()).isEqualTo(givenVo.getAccountName());
        assertThat(resultVo.getBirth()).isEqualTo(givenVo.getBirth());
        assertThat(resultVo.isVipYn()).isEqualTo(givenVo.isVipYn());
    }

    @Test
    @DisplayName("이메일혹은핸드폰으로 회원조회")
    @Transactional
    public void findOne() throws Exception {
        // #1. Given
        String hphone = "01048279018";
        String email = "max@gmail.com";

        // #2. When
        AccountVo resultVo = accountDao.findByEmailOrHphone(email, hphone);

        // #3. Then
        assertThat(resultVo).isNotNull();
        assertThat(resultVo.getAccountName()).isEqualTo("MAX");
    }

    @Test
    @DisplayName("회원리스트조회")
    @Transactional
    public void findAll() throws Exception {
        // #1. Given

        // #2. When
        List<AccountVo> resultVo = accountDao.findAll();

        // #3. Then
        assertThat(resultVo).isNotNull();
        assertThat(resultVo.size()).isGreaterThan(1);
    }

    @Test
    @DisplayName("회원가입")
    @Transactional
    public void save() throws Exception {
        // #1. Given
        String accountName = "NEW";
        String birth = "20001120";
        String hphone = "01059489291";
        String email = "new@gmail.com";
        boolean vipYn = false;
        long accountId = 4L;

        // #2. When
        accountDao.save(AccountVo.builder()
                        .accountName(accountName)
                        .birth(birth)
                        .hphone(hphone)
                        .email(email)
                        .vipYn(vipYn)
                        .insertUser("1")
                .build());

        AccountVo resultVo = accountDao.findById(accountId);

        // #3. Then
        assertThat(resultVo).isNotNull();
        assertThat(resultVo.getAccountName()).isEqualTo(accountName);
        assertThat(resultVo.getBirth()).isEqualTo(birth);
        assertThat(resultVo.getHphone()).isEqualTo(hphone);
        assertThat(resultVo.isVipYn()).isEqualTo(vipYn);
    }


}