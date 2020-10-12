package com.egorl.battlespy.domain.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "battle")
public class Battle {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "map")
    private String map;

    @Column(name = "author")
    private String author;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battle battle = (Battle) o;
        return Objects.equals(id, battle.id) &&
                Objects.equals(map, battle.map) &&
                Objects.equals(author, battle.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, map, author);
    }

    @Override
    public String toString() {
        return "Battle{" +
                "id=" + id +
                ", map='" + map + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
