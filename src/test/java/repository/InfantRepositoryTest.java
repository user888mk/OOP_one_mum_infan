package repository;

import model.Infant;
import model.Mum;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InfantRepositoryTest {
    private final InfantRepository infantRepository = new InfantRepository();

    @Test
    void verifyIfInfantIsAddingCorrectlyToTheRepository() {
        //given
        Mum mum = new Mum(2L, "Anna", 26, new ArrayList<>());
        Infant infant = new Infant(1L, "c", "Basia", LocalDate.of(1998, 01, 24), 27, 61, mum);
        //when
        infantRepository.getInfants().add(infant);
        //then
        Infant expectedInfant = new Infant(1L, "c", "Basia", LocalDate.of(1998, 01, 24), 27, 61, mum);
        Infant actual = infantRepository.getInfants().stream().findFirst().get();
        assertEquals(expectedInfant, actual);
    }

    @Test
    void alreadyExistInfantInList() {
        //given
        String upperCaseName = "BASIA";
        Mum mum = new Mum(2L, "Anna", 26, new ArrayList<>());
        Infant infant = new Infant(1L, "c", upperCaseName, LocalDate.of(1998, 01, 24), 27, 61, mum);
        //when
        infantRepository.getInfants().add(infant);
        //then
        Infant actualInfant = infantRepository.getInfants().stream().findFirst().get();
        Infant expectedInfant = new Infant(1L, "c", "BASIA", LocalDate.of(1998, 01, 24), 27, 61, mum);
        assertEquals(expectedInfant, actualInfant);
    }

    @Test
    void whenAddInfantListShouldBeNotEmpty() {
        //given
        Mum mum = new Mum(2L, "Anna", 26, new ArrayList<>());
        Infant infant = new Infant(1L, "c", "Basia", LocalDate.of(1998, 01, 24), 27, 61, mum);
        //when
        infantRepository.getInfants().add(infant);
        //then
        assertEquals(1, infantRepository.getInfants().size());
        assertFalse(infantRepository.getInfants().isEmpty());
    }

    @Test
    void whenAddInfantWithNullParameter() {
        //given
        Mum mum = new Mum(2L, "Anna", 26, new ArrayList<>());
        String nullGender = null;
        Infant infant = new Infant(1L, nullGender, "Basia", LocalDate.of(1998, 01, 24), 27, 61, mum);
        //when
        infantRepository.getInfants().add(infant);
        //then
        Infant actualInfant = infantRepository.getInfants().stream().findFirst().get();
        Infant expectedInfant = new Infant(1L, nullGender, "Basia", LocalDate.of(1998, 01, 24), 27, 61, mum);
        assertEquals(expectedInfant, actualInfant);
    }

    @Test
    void shouldThrowExceptionOnNullInfant() {
        //given
        Infant infant = null;
        //when
        infantRepository.getInfants().add(infant);
        //then
        assertThrows(NullPointerException.class, () -> {
            infantRepository.getInfants().stream().findFirst().get();
        });
    }
}