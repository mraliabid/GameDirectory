package com.gamedirectory.util;

import java.util.Arrays;

public enum LevelEnum {
    Noob("noob", 1),
    Pro("pro", 2),
    Invincible("invincible", 3);

    private final Integer credit;
    private final String level;

    LevelEnum(final String level,final Integer credit){
        this.credit = credit;
        this.level = level;
    }

    public Integer getCredit() {
        return credit;
    }

    public String getLevel() {
        return level;
    }

    public static LevelEnum of(String level){
        return Arrays.stream(values())
                .filter(v -> v.getLevel().equals(level))
                .findFirst()
                .orElse(null);
    }
}
