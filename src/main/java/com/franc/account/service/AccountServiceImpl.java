package com.franc.account.service;

import com.franc.account.dao.AccountDao;
import com.franc.account.vo.AccountVo;
import com.franc.exception.BizException;
import com.franc.exception.ExceptionContent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "accountService")
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountDao accountDao;

    /**
     * 회원가입
     * @param paramVo
     * @throws Exception
     */
    @Override
    public void save(AccountVo paramVo) throws Exception {

        // #1. 체크 - 해당 이메일로 가입한 계정이 있는지?
        if(this.findByEmail(paramVo.getEmail()) != null) {
            throw new BizException(ExceptionContent.BIZ_ALREADY_EMAIL);
        }

        // #2. 체크 - 해당 휴대폰번호로 가입한 계정이 있는지?
        if(this.findByHphone(paramVo.getHphone()) != null) {
            throw new BizException(ExceptionContent.BIZ_ALREADY_HPHONE);
        }

        // #3. 등록처리
        accountDao.save(paramVo);

    }


    /**
     * findById
     * @param accountId
     * @return AccountVo
     * @throws Exception
     */
    @Override
    public AccountVo findById(Long accountId) throws Exception {
        return accountDao.findById(accountId);
    }

    /**
     * findByEmail
     * @param email
     * @return AccountVo
     * @throws Exception
     */
    @Override
    public AccountVo findByEmail(String email) throws Exception {
        return accountDao.findByEmailOrHphone(email, "");
    }

    /**
     * findByHphone
     * @param hphone
     * @return AccountVo
     * @throws Exception
     */
    @Override
    public AccountVo findByHphone(String hphone) throws Exception {
        return accountDao.findByEmailOrHphone("", hphone);
    }
}
