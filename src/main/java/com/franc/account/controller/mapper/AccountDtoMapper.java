package com.franc.account.controller.mapper;

import com.franc.account.controller.dto.AccountGetDto;
import com.franc.account.controller.dto.AccountGetListDto;
import com.franc.account.controller.dto.AccountSaveDto;
import com.franc.account.vo.AccountVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDtoMapper {

    // AccountSaveDto_Request => AccountVo
    public AccountVo accountSaveRequestToAccountVo(AccountSaveDto.Request request);

    // AccountVo => AccountGetDto_Response
    public AccountGetDto.Response accountVoToAccountGetResponse(AccountVo vo);

    // List_AccountVo => AccountGetListDto_Response_List
    public List<AccountGetListDto.AccountData> accountVoListToAccountGetListResponseList(List<AccountVo> list);
}
