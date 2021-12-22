package service;

import model.Infant;
import model.Mum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MumServiceTest {
    private final MumService mumService = new MumService();

    @Test
    void correctWorkingMethodFindMumsShouldReturnMumYoungerThan25andWeightInfantGreaterThan4000g() {
        Mum firstMum = new Mum(2L, "Anna", 24, new ArrayList<>());
        Mum secondMum = new Mum(2L, "Anna", 34, new ArrayList<>());
        //given
        Infant firstInfant = new Infant(13L, "c", "test1", LocalDate.now(), 4800, 50, firstMum);
        Infant secondInfant = new Infant(7L, "c", "test2", LocalDate.now(), 3500, 48, firstMum);
        Infant thirdInfant = new Infant(19L, "c", "test3", LocalDate.now(), 4200, 41, secondMum);

        List<Infant> infantList = List.of(firstInfant, secondInfant, thirdInfant);
        MumService mumService = new MumService();
        //when
        List<Mum> mums = mumService.findMums(infantList);
        Mum expectedMum = new Mum(2L, "Anna", 24, new ArrayList<>());
        //then
        assertThat(mums.equals(expectedMum));
        assertThat(mums.contains(expectedMum));
        assertFalse(mums.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenFindMumsGetsNoMumsUnder25AndWithNoInfantsAbove4000g() {
        //given
        Mum mum = new Mum(2L, "Anna", 26, new ArrayList<>());
        Infant firstInfant = new Infant(13L, "c", "test1", LocalDate.now(), 3800, 50);
        Infant secondInfant = new Infant(7L, "c", "test2", LocalDate.now(), 3500, 48);
        Infant thirdInfant = new Infant(19L, "c", "test3", LocalDate.now(), 3200, 41);

        mum.getInfantList().addAll(List.of(firstInfant, secondInfant, thirdInfant));
        firstInfant.setMum(mum);
        secondInfant.setMum(mum);
        thirdInfant.setMum(mum);
        //when
        List<Mum> mums = mumService.findMums(mum.getInfantList());
        //then
        Assertions.assertTrue(mums.isEmpty());
        assertThat(mums.size()).isEqualTo(0);
        assertFalse(mums.contains(mum));
    }

    @Test
    void lackCorrectParametersMethodTwinsShouldReturnEmptyList() {
        //given
        Mum mum = new Mum(2L, "Anna", 26, new ArrayList<>());
        Infant firstInfant = new Infant(13L, "c", "test1", LocalDate.of(1998, 4, 23), 3800, 50);
        Infant secondInfant = new Infant(7L, "c", "test2", LocalDate.of(1997, 1, 5), 3500, 48);
        Infant thirdInfant = new Infant(19L, "c", "test3", LocalDate.of(1998, 3, 12), 3200, 41);
        firstInfant.setMum(mum);
        secondInfant.setMum(mum);
        thirdInfant.setMum(mum);

        List<Mum> mums = new ArrayList<>();
        mums.add(mum);
        //when
        List<Mum> twins = mumService.twins(mums);
        //then
        Assertions.assertTrue(twins.isEmpty());
        assertThat(twins.size()).isEqualTo(0);
        assertFalse(twins.contains(mum));
    }
}