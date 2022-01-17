package com.gamedirdiscovery.model;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Objects;
@Entity
public class Game {

    private String id;
    private String name;
    private String interest;
    private String level;

    public Game() {
    }

    public Game(String id, String name, String interest, String level) {
        this.id = id;
        this.name = name;
        this.interest = interest;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game1 = (Game) o;
        return Objects.equals(id, game1.id) && Objects.equals(name, game1.name) && Objects.equals(interest, game1.interest) && Objects.equals(level, game1.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, interest, level);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", interest='" + interest + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}

