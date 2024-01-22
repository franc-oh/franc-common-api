package com.franc.account.vo;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode(of = "accountId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountVo {
    private Long accountId;
    private String accountName;
    @Builder.Default
    private Character status = '1';
    private String birth;
    private String hphone;
    private String email;
    private Date insertDate;
    private String insertUser;
    private Date updateDate;
    private String updateUser;

}
