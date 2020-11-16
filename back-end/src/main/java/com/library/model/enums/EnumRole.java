package com.library.model.enums;

public enum EnumRole {
    ROLE_USER("User"),
    ROLE_MODERATOR("Moderator"),
    ROLE_ADMIN("Admin");

    private String nameRole;

    EnumRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }

}
