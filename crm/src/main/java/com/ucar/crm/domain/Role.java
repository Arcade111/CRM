package com.ucar.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Role {
    private Long id;

    private String sn;

    private String name;

    private List<Permission> permissions = new ArrayList<>();
}