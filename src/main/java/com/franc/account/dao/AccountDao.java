package com.franc.account.dao;

import com.franc.account.vo.AccountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountDao {
    public void save(AccountVo vo) throws Exception;

    public List<AccountVo> findAll() throws Exception;
    public AccountVo findById(@Param("accountId") Long accountId) throws Exception;
    public AccountVo findByEmailOrHphone(@Param("email") String email, @Param("hphone") String hphone) throws Exception;
}
