package repository;

import model.Mum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MumRepository {

    private List<Mum> mums = new ArrayList<>();

    public List<Mum> getMums() {
        return this.mums;
    }

    public Mum findMumById(Long mumId) {
        return this.mums.stream()
                .filter(mum -> Objects.equals(mum.getId(), mumId))
                .findFirst()
                .orElse(null);
    }
}