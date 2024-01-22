package com.franc.account.dao;

import com.franc.account.vo.AccountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {
    public AccountVo findById(@Param("accountId") Long accountId) throws Exception;
}
