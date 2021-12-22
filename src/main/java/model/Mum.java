package model;

import java.util.List;
import java.util.Objects;

public class Mum {
    private Long id;
    private String name;
    private Integer age;
    private List<Infant> infantList;

    public Mum(Long id, String name, Integer age, List<Infant> infantList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.infantList = infantList;
    }

    public List<Infant> getInfantList() {
        return this.infantList;
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return this.id;
    }

    public int getAge() {
        return age;
    }


    public String toString() {
        return this.id + " " + this.name + " " + this.age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mum mum = (Mum) o;
        return age == mum.age && Objects.equals(id, mum.id) && Objects.equals(name, mum.name) && Objects.equals(infantList, mum.infantList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, infantList);
    }
}