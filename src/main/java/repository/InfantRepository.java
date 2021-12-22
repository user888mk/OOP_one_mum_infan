package repository;

import model.Infant;

import java.util.ArrayList;
import java.util.List;

public class InfantRepository {

    private List<Infant> infants = new ArrayList<>();

    public InfantRepository() {
    }

    public List<Infant> getInfants() {
        return infants;
    }
}