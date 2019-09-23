package com.ucar.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Menu {
    private Long id;

    private String text;

    private String state;

    private Boolean checked;

    private String attributes;

    private Permission permission;

    private List<Menu> children = new ArrayList<>();
}