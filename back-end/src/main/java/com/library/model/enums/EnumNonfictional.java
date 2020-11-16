package com.library.model.enums;

public enum EnumNonfictional {
    ARCHITECTURE("Architecture"),
    AUTOBIOGRAPHY("Autobiography"),
    BIOGRAPHY("Biography"),
    BUSINESS("Business"),
    CRAFTS("Crafts"),
    COOKBOOK("Cookbook"),
    DIARY("Diary"),
    DICTIONARY("Dictionary"),
    ENCYCLOPEDIA("Encyclopedia"),
    GUIDE("Guide"),
    HEALTH("Health"),
    HISTORY("History"),
    HOME_AND_GARDEN("Home and garden"),
    HUMOR("Humor"),
    JOURNAL("Journal"),
    MATH("Math"),
    MEMOIR("Memoir"),
    PHILOSOPHY("Philosophy"),
    PRAYER("Prayer"),
    RELIGION("Religion"),
    REVIEW("Review"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    TRAVEL("Travel"),
    TRUE_CRIME("True crime");

    private String nameOfNonfictional;

    EnumNonfictional(String nameOfType) {
        this.nameOfNonfictional = nameOfType;
    }

    public String getNameOfNonfictional() {
        return nameOfNonfictional;
    }
}
