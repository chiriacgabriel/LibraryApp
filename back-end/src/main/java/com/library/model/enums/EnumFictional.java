package com.library.model.enums;

public enum EnumFictional {
    ACTION_AND_ADVENTURE("Action and adventure"),
    ALTERNATE_HISTORY("Alternate history"),
    ANTHOLOGY("Anthology"),
    CHICK_LIT("Chick lit"),
    CHILDRENS("Children"),
    CLASSIC("Classic"),
    COMIC_BOOK("Comic book"),
    COMING_OF_AGE("Coming of age"),
    CRIME("Crime"),
    DRAMA("Drama"),
    FAIRYTALE("Fairytale"),
    FANTASY("Fantasy"),
    NOVEL("Novel"),
    HISTORICAL_FICTION("Historical fiction"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    PARANORMAL_ROMANCE("Paranormal romance"),
    PICTURE("Picture"),
    POETRY("Poetry"),
    ROMANCE("Romance"),
    SATIRE("Satire"),
    SCIENCE_FICTION("Science fiction"),
    SHORT_STORY("Short story"),
    SUSPENSE("Suspense"),
    THRILLER("Thriller"),
    WESTERN("Western");

    private String nameOfFictional;

    EnumFictional(String nameOfFictional) {
        this.nameOfFictional = nameOfFictional;
    }

    public String getNameOfFictional() {
        return nameOfFictional;
    }
}
