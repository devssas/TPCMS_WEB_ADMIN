package com.tpcmswebadmin.infrastructure.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public abstract class PaginationDo {

    private Integer limit;

    private Integer pageNumber;

    public PaginationDo() {
        this.limit = 25;
        this.pageNumber = 0;
    }

}
