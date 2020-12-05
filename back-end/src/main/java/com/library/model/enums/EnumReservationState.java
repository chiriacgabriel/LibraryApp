package com.library.model.enums;

public enum  EnumReservationState {
    CREATED ("Created"),
    CANCELED ("Canceled"),
    FINALISED ("Finalised");

    private String nameOfState;

    EnumReservationState(String nameOfState) {
        this.nameOfState = nameOfState;
    }

    public String getNameOfState() {
        return nameOfState;
    }
}
