package service;

import model.Infant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InfantServiceTest {
    private final InfantService infantService = new InfantService();

    @Test
    @DisplayName("Find tallest infant method works as desired")
    void findTallestInfantReturnsCorrectInfant() {
        // given
        Infant firstInfant = new Infant(1L, "c", "test1", LocalDate.now(), 2, 50);
        Infant secondInfant = new Infant(2L, "c", "test2", LocalDate.now(), 3, 51);
        List<Infant> infantList = List.of(firstInfant, secondInfant);
        InfantService infantService = new InfantService();
        // when
        Infant tallestInfant = infantService.findTallestInfant(infantList, "c");
        // then
        assertNotNull(tallestInfant);
        assertEquals(51, tallestInfant.getHeight());
    }

    @Test
    @DisplayName("Find tallest infant method will return null if infant list is empty")
    void findTallestInfantReturnsNullOnEmptyList() {
        // given
        List<Infant> infantList = List.of();
        // when
        Infant tallestInfant = infantService.findTallestInfant(infantList, "c");
        // then
        assertNull(tallestInfant);
    }

    @Test
    @DisplayName("Find date of most birth")
    void findMostBornDayReturnsCorrectDate() {
        // given
        Infant firstInfant = new Infant(1L, "c", "test1", LocalDate.of(1999, 11, 20), 2, 50);
        Infant secondInfant = new Infant(2L, "c", "test2", LocalDate.of(1999, 11, 20), 3, 51);
        Infant thirdInfant = new Infant(3L, "c", "test3", LocalDate.of(1998, 11, 20), 3, 51);
        List<Infant> infantList = List.of(firstInfant, secondInfant, thirdInfant);
        // when
        LocalDate mostBornDay = infantService.findMostBornDay(infantList);
        // then
        assertNotNull(mostBornDay);
        assertEquals(LocalDate.of(1999, 11, 20), mostBornDay);
    }

    @Test
    @DisplayName("Find boy in girl list")
    void whenWeSearchBoyButListContainOnlyGirls() {
        //given
        Infant firstInfant = new Infant(4L, "c", "Basia", LocalDate.of(1991, 02, 27), 30, 61);
        Infant secondInfant = new Infant(17L, "c", "Kasia", LocalDate.of(1994, 06, 16), 24, 61);
        Infant thirdInfant = new Infant(37L, "c", "Asia", LocalDate.of(1999, 07, 04), 20, 61);
        //when
        List<Infant> infantList = List.of(firstInfant, secondInfant, thirdInfant);
        Infant s = infantService.findTallestInfant(infantList, "s");
        //then
        assertNull(s);
    }

    @Test
    @DisplayName("Find highest boy in the same parameters")
    void whenWeSearchHighestBoyButEveryoneHaveTheSameValueShouldReturnFirstWithCorrectParameters() {
        //given
        Infant firstInfant = new Infant(1L, "s", "Jakub", LocalDate.of(1998, 01, 24), 27, 61);
        Infant secondInfant = new Infant(4L, "s", "Adam", LocalDate.of(1991, 04, 18), 20, 61);
        Infant thirdInfant = new Infant(8L, "s", "Tomek", LocalDate.of(1997, 07, 07), 18, 61);
        //when
        List<Infant> infantList = List.of(firstInfant, secondInfant, thirdInfant);
        Infant s = infantService.findTallestInfant(infantList, "s");
        //then
        assertNotNull(s);
        assertEquals(s, firstInfant);
    }
}