package com.ucar.crm.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}
