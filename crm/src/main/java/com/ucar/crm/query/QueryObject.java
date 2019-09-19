package com.ucar.crm.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryObject {
    //当前页
    private int page = 1;
    //每页显示多少条
    private int rows = 10;

    public int getStart() {
        return (page - 1) * rows;
    }
}
