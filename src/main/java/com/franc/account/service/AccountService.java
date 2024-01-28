package com.franc.account.service;

import com.franc.account.vo.AccountVo;

import java.util.List;

public interface AccountService {

    public List<AccountVo> findAll() throws Exception;
    public AccountVo findById(Long accountId) throws Exception;
    public AccountVo findByEmail(String email) throws Exception;
    public AccountVo findByHphone(String hphone) throws Exception;
    public void save(AccountVo paramVo) throws Exception;


}
