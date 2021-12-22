
package repository;

import model.Mum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MumRepositoryTest {
    private final MumRepository mumRepository = new MumRepository();

    @Test
    void verifyIfMumIsAddingCorrectlyToTheRepository() {
        //given
        Mum mum = new Mum(2L, "Anna", 26, new ArrayList<>());
        Mum expectedMum = new Mum(2L, "Anna", 26, new ArrayList<>());
        //when
        mumRepository.getMums().add(mum);
        //then
        Mum actualMum = mumRepository.getMums().stream().findFirst().get();
        assertEquals(expectedMum, actualMum);
    }

    @Test
    void alreadyExistMumInList() {
        //given
        String upperCaseName = "ANNA";
        Mum mum = new Mum(2L, upperCaseName, 26, new ArrayList<>());
        //when
        mumRepository.getMums().add(mum);
        //then
        Mum actualMum = mumRepository.getMums().stream().findFirst().get();
        Mum expectedMum = new Mum(2L, "ANNA", 26, new ArrayList<>());
        assertEquals(expectedMum, actualMum);
        assertNotNull(actualMum);
    }

    @Test
    void whenAddMumListShouldBeNotEmpty() {
        //given
        Mum mum = new Mum(2L, "Anna", 26, new ArrayList<>());
        //when
        mumRepository.getMums().add(mum);
        //then
        List<Mum> mums = mumRepository.getMums();
        assertEquals(1, mums.size());
        assertFalse(mumRepository.getMums().isEmpty());
        assertNotNull(mums);
    }

    @Test
    void whenAddMumWithNullParameter() {
        //given
        String nullName = null;
        Mum mum = new Mum(5L, nullName, 23, new ArrayList<>());
        //when
        mumRepository.getMums().add(mum);
        //then
        Mum actualMum = mumRepository.getMums().stream().findFirst().get();
        Mum expectedMum = new Mum(5L, nullName, 23, new ArrayList<>());
        assertEquals(expectedMum, actualMum);
    }
}