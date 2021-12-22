package application;

import model.Infant;
import model.Mum;
import repository.InfantRepository;
import repository.MumRepository;
import service.InfantService;
import service.MumService;

import java.time.LocalDate;
import java.util.List;

public class Application {

    public void run() {
        // Load mum data
        MumRepository mumRepository = new MumRepository();
        MumService mumService = new MumService();
        mumService.loadData(mumRepository);

        // Load infant data
        InfantRepository infantRepository = new InfantRepository();
        InfantService infantService = new InfantService();
        infantService.loadData(infantRepository, mumRepository);

        List<Infant> infants = infantRepository.getInfants();
        List<Mum> mums = mumRepository.getMums();

        // Exercise 1. - znajdz najwyzszego chlopca
        Infant tallestBoy = infantService.findTallestInfant(infants, "s");
        System.out.println("Najwyzszy chlopiec nazywa sie: " + tallestBoy.getName());

        // Exercise 2. - znajdz najwyzsza dziewczynke
        Infant tallestGirl = infantService.findTallestInfant(infants, "c");
        System.out.println("Najwyzsza dziewczyna nazwa sie: " + tallestGirl.getName());

        // Exercise 3. - w ktorym dniu urodzilo sie najwiecej dzieci
        LocalDate mostBornDay = infantService.findMostBornDay(infants);
        System.out.println("Najwięcej dzieci urodziło się w: " + mostBornDay);

        // Exercise 4. - podaj imiona kobiet kotre w wieku ponizej 25 lat urodzily dzieci o wadze powyzej 4000g
        List<Mum> mumList = mumService.findMums(infants);
        System.out.println("Mamy poniżej 25 lat i wadze dziecka większej niz 4000 " + mumList);

        // Exercise 5. - wypisz dzieci ktore odziedziczyly imiona po mamie
        List<Infant> infantsInheritName = infantService.inheritName(infants);
        System.out.println("Odziedziczyły imie: " + infantsInheritName);

        // Exercise 6. - znajdz wszystkie matki ktore urodzily blizniaki.
        List<Mum> twins = mumService.twins(mums);
        System.out.println("Mamy które urodziły blizniaki" + twins);
    }
}