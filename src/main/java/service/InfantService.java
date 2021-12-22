package service;

import model.Infant;
import model.Mum;
import repository.InfantRepository;
import repository.MumRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class InfantService {

    public void loadData(InfantRepository infantRepository, MumRepository mumRepository) {
        File file = new File("noworodki.txt");

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] splittedArray = line.split(" ");

                Long id = Long.parseLong(splittedArray[0]);
                String gender = splittedArray[1];
                String name = splittedArray[2];
                String stringDate = splittedArray[3];
                LocalDate date = LocalDate.parse(stringDate);
                Integer weight = Integer.parseInt(splittedArray[4]);
                Integer height = Integer.parseInt(splittedArray[5]);
                Long mumId = Long.valueOf(splittedArray[6]);

                Mum mum = mumRepository.findMumById(mumId);

                if (mum != null) {
                    Infant infant = new Infant(id, gender, name, date, weight,
                            height, mum);
                    infantRepository.getInfants().add(infant);
                    mum.getInfantList().add(infant);
                } else {
                    System.out.println("Nie dodano infanata o id " + id + " ponieważ nie posiada on matki");
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Infant findTallestInfant(List<Infant> infantList, String gender) {
        Infant tallestInfant = new Infant();
        tallestInfant.setHeight(0);

        for (Infant infant : infantList) {
            if (infant.getGender().equals(gender) && infant.getHeight() > tallestInfant.getHeight()) {
                tallestInfant = infant;
            }
        }
        if (tallestInfant.getHeight() == 0) {
            return null;
        }
        return tallestInfant;
    }

    public LocalDate findMostBornDay(List<Infant> infants) {
        Map<LocalDate, Integer> dateToOccurencesMap = new HashMap<>();

        for (Infant infant : infants) {
            LocalDate bornDay = infant.getBornDay();

            if (dateToOccurencesMap.containsKey(bornDay)) {
                Integer occurences = dateToOccurencesMap.get(bornDay);
                occurences = occurences + 1;
                dateToOccurencesMap.put(bornDay, occurences);
            } else {
                dateToOccurencesMap.put(bornDay, 1);
            }
        }
        return Collections.max(dateToOccurencesMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    public List<Infant> inheritName(List<Infant> infants) {
        List<Infant> filteredInfantList = new ArrayList<>();

        for (Infant infant : infants) {
            if (infant.getName().equals(infant.getMum().getName())) {
                filteredInfantList.add(infant);
            }
        }
        return filteredInfantList;
    }
}