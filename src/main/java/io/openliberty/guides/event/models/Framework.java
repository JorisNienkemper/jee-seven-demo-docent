package io.openliberty.guides.event.models;

import javax.persistence.*;


import java.io.Serializable;

@Entity
public class Framework implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;

    private int score;

    private String logo;

    public Framework() {
    }

    public Framework(int id, String name, int score, String logo) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.logo = logo;
    }

    public Framework(String name, int score, String logo) {
        this.name = name;
        this.score = score;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Framework)) return false;

        Framework framework = (Framework) o;

        if (id != framework.id) return false;
        if (score != framework.score) return false;
        if (!name.equals(framework.name)) return false;
        return logo.equals(framework.logo);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + score;
        result = 31 * result + logo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Framework{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", logo='" + logo + '\'' +
                '}';
    }
}
