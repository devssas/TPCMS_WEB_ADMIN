package com.tpcmswebadmin.infrastructure.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDo extends PaginationDo {

    private String loginOfficersCode;

    private String loginOfficerUnitNumber;

}
