package com.library.model.enums;

public enum EnumBookCategory {
    FICTION ("Fiction"),
    NONFICTION ("Nonfiction");

    private String nameCategory;

    EnumBookCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }
}
