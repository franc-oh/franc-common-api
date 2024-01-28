package com.franc.account.controller.mapper;

import com.franc.account.controller.dto.AccountSaveDto;
import com.franc.account.vo.AccountVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDtoMapper {

    // AccountSaveDto_Request => AccountVo
    public AccountVo accountSaveRequestToAccountVo(AccountSaveDto.Request request);
}
