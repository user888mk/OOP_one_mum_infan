package model;

import java.time.LocalDate;
import java.util.Objects;

public class Infant {
    private Long id;
    private String gender;
    private String name;
    private LocalDate bornDay;
    private Integer weight;
    private Integer height;
    private Mum mum;

    public Infant(Long id, String gender, String name, LocalDate bornDay, Integer weight, Integer height, Mum mum) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.bornDay = bornDay;
        this.weight = weight;
        this.height = height;
        this.mum = mum;
    }

    public Infant(Long id, String gender, String name, LocalDate bornDay, Integer weight, Integer height) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.bornDay = bornDay;
        this.weight = weight;
        this.height = height;
    }

    public Mum getMum() {
        return this.mum;
    }

    public void setMum(Mum mum) {
        this.mum = mum;
    }

    public String getName() {
        return this.name;
    }

    public Infant() {
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getHeight() {
        return this.height;
    }

    public String getGender() {
        return this.gender;
    }

    public LocalDate getBornDay() {
        return bornDay;
    }

    public Integer getWeight() {
        return weight;
    }

    public String toString() {
        return this.id + " " + this.gender + " " + this.name + " " + this.bornDay + " " + this.weight + " " + this.height + " " + this.mum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Infant infant = (Infant) o;
        return weight == infant.weight && height == infant.height && Objects.equals(id, infant.id) && Objects.equals(gender, infant.gender) && Objects.equals(name, infant.name) && Objects.equals(bornDay, infant.bornDay) && Objects.equals(mum, infant.mum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, name, bornDay, weight, height, mum);
    }
}