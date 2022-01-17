package com.gameinfo.util;

import java.util.Arrays;

public enum GeographyEnum {

    Europe("Europe"),
    Asia("Asia"),
    Africa("Africa"),
    NorthAmerica("NorthAmerica"),
    SouthAmerica("SouthAmerica"),
    Antarctica("Antarctica"),
    Australia("Australia");

    private final String geography;

    GeographyEnum(String geography){
        this.geography = geography;
    }

    public String getGeography() {
        return geography;
    }

    public static GeographyEnum of(String geo){
        return Arrays.stream(values())
                .filter(v -> v.getGeography().equals(geo))
                .findFirst()
                .orElse(null);
    }
}
