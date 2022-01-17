package com.app.user.util;

import java.util.Arrays;

public enum CategoryEnum {
    Battle("Battle"),
    Fight("Fight"),
    Social("Social");

    private final String category;

    CategoryEnum(String category){
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public static CategoryEnum of(String cat){
        return Arrays.stream(values())
                .filter(v -> v.getCategory().equals(cat))
                .findFirst()
                .orElse(null);
    }

}
